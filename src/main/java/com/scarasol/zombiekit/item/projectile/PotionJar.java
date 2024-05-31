package com.scarasol.zombiekit.item.projectile;

import com.scarasol.zombiekit.entity.projectile.MolotovCocktailEntity;
import com.scarasol.zombiekit.entity.projectile.PotionJarEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class PotionJar extends Item {
    public PotionJar(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        entity.startUsingItem(hand);
        return new InteractionResultHolder(InteractionResult.SUCCESS, entity.getItemInHand(hand));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level world, LivingEntity entityLiving, int timeLeft) {
        if (!world.isClientSide() && entityLiving instanceof ServerPlayer entity) {
            AbstractArrow entityArrow = PotionJarEntity.shoot(world, entity, world.getRandom(), 0.8f, 4, 1);
            if (entity.getAbilities().instabuild) {
                entityArrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            } else {
                itemStack.shrink(1);
                if (itemStack.isEmpty())
                    entity.getInventory().removeItem(itemStack);
            }
        }
    }

}
