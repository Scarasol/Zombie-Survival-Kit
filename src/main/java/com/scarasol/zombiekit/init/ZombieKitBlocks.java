package com.scarasol.zombiekit.init;

import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.block.GasTankBlock;
import com.scarasol.zombiekit.block.ShortwaveRadioBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ZombieKitBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, ZombieKitMod.MODID);

    public static final RegistryObject<Block> SHORTWAVE_RADIO = REGISTRY.register("shortwave_radio", () -> new ShortwaveRadioBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 3.6f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> GAS_TANK = REGISTRY.register("gas_tank", () -> new GasTankBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 3.6f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));

}
