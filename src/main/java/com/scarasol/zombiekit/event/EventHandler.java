package com.scarasol.zombiekit.event;

import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.sona.manager.RustManager;
import com.scarasol.zombiekit.block.InjectorBlock;
import com.scarasol.zombiekit.block.ShortwaveRadioBlock;
import com.scarasol.zombiekit.config.CommonConfig;
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
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;


@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void livingEquipmentChangeEvent(LivingEquipmentChangeEvent event) {
        if (event.getSlot() == EquipmentSlot.MAINHAND || event.getSlot() == EquipmentSlot.OFFHAND)
            return;
        if (event.getFrom().getItem() instanceof BombArmor || event.getTo().getItem() instanceof BombArmor) {
            BombArmor.updateModifier(event.getEntity());
        }
        if (event.getFrom().getItem() instanceof ExoArmor || event.getTo().getItem() instanceof ExoArmor) {
            ExoArmor.updateModifier(event.getEntity());
        }
    }


    @SubscribeEvent
    public static void getAttack(LivingAttackEvent event) {
        Entity entity = event.getSource().getDirectEntity();
        if (entity != null) {
            event.setCanceled(ExoArmor.reactiveArmor(event.getEntity(), entity));
        }
    }

    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        ShortwaveRadioBlock.loadRadioString(event.getLevel());
        InjectorBlock.init();
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().level().isClientSide()) {
            SavedData mapData = MapVariables.get(event.getEntity().level());
            if (mapData != null)
                NetworkHandler.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncPacket(mapData));
        }
    }


    @SubscribeEvent
    public static void rightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        Entity entity = player.getVehicle();
        if (entity instanceof HeavyMachineGunEntity heavyMachineGunEntity && !(player.hasEffect(SonaMobEffects.STUN.get()) || (player.hasEffect(SonaMobEffects.SLIMINESS.get()) && player.hasEffect(SonaMobEffects.FROST.get())))) {
            boolean flag1 = !player.getItemInHand(InteractionHand.MAIN_HAND).is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get());
            boolean flag2 = !player.getItemInHand(InteractionHand.OFF_HAND).is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get());
            ItemStack itemStack = player.getItemInHand(event.getHand());
            if (itemStack.is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get()) && (event.getHand() == InteractionHand.MAIN_HAND || flag1)) {
                heavyMachineGunEntity.fire();
                player.addEffect(new MobEffectInstance(SonaMobEffects.EXPOSURE.get(), 20, 3, false, false));
                if (!player.getAbilities().instabuild && !heavyMachineGunEntity.getOverload()) {
                    itemStack.setCount(itemStack.getCount() - 1);
                }
            } else if (flag1 && flag2 && event.getHand() == InteractionHand.MAIN_HAND) {
                heavyMachineGunEntity.level().playSound(null, heavyMachineGunEntity.getX(), heavyMachineGunEntity.getY(), heavyMachineGunEntity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:heavy_machine_gun_trigger")), SoundSource.BLOCKS, 1, 1);
            }

        }
    }

    @SubscribeEvent
    public static void rightClickEmpty(PlayerInteractEvent.RightClickEmpty event) {
        Player player = event.getEntity();
        Entity entity = player.getVehicle();
        if (entity instanceof HeavyMachineGunEntity heavyMachineGunEntity && !(player.hasEffect(SonaMobEffects.STUN.get()) || (player.hasEffect(SonaMobEffects.SLIMINESS.get()) && player.hasEffect(SonaMobEffects.FROST.get())))) {
            boolean flag1 = !player.getItemInHand(InteractionHand.MAIN_HAND).is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get());
            boolean flag2 = !player.getItemInHand(InteractionHand.OFF_HAND).is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get());
            if (flag1 && flag2 && event.getHand() == InteractionHand.MAIN_HAND) {
                heavyMachineGunEntity.level().playSound(player, heavyMachineGunEntity.getX(), heavyMachineGunEntity.getY(), heavyMachineGunEntity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:heavy_machine_gun_trigger")), SoundSource.BLOCKS, 1, 1);
            }
        }
    }

    @SubscribeEvent
    public static void KnockbackEvent(LivingKnockBackEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity.getPersistentData().getBoolean("CancelKnockback")) {
            livingEntity.getPersistentData().putBoolean("CancelKnockback", false);
            event.setStrength(event.getStrength() * 0.1f);
        }
    }

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
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

    public static boolean illagerWhiteList(Mob mob){
        return com.scarasol.sona.configuration.CommonConfig.findIndex(ForgeRegistries.ENTITY_TYPES.getKey(mob.getType()).toString(), CommonConfig.ILLAGER_WHITELIST.get()) != -1;
    }

    @SubscribeEvent
    public static void onEntityJoined(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity == null)
            return;
        Level level = entity.level();
        if (entity instanceof Mob newSpawn) {
            if (newSpawn.getType().is(ZombieKitTags.MACHINE_GUNNER)){
                if ((newSpawn instanceof Raider || illagerWhiteList(newSpawn)) && CommonConfig.RAIDER_INDEPENDENCE.get()) {
                    newSpawn.goalSelector.addGoal(1, new HeavyMachineGunUsingGoal<>(newSpawn, 25f, livingEntity -> livingEntity instanceof Mob mob && (mob.getType().is(ZombieKitTags.SURVIVORS) || livingEntity instanceof IronGolem || livingEntity instanceof AbstractVillager || (livingEntity instanceof Enemy && !(livingEntity instanceof Creeper || livingEntity instanceof NeutralMob || illagerWhiteList(mob)))), true));
                }else if (newSpawn instanceof Enemy){
                    newSpawn.goalSelector.addGoal(1, new HeavyMachineGunUsingGoal<>(newSpawn, 25f, null, true));
                }else {
                    newSpawn.goalSelector.addGoal(1, new HeavyMachineGunUsingGoal<>(newSpawn, 25f, livingEntity -> livingEntity instanceof Enemy && !(livingEntity instanceof Creeper || livingEntity instanceof NeutralMob), false));
                }
            }
            if ((newSpawn instanceof Raider || illagerWhiteList(newSpawn)) && CommonConfig.RAIDER_INDEPENDENCE.get()) {
                newSpawn.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(newSpawn, Mob.class, 5, false, false, livingEntity -> livingEntity instanceof Enemy && !(livingEntity instanceof Raider || livingEntity instanceof Creeper || livingEntity instanceof NeutralMob || (livingEntity instanceof Mob mob && illagerWhiteList(mob)))));
            } else if (newSpawn instanceof Enemy && !(newSpawn instanceof Creeper || newSpawn instanceof NeutralMob)) {
                if (newSpawn.getType().is(ZombieKitTags.UV_RESISTANCE))
                    newSpawn.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(newSpawn, Mob.class, 5, false, false, livingEntity -> livingEntity instanceof UvLampEntity));
                if (CommonConfig.RAIDER_INDEPENDENCE.get())
                    newSpawn.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(newSpawn, Mob.class, 5, false, false, livingEntity -> livingEntity instanceof Raider || (livingEntity instanceof Mob mob && illagerWhiteList(mob))));
                if (newSpawn instanceof Zombie && !(newSpawn instanceof ZombieVillager && !entity.getPersistentData().getBoolean("spawn_have_changed"))) {
                    if (Mth.nextInt(level.getRandom(), 1, 100) <= CommonConfig.EQUIPMENT_INITIALIZATION.get() * 100) {
                        double i = Mth.nextInt(level.getRandom(), 1, 4);
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
                    if (Mth.nextInt(level.getRandom(), 1, 100) <= CommonConfig.BOMB_ARMOR_INITIALIZATION.get() * 100) {
                        if (Mth.nextInt(level.getRandom(), 1, 100) <= 40) {
                            newSpawn.setItemSlot(EquipmentSlot.FEET, new ItemStack(ZombieKitItems.BOMB_BOOTS.get()));
                        }
                        if (Mth.nextInt(level.getRandom(), 1, 100) <= 20) {
                            newSpawn.setItemSlot(EquipmentSlot.LEGS, new ItemStack(ZombieKitItems.BOMB_LEGGINGS.get()));
                        }
                        if (Mth.nextInt(level.getRandom(), 1, 100) <= 10) {
                            newSpawn.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ZombieKitItems.BOMB_CHESTPLATE.get()));
                        }
                        if (Mth.nextInt(level.getRandom(), 1, 100) <= 30) {
                            newSpawn.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ZombieKitItems.BOMB_HELMET.get()));
                        }
                    }
                    entity.getPersistentData().putBoolean("spawn_have_changed", true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntitySpawned(MobSpawnEvent.PositionCheck event) {
        LivingEntity newSpawn = event.getEntity();
        if (newSpawn instanceof Enemy && !(newSpawn instanceof Vex) && event.getLevel() instanceof ServerLevel serverLevel) {
            StructureManager structureFeatureManager = serverLevel.structureManager();
            Structure configuredStructureFeature = structureFeatureManager.registryAccess().registryOrThrow(Registries.STRUCTURE).get(ZombieKitTags.STRUCTURE);
            if (configuredStructureFeature != null) {
                if ((event.getSpawnType() == MobSpawnType.NATURAL || event.getSpawnType() == MobSpawnType.STRUCTURE || event.getSpawnType() == MobSpawnType.REINFORCEMENT) && structureFeatureManager.getStructureAt(BlockPos.containing(event.getX(), event.getY(), event.getZ()), configuredStructureFeature).isValid()) {
                    if (!(newSpawn instanceof Raider || newSpawn instanceof HeavyMachineGunEntity)) {
                        event.setResult(Event.Result.DENY);
                        event.getEntity().discard();
                    }
                    BlockState state = serverLevel.getBlockState(BlockPos.containing(event.getX(), event.getY(), event.getZ()).below());
                    if (state.getBlock() == Blocks.SMOOTH_STONE || state.getBlock() == Blocks.STONE) {
                        PatrollingMonster patrollingMonster;
                        for (int i = 0; i < new Random().nextInt(3) + 1; i++) {
                            if (serverLevel.getRandom().nextDouble() < 0.5) {
                                patrollingMonster = EntityType.PILLAGER.create(serverLevel);
                            } else {
                                patrollingMonster = EntityType.VINDICATOR.create(serverLevel);
                            }
                            patrollingMonster.setPos(event.getX(), event.getY(), event.getZ());
                            patrollingMonster.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(BlockPos.containing(event.getX(), event.getY(), event.getZ())), MobSpawnType.EVENT, null, null);
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
        if (ExoArmor.numberOfSuit(player) == 4 && player.isFallFlying() && player.isSprinting()) {
            Vec3 vec31 = player.getLookAngle();
            Vec3 vec32 = player.getDeltaMovement();
            player.setDeltaMovement(vec32.add(vec31.x * 0.1D + (vec31.x * 1.5D - vec32.x) * 0.5D, vec31.y * 0.1D + (vec31.y * 1.5D - vec32.y) * 0.5D, vec31.z * 0.1D + (vec31.z * 1.5D - vec32.z) * 0.5D));
        }
    }

    @SubscribeEvent
    public static void projectileHit(ProjectileImpactEvent event) {
        if (event.getRayTraceResult() instanceof EntityHitResult entityHitResult) {
            Entity target = entityHitResult.getEntity();
            if (target instanceof LivingEntity livingEntity && ExoArmor.reactiveArmor(livingEntity, event.getProjectile())) {
                event.setImpactResult(ProjectileImpactEvent.ImpactResult.SKIP_ENTITY);
            }
        }

    }
}
