package io.github.ignoramuses.bed_block_beyond;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class BedBlockBeyondPreLaunch implements PreLaunchEntrypoint {

	@Override
	public void onPreLaunch() {
		MixinExtrasBootstrap.init();
	}
}
