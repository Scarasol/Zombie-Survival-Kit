package com.scarasol.zombiekit.item.bonus;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Supplier;

public class KonnGara extends RecordItem {

    public KonnGara(int comparatorValue, Supplier<SoundEvent> soundSupplier, Properties builder) {
        super(comparatorValue, soundSupplier, builder);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.konn_gara.description").getString()));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean isFoil(ItemStack itemstack) {
        return true;
    }
}
