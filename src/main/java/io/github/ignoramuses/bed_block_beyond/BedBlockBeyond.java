package io.github.ignoramuses.bed_block_beyond;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.server.level.ServerPlayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BedBlockBeyond implements ModInitializer {
	public static final String ID = "bed_block_beyond";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		AllBeds.init();
		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			if (!alive) {
				oldPlayer.stopSleeping();
			}
		});
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}
