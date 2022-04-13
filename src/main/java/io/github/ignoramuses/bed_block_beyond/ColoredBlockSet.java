package io.github.ignoramuses.bed_block_beyond;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.BiFunction;

public final class ColoredBlockSet {
	private final String name;
	private final FabricBlockSettings baseSettings;
	private final BiFunction<DyeColor, FabricBlockSettings, Block> function;

	public ColoredBlockSet(String name,
						   FabricBlockSettings baseSettings,
						   BiFunction<DyeColor, FabricBlockSettings, Block> function) {
		this.name = name;
		this.baseSettings = baseSettings;
		this.function = function;

		for (DyeColor color : DyeColor.values()) {
			String colorName = color.getName();
			String blockName = colorName + '_' + this.name;
			ResourceLocation id = BedBlockBeyond.id(blockName);
			FabricBlockSettings modSettings = FabricBlockSettings.copyOf(this.baseSettings);
			modSettings.mapColor(color);
			Block block = function.apply(color, modSettings);
		}
	}
}
