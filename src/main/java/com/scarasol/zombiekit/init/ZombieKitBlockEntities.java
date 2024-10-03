package com.scarasol.zombiekit.init;

import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.block.entity.InjectorBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ZombieKitBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ZombieKitMod.MODID);
    public static final RegistryObject<BlockEntityType<?>> INJECTOR = register("injector", ZombieKitBlocks.INJECTOR, InjectorBlockEntity::new);


    private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
        return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
    }

}
