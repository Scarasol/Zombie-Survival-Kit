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

public class BaseballBat extends SwordItem {

    private final boolean studded;

    public BaseballBat(Tier tier, int damage, float speed, Properties properties, boolean studded) {
        super(tier, damage, speed, properties);
        this.studded = studded;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceEntity) {
        double multiplier = getTier() == Tiers.NETHERITE ? 1.8 : 1.2;
        entity.setDeltaMovement(new Vec3((multiplier * sourceEntity.getLookAngle().x), (multiplier * sourceEntity.getLookAngle().y), (multiplier * sourceEntity.getLookAngle().z)));
        if (studded)
            entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1, false, true));
        return super.hurtEnemy(itemstack, entity, sourceEntity);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.baseball_bat.description").getString()));
    }

}
