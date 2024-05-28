package com.scarasol.zombiekit.item.armor;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TacticalSuit extends CamouflageSuit {

    public TacticalSuit(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties, int camouflage) {
        super(armorMaterial, equipmentSlot, properties, camouflage);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot slot, String type) {
        return "zombiekit:textures/entities/skiing_suit.png";
    }
}
