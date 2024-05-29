package com.scarasol.zombiekit.item.armor;

import com.scarasol.sona.init.SonaMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

public abstract class CamouflageArmor extends ArmorItem {

    private final int camouflage;
    public static TagKey<Biome> DESERT = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge:desert_camouflage"));
    public static TagKey<Biome> FOREST = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge:forest_camouflage"));
    public static TagKey<Biome> SNOW = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge:snow_camouflage"));
    public static TagKey<Biome> DESERT_CAVE = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge:desert_camouflage_cave"));
    public static TagKey<Biome> FOREST_CAVE = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge:forest_camouflage_cave"));
    public static TagKey<Biome> SNOW_CAVE = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge:snow_camouflage_cave"));

    public CamouflageArmor(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties, int camouflage) {
        super(armorMaterial, equipmentSlot, properties);
        this.camouflage = camouflage;
    }

    public int getCamouflage() {
        return camouflage;
    }

    @Override
    public void onArmorTick(ItemStack itemStack, Level level, Player player){
        if (getSlot() == EquipmentSlot.CHEST){
            if (camouflage == 0 || player.isSprinting())
                return;
            BlockPos blockPos = player.blockPosition();
            if (!isProperPlace(blockPos, level))
                return;
            for (ItemStack armor : player.getArmorSlots()){
                if (!(armor.getItem() instanceof CamouflageArmor camouflageSuit && camouflageSuit.camouflage == this.camouflage))
                    return;
            }
            int amplifier = 0;
            if (player.isShiftKeyDown())
                amplifier++;
            if (level.getMaxLocalRawBrightness(blockPos) < 7 || (level.isRaining() && level.canSeeSkyFromBelowWater(blockPos)))
                amplifier++;
            player.addEffect(new MobEffectInstance(SonaMobEffects.CAMOUFLAGE.get(), 20, amplifier, false, false));
        }
    }

    public boolean isProperPlace(BlockPos blockPos, Level level){
        switch (camouflage){
            case 1 -> {
                if (level.getBiome(blockPos).is(DESERT_CAVE)){
                    return true;
                }else if (level.getBiome(blockPos).is(DESERT)){
                    return level.canSeeSkyFromBelowWater(blockPos) || blockPos.getY() > 63;
                }
            }
            case 2 -> {
                if (level.getBiome(blockPos).is(FOREST_CAVE)){
                    return true;
                }else if (level.getBiome(blockPos).is(FOREST)){
                    return level.canSeeSkyFromBelowWater(blockPos) || blockPos.getY() > 63;
                }
            }
            case 3 -> {
                if (level.getBiome(blockPos).is(SNOW_CAVE)){
                    return true;
                }else if (level.getBiome(blockPos).is(SNOW)){
                    return level.canSeeSkyFromBelowWater(blockPos) || blockPos.getY() > 63;
                }
            }
            default -> {
                return false;
            }
        }
        return false;
    }
}
