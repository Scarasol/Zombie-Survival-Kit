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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class SuspiciousDrug extends Item {
    public SuspiciousDrug(Properties properties) {
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
        float heal = com.scarasol.zombiekit.config.CommonConfig.SUSPICIOUS_HEAL.get();
        if (entity.getRandom().nextDouble() < com.scarasol.zombiekit.config.CommonConfig.SUSPICIOUS_SIDE_EFFECTS.get()){
            entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 1200, 0));
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 2));
            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 1200, 0));
            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 400, 0));
            if (CommonConfig.INFECTION_OPEN.get() && entity instanceof ILivingEntityAccessor livingEntityAccessor){
                InfectionManager.addInfection(livingEntityAccessor, -heal / 2);
            }
        }else {
            entity.addEffect(new MobEffectInstance(SonaMobEffects.IMMUNITY.get(), 3600, 0));
            if (CommonConfig.INFECTION_OPEN.get() && entity instanceof ILivingEntityAccessor livingEntityAccessor){
                InfectionManager.addInfection(livingEntityAccessor, -heal);
            }
        }
        if (!(entity instanceof Player player) || !player.isCreative()){
            itemstack.shrink(1);
        }
        return super.finishUsingItem(itemstack, world, entity);
    }
}
