package io.github.ignoramuses.bed_block_beyond;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.world.level.material.Material;

public class AllBeds {
	public static final ColoredBlockSet COT_SET = new ColoredBlockSet(
			"cot",
			FabricBlockSettings.of(Material.WOOL),
			CotBlock::new
	);

	public static void init() {
	}
}
