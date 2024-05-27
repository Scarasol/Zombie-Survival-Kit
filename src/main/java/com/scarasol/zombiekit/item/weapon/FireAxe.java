package com.scarasol.zombiekit.item.weapon;

import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.sona.item.IRustItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FireAxe extends AxeItem implements IRustItem {
    public FireAxe(Tier tier, float attackDamage, float speed, Properties properties) {
        super(tier, attackDamage, speed, properties);
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
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.fire_axe.description").getString()));
    }
}
