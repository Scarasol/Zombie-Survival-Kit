package com.scarasol.zombiekit.entity.projectile;

import com.mojang.math.Vector3f;
import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.zombiekit.init.ZombieKitEntities;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PlayMessages;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BileJarEntity extends ModProjectile{
    private final DustParticleOptions bile = new DustParticleOptions(new Vector3f(Vec3.fromRGB24(5597999)), 1.0f);

    public BileJarEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public BileJarEntity(EntityType<? extends BileJarEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public BileJarEntity(EntityType<? extends BileJarEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    public BileJarEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(ZombieKitEntities.BILE_JAR.get(), world);
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        return new ItemStack(ZombieKitItems.BILE_JAR.get());
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ZombieKitItems.BILE_JAR.get());
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    public static AbstractArrow shoot(Level world, LivingEntity entity, Random random, float power, double damage, int knockback) {
        AbstractArrow entityArrow = new BileJarEntity(ZombieKitEntities.BILE_JAR.get(), entity, world);
        ModProjectile.initProjectileEntity(entityArrow, world, entity, random, power, damage, knockback);
        return entityArrow;
    }


    public void doEffects(Level level, double x, double y, double z) {
        Vec3 _center = new Vec3(x, y, z);
        List<LivingEntity> _entfound = level.getEntitiesOfClass(LivingEntity.class, new AABB(_center, _center).inflate(7 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
        for (LivingEntity entityiterator : _entfound) {
            entityiterator.addEffect(new MobEffectInstance(SonaMobEffects.CONFUSION.get(), 200, 0));
        }
        if (level instanceof ServerLevel serverLevel) {
            level.playSound(null, new BlockPos(x, y, z), SoundEvents.GLASS_BREAK, SoundSource.NEUTRAL, 1, 1);
            serverLevel.sendParticles(bile, x, y, z, 1000, 2, 2, 2, 0.005);
        }
    }
}
