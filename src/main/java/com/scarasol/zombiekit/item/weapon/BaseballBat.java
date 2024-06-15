package com.scarasol.zombiekit.item.weapon;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BaseballBat extends SwordItem implements SweepWeapon{

    private final boolean studded;

    public BaseballBat(Tier tier, int damage, float speed, Properties properties, boolean studded) {
        super(tier, damage, speed, properties);
        this.studded = studded;
    }

    public boolean isStudded() {
        return studded;
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
        double multiplier = getTier() == Tiers.NETHERITE ? 1.8 : 1.2;
        target.setDeltaMovement(new Vec3((multiplier * attacker.getLookAngle().x), (multiplier * attacker.getLookAngle().y), (multiplier * attacker.getLookAngle().z)));
        if (studded)
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1, false, true));
    }
}
