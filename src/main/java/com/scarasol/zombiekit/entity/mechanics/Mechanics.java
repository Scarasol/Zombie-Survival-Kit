package com.scarasol.zombiekit.entity.mechanics;

import com.scarasol.sona.effect.Corrosion;
import com.scarasol.sona.effect.PhysicalEffect;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public abstract class Mechanics extends PathfinderMob{


    public Mechanics(EntityType<? extends Mechanics> type, Level world) {
        super(type, world);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud)
            return false;
        if (source == DamageSource.MAGIC)
            return false;
        if (source == DamageSource.WITHER || source == DamageSource.FREEZE || source == DamageSource.IN_WALL)
            return false;
        if (source.getMsgId().equals("witherSkull") || source == DamageSource.CACTUS)
            return false;
        if (source.isExplosion() || source.isFire() || source == DamageSource.LIGHTNING_BOLT)
            return super.hurt(source, amount);
        if (source == Corrosion.CORRODED || source == DamageSource.FALL || source == DamageSource.OUT_OF_WORLD)
            return super.hurt(source, amount);
        return super.hurt(source, 1f);
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public boolean canBeAffected(MobEffectInstance instance) {
        return instance.getEffect() instanceof PhysicalEffect;
    }
}
