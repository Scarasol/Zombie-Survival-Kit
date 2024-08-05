package com.scarasol.zombiekit.entity.mechanics;

import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.zombiekit.entity.projectile.HeavyMachineGunAmmoEntity;
import com.scarasol.zombiekit.init.ZombieKitEntities;
import com.scarasol.zombiekit.init.ZombieKitItems;
import com.scarasol.zombiekit.init.ZombieKitTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class HeavyMachineGunEntity extends Mechanics implements IAnimatable {
    public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(HeavyMachineGunEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> OVERLOAD = SynchedEntityData.defineId(HeavyMachineGunEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(HeavyMachineGunEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(HeavyMachineGunEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> DIRECTION = SynchedEntityData.defineId(HeavyMachineGunEntity.class, EntityDataSerializers.STRING);
    public static final AttributeModifier ATTRIBUTE_MODIFIER = new AttributeModifier(UUID.fromString("1CCA8D2D-9A0B-3FF2-E505-EF4A439570C3"), "machine_gun", 20, AttributeModifier.Operation.ADDITION);
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private boolean lastloop;
    private boolean init;
    public String animationprocedure = "empty";
    private double temperature = 0;
    private int cloudTime;

    public HeavyMachineGunEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ZombieKitEntities.HEAVY_MACHINE_GUN.get(), world);
    }

    public HeavyMachineGunEntity(EntityType<HeavyMachineGunEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setPersistenceRequired();

    }

    @Override
    public void tick() {
        super.tick();
        heat();
        if (this.getVehicle() == null || this.getVehicle() instanceof Minecart){
            setYBodyRot(getGunDirection().getAngle());
            yBodyRotO = getGunDirection().getAngle();
        }
        List<Entity> passengers = getPassengers();
        if (passengers.size() != 0 && passengers.get(0) instanceof LivingEntity passenger){
            turnGunpoint(passenger);
        }else {
            if (this.level instanceof ServerLevel serverLevel){
                if (!this.init){
                    StructureFeatureManager structureFeatureManager = serverLevel.structureFeatureManager();
                    ConfiguredStructureFeature<?, ?> configuredStructureFeature = structureFeatureManager.registryAccess().registryOrThrow(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY).get(ZombieKitTags.STRUCTURE);
                    if (configuredStructureFeature != null && structureFeatureManager.getStructureAt(new BlockPos(this.getX(), this.getY(), this.getZ()), configuredStructureFeature).isValid()){
                        BlockState state = serverLevel.getBlockState(new BlockPos(this.getX(), this.getY(), this.getZ()).below());
                        if (state.getBlock().getStateDefinition().getProperty("facing") instanceof EnumProperty facing){
                            setDirection(state.getValue(facing).toString());
                        }
                    }
                    init = true;
                }
            }
            tryMakeMobRide();
        }
    }

    public void heat(){
        if (getOverload()){
            this.temperature = Math.max(temperature - 0.05, 0);
            if (temperature < 50){
                this.setOverload(false);
            }
        }else {
            this.temperature = Math.max(temperature - 0.1, 0);
        }
        cloudTime = (cloudTime + 1) % 10;
        if (this.temperature > 50 && this.cloudTime % 10 == 0 && this.level instanceof ServerLevel server){
            steam(server);
            return;
        }
        if (this.temperature > 75 && this.cloudTime % 5 == 0 && this.level instanceof ServerLevel server){
            steam(server);
            if (temperature > 99){
                this.level.playSound(null, getX(), getY(), getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:heavy_machine_gun_overload")), SoundSource.BLOCKS, 1, 1);
                this.setOverload(true);
            }
        }
    }

    public void steam(ServerLevel server){
        switch (getGunDirection()) {
            case NORTH -> server.sendParticles(ParticleTypes.CLOUD, getX(), getY() + 1.5, getZ() - 1, 0, 0, 2, 0, 0.1);
            case SOUTH -> server.sendParticles(ParticleTypes.CLOUD, getX(), getY() + 1.5, getZ() + 1, 0, 0, 2, 0, 0.1);
            case EAST -> server.sendParticles(ParticleTypes.CLOUD, getX() + 1, getY() + 1.5, getZ(), 0, 0, 2, 0, 0.1);
            case WEST -> server.sendParticles(ParticleTypes.CLOUD, getX() - 1, getY() + 1.5, getZ(), 0, 0, 2, 0, 0.1);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.contains("Temperature")) {
            this.temperature = compoundTag.getDouble("Temperature");
        }
        if (compoundTag.contains("Overload")) {
            this.setOverload(compoundTag.getBoolean("Overload"));
        }
        if (compoundTag.contains("Direction")) {
            this.setDirection(compoundTag.getString("Direction"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putDouble("Temperature", this.temperature);
        compoundTag.putBoolean("Overload", this.getOverload());
        if (this.getGunDirection() != null) {
            compoundTag.putString("Direction", this.getGunDirection().toString());
        }
    }

    public void turnGunpoint(LivingEntity passenger){
        float y;
        if (passenger instanceof Player){
            y = passenger.getYRot();
        }else {
            y = passenger.getYHeadRot();
        }
        float x = passenger.getXRot();
        float angle;
        if (this.getVehicle() == null || this.getVehicle() instanceof Minecart){
            angle = getGunDirection().getAngle();
        }else {
            angle = this.yBodyRot;
        }

        while (angle > 180) {
            angle -= 360;
        }
        while (angle <= -180) {
            angle += 360;
        }
        if (angle >= 120f || angle <= -120f){
            while (y > 360) {
                y -= 360;
            }
            while (y < 0) {
                y += 360;
            }
            while (angle <= -120) {
                angle += 360;
            }
        }else {
            while (y > 180) {
                y -= 360;
            }
            while (y <= -180) {
                y += 360;
            }

        }
        if (y - angle > 60){
            setYRot(angle + 60);
            passenger.setYRot(angle + 60);
            passenger.setYHeadRot(passenger.getYRot());
            passenger.yRotO = passenger.getYRot();
            passenger.yHeadRotO = passenger.getYRot();
        }else if (angle - y > 60){
            setYRot(angle - 60);
            passenger.setYRot(angle - 60);
            passenger.setYHeadRot(passenger.getYRot());
            passenger.yRotO = passenger.getYRot();
            passenger.yHeadRotO = passenger.getYRot();
        }else {
            setYRot(y);
        }
        if (x > 30){
            setXRot(30);
            passenger.setXRot(30);
            passenger.xRotO = passenger.getXRot();
        }else if (x < -50){
            setXRot(-50);
            passenger.setXRot(-50);
            passenger.xRotO = passenger.getXRot();
        }else {
            setXRot(x);
        }
        setYHeadRot(getYRot());
        xRotO = getXRot();
        yRotO = getYRot();
        yHeadRotO = getYRot();
    }

    public void tryMakeMobRide(){
        List<LivingEntity> list = this.level.getNearbyEntities(LivingEntity.class, TargetingConditions.forNonCombat().range(5.0), this, this.getBoundingBox().inflate(2.0, 2.0, 2.0));
        if (!list.isEmpty()) {
            list.sort((l1, l2) -> {
                double a = Math.pow(l1.getX() - HeavyMachineGunEntity.this.getX(), 2) + Math.pow(l1.getZ() - HeavyMachineGunEntity.this.getZ(), 2);
                double b = Math.pow(l2.getX() - HeavyMachineGunEntity.this.getX(), 2) + Math.pow(l2.getZ() - HeavyMachineGunEntity.this.getZ(), 2);
                return Double.compare(a, b);
            });
            for (LivingEntity entity : list) {
                if (entity instanceof Mob mob && entity.getType().is(ZombieKitTags.MACHINE_GUNNER)){
                    if (!mob.isNoAi() && mob.getVehicle() == null){
                        mob.startRiding(this);
                        AttributeInstance attributeInstance = mob.getAttributes().getInstance(Attributes.FOLLOW_RANGE);
                        if (attributeInstance == null) continue;
                        attributeInstance.removeModifier(ATTRIBUTE_MODIFIER);
                        attributeInstance.addPermanentModifier(ATTRIBUTE_MODIFIER);
                        mob.setPersistenceRequired();
                        break;
                    }
                }
            }
        }
    }

    public void setDirection(String direction){
        this.entityData.set(DIRECTION, direction.toUpperCase());
    }

    public Direction getGunDirection(){
        return Direction.valueOf(this.entityData.get(DIRECTION));
    }

    public void fire() {
        if (!this.getOverload()){
            List<Entity> passengers = getPassengers();
            if (passengers.size() != 0 && passengers.get(0) instanceof LivingEntity owner){
                HeavyMachineGunAmmoEntity ammo = new HeavyMachineGunAmmoEntity(ZombieKitEntities.HEAVY_MACHINE_GUN_AMMO.get(), this.level);
                ammo.setOwner(owner);
                ammo.setPos(owner.getX(), owner.getEyeY(), owner.getZ());
                ammo.shoot(level, owner, this.level.getRandom(), 2.5f, 0, 0);
                if (!(this.hasEffect(SonaMobEffects.FROST.get()) || this.isFreezing()))
                    this.temperature = Math.min(temperature + 0.9, 100);
                if (passengers.get(0) instanceof Player player){
                    player.setYRot(player.getYRot() + Mth.nextFloat(this.level.getRandom(), -1.2f, 1.2f));
                    player.setXRot(player.getXRot() + Mth.nextFloat(this.level.getRandom(), -2, 0));
                    player.setYHeadRot(player.getYRot());
                    player.xRotO = player.getXRot();
                    player.yRotO = player.getYRot();
                    player.yHeadRotO = player.getYRot();
                }
            }
            setAnimation("shoot");
        }else {
            this.level.playSound(null, getX(), getY(), getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:heavy_machine_gun_trigger")), SoundSource.BLOCKS, 1, 1);
        }

    }

    public boolean canSee(Entity livingEntity) {
        boolean XRange = false;
        boolean YRange = false;
        double x = livingEntity.getX();
        double y = livingEntity.getY();
        double z = livingEntity.getZ();
        switch (getGunDirection()) {
            case NORTH -> {
                XRange = (z - this.getZ() < Math.pow(1d / 3d, 0.5) * (x - this.getX()) && z - this.getZ() < -1.0d * Math.pow(1d / 3d, 0.5) * (x - this.getX()));
                YRange = ((this.getZ() - z) > y - this.getY() && -1.0d * Math.pow(1d / 3d, 0.5) * (this.getZ() - z) < y - this.getY());
            }
            case SOUTH -> {
                XRange = (z - this.getZ() > Math.pow(1d / 3d, 0.5) * (x - this.getX()) && z - this.getZ() > -1.0d * Math.pow(1d / 3d, 0.5) * (x - this.getX()));
                YRange = ((z - this.getZ()) > y - this.getY() && -1.0d * Math.pow(1d / 3d, 0.5) * (z - this.getZ()) < y - this.getY());
            }
            case EAST -> {
                XRange = (x - this.getX() > Math.pow(1d / 3d, 0.5) * (z - this.getZ()) && x - this.getX() > -1.0d * Math.pow(1d / 3d, 0.5) * (z - this.getZ()));
                YRange = ((x - this.getX()) > y - this.getY() && -1.0d * Math.pow(1d / 3d, 0.5) * (x - this.getX()) < y - this.getY());
            }
            case WEST -> {
                XRange = (x - this.getX() < Math.pow(1d / 3d, 0.5) * (z - this.getZ()) && x - this.getX() < -1.0d * Math.pow(1d / 3d, 0.5) * (z - this.getZ()));
                YRange = ((this.getX() - x) > y - this.getY() && -1.0d * Math.pow(1d / 3d, 0.5) * (this.getX() - x) < y - this.getY());
            }
        }
        return XRange && YRange;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHOOT, false);
        this.entityData.define(OVERLOAD, false);
        this.entityData.define(ANIMATION, "undefined");
        this.entityData.define(TEXTURE, "m2_machine_gun");
        this.entityData.define(DIRECTION, "NORTH");
    }

    public void setTexture(String texture) {
        this.entityData.set(TEXTURE, texture);
    }

    public String getTexture() {
        return this.entityData.get(TEXTURE);
    }

    public void setOverload(boolean overload) {
        this.entityData.set(OVERLOAD, overload);
    }

    public boolean getOverload() {
        return this.entityData.get(OVERLOAD);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public double getPassengersRidingOffset() {
        return super.getPassengersRidingOffset() - 0.8;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
    }

    @Override
    public void heal(float p_21116_) {
    }

    @Override
    public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
        ItemStack itemstack = sourceentity.getItemInHand(hand);
        InteractionResult retval = InteractionResult.sidedSuccess(this.level.isClientSide());
        super.mobInteract(sourceentity, hand);
        if (itemstack.is(ZombieKitItems.WRENCH.get())){
            if (sourceentity.isShiftKeyDown()){
                ItemEntity entityToSpawn = new ItemEntity(this.level, getX(), getY(), getZ(), new ItemStack(ZombieKitItems.HEAVY_MACHINE_GUN_SUMMON.get()));
                entityToSpawn.setPickUpDelay(10);
                this.level.addFreshEntity(entityToSpawn);
                this.discard();
            }else if (!this.isNoAi()){
                this.setNoAi(true);
                this.level.playSound(null, getX(), getY(), getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:heavy_machine_gun_deploy")), SoundSource.BLOCKS, 1, 1);
            }
        }else if(itemstack.is(Items.POWDER_SNOW_BUCKET)){
            this.addEffect(new MobEffectInstance(SonaMobEffects.FROST.get(), 1200, 0));
            if (!sourceentity.getAbilities().instabuild){
                ItemStack setstack = new ItemStack(Items.BUCKET);
                setstack.setCount(1);
                ItemHandlerHelper.giveItemToPlayer(sourceentity, setstack);
                itemstack.setCount(itemstack.getCount() - 1);
            }
        }else {
            sourceentity.startRiding(this);
        }

        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.refreshDimensions();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source == DamageSource.DROWN)
            return false;
        return super.hurt(source, amount);
    }

    @Override
    public EntityDimensions getDimensions(Pose p_33597_) {
        return super.getDimensions(p_33597_).scale((float) 1);
    }


    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        this.spawnAtLocation(new ItemStack(ZombieKitItems.MACHINE_GUN_COMPONENTS.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0);
        builder = builder.add(Attributes.MAX_HEALTH, 20);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 0);
        builder = builder.add(Attributes.FOLLOW_RANGE, 0);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1000);
        return builder;
    }

    private <E extends IAnimatable> PlayState movementPredicate(AnimationEvent<E> event) {
        if (this.animationprocedure.equals("empty")) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    private <E extends IAnimatable> PlayState procedurePredicate(AnimationEvent<E> event) {
        Entity entity = this;
        if (this.lastloop) {
            this.lastloop = false;
            event.getController().setAnimation(new AnimationBuilder().addAnimation(this.animationprocedure, ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            event.getController().clearAnimationCache();
            return PlayState.STOP;
        }
        if (!this.animationprocedure.equals("empty") && event.getController().getAnimationState().equals(software.bernie.geckolib3.core.AnimationState.Stopped)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation(this.animationprocedure, ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            if (event.getController().getAnimationState().equals(software.bernie.geckolib3.core.AnimationState.Stopped)) {
                this.animationprocedure = "empty";
                event.getController().markNeedsReload();
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            this.remove(HeavyMachineGunEntity.RemovalReason.KILLED);
            this.dropExperience();
        }
    }

    public String getSyncedAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public void setAnimation(String animation) {
        this.entityData.set(ANIMATION, animation);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "movement", 0, this::movementPredicate));
        data.addAnimationController(new AnimationController<>(this, "procedure", 0, this::procedurePredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    static enum Direction {
        NORTH(180f),
        SOUTH(0f),
        WEST(90f),
        EAST(-90f);

        private final float angle;

        private Direction(float angle){
            this.angle = angle;
        }

        public float getAngle(){
            return this.angle;
        }
    }
}
