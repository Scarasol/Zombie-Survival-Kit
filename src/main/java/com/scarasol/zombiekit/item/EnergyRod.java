package com.scarasol.zombiekit.item;

import com.scarasol.zombiekit.init.ZombieKitItems;
import com.scarasol.zombiekit.item.armor.ExoArmor;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class EnergyRod extends Item {
    public EnergyRod(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.energy_rod.description").getString()));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        ItemStack itemStack = entity.getItemInHand(hand);
        ItemStack armor = entity.getItemBySlot(EquipmentSlot.CHEST);
        if (armor.is(ZombieKitItems.EXO_CHESTPLATE.get()) && ExoArmor.getPower(armor) < 100){
            ExoArmor.addPower(armor, 20);
            world.addParticle(DustParticleOptions.REDSTONE, entity.getX(), entity.getY() + 1, entity.getZ(), 0.0D, 0.0D, 0.0D);
            if (!entity.isCreative()){
                entity.getCooldowns().addCooldown(ZombieKitItems.ENERGY_ROD.get(), 20);
                itemStack.shrink(1);
            }
            return InteractionResultHolder.consume(itemStack);
        }
        return InteractionResultHolder.fail(itemStack);
    }
}
