package io.github.ignoramuses.bed_block_beyond;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.world.level.material.Material;

public class AllBeds {
	public static final ColoredBlockSet COT_SET = new ColoredBlockSet(
			"cot", "cot",
			FabricBlockSettings.of(Material.WOOL),
			CotBlock::new
	);

	public static final BlockAndItem BED_OF_NAILS = BlockAndItem.of("bed_of_nails",
			new NailBedBlock(FabricBlockSettings.of(Material.HEAVY_METAL)));

	public static void init() {
	}
}
