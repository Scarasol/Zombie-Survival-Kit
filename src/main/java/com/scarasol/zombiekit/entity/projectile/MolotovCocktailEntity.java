package com.scarasol.zombiekit.entity.projectile;

import com.scarasol.zombiekit.init.ZombieKitEntities;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class MolotovCocktailEntity extends ModProjectile {
    public MolotovCocktailEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public MolotovCocktailEntity(EntityType<? extends MolotovCocktailEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public MolotovCocktailEntity(EntityType<? extends MolotovCocktailEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    public MolotovCocktailEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(ZombieKitEntities.MOLOTOV_COCKTAIL.get(), world);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        return new ItemStack(ZombieKitItems.MOLOTOV_COCKTAIL.get());
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ZombieKitItems.MOLOTOV_COCKTAIL.get());
    }

    public static MolotovCocktailEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        MolotovCocktailEntity entityArrow = new MolotovCocktailEntity(ZombieKitEntities.MOLOTOV_COCKTAIL.get(), entity, world);
        entityArrow.setSecondsOnFire(100);
        ModProjectile.initProjectileEntity(entityArrow, world, entity, random, power, damage, knockback);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return entityArrow;
    }



    public void doEffects(Level level, double x, double y, double z) {
        double cst_pi = Math.acos(-1);
        double cst_r = 3;
        double cst_tha1 = 2 * cst_pi;
        double kx = Mth.floor(x);
        double ky = Mth.floor(y);
        double kz = Mth.floor(z);
        double tha1 = 0;
        while (tha1 <= cst_tha1) {
            double tha2 = 0;
            while (tha2 <= cst_pi) {
                double sr = 0;
                while (sr <= cst_r) {
                    double sx = sr * Math.sin(tha2) * Math.cos(tha1);
                    double sz = sr * Math.sin(tha2) * Math.sin(tha1);
                    double sy = sr * Math.cos(tha2);
                    sx = kx + sx;
                    sz = kz + sz;
                    sy = ky + sy;
                    BlockPos blockPos = BlockPos.containing(sx, sy, sz);
                    if (level.isEmptyBlock(blockPos) || level.getBlockState(blockPos).getBlock() == Blocks.SNOW || level.getBlockState(blockPos).getBlock() == Blocks.FIRE
                            || level.getBlockState(blockPos).getBlock() == Blocks.SOUL_FIRE) {
                        if (Blocks.FIRE.defaultBlockState().canSurvive(level, blockPos)) {
                            if (random.nextInt(10) > 6) {
                                level.setBlock(blockPos, Blocks.FIRE.defaultBlockState(), 3);
                            }
                        } else if (Blocks.SOUL_FIRE.defaultBlockState().canSurvive(level, blockPos)) {
                            if (random.nextInt(10) > 6) {
                                level.setBlock(blockPos, Blocks.SOUL_FIRE.defaultBlockState(), 3);
                            }
                        }
                        sr = sr + 1;
                    } else {
                        break;
                    }
                }
                tha2 = tha2 + cst_pi / 18;
            }
            tha1 = tha1 + cst_tha1 / 36;
        }
        if (level instanceof ServerLevel serverLevel) {
            level.playSound(null, BlockPos.containing(kx, ky, kz), SoundEvents.GLASS_BREAK, SoundSource.NEUTRAL, 1, 1);
            serverLevel.sendParticles(ParticleTypes.FLAME, x, y, z, 500, 1, 1, 1, 0.05);
        }
    }


}
