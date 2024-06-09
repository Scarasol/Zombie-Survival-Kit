package com.scarasol.zombiekit.item.bonus;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class WhiteFood extends ArmorItem {
    public WhiteFood(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.white_food.description_1").getString()));
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.white_food.description_2").getString()));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "zombiekit:textures/models/armor/sun_chestplate__layer_1.png";
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean isFoil(ItemStack itemstack) {
        return true;
    }

}
