package com.scarasol.zombiekit.item.bonus;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class IronCurtain extends Item {
    public IronCurtain(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("item.zombiekit.iron_curtain.description"));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean isFoil(ItemStack itemstack) {
        return true;
    }
}
