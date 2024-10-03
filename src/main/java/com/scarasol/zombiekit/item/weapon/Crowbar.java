package com.scarasol.zombiekit.item.weapon;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.sona.item.IRustItem;
import com.scarasol.zombiekit.config.CommonConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Crowbar extends SwordItem implements IRustItem {

    private Multimap<Attribute, AttributeModifier> weaponModifiers;

    public Crowbar(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }

    @Override
    public float getDamage() {
        double damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_CROWBAR_DAMAGE.get() : CommonConfig.CROWBAR_DAMAGE.get();
        return (float) (damage * 0.5);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        initModifier();
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.weaponModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    public void initModifier() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        double damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_CROWBAR_DAMAGE.get() : CommonConfig.CROWBAR_DAMAGE.get();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", damage - 1, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", CommonConfig.CROWBAR_SPEED.get() - 4, AttributeModifier.Operation.ADDITION));
        weaponModifiers = builder.build();
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceEntity) {
        int amplifier = getTier() == Tiers.NETHERITE ? 1 : 0;
        int time = getTier() == Tiers.NETHERITE ? 100 : 60;
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, time, amplifier, false, false));
        return super.hurtEnemy(itemstack, entity, sourceEntity);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("item.zombiekit.crowbar.description"));
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        if (toolAction == ToolActions.SWORD_SWEEP)
            return false;
        return super.canPerformAction(stack, toolAction);
    }
}
