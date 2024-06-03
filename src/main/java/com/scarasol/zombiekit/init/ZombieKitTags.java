package com.scarasol.zombiekit.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

public class ZombieKitTags {
    public static final ResourceKey<ConfiguredStructureFeature<?, ?>> STRUCTURE = ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation("zombiekit:prison"));

    public static final TagKey<EntityType<?>> SURVIVORS = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge:survivors"));
    public static final TagKey<ConfiguredStructureFeature<?, ?>> SHELTER = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation("zombiekit:shelter"));
}
