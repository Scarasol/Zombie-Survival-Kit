package com.scarasol.zombiekit.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ZombieKitTags {
    public static final ResourceKey<Structure> STRUCTURE = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation("zombiekit:prison"));

    public static final TagKey<EntityType<?>> UV_RESISTANCE = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("forge:uv_resistance"));
    public static final TagKey<EntityType<?>> UV_NONRESISTANCE = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("forge:uv_nonresistance"));
    public static final TagKey<EntityType<?>> SURVIVORS = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("forge:survivors"));
    public static final TagKey<EntityType<?>> MACHINE_GUNNER = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("forge:machine_gunner"));

    public static TagKey<Biome> DESERT = TagKey.create(Registries.BIOME, new ResourceLocation("forge:desert_camouflage"));
    public static TagKey<Biome> FOREST = TagKey.create(Registries.BIOME, new ResourceLocation("forge:forest_camouflage"));
    public static TagKey<Biome> SNOW = TagKey.create(Registries.BIOME, new ResourceLocation("forge:snow_camouflage"));
    public static TagKey<Biome> DESERT_CAVE = TagKey.create(Registries.BIOME, new ResourceLocation("forge:desert_camouflage_cave"));
    public static TagKey<Biome> FOREST_CAVE = TagKey.create(Registries.BIOME, new ResourceLocation("forge:forest_camouflage_cave"));
    public static TagKey<Biome> SNOW_CAVE = TagKey.create(Registries.BIOME, new ResourceLocation("forge:snow_camouflage_cave"));

    public static final TagKey<Structure> SHELTER = TagKey.create(Registries.STRUCTURE, new ResourceLocation("zombiekit:shelter"));
}
