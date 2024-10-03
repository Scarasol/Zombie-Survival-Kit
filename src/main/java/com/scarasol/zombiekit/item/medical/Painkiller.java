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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class Painkiller extends Item {
    public Painkiller(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        entity.startUsingItem(hand);
        return new InteractionResultHolder(InteractionResult.SUCCESS, entity.getItemInHand(hand));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 40;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        if (world.isClientSide())
            return super.finishUsingItem(itemstack, world, entity);
        entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2));
        entity.addEffect(new MobEffectInstance(SonaMobEffects.ANALGESIC.get(), 2400, 0));
        if (!(entity instanceof Player player) || !player.isCreative()){
            itemstack.shrink(1);
        }
        return super.finishUsingItem(itemstack, world, entity);
    }
}
