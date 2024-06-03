package com.scarasol.zombiekit.item.bonus;


import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface Bonus{
    @OnlyIn(Dist.CLIENT)
    default boolean isFoil(ItemStack itemstack) {
        return true;
    }
}
