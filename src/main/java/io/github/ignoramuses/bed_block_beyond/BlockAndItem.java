package io.github.ignoramuses.bed_block_beyond;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BlockAndItem {
	public final Block block;
	public final Item item;
	public final ResourceLocation id;

	public BlockAndItem(String name, Block block, Item item) {
		this.block = block;
		this.item = item;
		this.id = BedBlockBeyond.id(name);

		Registry.register(Registry.BLOCK, id, block);
		Registry.register(Registry.ITEM, id, item);
	}

	public static BlockAndItem of(String name, Block block) {
		BlockItem item = new BlockItem(block, new FabricItemSettings());
		return new BlockAndItem(name, block, item);
	}
}
