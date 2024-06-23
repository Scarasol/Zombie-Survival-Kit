package com.scarasol.zombiekit.item.weapon;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.scarasol.sona.item.IRustItem;
import com.scarasol.zombiekit.config.CommonConfig;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Knife extends SwordItem implements IRustItem {

    private Multimap<Attribute, AttributeModifier> weaponModifiers;

    public Knife(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }

    @Override
    public float getDamage() {
        double damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_KNIFE_DAMAGE.get() : CommonConfig.KNIFE_DAMAGE.get();
        return (float) (damage * 0.5);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        initModifier();
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.weaponModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    public void initModifier() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        double damage;
        if (this.equals(ZombieKitItems.TRIANGULAR_THORN.get())){
            damage = CommonConfig.TRIANGULAR_THORN_DAMAGE.get();
        }else {
            damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_KNIFE_DAMAGE.get() : CommonConfig.KNIFE_DAMAGE.get();
        }
        double speed = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_KNIFE_SPEED.get() : CommonConfig.KNIFE_SPEED.get();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", damage - 1, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", speed - 4, AttributeModifier.Operation.ADDITION));
        weaponModifiers = builder.build();
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemstack, Player player, LivingEntity livingEntity, InteractionHand interactionHand){
        super.interactLivingEntity(itemstack, player, livingEntity, interactionHand);
        if (!player.getCooldowns().isOnCooldown(itemstack.getItem()) && (livingEntity instanceof Mob mob && !player.equals(mob.getTarget()))){
            double damage;
            if (this.equals(ZombieKitItems.TRIANGULAR_THORN.get())){
                damage = CommonConfig.TRIANGULAR_THORN_DAMAGE.get();
            }else {
                damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_KNIFE_DAMAGE.get() : CommonConfig.KNIFE_DAMAGE.get();
            }
            livingEntity.hurt(DamageSource.playerAttack(player), (float) (CommonConfig.ASSASSINATE_MULTIPLIER.get() * damage));
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

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.knife.description").getString()));
    }
}
