package com.scarasol.zombiekit.item.medical;

import com.scarasol.sona.accessor.ILivingEntityAccessor;
import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.init.SonaMobEffects;
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

public class MedicalKit extends Item {
    public MedicalKit(Properties properties) {
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
        return 100;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        if (world.isClientSide())
            return super.finishUsingItem(itemstack, world, entity);
        entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 3, 0));
        entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1800, 1));
        entity.addEffect(new MobEffectInstance(SonaMobEffects.ANALGESIC.get(), 3600, 0));
        if (CommonConfig.INJURY_OPEN.get() && entity instanceof ILivingEntityAccessor livingEntityAccessor){
            InjuryManager.addInjury(livingEntityAccessor, 20);
            InjuryManager.addBandage(livingEntityAccessor, 50);
        }
        if (!(entity instanceof Player player) || !player.isCreative()){
            itemstack.hurtAndBreak(1, entity, consumer -> {
                entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
        return super.finishUsingItem(itemstack, world, entity);
    }
}
