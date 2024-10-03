package com.scarasol.zombiekit.entity.projectile;

import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.zombiekit.init.ZombieKitEntities;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
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

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class FirecrackerEntity extends ModProjectile {

    public FirecrackerEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public FirecrackerEntity(EntityType<? extends FirecrackerEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public FirecrackerEntity(EntityType<? extends FirecrackerEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    public FirecrackerEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(ZombieKitEntities.FIRECRACKER.get(), world);
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        return new ItemStack(ZombieKitItems.FIRECRACKER.get());
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ZombieKitItems.FIRECRACKER.get());
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    public static AbstractArrow shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        AbstractArrow entityArrow = new FirecrackerEntity(ZombieKitEntities.FIRECRACKER.get(), entity, world);
        ModProjectile.initProjectileEntity(entityArrow, world, entity, random, power, damage, knockback);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return entityArrow;
    }


    public void doEffects(Level level, double x, double y, double z) {
        if (level instanceof ServerLevel serverLevel) {
            level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.GENERIC_EXPLODE, SoundSource.NEUTRAL, 3, 1);
            serverLevel.sendParticles(ParticleTypes.FLAME, x, y, z, 500, 1, 1, 1, 0.05);
        }
    }
}
