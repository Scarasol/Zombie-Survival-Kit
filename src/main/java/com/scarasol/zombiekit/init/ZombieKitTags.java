package com.scarasol.zombiekit.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

public class ZombieKitTags {
    public static final ResourceKey<ConfiguredStructureFeature<?, ?>> STRUCTURE = ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation("zombiekit:prison"));

    public static final TagKey<EntityType<?>> UV_RESISTANCE = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge:uv_resistance"));
    public static final TagKey<EntityType<?>> UV_NONRESISTANCE = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge:uv_nonresistance"));
    public static final TagKey<EntityType<?>> SURVIVORS = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge:survivors"));
    public static final TagKey<EntityType<?>> MACHINE_GUNNER = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge:machine_gunner"));

    public static TagKey<Biome> DESERT = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge:desert_camouflage"));
    public static TagKey<Biome> FOREST = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge:forest_camouflage"));
    public static TagKey<Biome> SNOW = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge:snow_camouflage"));
    public static TagKey<Biome> DESERT_CAVE = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge:desert_camouflage_cave"));
    public static TagKey<Biome> FOREST_CAVE = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge:forest_camouflage_cave"));
    public static TagKey<Biome> SNOW_CAVE = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge:snow_camouflage_cave"));

    public static final TagKey<ConfiguredStructureFeature<?, ?>> SHELTER = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation("zombiekit:shelter"));
}
