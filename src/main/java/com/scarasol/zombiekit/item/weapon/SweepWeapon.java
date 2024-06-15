package com.scarasol.zombiekit.item.weapon;

import net.minecraft.world.entity.LivingEntity;

public interface SweepWeapon {
    void sweepEffect(LivingEntity target, LivingEntity attacker);
}
