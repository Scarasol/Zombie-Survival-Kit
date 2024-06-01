package com.scarasol.zombiekit.item.medical;

import com.scarasol.sona.accessor.ILivingEntityAccessor;
import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.sona.manager.InfectionManager;
import com.scarasol.sona.manager.InjuryManager;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Miracle extends Item {
    public Miracle(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        entity.startUsingItem(hand);
        return new InteractionResultHolder(InteractionResult.SUCCESS, entity.getItemInHand(hand));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 50;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        if (world.isClientSide())
            return super.finishUsingItem(itemstack, world, entity);
        removeEffects(entity);
        addEffects(entity);
        if (CommonConfig.INFECTION_OPEN.get() && entity instanceof ILivingEntityAccessor livingEntityAccessor){
            InfectionManager.addInfection(livingEntityAccessor, -100);
        }
        if (!(entity instanceof Player player) || !player.isCreative()){
            itemstack.shrink(1);
        }
        return super.finishUsingItem(itemstack, world, entity);
    }

    public void removeEffects(LivingEntity livingEntity){
        List<MobEffectInstance> effects = new ArrayList<>(livingEntity.getActiveEffects());
        for (MobEffectInstance effect : effects) {
            if (!effect.getEffect().isBeneficial()) {
                livingEntity.removeEffect(effect.getEffect());
            }
        }
    }

    public void addEffects(LivingEntity livingEntity){
        int i = livingEntity.hasEffect(SonaMobEffects.IMMUNITY.get()) ? livingEntity.getEffect(SonaMobEffects.IMMUNITY.get()).getAmplifier() + 1 : 0;
        livingEntity.addEffect(new MobEffectInstance(SonaMobEffects.IMMUNITY.get(), 6000, i, false, true));
        livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1, false, true));
        livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 400, -6, false, true));
        livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 0, false, true));
        livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 400, 0, false, true));
        livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 400, 0, false, true));
    }
}
