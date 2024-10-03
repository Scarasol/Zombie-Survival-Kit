package com.scarasol.zombiekit.item.weapon;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.sona.item.IRustItem;
import com.scarasol.zombiekit.config.CommonConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FireAxe extends AxeItem implements IRustItem {

    private Multimap<Attribute, AttributeModifier> weaponModifiers;

    public FireAxe(Tier tier, float attackDamage, float speed, Properties properties) {
        super(tier, attackDamage, speed, properties);
    }

    @Override
    public float getAttackDamage() {
        double damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_FIRE_AXE_DAMAGE.get() : CommonConfig.FIRE_AXE_DAMAGE.get();
        return (float) (damage * 0.5);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        initModifier();
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.weaponModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    public void initModifier() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        double damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_FIRE_AXE_DAMAGE.get() : CommonConfig.FIRE_AXE_DAMAGE.get();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", damage - 1, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", CommonConfig.FIRE_AXE_SPEED.get() - 4, AttributeModifier.Operation.ADDITION));
        weaponModifiers = builder.build();
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceEntity) {
        int addition = getTier() == Tiers.NETHERITE ? 2 : 1;
        int time = getTier() == Tiers.NETHERITE ? 100 : 60;
        if (entity.hasEffect(SonaMobEffects.FRAGILITY.get())) {
            entity.addEffect(new MobEffectInstance(SonaMobEffects.FRAGILITY.get(), time, entity.getEffect(SonaMobEffects.FRAGILITY.get()).getAmplifier() + addition, false, false));
        }else {
            entity.addEffect(new MobEffectInstance(SonaMobEffects.FRAGILITY.get(), time, addition - 1, false, false));
        }
        return super.hurtEnemy(itemstack, entity, sourceEntity);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("item.zombiekit.fire_axe.description"));
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker){
        return true;
    }
}
