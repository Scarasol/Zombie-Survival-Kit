package com.scarasol.zombiekit.init;

import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.entity.mechanics.DroneEntity;
import com.scarasol.zombiekit.entity.mechanics.HeavyMachineGunEntity;
import com.scarasol.zombiekit.entity.mechanics.UvLampEntity;
import com.scarasol.zombiekit.entity.projectile.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ZombieKitEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ZombieKitMod.MODID);

    public static final RegistryObject<EntityType<MolotovCocktailEntity>> MOLOTOV_COCKTAIL = register("molotov_cocktail", EntityType.Builder.<MolotovCocktailEntity>of(MolotovCocktailEntity::new, MobCategory.MISC)
            .setCustomClientFactory(MolotovCocktailEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<PotionJarEntity>> POTION_JAR = register("potion_jar", EntityType.Builder.<PotionJarEntity>of(PotionJarEntity::new, MobCategory.MISC)
            .setCustomClientFactory(PotionJarEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<BileJarEntity>> BILE_JAR = register("bile_jar", EntityType.Builder.<BileJarEntity>of(BileJarEntity::new, MobCategory.MISC)
            .setCustomClientFactory(BileJarEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<FirecrackerEntity>> FIRECRACKER = register("firecracker", EntityType.Builder.<FirecrackerEntity>of(FirecrackerEntity::new, MobCategory.MISC)
            .setCustomClientFactory(FirecrackerEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<WrenchEntity>> WRENCH = register("wrench", EntityType.Builder.<WrenchEntity>of(WrenchEntity::new, MobCategory.MISC)
            .setCustomClientFactory(WrenchEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<FlareGunEntity>> FLARE_GUN = register("flare_gun",
            EntityType.Builder.<FlareGunEntity>of(FlareGunEntity::new, MobCategory.MISC).setCustomClientFactory(FlareGunEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<FlaresEntity>> FLARES = register("flares", EntityType.Builder.<FlaresEntity>of(FlaresEntity::new, MobCategory.MISC)
            .setCustomClientFactory(FlaresEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<DroneEntity>> DRONE = register("drone",
            EntityType.Builder.<DroneEntity>of(DroneEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(25).setUpdateInterval(3).setCustomClientFactory(DroneEntity::new).sized(0.6f, 0.4f));
    public static final RegistryObject<EntityType<HeavyMachineGunAmmoEntity>> HEAVY_MACHINE_GUN_AMMO = register("heavy_machine_gun_ammo", EntityType.Builder.<HeavyMachineGunAmmoEntity>of(HeavyMachineGunAmmoEntity::new, MobCategory.MISC)
            .setCustomClientFactory(HeavyMachineGunAmmoEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<HeavyMachineGunEntity>> HEAVY_MACHINE_GUN = register("heavy_machine_gun",
            EntityType.Builder.<HeavyMachineGunEntity>of(HeavyMachineGunEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HeavyMachineGunEntity::new).sized(0.8f, 1f));
    public static final RegistryObject<EntityType<UvLampEntity>> UV_LAMP = register("uv_lamp",
            EntityType.Builder.<UvLampEntity>of(UvLampEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(UvLampEntity::new));





    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryName, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register(registryName, () -> entityTypeBuilder.build(registryName));
    }


//    @SubscribeEvent
//    public static void init(FMLCommonSetupEvent event) {
//        event.enqueueWork(() -> {
//            FirecrackerTemEntityEntity.init();
//            UvLampEntity.init();
//            DroneEntity.init();
//            HeavyMachineGunEntity.init();
//        });
//    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(UV_LAMP.get(), UvLampEntity.createAttributes().build());
        event.put(DRONE.get(), DroneEntity.createAttributes().build());
        event.put(HEAVY_MACHINE_GUN.get(), HeavyMachineGunEntity.createAttributes().build());
    }


}
