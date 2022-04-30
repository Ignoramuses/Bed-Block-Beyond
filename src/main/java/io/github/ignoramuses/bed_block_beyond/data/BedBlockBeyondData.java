package io.github.ignoramuses.bed_block_beyond.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BedBlockBeyondData implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator gen) {
		gen.addProvider(new BlockStatesAndItemModelsGen(gen));
	}
}
