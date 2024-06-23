package com.scarasol.zombiekit.item.weapon;

import com.scarasol.zombiekit.config.CommonConfig;
import com.scarasol.zombiekit.entity.projectile.FirecrackerEntity;
import com.scarasol.zombiekit.entity.projectile.WrenchEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Wrench extends Item {
    public Wrench(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.wrench.description").getString()));
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
            WrenchEntity entityArrow;
            if (this.getUseDuration(itemStack) - timeLeft >= 10) {
                entityArrow = WrenchEntity.shoot(world, entity, world.getRandom(), 1f, 9, 0, true);
                ((ServerPlayer) entityLiving).getCooldowns().addCooldown(itemStack.getItem(), CommonConfig.WRENCH_COOLDOWN.get() * 20);
            } else {
                entityArrow = WrenchEntity.shoot(world, entity, world.getRandom(), 1f, 9, 0, false);
            }
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
