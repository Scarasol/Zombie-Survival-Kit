package com.scarasol.zombiekit.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ZombieKitTabs {

    public static CreativeModeTab TAB_ZOMBIEKIT;

    public static void load() {
        TAB_ZOMBIEKIT = new CreativeModeTab("tabzombiekit") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ZombieKitItems.FIRE_AXE.get());
            }

            @OnlyIn(Dist.CLIENT)
            public boolean hasSearchBar() {
                return false;
            }
        };
    }

}
