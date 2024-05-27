package com.scarasol.zombiekit.item.weapon;

import com.scarasol.sona.init.SonaMobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Crowbar extends SwordItem {

    public Crowbar(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
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
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.crowbar.description").getString()));
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        if (toolAction == ToolActions.SWORD_SWEEP)
            return false;
        return super.canPerformAction(stack, toolAction);
    }
}
