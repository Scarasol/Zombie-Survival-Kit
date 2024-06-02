package com.scarasol.zombiekit.event;


import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.block.ShortwaveRadioBlock;
import com.scarasol.zombiekit.item.armor.BombArmor;
import com.scarasol.zombiekit.item.armor.ExoArmor;
import com.scarasol.zombiekit.network.MapVariables;
import com.scarasol.zombiekit.network.NetworkHandler;
import com.scarasol.zombiekit.network.SavedDataSyncPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

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
        if (event.getFrom().getItem() instanceof ExoArmor || event.getTo().getItem() instanceof ExoArmor){
            ExoArmor.updateModifier(event.getEntityLiving());
        }
    }


    @SubscribeEvent
    public static void getAttack(LivingAttackEvent event){
        Entity entity = event.getSource().getDirectEntity();
        if (entity != null){
            ExoArmor.reactiveArmor(event.getEntityLiving(), entity);
        }
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        ShortwaveRadioBlock.loadRadioString(event.getWorld());
//        InjectorBlock.init();
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getPlayer().level.isClientSide()) {
            SavedData mapData = MapVariables.get(event.getPlayer().level);
            if (mapData != null)
                NetworkHandler.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()), new SavedDataSyncPacket(mapData));
        }
    }


    @SubscribeEvent
    public static void test(PlayerInteractEvent.RightClickItem event) {
        if (!event.getPlayer().level.isClientSide()) {
            ZombieKitMod.LOGGER.info(String.valueOf(event.getPlayer().getDeltaMovement().length()));
        }
    }





}
