package io.github.ignoramuses.bed_block_beyond.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;

import io.github.ignoramuses.bed_block_beyond.NailBedBlock;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@WrapWithCondition(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;stopSleeping()V"))
	private boolean bed_block_beyond$preventNailsWakingPlayers(LivingEntity entity, DamageSource source, float amount) {
		return source != NailBedBlock.NAILS;
	}
}
