package com.scarasol.zombiekit.item.armor;

import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {

    SKIING("skiing", 15, new int[]{2, 6, 5, 2}, 9, SoundEvents.ARMOR_EQUIP_LEATHER, 1f, 0f, () -> {
        return Ingredient.of(ZombieKitItems.CLOTH.get());
    }),
    TACTICAL("tactical", 33, new int[]{4, 7, 7, 5}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
        return Ingredient.of(ZombieKitItems.CLOTH.get());
    }),
    RIOT("riot", 35, new int[]{4, 7, 7, 5}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 3f, 0.1f, () -> {
        return Ingredient.of(ZombieKitItems.SPECIAL_STEEL_SHEET.get());
    }),
    BOMB("bomb", 40, new int[]{11, 15, 16, 13}, 10, SoundEvents.ARMOR_EQUIP_NETHERITE, 5f, 0.25f, () -> {
        return Ingredient.EMPTY;
    }),
    EXO("exo", 37, new int[]{4, 7, 8, 6}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.2f, () -> {
        return Ingredient.of(ZombieKitItems.BULLETPROOF_INSERT.get());
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private ModArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot equipmentSlot) {
        return HEALTH_PER_SLOT[equipmentSlot.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot equipmentSlot) {
        return this.slotProtections[equipmentSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
