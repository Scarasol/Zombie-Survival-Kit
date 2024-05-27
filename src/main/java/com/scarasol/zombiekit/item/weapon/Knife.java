package com.scarasol.zombiekit.item.weapon;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class Knife extends SwordItem {

    public Knife(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemstack, Player player, LivingEntity livingEntity, InteractionHand interactionHand){
        super.interactLivingEntity(itemstack, player, livingEntity, interactionHand);
        if (!player.getCooldowns().isOnCooldown(itemstack.getItem()) && (livingEntity instanceof Mob mob && !player.equals(mob.getTarget()))){
            livingEntity.hurt(DamageSource.playerAttack(player), 2.5f * getDamage());
            if (!player.getAbilities().instabuild) {
                itemstack.hurtAndBreak(5, player, consumer -> consumer.broadcastBreakEvent(interactionHand));
                player.getCooldowns().addCooldown(itemstack.getItem(), 140);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        if (toolAction == ToolActions.SWORD_SWEEP)
            return false;
        return super.canPerformAction(stack, toolAction);
    }
}
