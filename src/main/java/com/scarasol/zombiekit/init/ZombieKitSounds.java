package com.scarasol.zombiekit.init;

import com.scarasol.sona.SonaMod;
import com.scarasol.zombiekit.ZombieKitMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ZombieKitSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ZombieKitMod.MODID);

    public static final RegistryObject<SoundEvent> acid_spray = REGISTRY.register("acid_spray", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "acid_spray")));
    public static final RegistryObject<SoundEvent> turn_on = REGISTRY.register("turn_on", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "turn_on")));
    public static final RegistryObject<SoundEvent> radio_static = REGISTRY.register("radio_static", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "radio_static")));
    public static final RegistryObject<SoundEvent> radio_response = REGISTRY.register("radio_response", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "radio_response")));
    public static final RegistryObject<SoundEvent> radio_static_long = REGISTRY.register("radio_static_long", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "radio_static_long")));
    public static final RegistryObject<SoundEvent> flare_gun_fire = REGISTRY.register("flare_gun_fire", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "flare_gun_fire")));
    public static final RegistryObject<SoundEvent> konn_gara = REGISTRY.register("konn_gara", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "konn_gara")));
    public static final RegistryObject<SoundEvent> drone_idle = REGISTRY.register("drone_idle", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "drone_idle")));
    public static final RegistryObject<SoundEvent> drone_switch = REGISTRY.register("drone_switch", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "drone_switch")));
    public static final RegistryObject<SoundEvent> drone_crush = REGISTRY.register("drone_crush", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "drone_crush")));
    public static final RegistryObject<SoundEvent> heavy_machine_gun_fire = REGISTRY.register("heavy_machine_gun_fire", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "heavy_machine_gun_fire")));
    public static final RegistryObject<SoundEvent> heavy_machine_gun_overload = REGISTRY.register("heavy_machine_gun_overload", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "heavy_machine_gun_overload")));
    public static final RegistryObject<SoundEvent> heavy_machine_gun_trigger = REGISTRY.register("heavy_machine_gun_trigger", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "heavy_machine_gun_trigger")));
    public static final RegistryObject<SoundEvent> heavy_machine_gun_deploy = REGISTRY.register("heavy_machine_gun_deploy", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "heavy_machine_gun_deploy")));
    public static final RegistryObject<SoundEvent> chainsaw_idle = REGISTRY.register("chainsaw_idle", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "chainsaw_idle")));
    public static final RegistryObject<SoundEvent> chainsaw_start_failed = REGISTRY.register("chainsaw_start_failed", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "chainsaw_start_failed")));
    public static final RegistryObject<SoundEvent> chainsaw_attack = REGISTRY.register("chainsaw_attack", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "chainsaw_attack")));
    public static final RegistryObject<SoundEvent> exo_fly = REGISTRY.register("exo_fly", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "exo_fly")));
    public static final RegistryObject<SoundEvent> reactive_armor_ready = REGISTRY.register("reactive_armor_ready", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "reactive_armor_ready")));
    public static final RegistryObject<SoundEvent> reactive_armor_release = REGISTRY.register("reactive_armor_release", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "reactive_armor_release")));
    public static final RegistryObject<SoundEvent> mode_switch = REGISTRY.register("mode_switch", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "mode_switch")));
    public static final RegistryObject<SoundEvent> radar_activated = REGISTRY.register("radar_activated", () -> new SoundEvent(new ResourceLocation(ZombieKitMod.MODID, "radar_activated")));


}
