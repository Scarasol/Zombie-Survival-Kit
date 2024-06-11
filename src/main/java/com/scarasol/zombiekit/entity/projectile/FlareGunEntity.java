package com.scarasol.zombiekit.entity.projectile;

import com.scarasol.zombiekit.init.ZombieKitEntities;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class FlareGunEntity extends ModProjectile {
    private int life = 0;

    public FlareGunEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(ZombieKitEntities.FLARE_GUN.get(), world);
    }

    public FlareGunEntity(EntityType<? extends FlareGunEntity> type, Level world) {
        super(type, world);
    }

    public FlareGunEntity(EntityType<? extends FlareGunEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public FlareGunEntity(EntityType<? extends FlareGunEntity> type, LivingEntity entity, Level world) {
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
        if (life > 20){
            doEffects(level, getX(), getY(), getZ());
            this.discard();
        }else {
            level.addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY(), this.getZ(), this.random.nextGaussian() * 0.05, -this.getDeltaMovement().y * 0.5, this.random.nextGaussian() * 0.05);
            life++;
        }
        if (this.inGround)
            this.discard();
    }


    @Override
    public void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        discard();
    }

    @Override
    public void doEffects(Level level, double x, double y, double z) {
        for (int i = 0; i < 200; i++){
            FlaresEntity flaresEntity = new FlaresEntity(ZombieKitEntities.FLARES.get(), level);
            flaresEntity.setBaseDamage(0);
            flaresEntity.setKnockback(0);
            flaresEntity.setSilent(true);
            flaresEntity.setSecondsOnFire(100);
            flaresEntity.setPos(getX(), getY(), getZ());
            flaresEntity.shoot(Mth.nextDouble(level.getRandom(), -2, 2), Mth.nextDouble(level.getRandom(), -2, 2), Mth.nextDouble(level.getRandom(), -2, 2), 1, 0);
            level.addFreshEntity(flaresEntity);
//			level.addAlwaysVisibleParticle(ParticleTypes.FLAME, getX(), getY(), getZ(), 1, 1, 1);
        }
        if (level instanceof ServerLevel _level){
            _level.getServer().getCommands().performCommand(
                    new CommandSourceStack(CommandSource.NULL, new Vec3(getX(), getY(), getZ()), Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)
                            .withSuppressedOutput(),
                    "particle minecraft:flame ~ ~ ~ 1 1 1 0.05 500 force");
        }
    }

    public static FlareGunEntity shoot(Level world, LivingEntity entity, Random random, float power, double damage, int knockback) {
        FlareGunEntity entityArrow = new FlareGunEntity(ZombieKitEntities.FLARE_GUN.get(), entity, world);
        ModProjectile.initProjectileEntity(entityArrow, world, entity, random, power, damage, knockback);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:flare_gun_fire")), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return entityArrow;
    }




}
