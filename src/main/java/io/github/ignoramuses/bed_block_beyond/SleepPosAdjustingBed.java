package io.github.ignoramuses.bed_block_beyond;

import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public interface SleepPosAdjustingBed {
	@Nullable
	Vec3 adjustPos(Vec3 original);
}
