package com.scarasol.zombiekit.item;

import com.scarasol.zombiekit.entity.mechanics.DroneEntity;
import com.scarasol.zombiekit.init.ZombieKitEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class Drone extends Item {
    public Drone(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        Player entity = context.getPlayer();
        if (context.getLevel() instanceof ServerLevel server) {
            BlockPos clickPos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            DroneEntity drone = ZombieKitEntities.DRONE.get().create(server);
            switch (direction) {
                case UP -> drone.setPos(clickPos.getX() + 0.5, clickPos.getY() + 1.5, clickPos.getZ() + 0.5);
                case DOWN -> drone.setPos(clickPos.getX() + 0.5, clickPos.getY() - 0.5, clickPos.getZ() + 0.5);
                case WEST -> drone.setPos(clickPos.getX() - 0.5, clickPos.getY() + 0.5, clickPos.getZ() + 0.5);
                case EAST -> drone.setPos(clickPos.getX() + 1.5, clickPos.getY() + 0.5, clickPos.getZ() + 0.5);
                case SOUTH -> drone.setPos(clickPos.getX() + 0.5, clickPos.getY() + 0.5, clickPos.getZ() + 1.5);
                case NORTH -> drone.setPos(clickPos.getX() + 0.5, clickPos.getY() + 0.5, clickPos.getZ() - 0.5);
            }
            drone.setOwner(entity);
            drone.finalizeSpawn(server, server.getCurrentDifficultyAt(clickPos), MobSpawnType.SPAWN_EGG, null, null);
            server.addFreshEntityWithPassengers(drone);
            if (!entity.getAbilities().instabuild){
                ItemStack itemStack = context.getItemInHand();
                itemStack.setCount(itemStack.getCount() - 1);
            }
        }
        return InteractionResult.CONSUME;
    }
}
