package com.scarasol.zombiekit.init;

import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.item.weapon.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ZombieKitItems {

    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ZombieKitMod.MODID);

    public static final RegistryObject<Item> BASEBALL_BAT = REGISTRY.register("baseball_bat", () -> new BaseballBat(Tiers.WOOD, 8, -2.7f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT), false));
    public static final RegistryObject<Item> STUDDED_BASEBALL_BAT = REGISTRY.register("studded_baseball_bat", () -> new BaseballBat(Tiers.WOOD, 11, -2.7f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT), true));
    public static final RegistryObject<Item> NETHERITE_BASEBALL_BAT = REGISTRY.register("netherite_baseball_bat", () -> new BaseballBat(Tiers.NETHERITE, 10, -2.7f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT).fireResistant(), false));

    public static final RegistryObject<Item> CROWBAR = REGISTRY.register("crowbar", () -> new Crowbar(Tiers.IRON, 12, -2.4f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> NETHERITE_CROWBAR = REGISTRY.register("netherite_crowbar", () -> new Crowbar(Tiers.NETHERITE, 19, -2.4f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT).fireResistant()));

    public static final RegistryObject<Item> FIRE_AXE = REGISTRY.register("fire_axe", () -> new FireAxe(Tiers.IRON, 15f, -3.2f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> NETHERITE_FIRE_AXE = REGISTRY.register("netherite_fire_axe", () -> new FireAxe(Tiers.NETHERITE, 22f, -3.2f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT).fireResistant()));

    public static final RegistryObject<Item> KNIFE = REGISTRY.register("knife", () -> new Knife(Tiers.IRON, 6, -1.5f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> NETHERITE_KNIFE = REGISTRY.register("netherite_knife", () -> new Knife(Tiers.NETHERITE, 7, -1f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT).fireResistant()));
    public static final RegistryObject<Item> TRIANGULAR_THORN = REGISTRY.register("triangular_thorn", () -> new Knife(Tiers.NETHERITE, 10, -1f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT).fireResistant()));

    public static final RegistryObject<Item> MACHETE = REGISTRY.register("machete", () -> new Machete(Tiers.IRON, 9, -2.4f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> NETHERITE_MACHETE = REGISTRY.register("netherite_machete", () -> new Machete(Tiers.NETHERITE, 13, -2.4f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT).fireResistant()));

    public static final RegistryObject<Item> RAKE = REGISTRY.register("rake", () -> new Rake(Tiers.IRON, 7, -3f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> NETHERITE_RAKE = REGISTRY.register("netherite_rake", () -> new Rake(Tiers.NETHERITE, 10, -3f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT).fireResistant()));


    public static final RegistryObject<Item> LATEX = REGISTRY.register("latex", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> RUBBER = REGISTRY.register("rubber", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> FIBRE = REGISTRY.register("fibre", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> CLOTH = REGISTRY.register("cloth", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> QUARTZ_SAND = REGISTRY.register("quartz_sand", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> SPECIAL_CERAMICS = REGISTRY.register("special_ceramics", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> BULLETPROOF_INSERT = REGISTRY.register("bulletproof_insert", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> SPECIAL_STEEL_SHEET = REGISTRY.register("special_steel_sheet", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> CAMPHOR = REGISTRY.register("camphor", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> DEATH_BAT = REGISTRY.register("death_bat", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> SALTPETER = REGISTRY.register("saltpeter", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> CRUDE_NITRATE = REGISTRY.register("crude_nitrate", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> HYDROGEN_NITRATE = REGISTRY.register("hydrogen_nitrate", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> NITROCELLULOSE = REGISTRY.register("nitrocellulose", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> PLASTICS = REGISTRY.register("plastics", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> ALCOHOL = REGISTRY.register("alcohol", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> IRON_WIRE = REGISTRY.register("iron_wire", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> SMOKELESS_GUNPOWDER = REGISTRY.register("smokeless_gunpowder", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> SULFUR = REGISTRY.register("sulfur", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));


    private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }


}
