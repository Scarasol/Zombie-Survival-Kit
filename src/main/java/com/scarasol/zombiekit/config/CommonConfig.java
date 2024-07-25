package com.scarasol.zombiekit.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Double> BASEBALL_BAT_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> STUDDED_BASEBALL_BAT_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> NETHERITE_BASEBALL_BAT_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> BASEBALL_BAT_SPEED;

    public static final ForgeConfigSpec.ConfigValue<Double> CHAINSAW_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHAINSAW_POWER;


    public static final ForgeConfigSpec.ConfigValue<Double> CROWBAR_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> NETHERITE_CROWBAR_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> CROWBAR_SPEED;

    public static final ForgeConfigSpec.ConfigValue<Double> FIRE_AXE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> NETHERITE_FIRE_AXE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> FIRE_AXE_SPEED;

    public static final ForgeConfigSpec.ConfigValue<Double> KNIFE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> NETHERITE_KNIFE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> TRIANGULAR_THORN_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> KNIFE_SPEED;
    public static final ForgeConfigSpec.ConfigValue<Double> NETHERITE_KNIFE_SPEED;
    public static final ForgeConfigSpec.ConfigValue<Double> ASSASSINATE_MULTIPLIER;

    public static final ForgeConfigSpec.ConfigValue<Double> MACHETE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> NETHERITE_MACHETE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> MACHETE_SPEED;

    public static final ForgeConfigSpec.ConfigValue<Double> RAKE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> NETHERITE_RAKE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> RAKE_SPEED;
    public static final ForgeConfigSpec.ConfigValue<Double> RAKE_RANGE_INCREASE;
    public static final ForgeConfigSpec.ConfigValue<Double> NETHERITE_RAKE_RANGE_INCREASE;

    public static final ForgeConfigSpec.ConfigValue<Double> WRENCH_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> WRENCH_COOLDOWN;

    public static final ForgeConfigSpec.ConfigValue<Boolean> SWEEP;
    public static final ForgeConfigSpec.ConfigValue<Double> SWEEP_MULTIPLIER;

    public static final ForgeConfigSpec.ConfigValue<Integer> BANDAGE_INJURY;
    public static final ForgeConfigSpec.ConfigValue<Integer> BANDAGE;

    public static final ForgeConfigSpec.ConfigValue<Boolean> LANDMINE_CHAIN;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHAIN_RANGE;

    public static final ForgeConfigSpec.ConfigValue<Integer> RADAR_RANGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> RADAR_POWER;

    public static final ForgeConfigSpec.ConfigValue<Double> BROKEN_CHANCE;

    public static final ForgeConfigSpec.ConfigValue<Integer> CHARGING_RATE;

    public static final ForgeConfigSpec.ConfigValue<Double> ILLAGER_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> ILLAGER_NUMBER;
    public static final ForgeConfigSpec.ConfigValue<Double> VINDICATOR_CHANCE;

    public static final ForgeConfigSpec.ConfigValue<Integer> HEAVY_MACHINE_GUN_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Double> ARMOR_PIERCING_RATE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> IGNORING_INVULNERABILITY;

    public static final ForgeConfigSpec.ConfigValue<Boolean> HIGH_PERFORMANCE_MODE;

    public static final ForgeConfigSpec.ConfigValue<Boolean> RAIDER_INDEPENDENCE;
    public static final ForgeConfigSpec.ConfigValue<Double> EQUIPMENT_INITIALIZATION;
    public static final ForgeConfigSpec.ConfigValue<Double> BOMB_ARMOR_INITIALIZATION;

    static {
        BUILDER.push("Weapon");

        BUILDER.push("BaseballBat");
        BASEBALL_BAT_DAMAGE = BUILDER.defineInRange("Baseball Bat Damage", 9.0, 1, 5000);
        STUDDED_BASEBALL_BAT_DAMAGE = BUILDER.defineInRange("Studded Baseball Bat Damage", 12.0, 1, 5000);
        NETHERITE_BASEBALL_BAT_DAMAGE = BUILDER.defineInRange("Netherite Baseball Bat Damage", 15.0, 1, 5000);
        BASEBALL_BAT_SPEED = BUILDER.defineInRange("Baseball Bat Speed", 1.3, 0.0, 8.0);
        BUILDER.pop();

        BUILDER.push("Chainsaw");
        CHAINSAW_DAMAGE = BUILDER.comment("Damage per tick")
                .defineInRange("Chainsaw Damage", 1.2, 0, 5000);
        CHAINSAW_POWER = BUILDER.comment("Ticks required to reduce the power of a chainsaw by 4%" +
                "\n1 sec = 20 ticks")
                .defineInRange("Chainsaw Power", 480, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("Crowbar");
        CROWBAR_DAMAGE = BUILDER.defineInRange("Crowbar Damage", 15.0, 1, 5000);
        NETHERITE_CROWBAR_DAMAGE = BUILDER.defineInRange("Netherite Crowbar Damage", 24.0, 1, 5000);
        CROWBAR_SPEED = BUILDER.defineInRange("Crowbar Speed", 1.6, 0.0, 8.0);
        BUILDER.pop();

        BUILDER.push("FireAxe");
        FIRE_AXE_DAMAGE = BUILDER.defineInRange("FireAxe Damage", 18.0, 1, 5000);
        NETHERITE_FIRE_AXE_DAMAGE = BUILDER.defineInRange("Netherite FireAxe Damage", 27.0, 1, 5000);
        FIRE_AXE_SPEED = BUILDER.defineInRange("FireAxe Speed", 0.8, 0.0, 8.0);
        BUILDER.pop();

        BUILDER.push("Knife");
        KNIFE_DAMAGE = BUILDER.defineInRange("Knife Damage", 9.0, 1, 5000);
        NETHERITE_KNIFE_DAMAGE = BUILDER.defineInRange("Netherite Knife Damage", 12.0, 1, 5000);
        TRIANGULAR_THORN_DAMAGE = BUILDER.defineInRange("Triangular Thorn Damage", 15.0, 1, 5000);
        KNIFE_SPEED = BUILDER.defineInRange("Knife Speed", 2.5, 0.0, 8.0);
        NETHERITE_KNIFE_SPEED = BUILDER.defineInRange("Netherite Knife and Triangular Thorn Speed", 3.0, 0.0, 8.0);
        ASSASSINATE_MULTIPLIER = BUILDER.defineInRange("Assassinate Multiplier", 2.5, 1, 5000);
        BUILDER.pop();

        BUILDER.push("Machete");
        MACHETE_DAMAGE = BUILDER.defineInRange("Machete Damage", 12.0, 1, 5000);
        NETHERITE_MACHETE_DAMAGE = BUILDER.defineInRange("Netherite Machete Damage", 18.0, 1, 5000);
        MACHETE_SPEED = BUILDER.defineInRange("Machete Speed", 1.6, 0.0, 8.0);
        BUILDER.pop();

        BUILDER.push("Rake");
        RAKE_DAMAGE = BUILDER.defineInRange("Rake Damage", 10.0, 1, 5000);
        NETHERITE_RAKE_DAMAGE = BUILDER.defineInRange("Netherite Rake Damage", 15.0, 1, 5000);
        RAKE_SPEED = BUILDER.defineInRange("Rake Speed", 1.0, 0.0, 8.0);
        RAKE_RANGE_INCREASE = BUILDER.comment("Increased attack distance for rakes.")
                .defineInRange("Rake Attack Range", 1.0, 0.0, 5000);
        NETHERITE_RAKE_RANGE_INCREASE = BUILDER.comment("Increased attack distance for netherite rakes.")
                .defineInRange("Netherite Rake Attack Range", 2.0, 0.0, 5000);
        BUILDER.pop();

        BUILDER.push("Wrench");
        WRENCH_DAMAGE = BUILDER.defineInRange("Wrench Damage", 9.0, 1, 5000);
        WRENCH_COOLDOWN = BUILDER.comment("Cooldown on wrench power-up throws.")
                .defineInRange("Wrench Cooldown (Second)", 7, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("Misc");
        SWEEP = BUILDER.comment("Whether or not the sweeping attacks of baseball bats and machetes deal damage based on their attack damage despite not having the Sweeping Edge enchantment.")
                .define("Sweep Improvement", true);
        SWEEP_MULTIPLIER = BUILDER.comment("Sweeping attacks without enchantment deal a percentage of the weapon's attack damage.")
                .defineInRange("Sweep Multiplier", 0.5, 0.0, 1.0);
        BUILDER.pop();

        BUILDER.pop();


        BUILDER.push("Items");
        BANDAGE_INJURY = BUILDER.comment("Injury value a bandage will recover.")
                .defineInRange("Bandage Heal", 10, 1, 100);
        BANDAGE = BUILDER.comment("Bandage value a bandage will recover.")
                .defineInRange("Dress Wound Value", 30, 1, 50);
        BUILDER.pop();


        BUILDER.push("Blocks");

        BUILDER.push("Landmine & GasTank");
        LANDMINE_CHAIN = BUILDER.comment("Whether landmines and gas tanks explode in a chain.")
                .define("Landmine Chain", true);
        CHAIN_RANGE = BUILDER.comment("Range of chain explosions.")
                .defineInRange("Chain Range", 2, 1, 5);
        BUILDER.pop();

        BUILDER.push("Ultra Wide Band Radar");
        RADAR_RANGE = BUILDER.defineInRange("Radar Detection Range", 48, 2, 128);
        RADAR_POWER = BUILDER.comment("When the radar is running after how many ticks the power consumption is 1%." +
                "\n1 sec = 20 ticks")
                .defineInRange("Radar Power", 60, 1, 5000);
        BUILDER.pop();

        BUILDER.push("Barbed Wire");
        BROKEN_CHANCE = BUILDER.comment("The probability that a barbed wire fence would be broken every tick when entities is trapped in it." +
                "\n1 sec = 20 ticks")
                .defineInRange("Broken Probability", 0.0005, 0.0, 1.0);
        BUILDER.pop();

        BUILDER.push("Charger");
        CHARGING_RATE = BUILDER.comment("The charger will charge the battery by 1% after how many ticks." +
                "\n1 sec = 20 ticks")
                .defineInRange("Charging Rate", 120, 1, 5000);
        BUILDER.pop();

        BUILDER.push("Shortwave Radio");
        BUILDER.comment("Survivors recruited by shortwave radio are randomly selected in the forge:survivors tag.");
        ILLAGER_CHANCE = BUILDER.comment("Probability of spawning illagers when shortwave radio spawns survivors.")
                .defineInRange("Spawning Illager Probability", 0.05, 0.0, 1.0);
        ILLAGER_NUMBER = BUILDER.comment("The charger will charge the battery by 1% after how many ticks.")
                .defineInRange("Number of illagers", 8, 1, 20);
        VINDICATOR_CHANCE = BUILDER.comment("Probability of spawning vindicator.")
                .defineInRange("Spawning Vindicator Probability", 0.3, 0.0, 1.0);
        BUILDER.pop();

        BUILDER.pop();


        BUILDER.push("Entities");

        BUILDER.push("Heavy Machine Gun");
        BUILDER.comment("Entities tagged with forge:machine_gunner will use heavy machine guns.");
        HEAVY_MACHINE_GUN_DAMAGE = BUILDER.comment("Heavy machine gun bullet damage.")
                .defineInRange("Damage", 18, 1, 5000);
        ARMOR_PIERCING_RATE = BUILDER.comment("How much bullet damage ignores armor.")
                .defineInRange("Armor Piercing Rate", 0.5, 0.0, 1.0);
        IGNORING_INVULNERABILITY = BUILDER.comment("Whether bullet damage ignores damage invulnerability time.")
                .define("Ignoring Invulnerability", true);
        BUILDER.pop();

        BUILDER.push("UV Lamp");
        HIGH_PERFORMANCE_MODE = BUILDER.comment("UV lamps in this mode will ignite undead within range that are not resistant to UV light.")
                .define("High Performance Mode", false);
        BUILDER.pop();

        BUILDER.push("Misc");
        RAIDER_INDEPENDENCE = BUILDER.comment("Whether Illagers will be enemy to other monsters.")
                .define("Illager Independence", true);
        EQUIPMENT_INITIALIZATION = BUILDER.comment("Probability that a zombie is equipped with weapons from this module at spawn.")
                .defineInRange("Weapon Chance", 0.05, 0.0, 1.0);
        BOMB_ARMOR_INITIALIZATION = BUILDER.comment("Probability that a zombie is equipped with bomb suit from this module at spawn.")
                .defineInRange("Bomb Chance", 0.02, 0.0, 1.0);
        BUILDER.pop();

        BUILDER.pop();



        SPEC = BUILDER.build();
    }
}
