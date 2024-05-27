package com.scarasol.zombiekit;

import com.mojang.logging.LogUtils;
import com.scarasol.zombiekit.init.ZombieKitItems;
import com.scarasol.zombiekit.init.ZombieKitTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ZombieKitMod.MODID)
public class ZombieKitMod
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "zombiekit";


    public ZombieKitMod()
    {
        ZombieKitTabs.load();
        // Register ourselves for server and other game events we are interested in
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ZombieKitItems.REGISTRY.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
