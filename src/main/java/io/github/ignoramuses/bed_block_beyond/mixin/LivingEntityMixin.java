package io.github.ignoramuses.bed_block_beyond.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;

import io.github.ignoramuses.bed_block_beyond.NailBedBlock;
import io.github.ignoramuses.bed_block_beyond.SleepPosAdjustingBed;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@WrapWithCondition(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;stopSleeping()V"))
	private boolean bed_block_beyond$preventNailsWakingPlayers(LivingEntity entity, DamageSource source, float amount) {
		return source != NailBedBlock.NAILS;
	}

	@ModifyArgs(method = "setPosToBed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setPos(DDD)V"))
	private void bed_block_beyond$adjustBedSleepPos(Args args) {
		Vec3 pos = new Vec3(args.get(0), args.get(1), args.get(2));
		BlockState state = level.getBlockState(new BlockPos(pos));
		if (state.getBlock() instanceof SleepPosAdjustingBed bed) {
			Vec3 newPos = bed.adjustPos(pos);
			if (newPos != null) {
				args.set(0, newPos.x());
				args.set(1, newPos.y());
				args.set(2, newPos.z());
			}
		}
	}
}
