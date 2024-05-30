package com.scarasol.zombiekit.init;

import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.entity.projectile.MolotovCocktailEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ZombieKitEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, ZombieKitMod.MODID);

    public static final RegistryObject<EntityType<MolotovCocktailEntity>> MOLOTOV_COCKTAIL = register("projectile_molotov_cocktail", EntityType.Builder.<MolotovCocktailEntity>of(MolotovCocktailEntity::new, MobCategory.MISC)
            .setCustomClientFactory(MolotovCocktailEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryName, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register(registryName, () -> entityTypeBuilder.build(registryName));
    }


//    @SubscribeEvent
//    public static void init(FMLCommonSetupEvent event) {
//        event.enqueueWork(() -> {
//            FirecrackerTemEntityEntity.init();
//            UvLampEntityEntity.init();
//            DroneEntity.init();
//            HeavyMachineGunEntity.init();
//        });
//    }

//    @SubscribeEvent
//    public static void registerAttributes(EntityAttributeCreationEvent event) {
//        event.put(FIRECRACKER_TEM_ENTITY.get(), FirecrackerTemEntityEntity.createAttributes().build());
//        event.put(UV_LAMP_ENTITY.get(), UvLampEntityEntity.createAttributes().build());
//        event.put(DRONE.get(), DroneEntity.createAttributes().build());
//        event.put(HEAVY_MACHINE_GUN.get(), HeavyMachineGunEntity.createAttributes().build());
//    }


}
