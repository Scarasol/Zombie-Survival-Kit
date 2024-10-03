package com.scarasol.zombiekit.init;

import com.scarasol.zombiekit.item.armor.ExoArmor;
import com.scarasol.zombiekit.network.ExoPacket;
import com.scarasol.zombiekit.network.NetworkHandler;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ZombieKitKeyMappings {
    public static final KeyMapping EXO_SNEAK_MODE = new KeyMapping("key.zombiekit.exo_sneak_mode", GLFW.GLFW_KEY_C, "key.categories.zombiekit");
    public static final KeyMapping EXO_COMBAT_MODE = new KeyMapping("key.zombiekit.exo_combat_mode", GLFW.GLFW_KEY_V, "key.categories.zombiekit");
    public static final KeyMapping EXO_REACTIVE_ARMOR = new KeyMapping("key.zombiekit.exo_reactive_armor", GLFW.GLFW_KEY_X, "key.categories.zombiekit");
    public static final KeyMapping EXO_RADAR = new KeyMapping("key.zombiekit.exo_radar", GLFW.GLFW_KEY_Z, "key.categories.zombiekit");


    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(EXO_SNEAK_MODE);
        event.register(EXO_REACTIVE_ARMOR);
        event.register(EXO_COMBAT_MODE);
        event.register(EXO_RADAR);
    }

    @Mod.EventBusSubscriber({Dist.CLIENT})
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onKeyInput(TickEvent.ClientTickEvent event) {
            if (Minecraft.getInstance().screen == null && ExoArmor.numberOfSuit(Minecraft.getInstance().player) == 4) {
                    if (EXO_SNEAK_MODE.consumeClick()) {
                        NetworkHandler.PACKET_HANDLER.sendToServer(new ExoPacket(1));
                    }else if (EXO_COMBAT_MODE.consumeClick()){
                        NetworkHandler.PACKET_HANDLER.sendToServer(new ExoPacket(2));
                    }else if (EXO_REACTIVE_ARMOR.consumeClick()){
                        NetworkHandler.PACKET_HANDLER.sendToServer(new ExoPacket(3));
                    }else if (EXO_RADAR.consumeClick()){
                        NetworkHandler.PACKET_HANDLER.sendToServer(new ExoPacket(4));
                    }
            }
        }
    }

}
