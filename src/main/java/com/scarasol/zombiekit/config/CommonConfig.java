package com.scarasol.zombiekit.config;

import net.minecraftforge.common.ForgeConfigSpec;

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
                .defineInRange("Chainsaw Power", 480, 1, 5000);
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
                .defineInRange("Wrench Cooldown (Second)", 7, 1, 5000);
        BUILDER.pop();

        BUILDER.push("Misc");
        SWEEP = BUILDER.comment("Whether or not the sweeping attacks of baseball bats and machetes deal damage based on their attack damage despite not having the Sweeping Edge enchantment.")
                .define("Sweep Improvement", true);
        SWEEP_MULTIPLIER = BUILDER.comment("Sweeping attacks deal a percentage of the weapon's attack damage.")
                .defineInRange("Sweep Multiplier", 0.5, 0.0, 1.0);
        BUILDER.pop();

        BUILDER.pop();



        SPEC = BUILDER.build();
    }
}
