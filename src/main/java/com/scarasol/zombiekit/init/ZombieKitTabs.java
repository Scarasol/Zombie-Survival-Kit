package com.scarasol.zombiekit.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ZombieKitTabs {

    public static CreativeModeTab TAB_ZOMBIEKIT;
    public static CreativeModeTab TAB_ZOMBIEKIT_COMBAT;
    public static CreativeModeTab TAB_ZOMBIEKIT_TOOL;

    public static void load() {
        TAB_ZOMBIEKIT_COMBAT = new CreativeModeTab("zombiekit_combat") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ZombieKitItems.MOLOTOV_COCKTAIL.get());
            }

            @OnlyIn(Dist.CLIENT)
            public boolean hasSearchBar() {
                return false;
            }
        };
        TAB_ZOMBIEKIT_TOOL = new CreativeModeTab("zombiekit_tool") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ZombieKitItems.MIRACLE.get());
            }

            @OnlyIn(Dist.CLIENT)
            public boolean hasSearchBar() {
                return false;
            }
        };
        TAB_ZOMBIEKIT = new CreativeModeTab("tabzombiekit") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ZombieKitItems.PLASTICS.get());
            }

            @OnlyIn(Dist.CLIENT)
            public boolean hasSearchBar() {
                return false;
            }
        };

    }

}
