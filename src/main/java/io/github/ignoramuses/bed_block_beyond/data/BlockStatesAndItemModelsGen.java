package io.github.ignoramuses.bed_block_beyond.data;

import io.github.ignoramuses.bed_block_beyond.AllBeds;
import io.github.ignoramuses.bed_block_beyond.ColoredBlockSet.Entry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;

public class BlockStatesAndItemModelsGen extends FabricModelProvider {
	public BlockStatesAndItemModelsGen(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators gen) {
		bed2x1FacingPart(gen, AllBeds.BED_OF_NAILS.block);
		for (Entry e : AllBeds.COT_SET) {
			bed2x1FacingPart(gen, e.block());
		}
	}

	/**
	 * Generate a blockstate file for the given block, assuming it has the FACING and PART properties.
	 */
	public static void bed2x1FacingPart(BlockModelGenerators gen, Block block) {
		gen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
				.with(PropertyDispatch.property(BedBlock.FACING)
						.generate(d -> gen.applyRotation(
										FrontAndTop.fromFrontAndTop(d, Direction.UP),
										Variant.variant()
								)
						)
				).with(PropertyDispatch.property(BedBlock.PART)
						.generate(part -> Variant.variant()
								.with(
										VariantProperties.MODEL,
										ModelLocationUtils.getModelLocation(block, '_' + part.getSerializedName())
								)
						)
				)
		);
	}

	@Override
	public void generateItemModels(ItemModelGenerators gen) {
		gen.generateFlatItem(AllBeds.BED_OF_NAILS.item, ModelTemplates.FLAT_ITEM);
		for (Entry e : AllBeds.COT_SET) {
			gen.generateFlatItem(e.item(), ModelTemplates.FLAT_ITEM);
		}
	}
}
