package com.scarasol.zombiekit.entity.projectile;

import com.scarasol.zombiekit.init.ZombieKitEntities;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

import java.util.Random;


@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public abstract class ModProjectile  extends AbstractArrow implements ItemSupplier {
    public ModProjectile(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public ModProjectile(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public ModProjectile(EntityType<? extends AbstractArrow> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    @Override
    public void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        doEffects(level, getX(), getY(), getZ());
    }

    @Override
    public void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        doEffects(level, getX(), getY(), getZ());
    }

    public abstract void doEffects(Level level, double x, double y, double z);

    @Override
    public void tick() {
        super.tick();
        onGround();
    }

    public void onGround(){
        if (this.inGround)
            this.discard();
    }

    public static void initProjectileEntity(AbstractArrow entityArrow, Level world, LivingEntity entity, Random random, float power, double damage, int knockback){
        entityArrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
        entityArrow.setSilent(true);
        entityArrow.setCritArrow(false);
        entityArrow.setBaseDamage(damage);
        entityArrow.setKnockback(knockback);
        world.addFreshEntity(entityArrow);
    }

}
