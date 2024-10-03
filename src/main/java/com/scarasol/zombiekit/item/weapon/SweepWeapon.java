package com.scarasol.zombiekit.item.weapon;

import com.scarasol.zombiekit.config.CommonConfig;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;


public interface SweepWeapon {
    default void sweepEffect(LivingEntity target, LivingEntity attacker){
        if (attacker instanceof Player player && CommonConfig.SWEEP.get()){
            target.hurt(attacker.level().damageSources().playerAttack(player), (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE) * CommonConfig.SWEEP_MULTIPLIER.get()));
        }
    }
}
