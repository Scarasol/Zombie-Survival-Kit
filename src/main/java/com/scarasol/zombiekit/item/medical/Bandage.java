package com.scarasol.zombiekit.item.medical;

import com.scarasol.sona.accessor.ILivingEntityAccessor;
import com.scarasol.sona.configuration.CommonConfig;
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

public class Bandage extends Item {
    public Bandage(Properties properties) {
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
        entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
        if (CommonConfig.INJURY_OPEN.get() && entity instanceof ILivingEntityAccessor livingEntityAccessor){
            InjuryManager.addInjury(livingEntityAccessor, com.scarasol.zombiekit.config.CommonConfig.BANDAGE_INJURY.get());
            InjuryManager.addBandage(livingEntityAccessor, com.scarasol.zombiekit.config.CommonConfig.BANDAGE.get());
        }
        if (!(entity instanceof Player player) || !player.isCreative()){
            itemstack.shrink(1);
        }
        return super.finishUsingItem(itemstack, world, entity);
    }


}
