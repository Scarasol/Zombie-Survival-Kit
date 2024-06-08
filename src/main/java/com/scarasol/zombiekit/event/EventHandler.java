package com.scarasol.zombiekit.event;


import com.mojang.datafixers.kinds.IdF;
import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.sona.manager.RustManager;
import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.block.InjectorBlock;
import com.scarasol.zombiekit.block.ShortwaveRadioBlock;
import com.scarasol.zombiekit.entity.goal.HeavyMachineGunUsingGoal;
import com.scarasol.zombiekit.entity.mechanics.HeavyMachineGunEntity;
import com.scarasol.zombiekit.entity.mechanics.UvLampEntity;
import com.scarasol.zombiekit.init.ZombieKitItems;
import com.scarasol.zombiekit.init.ZombieKitTags;
import com.scarasol.zombiekit.item.armor.BombArmor;
import com.scarasol.zombiekit.item.armor.ExoArmor;
import com.scarasol.zombiekit.network.MapVariables;
import com.scarasol.zombiekit.network.NetworkHandler;
import com.scarasol.zombiekit.network.SavedDataSyncPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.*;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;
import java.util.UUID;


@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void livingEquipmentChangeEvent(LivingEquipmentChangeEvent event){
        if (event.getSlot() == EquipmentSlot.MAINHAND || event.getSlot() == EquipmentSlot.OFFHAND)
            return;
        if (event.getFrom().getItem() instanceof BombArmor || event.getTo().getItem() instanceof BombArmor){
            BombArmor.updateModifier(event.getEntityLiving());
        }
        if (event.getFrom().getItem() instanceof ExoArmor || event.getTo().getItem() instanceof ExoArmor){
            ExoArmor.updateModifier(event.getEntityLiving());
        }
    }


    @SubscribeEvent
    public static void getAttack(LivingAttackEvent event){
        Entity entity = event.getSource().getDirectEntity();
        if (entity != null){
            ExoArmor.reactiveArmor(event.getEntityLiving(), entity);
        }
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        ShortwaveRadioBlock.loadRadioString(event.getWorld());
        InjectorBlock.init();
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getPlayer().level.isClientSide()) {
            SavedData mapData = MapVariables.get(event.getPlayer().level);
            if (mapData != null)
                NetworkHandler.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()), new SavedDataSyncPacket(mapData));
        }
    }


    @SubscribeEvent
    public static void rightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getPlayer();
        Entity entity = player.getVehicle();
        if (entity instanceof HeavyMachineGunEntity heavyMachineGunEntity && !(player.hasEffect(SonaMobEffects.STUN.get()) || (player.hasEffect(SonaMobEffects.SLIMINESS.get()) && player.hasEffect(SonaMobEffects.FROST.get())))){
            boolean flag1 = !player.getItemInHand(InteractionHand.MAIN_HAND).is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get());
            boolean flag2 = !player.getItemInHand(InteractionHand.OFF_HAND).is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get());
            ItemStack itemStack = player.getItemInHand(event.getHand());
            if (itemStack.is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get()) && (event.getHand() == InteractionHand.MAIN_HAND || flag1)){
                heavyMachineGunEntity.fire();
                player.addEffect(new MobEffectInstance(SonaMobEffects.EXPOSURE.get(), 20, 3, false, false));
                if (!player.getAbilities().instabuild && !heavyMachineGunEntity.getOverload()){
                    itemStack.setCount(itemStack.getCount() - 1);
                }
            }else if (flag1 && flag2 && event.getHand() == InteractionHand.MAIN_HAND) {
                heavyMachineGunEntity.getLevel().playSound(null, heavyMachineGunEntity.getX(), heavyMachineGunEntity.getY(), heavyMachineGunEntity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:heavy_machine_gun_trigger")), SoundSource.BLOCKS, 1, 1);
            }

        }
    }

    @SubscribeEvent
    public static void rightClickEmpty(PlayerInteractEvent.RightClickEmpty event) {
        Player player = event.getPlayer();
        Entity entity = player.getVehicle();
        if (entity instanceof HeavyMachineGunEntity heavyMachineGunEntity && !(player.hasEffect(SonaMobEffects.STUN.get()) || (player.hasEffect(SonaMobEffects.SLIMINESS.get()) && player.hasEffect(SonaMobEffects.FROST.get())))){
            boolean flag1 = !player.getItemInHand(InteractionHand.MAIN_HAND).is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get());
            boolean flag2 = !player.getItemInHand(InteractionHand.OFF_HAND).is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get());
            if (flag1 && flag2 && event.getHand() == InteractionHand.MAIN_HAND){
                heavyMachineGunEntity.getLevel().playSound(player, heavyMachineGunEntity.getX(), heavyMachineGunEntity.getY(), heavyMachineGunEntity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:heavy_machine_gun_trigger")), SoundSource.BLOCKS, 1, 1);
            }
        }
    }

    @SubscribeEvent
    public static void rightClickEntity(PlayerInteractEvent.EntityInteractSpecific event) {
        Player player = event.getPlayer();
        if (event.getTarget() instanceof ArmorStand armorStand){
            ItemStack armor = armorStand.getItemBySlot(EquipmentSlot.CHEST);
            if (armor.is(ZombieKitItems.EXO_CHESTPLATE.get()) && player.getMainHandItem().is(Items.REDSTONE)){
                ExoArmor.addPower(armor, 5);
                if (!player.isCreative())
                    player.getMainHandItem().shrink(1);
                armorStand.getLevel().addParticle(DustParticleOptions.REDSTONE, armorStand.getX(), armorStand.getY(), armorStand.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @SubscribeEvent
    public static void KnockbackEvent(LivingKnockBackEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        if (livingEntity.getPersistentData().getBoolean("CancelKnockback")){
            livingEntity.getPersistentData().putBoolean("CancelKnockback", false);
            event.setStrength(event.getStrength() * 0.1f);
        }
    }

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingUpdateEvent event) {
        if (event != null && event.getEntity() != null) {
            if (event.getEntity() instanceof HeavyMachineGunEntity syncable) {
                String animation = syncable.getSyncedAnimation();
                if (!animation.equals("undefined")) {
                    syncable.setAnimation("undefined");
                    syncable.animationprocedure = animation;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityJoined(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity == null)
            return;
        double i = 0;
        if (entity instanceof Mob _entity) {
            Mob newSpawn = _entity;
            if (newSpawn instanceof Raider || newSpawn instanceof Vex) {
                newSpawn.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(newSpawn, Mob.class, 5, false, false, livingEntity -> livingEntity instanceof Enemy && !(livingEntity instanceof Raider || livingEntity instanceof Creeper || livingEntity instanceof NeutralMob || livingEntity instanceof Vex)));
                if (newSpawn instanceof Vindicator vindicator){
                    vindicator.goalSelector.addGoal(1, new HeavyMachineGunUsingGoal<>(vindicator, 25f, livingEntity -> (livingEntity instanceof Mob mob && mob.getType().is(TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge:force_villager"))))|| livingEntity instanceof IronGolem || livingEntity instanceof AbstractVillager || (livingEntity instanceof Enemy && !(livingEntity instanceof Creeper || livingEntity instanceof NeutralMob || livingEntity instanceof Vex)), true));
                }
            }else if (newSpawn instanceof Enemy && !(newSpawn instanceof Creeper || newSpawn instanceof NeutralMob)){
                if (newSpawn.getType().is(ZombieKitTags.UV_RESISTANCE))
                    newSpawn.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(newSpawn, Mob.class, 5, false, false, livingEntity -> livingEntity instanceof UvLampEntity));
                newSpawn.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(newSpawn, Mob.class, 5, false, false, livingEntity -> livingEntity instanceof Raider || livingEntity instanceof Vex));
                if (newSpawn instanceof Zombie && !(newSpawn instanceof ZombieVillager && !entity.getPersistentData().getBoolean("spawn_have_changed"))){
                    if (Mth.nextInt(new Random(), 1, 100) <= 5) {
                        i = Mth.nextInt(new Random(), 1, 4);
                        if (1 == i) {
                            ItemStack setStack = new ItemStack(ZombieKitItems.MACHETE.get());
                            setStack.setCount(1);
                            RustManager.putRust(setStack, 75);
                            newSpawn.setItemInHand(InteractionHand.MAIN_HAND, setStack);
                        } else if (2 == i) {
                            ItemStack setStack = new ItemStack(ZombieKitItems.CROWBAR.get());
                            setStack.setCount(1);
                            RustManager.putRust(setStack, 75);
                            newSpawn.setItemInHand(InteractionHand.MAIN_HAND, setStack);
                        } else if (3 == i) {
                            ItemStack setStack = new ItemStack(ZombieKitItems.FIRE_AXE.get());
                            setStack.setCount(1);
                            RustManager.putRust(setStack, 75);
                            newSpawn.setItemInHand(InteractionHand.MAIN_HAND, setStack);
                        } else {
                            ItemStack setStack = new ItemStack(ZombieKitItems.KNIFE.get());
                            setStack.setCount(1);
                            RustManager.putRust(setStack, 75);
                            newSpawn.setItemInHand(InteractionHand.MAIN_HAND, setStack);
                        }
                    }
                    if (Mth.nextInt(new Random(), 1, 100) <= 2) {
                        if (Mth.nextInt(new Random(), 1, 100) <= 40) {
                            newSpawn.setItemSlot(EquipmentSlot.FEET, new ItemStack(ZombieKitItems.BOMB_BOOTS.get()));
                        }
                        if (Mth.nextInt(new Random(), 1, 100) <= 20) {
                            newSpawn.setItemSlot(EquipmentSlot.LEGS, new ItemStack(ZombieKitItems.BOMB_LEGGINGS.get()));
                        }
                        if (Mth.nextInt(new Random(), 1, 100) <= 10) {
                            newSpawn.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ZombieKitItems.BOMB_CHESTPLATE.get()));
                        }
                        if (Mth.nextInt(new Random(), 1, 100) <= 30) {
                            newSpawn.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ZombieKitItems.BOMB_HELMET.get()));
                        }
                    }
                    entity.getPersistentData().putBoolean("spawn_have_changed", true);
                }
            }else if (newSpawn.getType().is(ZombieKitTags.FORCE_VILLAGER)){
                newSpawn.goalSelector.addGoal(1, new HeavyMachineGunUsingGoal<>(newSpawn, 25f, livingEntity -> livingEntity instanceof Enemy && !(livingEntity instanceof Creeper || livingEntity instanceof NeutralMob), false));
            }

        }
    }

    @SubscribeEvent
    public static void onEntitySpawned(LivingSpawnEvent.CheckSpawn event) {
        LivingEntity newSpawn = event.getEntityLiving();
        if (newSpawn instanceof Enemy && !(newSpawn instanceof Vex) && event.getWorld() instanceof ServerLevel serverLevel){
            StructureFeatureManager structureFeatureManager = serverLevel.structureFeatureManager();
            ConfiguredStructureFeature<?, ?> configuredStructureFeature = structureFeatureManager.registryAccess().registryOrThrow(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY).get(ZombieKitTags.STRUCTURE);
            if (configuredStructureFeature != null){
                if ((event.getSpawnReason() == MobSpawnType.NATURAL || event.getSpawnReason() == MobSpawnType.STRUCTURE || event.getSpawnReason() == MobSpawnType.REINFORCEMENT) && structureFeatureManager.getStructureAt(new BlockPos(event.getX(), event.getY(), event.getZ()), configuredStructureFeature).isValid()){
                    if (!(newSpawn instanceof Raider || newSpawn instanceof HeavyMachineGunEntity)){
                        event.setResult(Event.Result.DENY);
                        event.getEntityLiving().discard();
                    }
                    BlockState state = serverLevel.getBlockState(new BlockPos(event.getX(), event.getY(), event.getZ()).below());
                    if (state.getBlock() == Blocks.SMOOTH_STONE || state.getBlock() == Blocks.STONE){
                        PatrollingMonster patrollingMonster;
                        for (int i = 0; i < new Random().nextInt(3) + 1; i++){
                            if (serverLevel.getRandom().nextDouble() < 0.5){
                                patrollingMonster = EntityType.PILLAGER.create(serverLevel);
                            }else {
                                patrollingMonster = EntityType.VINDICATOR.create(serverLevel);
                            }
                            patrollingMonster.setPos(event.getX(), event.getY(), event.getZ());
                            patrollingMonster.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(new BlockPos(event.getX(), event.getY(), event.getZ())), MobSpawnType.EVENT, null, null);
                            serverLevel.addFreshEntity(patrollingMonster);
                        }
                    }

                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (ExoArmor.numberOfSuit(player) == 4 && player.isFallFlying() && player.isSprinting()){
            Vec3 vec31 = player.getLookAngle();
            Vec3 vec32 = player.getDeltaMovement();
            player.setDeltaMovement(vec32.add(vec31.x * 0.1D + (vec31.x * 1.5D - vec32.x) * 0.5D, vec31.y * 0.1D + (vec31.y * 1.5D - vec32.y) * 0.5D, vec31.z * 0.1D + (vec31.z * 1.5D - vec32.z) * 0.5D));
        }
    }

    @SubscribeEvent
    public static void projectileHit(ProjectileImpactEvent event){
        if (event.getRayTraceResult() instanceof EntityHitResult entityHitResult){
            Entity target = entityHitResult.getEntity();
            if (target instanceof LivingEntity livingEntity){
                event.setCanceled(ExoArmor.reactiveArmor(livingEntity, event.getProjectile()));
            }
        }

    }

}
