package io.github.ignoramuses.bed_block_beyond;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public class NailBedBlock extends BedBlock {

	public static final DamageSource NAILS = new DamageSource("nails"){};

	public NailBedBlock(Properties properties) {
		super(DyeColor.GRAY, properties);
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		super.entityInside(state, level, pos, entity);
		entity.hurt(NAILS, 1);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}
}
