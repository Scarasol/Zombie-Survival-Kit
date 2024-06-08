package com.scarasol.zombiekit.entity.mechanics;

import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.zombiekit.init.ZombieKitEntities;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

public class DroneEntity extends Mechanics{
    protected BlockPos hoverPlace;
    private boolean hover = false;
    private boolean enemy = false;
    private UUID owner;

    public DroneEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ZombieKitEntities.DRONE.get(), world);
    }

    public DroneEntity(EntityType<DroneEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
        setPersistenceRequired();
//		setGravity();
        this.moveControl = new DroneMoveControl(this);
    }

    public DroneEntity(EntityType<DroneEntity> type, Level world, boolean enemy) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
        setPersistenceRequired();
//		setGravity();
        this.moveControl = new DroneMoveControl(this);
        this.enemy = enemy;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        InteractionResult retval = InteractionResult.sidedSuccess(this.level.isClientSide());
        if (!isNoGravity() && !this.level.isClientSide()){
            if (itemstack.is(ZombieKitItems.WRENCH.get())){
                if (player.isShiftKeyDown()){
                    ItemEntity entityToSpawn = new ItemEntity(this.level, getX(), getY(), getZ(), new ItemStack(ZombieKitItems.DRONE_SUMMON.get()));
                    entityToSpawn.setPickUpDelay(10);
                    this.level.addFreshEntity(entityToSpawn);
                    this.discard();
                }else {
                    if (enemy){
                        enemy = false;
                        player.displayClientMessage(new TranslatableComponent("entity.zombiekit.drone.switch_2"), true);
                    }else {
                        enemy = true;
                        player.displayClientMessage(new TranslatableComponent("entity.zombiekit.drone.switch_1"), true);
                    }
                    this.level.playSound(null, this, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:drone_switch")), SoundSource.HOSTILE, 0.3F, 1F);
                }
            }else {
                this.setNoGravity(true);
                hoverPlace = new BlockPos(getX(), getY() + 10, getZ());
            }
        }
        super.mobInteract(player, hand);
        return retval;
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        return new FlyingPathNavigation(this, world);
    }

    @Override
    public void tick() {
        if (getTarget() == null && isNoGravity()) {
            this.level.playSound(null, this, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:drone_idle")), SoundSource.HOSTILE, 0.05F, 1F);
            if (hoverPlace == null){
                hoverPlace = new BlockPos(getX(), getY(), getZ());
            }else {
                Vec3 vec3 = new Vec3(hoverPlace.getX() - getX(), hoverPlace.getY() - getY(), hoverPlace.getZ() - getZ());
                if (vec3.length() > 100) {
                    hoverPlace = new BlockPos(getX(), getY(), getZ());
                }
                if (vec3.length() >= 1) {
                    if (!hover){
                        moveControl.setWantedPosition(hoverPlace.getX(), hoverPlace.getY(), hoverPlace.getZ(), 1.0);
                    }else if (vec3.length() >= 2.5) {
                        hover = false;
                    }
                } else if (vec3.length() < 1){
                    hover = true;
                }
            }
        }else {
            hover = false;
        }
        if (this.isInWaterOrBubble()){
            hurt(DamageSource.DROWN, 1);
        }
        if (this.hasEffect(SonaMobEffects.SLIMINESS.get())){
            this.setNoGravity(false);
        }
        super.tick();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.contains("HoverX")) {
            this.hoverPlace = new BlockPos(compoundTag.getInt("HoverX"), compoundTag.getInt("HoverY"), compoundTag.getInt("HoverZ"));
        }
        if (compoundTag.contains("Enemy")) {
            this.enemy = compoundTag.getBoolean("Enemy");
        }
        if (compoundTag.contains("DroneOwner")) {
            this.owner = UUID.fromString(compoundTag.getString("DroneOwner"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        if (this.hoverPlace != null) {
            compoundTag.putInt("HoverX", this.hoverPlace.getX());
            compoundTag.putInt("HoverY", this.hoverPlace.getY());
            compoundTag.putInt("HoverZ", this.hoverPlace.getZ());
        }
        if (this.owner != null) {
            compoundTag.putString("DroneOwner", this.owner.toString());
        }
        compoundTag.putBoolean("Enemy", this.enemy);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new DroneAttackGoal());
    }

    public boolean isEnemy(){
        return enemy;
    }

    public void setOwner(Player player){
        owner = player.getUUID();
    }

    public Player getOwner(){
        return this.level.getPlayerByUUID(owner);
    }

    public UUID getOwnerUUID(){
        return owner;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:drone_crush"));
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        this.spawnAtLocation(new ItemStack(ZombieKitItems.DRONE_COMPONENTS.get()));
    }


    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 2.0);
        builder = builder.add(Attributes.MAX_HEALTH, 4);
        builder = builder.add(Attributes.ARMOR, 10);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 0);
        builder = builder.add(Attributes.FOLLOW_RANGE, 25);
        builder = builder.add(Attributes.FLYING_SPEED, 2.0);
        return builder;
    }

    class DroneAttackGoal extends Goal {
        private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range(25.0);

        public DroneAttackGoal(){
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            if (!DroneEntity.this.isNoGravity()){
                return false;
            }
            if (DroneEntity.this.getTarget() != null && DroneEntity.this.getTarget().isAlive()) {
                return true;
            } else {
                if (DroneEntity.this.isEnemy()){
                    if (searchPlayer()){
                        return true;
                    }
                }
                if (searchEntities()){
                    return true;
                }
                return false;
            }
        }

        public boolean searchEntities(){
            List<LivingEntity> list = DroneEntity.this.level.getNearbyEntities(LivingEntity.class, this.attackTargeting, DroneEntity.this, DroneEntity.this.getBoundingBox().inflate(25.0, 15.0, 25.0));
            if (!list.isEmpty()) {
                list.sort((l1, l2) -> {
                    double a = Math.pow(l1.getX() - DroneEntity.this.getX(), 2) + Math.pow(l1.getZ() - DroneEntity.this.getZ(), 2);
                    double b = Math.pow(l2.getX() - DroneEntity.this.getX(), 2) + Math.pow(l2.getZ() - DroneEntity.this.getZ(), 2);
                    return Double.compare(a, b);
                });
                for (LivingEntity entity : list) {
                    if (DroneEntity.this.canAttack(entity, TargetingConditions.forCombat())) {
                        if (DroneEntity.this.getOwnerUUID() == null){
                            if (entity.getY() < DroneEntity.this.getY() && entity instanceof Enemy && !(entity instanceof Raider || entity instanceof NeutralMob || entity instanceof Vex)){
                                DroneEntity.this.setTarget(entity);
                                return true;
                            }
                        }else {
                            if (entity.getY() < DroneEntity.this.getY() && entity instanceof Enemy && !(entity instanceof NeutralMob)){
                                DroneEntity.this.setTarget(entity);
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        public boolean searchPlayer(){
            List<Player> list = DroneEntity.this.level.getNearbyPlayers(this.attackTargeting, DroneEntity.this, DroneEntity.this.getBoundingBox().inflate(16.0, 15.0, 16.0));
            if (!list.isEmpty()) {
                list.sort((Comparator<LivingEntity>) (l1, l2) -> {
                    double a = Math.pow(l1.getX() - DroneEntity.this.getX(), 2) + Math.pow(l1.getZ() - DroneEntity.this.getZ(), 2);
                    double b = Math.pow(l2.getX() - DroneEntity.this.getX(), 2) + Math.pow(l2.getZ() - DroneEntity.this.getZ(), 2);
                    return Double.compare(a, b);
                });
                for (Player entity : list) {
                    if (entity.getY() < DroneEntity.this.getY() && DroneEntity.this.canAttack(entity, TargetingConditions.forCombat()) && !entity.getUUID().equals(DroneEntity.this.getOwnerUUID())) {
                        DroneEntity.this.setTarget(entity);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return DroneEntity.this.getMoveControl().hasWanted() && DroneEntity.this.getTarget() != null && DroneEntity.this.getTarget().isAlive() && DroneEntity.this.isNoGravity();
        }

        @Override
        public void start() {
            LivingEntity livingentity = DroneEntity.this.getTarget();
            Vec3 vec3d = livingentity.getEyePosition(1);
            DroneEntity.this.moveControl.setWantedPosition(vec3d.x, vec3d.y, vec3d.z, 1.0);
        }

        @Override
        public void tick() {
            LivingEntity livingentity = DroneEntity.this.getTarget();
            Vec3 vec3 = new Vec3(livingentity.getX() - DroneEntity.this.getX(), livingentity.getY() - DroneEntity.this.getY(), livingentity.getZ() - DroneEntity.this.getZ());
            double d = vec3.length();
            if (d < 2.5 && canContinueToUse()) {
                DroneEntity.this.level.explode(DroneEntity.this, DroneEntity.this.getX(), DroneEntity.this.getY(), DroneEntity.this.getZ(), 4, Explosion.BlockInteraction.NONE);
                DroneEntity.this.discard();
            } else {
                double d0 = DroneEntity.this.distanceToSqr(livingentity);
                if (d0 < 25) {
                    Vec3 vec3d = livingentity.getEyePosition(1);
                    DroneEntity.this.moveControl.setWantedPosition(vec3d.x, vec3d.y, vec3d.z, 1.0);
                }
                if (!canContinueToUse()){
                    DroneEntity.this.setTarget(null);
                }
            }
        }
    }

    class DroneMoveControl extends MoveControl {
        public DroneMoveControl(DroneEntity DroneEntity) {
            super(DroneEntity);
        }

        public void hover() {
            Vec3 vec3 = new Vec3(0, DroneEntity.this.hoverPlace.getY() - DroneEntity.this.getY(), 0);
            if (vec3.length() > 1.5 && DroneEntity.this.hover){
                DroneEntity.this.setDeltaMovement(vec3.scale(0.1));
            }

        }

        @Override
        public void tick() {
            if (!DroneEntity.this.isNoGravity()){
                return;
            }
            if (this.operation != MoveControl.Operation.MOVE_TO) {
                hover();
                return;
            }
            Vec3 vec3 = new Vec3(this.wantedX - DroneEntity.this.getX(), this.wantedY - DroneEntity.this.getY(), this.wantedZ - DroneEntity.this.getZ());
            double d = vec3.length();
            if (d < DroneEntity.this.getBoundingBox().getSize()) {
                this.operation = MoveControl.Operation.WAIT;
                DroneEntity.this.setDeltaMovement(DroneEntity.this.getDeltaMovement().scale(0.5));
            } else {
                DroneEntity.this.setDeltaMovement(DroneEntity.this.getDeltaMovement().add(vec3.scale(this.speedModifier * 0.1 / d)));
                if (DroneEntity.this.getTarget() == null) {
                    Vec3 vec32 = DroneEntity.this.getDeltaMovement();
                    DroneEntity.this.setYRot(-((float)Mth.atan2(vec32.x, vec32.z)) * 57.295776f);
                    DroneEntity.this.yBodyRot = DroneEntity.this.getYRot();
                } else {
                    double d2 = DroneEntity.this.getTarget().getX() - DroneEntity.this.getX();
                    double d3 = DroneEntity.this.getTarget().getZ() - DroneEntity.this.getZ();
                    DroneEntity.this.setYRot(-((float)Mth.atan2(d2, d3)) * 57.295776f);
                    DroneEntity.this.yBodyRot = DroneEntity.this.getYRot();
                }
            }
        }
    }
}
