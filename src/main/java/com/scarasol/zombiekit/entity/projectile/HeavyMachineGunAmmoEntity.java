package com.scarasol.zombiekit.entity.projectile;

import com.scarasol.zombiekit.config.CommonConfig;
import com.scarasol.zombiekit.init.ZombieKitEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class HeavyMachineGunAmmoEntity extends ModProjectile {
    private int life = 0;

    public HeavyMachineGunAmmoEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(ZombieKitEntities.HEAVY_MACHINE_GUN_AMMO.get(), world);
    }

    public HeavyMachineGunAmmoEntity(EntityType<? extends HeavyMachineGunAmmoEntity> type, Level world) {
        super(type, world);
    }

    public HeavyMachineGunAmmoEntity(EntityType<? extends HeavyMachineGunAmmoEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public HeavyMachineGunAmmoEntity(EntityType<? extends HeavyMachineGunAmmoEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        return new ItemStack(Blocks.AIR);
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public void tick() {
        super.tick();
        if (++life > 200)
            this.discard();
    }

    @Override
    public void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        Entity owner = this.getOwner();
        DamageSource ammo1;
        DamageSource ammo2;
        if (owner != null){
            ammo1 = DamageSource.arrow(this, owner);
            ammo2 = DamageSource.arrow(this, owner).bypassArmor();
        }else {
            ammo1 = DamageSource.arrow(this, this);
            ammo2 = DamageSource.arrow(this, this).bypassArmor();
        }
        float armorIgnored = (float) (CommonConfig.HEAVY_MACHINE_GUN_DAMAGE.get() * CommonConfig.ARMOR_PIERCING_RATE.get());
        float hurt = CommonConfig.HEAVY_MACHINE_GUN_DAMAGE.get() - armorIgnored;

        int invulnerableTime = entity.invulnerableTime;
        if (CommonConfig.IGNORING_INVULNERABILITY.get())
            entity.invulnerableTime = 0;
        entity.hurt(ammo1, hurt);
        if (invulnerableTime <= 10 || CommonConfig.IGNORING_INVULNERABILITY.get())
            entity.invulnerableTime = 0;
        entity.hurt(ammo2, armorIgnored);
        discard();
    }

    @Override
    public void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        BlockState blockState = this.level.getBlockState(blockHitResult.getBlockPos());
        Block block = blockState.getBlock();
        if(blockState.is(BlockTags.create(new ResourceLocation("forge:glass"))) || blockState.is(BlockTags.create(new ResourceLocation("forge:glass_panes")))){
            this.level.destroyBlock(blockHitResult.getBlockPos(), false, this);
        }
    }

    @Override
    public void doEffects(Level level, double x, double y, double z) {
    }

    public static HeavyMachineGunAmmoEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        HeavyMachineGunAmmoEntity entityArrow = new HeavyMachineGunAmmoEntity(ZombieKitEntities.HEAVY_MACHINE_GUN_AMMO.get(), entity, world);
        entityArrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
        entityArrow.setSilent(true);
        entityArrow.setCritArrow(false);
        entityArrow.setBaseDamage(damage);
        entityArrow.setKnockback(knockback);
        entityArrow.setNoGravity(true);
        world.addFreshEntity(entityArrow);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:heavy_machine_gun_fire")), SoundSource.BLOCKS, 2.5F, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return entityArrow;
    }
}
