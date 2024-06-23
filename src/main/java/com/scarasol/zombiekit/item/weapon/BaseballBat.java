package com.scarasol.zombiekit.item.weapon;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.scarasol.zombiekit.config.CommonConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BaseballBat extends SwordItem implements SweepWeapon{

    private final boolean studded;
    private Multimap<Attribute, AttributeModifier> weaponModifiers;

    public BaseballBat(Tier tier, int damage, float speed, Properties properties, boolean studded) {
        super(tier, damage, speed, properties);
        this.studded = studded;
    }

    public boolean isStudded() {
        return studded;
    }

    @Override
    public float getDamage() {
        double damage;
        if (isStudded()){
            damage = CommonConfig.STUDDED_BASEBALL_BAT_DAMAGE.get();
        }else {
            damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_BASEBALL_BAT_DAMAGE.get() : CommonConfig.BASEBALL_BAT_DAMAGE.get();
        }
        return (float) (damage * 0.5);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        initModifier();
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.weaponModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    public void initModifier() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        double damage;
        if (isStudded()){
            damage = CommonConfig.STUDDED_BASEBALL_BAT_DAMAGE.get();
        }else {
            damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_BASEBALL_BAT_DAMAGE.get() : CommonConfig.BASEBALL_BAT_DAMAGE.get();
        }
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", damage - 1, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", CommonConfig.BASEBALL_BAT_SPEED.get() - 4, AttributeModifier.Operation.ADDITION));
        weaponModifiers = builder.build();
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceEntity) {
        sweepEffect(entity, sourceEntity);
        return super.hurtEnemy(itemstack, entity, sourceEntity);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.baseball_bat.description").getString()));
    }

    @Override
    public void sweepEffect(LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player && CommonConfig.SWEEP.get()){
            target.hurt(DamageSource.playerAttack(player), (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE) * CommonConfig.SWEEP_MULTIPLIER.get()));
        }
        double multiplier = getTier() == Tiers.NETHERITE ? 1.8 : 1.2;
        target.setDeltaMovement(new Vec3((multiplier * attacker.getLookAngle().x), (multiplier * attacker.getLookAngle().y), (multiplier * attacker.getLookAngle().z)));
        if (studded)
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1, false, true));
    }
}
