package com.scarasol.zombiekit.event;


import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.block.InjectorBlock;
import com.scarasol.zombiekit.block.ShortwaveRadioBlock;
import com.scarasol.zombiekit.entity.mechanics.HeavyMachineGunEntity;
import com.scarasol.zombiekit.init.ZombieKitItems;
import com.scarasol.zombiekit.item.armor.BombArmor;
import com.scarasol.zombiekit.item.armor.ExoArmor;
import com.scarasol.zombiekit.network.MapVariables;
import com.scarasol.zombiekit.network.NetworkHandler;
import com.scarasol.zombiekit.network.SavedDataSyncPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
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
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

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
        InjectorBlock.init();
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
    public static void rightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getPlayer();
        Entity entity = player.getVehicle();
        if (entity instanceof HeavyMachineGunEntity heavyMachineGunEntity && !(player.hasEffect(SonaMobEffects.STUN.get()) || (player.hasEffect(SonaMobEffects.SLIMINESS.get()) && player.hasEffect(SonaMobEffects.FROST.get())))){
            boolean flag1 = !player.getItemInHand(InteractionHand.MAIN_HAND).is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get());
            boolean flag2 = !player.getItemInHand(InteractionHand.OFF_HAND).is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get());
            ItemStack itemStack = player.getItemInHand(event.getHand());
            if (itemStack.is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get()) && (event.getHand() == InteractionHand.MAIN_HAND || flag1)){
                heavyMachineGunEntity.fire();
                player.addEffect(new MobEffectInstance(SonaMobEffects.EXPOSURE.get(), 20, 3, false, false));
                if (!player.getAbilities().instabuild && !heavyMachineGunEntity.getOverload()){
                    itemStack.setCount(itemStack.getCount() - 1);
                }
            }else if (flag1 && flag2 && event.getHand() == InteractionHand.MAIN_HAND) {
                heavyMachineGunEntity.getLevel().playSound(null, heavyMachineGunEntity.getX(), heavyMachineGunEntity.getY(), heavyMachineGunEntity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:heavy_machine_gun_trigger")), SoundSource.BLOCKS, 1, 1);
            }

        }
    }

    @SubscribeEvent
    public static void rightClickEmpty(PlayerInteractEvent.RightClickEmpty event) {
        Player player = event.getPlayer();
        Entity entity = player.getVehicle();
        if (entity instanceof HeavyMachineGunEntity heavyMachineGunEntity && !(player.hasEffect(SonaMobEffects.STUN.get()) || (player.hasEffect(SonaMobEffects.SLIMINESS.get()) && player.hasEffect(SonaMobEffects.FROST.get())))){
            boolean flag1 = !player.getItemInHand(InteractionHand.MAIN_HAND).is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get());
            boolean flag2 = !player.getItemInHand(InteractionHand.OFF_HAND).is(ZombieKitItems.HEAVY_MACHINE_GUN_AMMO.get());
            if (flag1 && flag2 && event.getHand() == InteractionHand.MAIN_HAND){
                heavyMachineGunEntity.getLevel().playSound(player, heavyMachineGunEntity.getX(), heavyMachineGunEntity.getY(), heavyMachineGunEntity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:heavy_machine_gun_trigger")), SoundSource.BLOCKS, 1, 1);
            }
        }
    }

    @SubscribeEvent
    public static void KnockbackEvent(LivingKnockBackEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        if (livingEntity.getPersistentData().getBoolean("cancelKnockback")){
            livingEntity.getPersistentData().putBoolean("cancelKnockback", false);
            event.setStrength(event.getStrength() * 0.1f);
        }
    }

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingUpdateEvent event) {
        if (event != null && event.getEntity() != null) {
            if (event.getEntity() instanceof HeavyMachineGunEntity syncable) {
                String animation = syncable.getSyncedAnimation();
                if (!animation.equals("undefined")) {
                    syncable.setAnimation("undefined");
                    syncable.animationprocedure = animation;
                }
            }
        }
    }

}
