package com.scarasol.zombiekit.entity.projectile;

import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.zombiekit.init.ZombieKitBlocks;
import com.scarasol.zombiekit.init.ZombieKitEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class FlaresEntity extends ModProjectile{
    private BlockPos lastPos;

    public FlaresEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(ZombieKitEntities.FLARES.get(), world);
    }

    public FlaresEntity(EntityType<? extends FlaresEntity> type, Level world) {
        super(type, world);
    }

    public FlaresEntity(EntityType<? extends FlaresEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public FlaresEntity(EntityType<? extends FlaresEntity> type, LivingEntity entity, Level world) {
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
        if (!(new BlockPos(this.getX(), this.getY(),this.getZ()).equals(lastPos))){
            lastPos = new BlockPos(this.getX(), this.getY(),this.getZ());
        }
        if (this.inGround || this.isInWaterOrBubble())
            this.discard();
    }

    @Override
    public void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        BlockState blockState = level.getBlockState(new BlockPos(getX(), getY(), getZ()));
        if (blockState.getMaterial() == Material.AIR || blockState.getMaterial() == Material.SNOW || blockState instanceof IPlantable){
            level.setBlock(new BlockPos(getX(), getY(), getZ()), ZombieKitBlocks.FLARES_LIGHT.get().defaultBlockState(), 3);
        }else if (lastPos != null) {
            BlockState blockState2 = level.getBlockState(lastPos);
            if (blockState2.getMaterial() == Material.AIR || blockState2.getMaterial() == Material.SNOW || blockState2 instanceof IPlantable){
                level.setBlock(lastPos, ZombieKitBlocks.FLARES_LIGHT.get().defaultBlockState(), 3);
            }
        }
    }

    @Override
    public void doEffects(Level level, double x, double y, double z) {
    }

    @Override
    public void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (entityHitResult.getEntity() instanceof LivingEntity _entity){
            _entity.addEffect(new MobEffectInstance(SonaMobEffects.IGNITION.get(), 140, 3, false, false));
        }
        discard();
    }

    public static FlaresEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        FlaresEntity entityArrow = new FlaresEntity(ZombieKitEntities.FLARES.get(), entity, world);
        ModProjectile.initProjectileEntity(entityArrow, world, entity, random, power, damage, knockback);
        entityArrow.setSecondsOnFire(100);
        return entityArrow;
    }



}
