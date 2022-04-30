package io.github.ignoramuses.bed_block_beyond;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jetbrains.annotations.Nullable;

public class CotBlock extends BedBlock implements SleepPosAdjustingBed {
	public static final VoxelShape BASE = Block.box(0, 10, 0, 16, 15, 16);
	public static final VoxelShape NORTH_LEG = Block.box(2, 0, 1, 14, 10, 4);
	public static final VoxelShape SOUTH_LEG = Block.box(2, 0, 14, 14, 10, 16);
	public static final VoxelShape NORTH_HEAD_SHAPE = Shapes.or(BASE, NORTH_LEG, SOUTH_LEG);
	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(NORTH_HEAD_SHAPE, Direction.NORTH);
	public static final VoxelShape SOUTH_HEAD_SHAPE = SHAPER.get(Direction.SOUTH);
	public static final VoxelShape EAST_HEAD_SHAPE = SHAPER.get(Direction.EAST);
	public static final VoxelShape WEST_HEAD_SHAPE = SHAPER.get(Direction.WEST);


	public CotBlock(DyeColor color, FabricBlockSettings properties) {
		super(color, properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		Direction facing = state.getValue(FACING);
		BedPart part = state.getValue(PART);
		boolean head = part == BedPart.HEAD;
		return switch (facing) {
			case NORTH -> head ? NORTH_HEAD_SHAPE : SOUTH_HEAD_SHAPE;
			case SOUTH -> head ? SOUTH_HEAD_SHAPE : NORTH_HEAD_SHAPE;
			case WEST -> head ? WEST_HEAD_SHAPE : EAST_HEAD_SHAPE;
			case EAST -> head ? EAST_HEAD_SHAPE : WEST_HEAD_SHAPE;
			case UP, DOWN -> throw new RuntimeException("what");
		};
	}

	@Nullable
	@Override
	public Vec3 adjustPos(Vec3 original) {
		return original.add(0, 0.5, 0);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}
}
