package com.scarasol.zombiekit.item;

import com.scarasol.zombiekit.entity.projectile.FlareGunEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FlareGun extends Item {

    public FlareGun(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        if (!world.isClientSide()) {
            FlareGunEntity entityarrow = FlareGunEntity.shoot(world, entity, world.getRandom(), 1.5f, 5, 5);
            entityarrow.pickup = AbstractArrow.Pickup.DISALLOWED;
            if (!entity.getAbilities().instabuild){
                ItemStack itemStack = entity.getItemInHand(hand);
                itemStack.shrink(1);
            }
        }
        return new InteractionResultHolder<>(InteractionResult.CONSUME, entity.getItemInHand(hand));
    }

}
