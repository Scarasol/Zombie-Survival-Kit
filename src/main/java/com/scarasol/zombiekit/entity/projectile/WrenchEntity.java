package com.scarasol.zombiekit.entity.projectile;

import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.zombiekit.config.CommonConfig;
import com.scarasol.zombiekit.init.ZombieKitEntities;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Random;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class WrenchEntity extends AbstractArrow implements ItemSupplier {
    private boolean dealtDamage;
    private boolean accumulating;
    private ItemStack wrenchItem = new ItemStack(ZombieKitItems.WRENCH.get());

    public WrenchEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(ZombieKitEntities.WRENCH.get(), world);
    }

    public WrenchEntity(EntityType<? extends WrenchEntity> type, Level world) {
        super(type, world);
    }

    public WrenchEntity(EntityType<? extends WrenchEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public WrenchEntity(EntityType<? extends WrenchEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    public WrenchEntity(Level p_37569_, LivingEntity p_37570_, ItemStack p_37571_) {
        super(ZombieKitEntities.WRENCH.get(), p_37570_, p_37569_);
        this.wrenchItem = p_37571_.copy();
    }


    @Nullable
    protected EntityHitResult findHitEntity(Vec3 p_37575_, Vec3 p_37576_) {
        return this.dealtDamage ? null : super.findHitEntity(p_37575_, p_37576_);
    }
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ZombieKitItems.WRENCH.get());
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }
        super.tick();
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37573_) {
        Entity entity = p_37573_.getEntity();
        float f = CommonConfig.WRENCH_DAMAGE.get().floatValue();
        if (entity instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)entity;
            f += EnchantmentHelper.getDamageBonus(this.wrenchItem, livingentity.getMobType());
        }
        Entity entity1 = this.getOwner();
        DamageSource damageSource = (new IndirectEntityDamageSource("wrench", this, entity1 == null ? this : entity1)).setProjectile();
        this.dealtDamage = true;
        SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
        if (entity.hurt(damageSource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity1 = (LivingEntity)entity;
                if (entity1 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity1, entity1);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity1);
                }
                if (this.getKnockback() > 0) {
                    Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)this.getKnockback() * 0.6D);
                    if (vec3.lengthSqr() > 0.0D) {
                        livingentity1.push(vec3.x, 0.1D, vec3.z);
                    }
                }
                if (this.accumulating)
                {
                    livingentity1.addEffect(new MobEffectInstance(SonaMobEffects.STUN.get(), 100, 0));
                    livingentity1.addEffect(new MobEffectInstance(SonaMobEffects.CONFUSION.get(), 100, 0));
                }
                this.doPostHurtEffects(livingentity1);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        this.playSound(soundevent, 1.0F, 1.0F);
    }

    public static WrenchEntity shoot(Level world, LivingEntity entity, Random random, float power, double damage, int knockback) {
        WrenchEntity entityarrow = new WrenchEntity(ZombieKitEntities.WRENCH.get(), entity, world);
        entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
        entityarrow.setSilent(true);
        entityarrow.setCritArrow(false);
        entityarrow.setBaseDamage(damage);
        entityarrow.setKnockback(knockback);
        world.addFreshEntity(entityarrow);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return entityarrow;
    }

    public static WrenchEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback, boolean accu) {
        WrenchEntity entityarrow = new WrenchEntity(ZombieKitEntities.WRENCH.get(), entity, world);
        entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
        entityarrow.setSilent(true);
        entityarrow.setCritArrow(false);
        entityarrow.setBaseDamage(damage);
        entityarrow.setKnockback(knockback);
        entityarrow.accumulating = accu;
        world.addFreshEntity(entityarrow);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return entityarrow;
    }

    public static WrenchEntity shoot(LivingEntity entity, LivingEntity target) {
        WrenchEntity entityarrow = new WrenchEntity(ZombieKitEntities.WRENCH.get(), entity, entity.level);
        double dx = target.getX() - entity.getX();
        double dy = target.getY() + target.getEyeHeight() - 1.1;
        double dz = target.getZ() - entity.getZ();
        entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
        entityarrow.setSilent(true);
        entityarrow.setBaseDamage(5);
        entityarrow.setKnockback(5);
        entityarrow.setCritArrow(false);
        entity.level.addFreshEntity(entityarrow);
        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1, 1f / (new Random().nextFloat() * 0.5f + 1));
        return entityarrow;
    }

    public void tickDespawn() {
        if (this.pickup != AbstractArrow.Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }
    public void readAdditionalSaveData(CompoundTag p_37578_) {
        super.readAdditionalSaveData(p_37578_);
        if (p_37578_.contains("Wrench", 10)) {
            this.wrenchItem = ItemStack.of(p_37578_.getCompound("Wrench"));
        }
        this.dealtDamage = p_37578_.getBoolean("DealtDamage");
        this.accumulating = p_37578_.getBoolean("Accumulating");
    }

    public void addAdditionalSaveData(CompoundTag p_37582_) {
        super.addAdditionalSaveData(p_37582_);
        p_37582_.put("Wrench", this.wrenchItem.save(new CompoundTag()));
        p_37582_.putBoolean("DealtDamage", this.dealtDamage);
        p_37582_.putBoolean("Accumulating", this.accumulating);
    }
}
