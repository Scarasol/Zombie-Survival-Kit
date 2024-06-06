package com.scarasol.zombiekit.entity.mechanics;

import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.zombiekit.init.ZombieKitBlocks;
import com.scarasol.zombiekit.init.ZombieKitEntities;
import com.scarasol.zombiekit.init.ZombieKitItems;
import com.scarasol.zombiekit.init.ZombieKitTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UvLampEntity extends Mechanics{

    private boolean hasBattery;
    private int power;
    private boolean lightswitch;


    public UvLampEntity(EntityType<? extends Mechanics> type, Level world) {
        super(type, world);
    }

    public UvLampEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ZombieKitEntities.UV_LAMP.get(), world);
    }

    public boolean isHasBattery() {
        return hasBattery;
    }

    public void setHasBattery(boolean hasBattery) {
        this.hasBattery = hasBattery;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isLightswitch() {
        return lightswitch;
    }

    public void setLightswitch(boolean lightswitch) {
        this.lightswitch = lightswitch;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        Level level = this.level;
        if (!hasBattery && player.getOffhandItem().getItem() == ZombieKitItems.WRENCH.get() && player.getMainHandItem().getItem() == ZombieKitItems.BATTERY.get()){
            hasBattery = true;
            power = 100 - player.getMainHandItem().getDamageValue();
            if (!player.isCreative()){
                player.getMainHandItem().shrink(1);
            }
            return InteractionResult.SUCCESS;
        }else if (hasBattery && player.getOffhandItem().getItem() == ZombieKitItems.WRENCH.get()){
            ItemStack itemStack = new ItemStack(ZombieKitItems.BATTERY.get(), 1);
            itemStack.setDamageValue(100 - power);
            ItemEntity itemEntity = new ItemEntity(level, getX(), getY(), getZ(), itemStack);
            itemEntity.setPickUpDelay(10);
            level.addFreshEntity(itemEntity);
            hasBattery = false;
            power = 0;
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        if (lightswitch){
            level.destroyBlock(getOnPos().above(), false);
            level.destroyBlock(getOnPos().above().above(), false);
        }
        if (hasBattery){
            ItemStack itemStack = new ItemStack(ZombieKitItems.BATTERY.get(), 1);
            itemStack.setDamageValue(100 - power);
            ItemEntity itemEntity = new ItemEntity(level, getX(), getY(), getZ(), itemStack);
            itemEntity.setPickUpDelay(10);
            level.addFreshEntity(itemEntity);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.contains("HasBattery"))
            hasBattery = compoundTag.getBoolean("HasBattery");
        if (compoundTag.contains("Power"))
            power = compoundTag.getInt("Power");
        if (compoundTag.contains("Lightswitch"))
            lightswitch = compoundTag.getBoolean("Lightswitch");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Power", power);
        compoundTag.putBoolean("HasBattery", hasBattery);
        compoundTag.putBoolean("Lightswitch", lightswitch);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isOnGround())
            setNoAi(true);
        if (hasBattery){
            if (level.getBestNeighborSignal(this.getOnPos()) > 0){
                if (lightswitch){
                    level.destroyBlock(getOnPos().above(), false);
                    level.destroyBlock(getOnPos().above().above(), false);
                    lightswitch = false;
                    level.playSound(null, getOnPos(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:turn_on")), SoundSource.NEUTRAL, 1, 1);
                }
                if (level.getGameTime() % 120 == 0)
                    power = Math.min(power + 1, 100);
            }else if (power > 0){
                if (!lightswitch){
                    lightswitch = true;
                    level.setBlock(getOnPos().above(), ZombieKitBlocks.SPREAD_LIGHT_FATHER.get().defaultBlockState(), 3);
                    level.setBlock(getOnPos().above().above(), ZombieKitBlocks.SPREAD_LIGHT_FATHER.get().defaultBlockState(), 3);
                    level.playSound(null, getOnPos(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:turn_on")), SoundSource.NEUTRAL, 1, 1);
                }
                searchUndead();
                if (level.getGameTime() % 120 == 0)
                    power = Math.max(power - 1, 0);
            }else {
                if (lightswitch){
                    level.destroyBlock(getOnPos().above(), false);
                    level.destroyBlock(getOnPos().above().above(), false);
                    lightswitch = false;
                    level.playSound(null, getOnPos(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:turn_on")), SoundSource.NEUTRAL, 1, 1);
                }
            }
        }else {
            if (lightswitch){
                level.destroyBlock(getOnPos().above(), false);
                level.destroyBlock(getOnPos().above().above(), false);
                lightswitch = false;
                level.playSound(null, getOnPos(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:turn_on")), SoundSource.NEUTRAL, 1, 1);
            }
        }
    }

    public void searchUndead(){
        Vec3 _center = new Vec3(getX(), getY(), getZ());
        List<Mob> entFound = level.getEntitiesOfClass(Mob.class, new AABB(_center, _center).inflate(8, 4, 8), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
        for (Mob entityIterator : entFound) {
            if (entityIterator.getMobType() == MobType.UNDEAD || CommonConfig.findIndex(entityIterator.getType().toString(), CommonConfig.INFECTION_SOURCE_MOB.get()) != -1){
                if (entityIterator.getType().is(ZombieKitTags.UV_RESISTANCE) || !entityIterator.hasLineOfSight(this))
                    continue;
                entityIterator.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 0, false, false));
                entityIterator.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 0, false, false));
                entityIterator.addEffect(new MobEffectInstance(SonaMobEffects.FRAGILITY.get(), 20, 1, false, false));
                if (entityIterator.getTarget() == null || !entityIterator.getTarget().isAlive() || entityIterator.getType().is(ZombieKitTags.UV_NONRESISTANCE)){
                    entityIterator.setTarget(null);
                    BlockPos blockPos = entityIterator.getOnPos();
                    BlockPos uvPos = getOnPos();
                    BlockPos newPos = blockPos.offset(blockPos.getX() - uvPos.getX(), blockPos.getY() - uvPos.getY(), blockPos.getZ() - uvPos.getZ());
                    entityIterator.getNavigation().moveTo(newPos.getX(), newPos.getY(), newPos.getZ(), 1);
                }
            }
        }
    }


    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0);
        builder = builder.add(Attributes.MAX_HEALTH, 20);
        builder = builder.add(Attributes.ARMOR, 10);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 0);
        builder = builder.add(Attributes.FOLLOW_RANGE, 16);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1);
        return builder;
    }



}
