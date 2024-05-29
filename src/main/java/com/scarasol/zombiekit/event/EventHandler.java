package com.scarasol.zombiekit.event;


import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.item.armor.BombArmor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;


@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void livingEquipmentChangeEvent(LivingEquipmentChangeEvent event){
        if (event.getSlot() == EquipmentSlot.MAINHAND || event.getSlot() == EquipmentSlot.OFFHAND)
            return;
        if (event.getFrom().getItem() instanceof BombArmor || event.getTo().getItem() instanceof BombArmor){
            BombArmor.updateModifier(event.getEntityLiving());
        }
    }


    @SubscribeEvent
    public static void fly(PlayerInteractEvent.EntityInteract event){
        if (event.getPlayer().getLevel() instanceof ServerLevel serverLevel && event.getTarget() instanceof LivingEntity livingEntity){
            FireworkRocketEntity rocketEntity = new FireworkRocketEntity(serverLevel, event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND), livingEntity);
            serverLevel.addFreshEntity(rocketEntity);
        }
    }

}
