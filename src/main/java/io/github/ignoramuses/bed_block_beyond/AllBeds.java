package io.github.ignoramuses.bed_block_beyond;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;

public class AllBeds {
	public static final CotBlock COT = Registry.register(
			Registry.BLOCK,
			BedBlockBeyond.id("cot"),
			new CotBlock(FabricBlockSettings.copyOf(Blocks.WHITE_BED))
	);

	public static void init() {
	}
}
