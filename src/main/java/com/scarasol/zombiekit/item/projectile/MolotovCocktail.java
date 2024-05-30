package com.scarasol.zombiekit.item.projectile;

import com.scarasol.zombiekit.entity.projectile.MolotovCocktailEntity;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class MolotovCocktail extends Item {

    public MolotovCocktail(Properties properties) {
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
    public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entityLiving, int timeLeft) {
        if (!world.isClientSide() && entityLiving instanceof ServerPlayer entity) {
            ItemStack stack = ProjectileWeaponItem.getHeldProjectile(entity, e -> e.getItem() == ZombieKitItems.MOLOTOV_COCKTAIL.get());
            if (stack == ItemStack.EMPTY) {
                for (int i = 0; i < entity.getInventory().items.size(); i++) {
                    ItemStack teststack = entity.getInventory().items.get(i);
                    if (teststack.getItem() == ZombieKitItems.MOLOTOV_COCKTAIL.get()) {
                        stack = teststack;
                        break;
                    }
                }
            }
            if (stack != ItemStack.EMPTY) {
                MolotovCocktailEntity entityarrow = MolotovCocktailEntity.shoot(world, entity, world.getRandom(), 0.8f, 4, 1);
                if (entity.getAbilities().instabuild) {
                    entityarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                } else {
                    stack.shrink(1);
                    if (stack.isEmpty())
                        entity.getInventory().removeItem(stack);
                }
            }
        }

    }
}
