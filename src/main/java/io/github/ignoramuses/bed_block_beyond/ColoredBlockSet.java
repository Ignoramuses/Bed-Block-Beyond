package io.github.ignoramuses.bed_block_beyond;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ColoredBlockSet {
	private final String name;
	private final FabricBlockSettings baseSettings;
	private final BiFunction<DyeColor, FabricBlockSettings, Block> function;
	private final Map<DyeColor, Entry> colorsToEntries = new IdentityHashMap<>();


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
			BlockItem item = new BlockItem(block, new FabricItemSettings());

			Registry.register(Registry.BLOCK, id, block);
			Registry.register(Registry.ITEM, id, item);

			colorsToEntries.put(color, new Entry(id, block, item));
		}
	}

	public Block getBlock(DyeColor color) {
		Entry entry = colorsToEntries.get(color);
		return entry.block();
	}

	public ResourceLocation getID(DyeColor color) {
		Entry entry = colorsToEntries.get(color);
		return entry.id();
	}

	public BlockItem getItem(DyeColor color) {
		Entry entry = colorsToEntries.get(color);
		return entry.item();
	}

	public record Entry(ResourceLocation id, Block block, BlockItem item) {
	}
}
