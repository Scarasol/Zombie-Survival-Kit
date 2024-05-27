package com.scarasol.zombiekit.item.weapon;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class Rake extends HoeItem {

    public Rake(Tier tier, int attackDamage, float speed, Properties properties) {
        super(tier, attackDamage, speed, properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        if (equipmentSlot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getDefaultAttributeModifiers(equipmentSlot));
            double amplifier = getTier() == Tiers.NETHERITE ? 2 : 1;
            builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3"), "Rake Modifier", amplifier, AttributeModifier.Operation.ADDITION));
            builder.put(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier(UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA4"), "Rake Modifier", amplifier, AttributeModifier.Operation.ADDITION));
            return builder.build();
        }
        return super.getDefaultAttributeModifiers(equipmentSlot);
    }
}
