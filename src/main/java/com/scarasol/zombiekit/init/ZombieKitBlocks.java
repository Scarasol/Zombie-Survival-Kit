package com.scarasol.zombiekit.init;

import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.block.*;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ZombieKitBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, ZombieKitMod.MODID);

    public static final RegistryObject<Block> TRAP_COVER = REGISTRY.register("trap_cover", () -> new TrapCoverBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.AZALEA_LEAVES).strength(1f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> BARBED_WIRE = REGISTRY.register("barbed_wire", () -> new BarbedWireBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1f, 10f).noCollission().jumpFactor(0.5f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false), 0));
    public static final RegistryObject<Block> BARBED_WIRE_BROKEN = REGISTRY.register("barbed_wire_broken", () -> new BarbedWireBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1f, 10f).noCollission().jumpFactor(0.5f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false), 1));
    public static final RegistryObject<Block> BARBED_WIRE_EXTREMELY_BROKEN = REGISTRY.register("barbed_wire_extremly_broken", () -> new BarbedWireBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1f, 10f).noCollission().jumpFactor(0.5f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false), 2));
    public static final RegistryObject<Block> LANDMINE = REGISTRY.register("landmine", () -> new LandmineBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f).requiresCorrectToolForDrops().noCollission().noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape().offsetType(Block.OffsetType.XZ), new ArrayList<>()));
    public static final RegistryObject<Block> CHEMICAL_LANDMINE = REGISTRY.register("chemical_landmine", () -> new LandmineBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f).requiresCorrectToolForDrops().noCollission().noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape().offsetType(Block.OffsetType.XZ),
            Arrays.asList(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 140, 1, false, false),
                    new MobEffectInstance(MobEffects.WITHER, 100, 3, false, false))));
    public static final RegistryObject<Block> CHARGER = REGISTRY.register("charger", () -> new ChargerBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).lightLevel(ChargerBlock.getLightLevel(8)).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> ULTRA_WIDEBAND_RADAR = REGISTRY.register("ultra_wideband_radar", () -> new UltraWidebandRadarBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> INJECTOR = REGISTRY.register("injector", () -> new InjectorBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).sound(SoundType.STONE).strength(1f, 3.5f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> SHORTWAVE_RADIO = REGISTRY.register("shortwave_radio", () -> new ShortwaveRadioBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 3.6f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> GAS_TANK = REGISTRY.register("gas_tank", () -> new GasTankBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 3.6f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLARES_LIGHT = REGISTRY.register("flares_light", () -> new FlaresLightBlock(BlockBehaviour.Properties.of(Material.AIR, MaterialColor.NONE).sound(SoundType.STONE).strength(-1, 3600000).lightLevel(s -> 15).noCollission().noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> SALTPETER_CAULDRON = REGISTRY.register("saltpeter_cauldron", () -> new SaltpeterCauldronBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).randomTicks().strength(2f, 2f).requiresCorrectToolForDrops(), CauldronInteraction.WATER));
    public static final RegistryObject<Block> SPREAD_LIGHT = REGISTRY.register("spread_light", () -> new SpreadLight(BlockBehaviour.Properties.of(Material.AIR, MaterialColor.NONE).sound(SoundType.STONE).strength(-1, 3600000).lightLevel(s -> 15).noCollission().noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> SPREAD_LIGHT_FATHER = REGISTRY.register("spread_light_father", () -> new SpreadLightFather(BlockBehaviour.Properties.of(Material.AIR, MaterialColor.NONE).sound(SoundType.STONE).strength(-1, 3600000).lightLevel(s -> 15).noCollission().noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientSideHandler {

        @SubscribeEvent
        public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
            TrapCoverBlock.blockColorLoad(event);
        }

        @SubscribeEvent
        public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
            TrapCoverBlock.itemColorLoad(event);
        }

    }

}
