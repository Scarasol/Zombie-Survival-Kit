package com.scarasol.zombiekit.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ZombieKitSounds {
    public static Map<ResourceLocation, SoundEvent> REGISTRY = new HashMap<>();
    static {
        REGISTRY.put(new ResourceLocation("zombiekit", "acid_spray"), new SoundEvent(new ResourceLocation("zombiekit", "acid_spray")));
        REGISTRY.put(new ResourceLocation("zombiekit", "turn_on"), new SoundEvent(new ResourceLocation("zombiekit", "turn_on")));
        REGISTRY.put(new ResourceLocation("zombiekit", "radio_static"), new SoundEvent(new ResourceLocation("zombiekit", "radio_static")));
        REGISTRY.put(new ResourceLocation("zombiekit", "radio_response"), new SoundEvent(new ResourceLocation("zombiekit", "radio_response")));
        REGISTRY.put(new ResourceLocation("zombiekit", "radio_static_long"), new SoundEvent(new ResourceLocation("zombiekit", "radio_static_long")));
        REGISTRY.put(new ResourceLocation("zombiekit", "flare_gun_fire"), new SoundEvent(new ResourceLocation("zombiekit", "flare_gun_fire")));
        REGISTRY.put(new ResourceLocation("zombiekit", "konn_gara"), new SoundEvent(new ResourceLocation("zombiekit", "konn_gara")));
        REGISTRY.put(new ResourceLocation("zombiekit", "drone_idle"), new SoundEvent(new ResourceLocation("zombiekit", "drone_idle")));
        REGISTRY.put(new ResourceLocation("zombiekit", "drone_switch"), new SoundEvent(new ResourceLocation("zombiekit", "drone_switch")));
        REGISTRY.put(new ResourceLocation("zombiekit", "drone_crush"), new SoundEvent(new ResourceLocation("zombiekit", "drone_crush")));
        REGISTRY.put(new ResourceLocation("zombiekit", "heavy_machine_gun_fire"), new SoundEvent(new ResourceLocation("zombiekit", "heavy_machine_gun_fire")));
        REGISTRY.put(new ResourceLocation("zombiekit", "heavy_machine_gun_overload"), new SoundEvent(new ResourceLocation("zombiekit", "heavy_machine_gun_overload")));
        REGISTRY.put(new ResourceLocation("zombiekit", "heavy_machine_gun_trigger"), new SoundEvent(new ResourceLocation("zombiekit", "heavy_machine_gun_trigger")));
        REGISTRY.put(new ResourceLocation("zombiekit", "heavy_machine_gun_deploy"), new SoundEvent(new ResourceLocation("zombiekit", "heavy_machine_gun_deploy")));
        REGISTRY.put(new ResourceLocation("zombiekit", "chainsaw_idle"), new SoundEvent(new ResourceLocation("zombiekit", "chainsaw_idle")));
        REGISTRY.put(new ResourceLocation("zombiekit", "chainsaw_start_failed"), new SoundEvent(new ResourceLocation("zombiekit", "chainsaw_start_failed")));
        REGISTRY.put(new ResourceLocation("zombiekit", "chainsaw_attack"), new SoundEvent(new ResourceLocation("zombiekit", "chainsaw_attack")));
        REGISTRY.put(new ResourceLocation("zombiekit", "exo_fly"), new SoundEvent(new ResourceLocation("zombiekit", "exo_fly")));
        REGISTRY.put(new ResourceLocation("zombiekit", "reactive_armor_ready"), new SoundEvent(new ResourceLocation("zombiekit", "reactive_armor_ready")));
        REGISTRY.put(new ResourceLocation("zombiekit", "reactive_armor_release"), new SoundEvent(new ResourceLocation("zombiekit", "reactive_armor_release")));
        REGISTRY.put(new ResourceLocation("zombiekit", "mode_on"), new SoundEvent(new ResourceLocation("zombiekit", "mode_on")));
        REGISTRY.put(new ResourceLocation("zombiekit", "mode_off"), new SoundEvent(new ResourceLocation("zombiekit", "mode_off")));
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        for (Map.Entry<ResourceLocation, SoundEvent> sound : REGISTRY.entrySet())
            event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
    }

}
