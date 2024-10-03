package com.scarasol.zombiekit.item;

import com.scarasol.zombiekit.entity.mechanics.HeavyMachineGunEntity;
import com.scarasol.zombiekit.entity.mechanics.UvLampEntity;
import com.scarasol.zombiekit.init.ZombieKitEntities;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

public class UvLamp extends Item {
    public UvLamp(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("item.zombiekit.uv_lamp.description_1"));
        list.add(Component.translatable("item.zombiekit.uv_lamp.description_2"));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        Player entity = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();
        if (context.getLevel() instanceof ServerLevel server) {
            BlockPos clickPos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            UvLampEntity uvLamp = ZombieKitEntities.UV_LAMP.get().create(server);
            switch (direction) {
                case UP -> uvLamp.setPos(clickPos.getX() + 0.5, clickPos.getY() + 1.5, clickPos.getZ() + 0.5);
                case DOWN -> uvLamp.setPos(clickPos.getX() + 0.5, clickPos.getY() - 0.5, clickPos.getZ() + 0.5);
                case WEST -> uvLamp.setPos(clickPos.getX() - 0.5, clickPos.getY() + 0.5, clickPos.getZ() + 0.5);
                case EAST -> uvLamp.setPos(clickPos.getX() + 1.5, clickPos.getY() + 0.5, clickPos.getZ() + 0.5);
                case SOUTH -> uvLamp.setPos(clickPos.getX() + 0.5, clickPos.getY() + 0.5, clickPos.getZ() + 1.5);
                case NORTH -> uvLamp.setPos(clickPos.getX() + 0.5, clickPos.getY() + 0.5, clickPos.getZ() - 0.5);
            }
            uvLamp.finalizeSpawn(server, server.getCurrentDifficultyAt(clickPos), MobSpawnType.SPAWN_EGG, null, null);
            uvLamp.setHealth(20 - itemStack.getDamageValue());
            server.addFreshEntityWithPassengers(uvLamp);
            if (!entity.getAbilities().instabuild){
                itemStack.setCount(itemStack.getCount() - 1);
            }
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public boolean isValidRepairItem(ItemStack itemStack, ItemStack repairItem) {
        return repairItem.is(ZombieKitItems.PLASTICS.get()) || super.isValidRepairItem(itemStack, repairItem);
    }
}
