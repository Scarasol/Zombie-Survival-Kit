package com.scarasol.zombiekit.init;

import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.item.HeavyMachineGunAmmo;
import com.scarasol.zombiekit.item.PocketRadio;
import com.scarasol.zombiekit.item.armor.*;
import com.scarasol.zombiekit.item.medical.*;
import com.scarasol.zombiekit.item.projectile.BileJar;
import com.scarasol.zombiekit.item.projectile.Firecracker;
import com.scarasol.zombiekit.item.projectile.MolotovCocktail;
import com.scarasol.zombiekit.item.projectile.PotionJar;
import com.scarasol.zombiekit.item.weapon.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ZombieKitItems {

    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ZombieKitMod.MODID);

    public static final RegistryObject<Item> SKIING_HELMET = REGISTRY.register("skiing_helmet", () -> new SkiingArmor(ModArmorMaterial.SKIING, EquipmentSlot.HEAD, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));
    public static final RegistryObject<Item> SKIING_CHESTPLATE = REGISTRY.register("skiing_chestplate", () -> new SkiingArmor(ModArmorMaterial.SKIING, EquipmentSlot.CHEST, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));
    public static final RegistryObject<Item> SKIING_LEGGINGS = REGISTRY.register("skiing_leggings", () -> new SkiingArmor(ModArmorMaterial.SKIING, EquipmentSlot.LEGS, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));
    public static final RegistryObject<Item> SKIING_BOOTS = REGISTRY.register("skiing_boots", () -> new SkiingArmor(ModArmorMaterial.SKIING, EquipmentSlot.FEET, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));

    public static final RegistryObject<Item> STANDARD_TACTICAL_HELMET = REGISTRY.register("standard_tactical_helmet", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.HEAD, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 0));
    public static final RegistryObject<Item> STANDARD_TACTICAL_CHESTPLATE = REGISTRY.register("standard_tactical_chestplate", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.CHEST, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 0));
    public static final RegistryObject<Item> STANDARD_TACTICAL_LEGGINGS = REGISTRY.register("standard_tactical_leggings", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.LEGS, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 0));
    public static final RegistryObject<Item> STANDARD_TACTICAL_BOOTS = REGISTRY.register("standard_tactical_boots", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.FEET, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 0));

    public static final RegistryObject<Item> STANDARD_RIOT_HELMET = REGISTRY.register("standard_riot_helmet", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.HEAD, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 0));
    public static final RegistryObject<Item> STANDARD_RIOT_CHESTPLATE = REGISTRY.register("standard_riot_chestplate", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.CHEST, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 0));
    public static final RegistryObject<Item> STANDARD_RIOT_LEGGINGS = REGISTRY.register("standard_riot_leggings", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.LEGS, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 0));
    public static final RegistryObject<Item> STANDARD_RIOT_BOOTS = REGISTRY.register("standard_riot_boots", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.FEET, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 0));

    public static final RegistryObject<Item> DESERT_TACTICAL_HELMET = REGISTRY.register("desert_tactical_helmet", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.HEAD, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 1));
    public static final RegistryObject<Item> DESERT_TACTICAL_CHESTPLATE = REGISTRY.register("desert_tactical_chestplate", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.CHEST, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 1));
    public static final RegistryObject<Item> DESERT_TACTICAL_LEGGINGS = REGISTRY.register("desert_tactical_leggings", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.LEGS, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 1));
    public static final RegistryObject<Item> DESERT_TACTICAL_BOOTS = REGISTRY.register("desert_tactical_boots", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.FEET, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 1));

    public static final RegistryObject<Item> DESERT_RIOT_HELMET = REGISTRY.register("desert_riot_helmet", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.HEAD, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 1));
    public static final RegistryObject<Item> DESERT_RIOT_CHESTPLATE = REGISTRY.register("desert_riot_chestplate", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.CHEST, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 1));
    public static final RegistryObject<Item> DESERT_RIOT_LEGGINGS = REGISTRY.register("desert_riot_leggings", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.LEGS, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 1));
    public static final RegistryObject<Item> DESERT_RIOT_BOOTS = REGISTRY.register("desert_riot_boots", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.FEET, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 1));

    public static final RegistryObject<Item> FOREST_TACTICAL_HELMET = REGISTRY.register("forest_tactical_helmet", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.HEAD, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 2));
    public static final RegistryObject<Item> FOREST_TACTICAL_CHESTPLATE = REGISTRY.register("forest_tactical_chestplate", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.CHEST, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 2));
    public static final RegistryObject<Item> FOREST_TACTICAL_LEGGINGS = REGISTRY.register("forest_tactical_leggings", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.LEGS, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 2));
    public static final RegistryObject<Item> FOREST_TACTICAL_BOOTS = REGISTRY.register("forest_tactical_boots", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.FEET, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 2));

    public static final RegistryObject<Item> FOREST_RIOT_HELMET = REGISTRY.register("forest_riot_helmet", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.HEAD, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 2));
    public static final RegistryObject<Item> FOREST_RIOT_CHESTPLATE = REGISTRY.register("forest_riot_chestplate", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.CHEST, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 2));
    public static final RegistryObject<Item> FOREST_RIOT_LEGGINGS = REGISTRY.register("forest_riot_leggings", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.LEGS, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 2));
    public static final RegistryObject<Item> FOREST_RIOT_BOOTS = REGISTRY.register("forest_riot_boots", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.FEET, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 2));

    public static final RegistryObject<Item> SNOW_TACTICAL_HELMET = REGISTRY.register("snow_tactical_helmet", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.HEAD, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 3));
    public static final RegistryObject<Item> SNOW_TACTICAL_CHESTPLATE = REGISTRY.register("snow_tactical_chestplate", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.CHEST, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 3));
    public static final RegistryObject<Item> SNOW_TACTICAL_LEGGINGS = REGISTRY.register("snow_tactical_leggings", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.LEGS, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 3));
    public static final RegistryObject<Item> SNOW_TACTICAL_BOOTS = REGISTRY.register("snow_tactical_boots", () -> new TacticalArmor(ModArmorMaterial.TACTICAL, EquipmentSlot.FEET, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 3));

    public static final RegistryObject<Item> SNOW_RIOT_HELMET = REGISTRY.register("snow_riot_helmet", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.HEAD, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 3));
    public static final RegistryObject<Item> SNOW_RIOT_CHESTPLATE = REGISTRY.register("snow_riot_chestplate", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.CHEST, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 3));
    public static final RegistryObject<Item> SNOW_RIOT_LEGGINGS = REGISTRY.register("snow_riot_leggings", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.LEGS, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 3));
    public static final RegistryObject<Item> SNOW_RIOT_BOOTS = REGISTRY.register("snow_riot_boots", () -> new RiotArmor(ModArmorMaterial.RIOT, EquipmentSlot.FEET, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), 3));

    public static final RegistryObject<Item> BOMB_HELMET = REGISTRY.register("bomb_helmet", () -> new BombArmor(ModArmorMaterial.BOMB, EquipmentSlot.HEAD, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));
    public static final RegistryObject<Item> BOMB_CHESTPLATE = REGISTRY.register("bomb_chestplate", () -> new BombArmor(ModArmorMaterial.BOMB, EquipmentSlot.CHEST, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));
    public static final RegistryObject<Item> BOMB_LEGGINGS = REGISTRY.register("bomb_leggings", () -> new BombArmor(ModArmorMaterial.BOMB, EquipmentSlot.LEGS, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));
    public static final RegistryObject<Item> BOMB_BOOTS = REGISTRY.register("bomb_boots", () -> new BombArmor(ModArmorMaterial.BOMB, EquipmentSlot.FEET, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));

    public static final RegistryObject<Item> EXO_HELMET = REGISTRY.register("exo_helmet", () -> new ExoArmor(ModArmorMaterial.EXO, EquipmentSlot.HEAD, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).fireResistant()));
    public static final RegistryObject<Item> EXO_CHESTPLATE = REGISTRY.register("exo_chestplate", () -> new ExoArmor(ModArmorMaterial.EXO, EquipmentSlot.CHEST, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).fireResistant()));
    public static final RegistryObject<Item> EXO_LEGGINGS = REGISTRY.register("exo_leggings", () -> new ExoArmor(ModArmorMaterial.EXO, EquipmentSlot.LEGS, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).fireResistant()));
    public static final RegistryObject<Item> EXO_BOOTS = REGISTRY.register("exo_boots", () -> new ExoArmor(ModArmorMaterial.EXO, EquipmentSlot.FEET, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).fireResistant()));


    public static final RegistryObject<Item> BASEBALL_BAT = REGISTRY.register("baseball_bat", () -> new BaseballBat(Tiers.WOOD, 8, -2.7f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), false));
    public static final RegistryObject<Item> STUDDED_BASEBALL_BAT = REGISTRY.register("studded_baseball_bat", () -> new BaseballBat(Tiers.WOOD, 11, -2.7f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT), true));
    public static final RegistryObject<Item> NETHERITE_BASEBALL_BAT = REGISTRY.register("netherite_baseball_bat", () -> new BaseballBat(Tiers.NETHERITE, 10, -2.7f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).fireResistant(), false));

    public static final RegistryObject<Item> CROWBAR = REGISTRY.register("crowbar", () -> new Crowbar(Tiers.IRON, 12, -2.4f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));
    public static final RegistryObject<Item> NETHERITE_CROWBAR = REGISTRY.register("netherite_crowbar", () -> new Crowbar(Tiers.NETHERITE, 19, -2.4f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).fireResistant()));

    public static final RegistryObject<Item> FIRE_AXE = REGISTRY.register("fire_axe", () -> new FireAxe(Tiers.IRON, 15f, -3.2f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));
    public static final RegistryObject<Item> NETHERITE_FIRE_AXE = REGISTRY.register("netherite_fire_axe", () -> new FireAxe(Tiers.NETHERITE, 22f, -3.2f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).fireResistant()));

    public static final RegistryObject<Item> KNIFE = REGISTRY.register("knife", () -> new Knife(Tiers.IRON, 6, -1.5f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));
    public static final RegistryObject<Item> NETHERITE_KNIFE = REGISTRY.register("netherite_knife", () -> new Knife(Tiers.NETHERITE, 7, -1f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).fireResistant()));
    public static final RegistryObject<Item> TRIANGULAR_THORN = REGISTRY.register("triangular_thorn", () -> new Knife(Tiers.NETHERITE, 10, -1f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).fireResistant()));

    public static final RegistryObject<Item> MACHETE = REGISTRY.register("machete", () -> new Machete(Tiers.IRON, 9, -2.4f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));
    public static final RegistryObject<Item> NETHERITE_MACHETE = REGISTRY.register("netherite_machete", () -> new Machete(Tiers.NETHERITE, 13, -2.4f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).fireResistant()));

    public static final RegistryObject<Item> RAKE = REGISTRY.register("rake", () -> new Rake(Tiers.IRON, 7, -3f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT)));
    public static final RegistryObject<Item> NETHERITE_RAKE = REGISTRY.register("netherite_rake", () -> new Rake(Tiers.NETHERITE, 10, -3f, new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).fireResistant()));

    public static final RegistryObject<Item> WRENCH = REGISTRY.register("wrench", () -> new Wrench(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).stacksTo(4)));


    public static final RegistryObject<Item> MOLOTOV_COCKTAIL = REGISTRY.register("molotov_cocktail", () -> new MolotovCocktail(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).stacksTo(16)));
    public static final RegistryObject<Item> POTION_JAR = REGISTRY.register("potion_jar", () -> new PotionJar(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).stacksTo(16)));
    public static final RegistryObject<Item> BILE_JAR = REGISTRY.register("bile_jar", () -> new BileJar(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).stacksTo(16)));
    public static final RegistryObject<Item> FIRECRACKER = REGISTRY.register("firecracker", () -> new Firecracker(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_COMBAT).stacksTo(16)));


    public static final RegistryObject<Item> BANDAGE = REGISTRY.register("bandage", () -> new Bandage(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_TOOL).stacksTo(16)));
    public static final RegistryObject<Item> PAINKILLER = REGISTRY.register("painkiller", () -> new Painkiller(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_TOOL).stacksTo(16)));
    public static final RegistryObject<Item> MEDICAL_KIT = REGISTRY.register("medical_kit", () -> new MedicalKit(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_TOOL)));
    public static final RegistryObject<Item> SUSPICIOUS_DRUG = REGISTRY.register("suspicious_drug", () -> new SuspiciousDrug(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_TOOL).stacksTo(16)));
    public static final RegistryObject<Item> MIRACLE = REGISTRY.register("miracle", () -> new Miracle(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_TOOL).stacksTo(16).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> HEAVY_MACHINE_GUN_AMMO = REGISTRY.register("heavy_machine_gun_ammo", () -> new HeavyMachineGunAmmo(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_TOOL)));

    public static final RegistryObject<Item> POCKET_RADIO = REGISTRY.register("pocket_radio", () -> new PocketRadio(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT_TOOL).stacksTo(1)));
    public static final RegistryObject<Item> SHORTWAVE_RADIO = block(ZombieKitBlocks.SHORTWAVE_RADIO, ZombieKitTabs.TAB_ZOMBIEKIT_TOOL);

    public static final RegistryObject<Item> GAS_TANK = block(ZombieKitBlocks.GAS_TANK, ZombieKitTabs.TAB_ZOMBIEKIT_TOOL);


    public static final RegistryObject<Item> DESERT_CAMOUFLAGE_DYE = REGISTRY.register("desert_camouflage_dye", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> FOREST_CAMOUFLAGE_DYE = REGISTRY.register("forest_camouflage_dye", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> SNOW_CAMOUFLAGE_DYE = REGISTRY.register("snow_camouflage_dye", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT).rarity(Rarity.RARE)));

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

    public static final RegistryObject<Item> DRONE_COMPONENTS = REGISTRY.register("drone_components", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));
    public static final RegistryObject<Item> MACHINE_GUN_COMPONENTS = REGISTRY.register("machine_gun_components", () -> new Item(new Item.Properties().tab(ZombieKitTabs.TAB_ZOMBIEKIT)));


    private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }


}
