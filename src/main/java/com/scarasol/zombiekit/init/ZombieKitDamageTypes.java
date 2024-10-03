package com.scarasol.zombiekit.init;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;


public class ZombieKitDamageTypes {

    public static final ResourceKey<DamageType> BARBED_WIRE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("zombiekit:barbed_wire"));
    public static final ResourceKey<DamageType> HEAVY_MACHINE_GUN = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("zombiekit:heavy_machine_gun"));
    public static final ResourceKey<DamageType> HEAVY_MACHINE_GUN_BYPASS_ARMOR = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("zombiekit:heavy_machine_gun_bypass_armor"));
    public static final ResourceKey<DamageType> WRENCH = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("zombiekit:wrench"));

    public static DamageSource damageSource(RegistryAccess registryAccess, ResourceKey<DamageType> resourceKey){
        return new DamageSource(registryAccess.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(resourceKey));
    }

    public static DamageSource damageSource(RegistryAccess registryAccess, ResourceKey<DamageType> resourceKey, Entity arrow, Entity owner){
        return new DamageSource(registryAccess.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(resourceKey), arrow, owner);
    }
}
