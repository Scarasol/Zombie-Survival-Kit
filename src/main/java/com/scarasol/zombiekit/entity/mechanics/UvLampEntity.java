package com.scarasol.zombiekit.entity.mechanics;

import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.zombiekit.init.ZombieKitBlocks;
import com.scarasol.zombiekit.init.ZombieKitEntities;
import com.scarasol.zombiekit.init.ZombieKitItems;
import com.scarasol.zombiekit.init.ZombieKitTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
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

    public static final EntityDataAccessor<Boolean> hasBattery = SynchedEntityData.defineId(UvLampEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> power =SynchedEntityData.defineId(UvLampEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> lightswitch =SynchedEntityData.defineId(UvLampEntity.class, EntityDataSerializers.BOOLEAN);


    public UvLampEntity(EntityType<? extends Mechanics> type, Level world) {
        super(type, world);
    }

    public UvLampEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ZombieKitEntities.UV_LAMP.get(), world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(hasBattery, false);
        this.entityData.define(power, 0);
        this.entityData.define(lightswitch, false);

    }

    public boolean isHasBattery() {
        return entityData.get(hasBattery);
    }

    public void setHasBattery(boolean hasBattery) {
        entityData.set(UvLampEntity.hasBattery, hasBattery);
    }

    public int getPower() {
        return entityData.get(power);
    }

    public void setPower(int power) {
        entityData.set(UvLampEntity.power, power);
    }

    public boolean isLightswitch() {
        return entityData.get(lightswitch);
    }

    public void setLightswitch(boolean lightswitch) {
        entityData.set(UvLampEntity.lightswitch, lightswitch);
    }

    public void popBattery(){
        ItemStack itemStack = new ItemStack(ZombieKitItems.BATTERY.get(), 1);
        itemStack.setDamageValue(100 - getPower());
        ItemEntity itemEntity = new ItemEntity(level, getX(), getY(), getZ(), itemStack);
        itemEntity.setPickUpDelay(10);
        level.addFreshEntity(itemEntity);
        setHasBattery(false);
        setPower(0);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        Level level = this.level;
        if (!isHasBattery() && player.getOffhandItem().getItem() == ZombieKitItems.WRENCH.get() && player.getMainHandItem().getItem() == ZombieKitItems.BATTERY.get()){
            setHasBattery(true);
            setPower(100 - player.getMainHandItem().getDamageValue());
            if (!player.isCreative()){
                player.getMainHandItem().shrink(1);
            }
            return InteractionResult.SUCCESS;
        }else if (isHasBattery() && player.getOffhandItem().getItem() == ZombieKitItems.WRENCH.get()){
            popBattery();
            return InteractionResult.SUCCESS;
        }else if (player.getMainHandItem().getItem() == ZombieKitItems.WRENCH.get()){
            if (isHasBattery())
                popBattery();
            ItemStack itemStack = new ItemStack(ZombieKitItems.UV_LAMP.get(), 1);
            itemStack.setDamageValue(20 - Mth.floor(getHealth()));
            ItemEntity itemEntity = new ItemEntity(level, getX(), getY(), getZ(), itemStack);
            itemEntity.setPickUpDelay(10);
            level.addFreshEntity(itemEntity);
            this.discard();
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        if (isLightswitch()){
            level.destroyBlock(getOnPos().above(), false);
            level.destroyBlock(getOnPos().above().above(), false);
        }
        if (isHasBattery()){
            popBattery();
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.contains("HasBattery"))
            setHasBattery(compoundTag.getBoolean("HasBattery"));
        if (compoundTag.contains("Power"))
            setPower(compoundTag.getInt("Power"));
        if (compoundTag.contains("Lightswitch"))
            setLightswitch(compoundTag.getBoolean("Lightswitch"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Power", getPower());
        compoundTag.putBoolean("HasBattery", isHasBattery());
        compoundTag.putBoolean("Lightswitch", isLightswitch());
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isOnGround())
            setNoAi(true);
        if (isHasBattery()){
            if (level.getBestNeighborSignal(this.getOnPos()) > 0){
                if (isLightswitch()){
                    level.destroyBlock(getOnPos().above(), false);
                    level.destroyBlock(getOnPos().above().above(), false);
                    setLightswitch(false);
                    level.playSound(null, getOnPos(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:turn_on")), SoundSource.NEUTRAL, 1, 1);
                }
                if (level.getGameTime() % com.scarasol.zombiekit.config.CommonConfig.LAMP_POWER.get() == 0)
                    setPower(Math.min(getPower() + 1, 100));
            }else if (getPower() > 0){
                if (!isLightswitch()){
                    setLightswitch(true);
                    level.setBlock(getOnPos().above(), ZombieKitBlocks.SPREAD_LIGHT_FATHER.get().defaultBlockState(), 3);
                    level.setBlock(getOnPos().above().above(), ZombieKitBlocks.SPREAD_LIGHT_FATHER.get().defaultBlockState(), 3);
                    level.playSound(null, getOnPos(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:turn_on")), SoundSource.NEUTRAL, 1, 1);
                }
                searchUndead();
                if (level.getGameTime() % com.scarasol.zombiekit.config.CommonConfig.LAMP_POWER.get() == 0)
                    setPower(Math.max(getPower() - 1, 0));
            }else {
                if (isLightswitch()){
                    level.destroyBlock(getOnPos().above(), false);
                    level.destroyBlock(getOnPos().above().above(), false);
                    setLightswitch(false);
                    level.playSound(null, getOnPos(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:turn_on")), SoundSource.NEUTRAL, 1, 1);
                }
            }
        }else {
            if (isLightswitch()){
                level.destroyBlock(getOnPos().above(), false);
                level.destroyBlock(getOnPos().above().above(), false);
                setLightswitch(false);
                level.playSound(null, getOnPos(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:turn_on")), SoundSource.NEUTRAL, 1, 1);
            }
        }
    }

    public void searchUndead(){
        Vec3 _center = new Vec3(getX(), getY(), getZ());
        List<Mob> entFound = level.getEntitiesOfClass(Mob.class, new AABB(_center, _center).inflate(8, 4, 8), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
        for (Mob entityIterator : entFound) {
            if (entityIterator.getMobType() == MobType.UNDEAD || CommonConfig.findIndex(ForgeRegistries.ENTITIES.getKey(entityIterator.getType()).toString(), CommonConfig.INFECTION_SOURCE_MOB.get()) != -1){
                if (entityIterator.getType().is(ZombieKitTags.UV_RESISTANCE) || !entityIterator.hasLineOfSight(this))
                    continue;
                if (com.scarasol.zombiekit.config.CommonConfig.HIGH_PERFORMANCE_MODE.get()){
                    entityIterator.addEffect(new MobEffectInstance(SonaMobEffects.IGNITION.get(), 20, 2, false, false));
                }
                entityIterator.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 1, false, false));
                entityIterator.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, false, false));
                entityIterator.addEffect(new MobEffectInstance(SonaMobEffects.FRAGILITY.get(), 20, 2, false, false));
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
