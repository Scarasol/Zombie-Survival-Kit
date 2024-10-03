package com.scarasol.zombiekit;

import com.mojang.logging.LogUtils;

import com.scarasol.zombiekit.config.CommonConfig;
import com.scarasol.zombiekit.init.*;
import com.scarasol.zombiekit.network.NetworkHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ZombieKitMod.MODID)
public class ZombieKitMod
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "zombiekit";


    public ZombieKitMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, "zombiekit-common.toml");
        // Register ourselves for server and other game events we are interested in
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ZombieKitSounds.REGISTRY.register(bus);
        ZombieKitBlocks.REGISTRY.register(bus);
        ZombieKitItems.REGISTRY.register(bus);
        ZombieKitEntities.REGISTRY.register(bus);
        ZombieKitBlockEntities.REGISTRY.register(bus);
        ZombieKitTabs.REGISTRY.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
        NetworkHandler.addNetworkMessage();
    }
}
