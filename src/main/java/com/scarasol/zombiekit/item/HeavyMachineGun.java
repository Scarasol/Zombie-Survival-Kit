package com.scarasol.zombiekit.item;

import com.scarasol.zombiekit.entity.mechanics.HeavyMachineGunEntity;
import com.scarasol.zombiekit.init.ZombieKitEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

public class HeavyMachineGun extends Item {
    public HeavyMachineGun(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("item.zombiekit.heavy_machine_gun.description"));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        Player entity = context.getPlayer();
        if (context.getLevel() instanceof ServerLevel server) {
            BlockPos clickPos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            Direction playerDirection = entity.getDirection();
            HeavyMachineGunEntity machine_gun = ZombieKitEntities.HEAVY_MACHINE_GUN.get().create(server);
            switch (direction) {
                case UP -> machine_gun.setPos(clickPos.getX() + 0.5, clickPos.getY() + 1.5, clickPos.getZ() + 0.5);
                case DOWN -> machine_gun.setPos(clickPos.getX() + 0.5, clickPos.getY() - 0.5, clickPos.getZ() + 0.5);
                case WEST -> machine_gun.setPos(clickPos.getX() - 0.5, clickPos.getY() + 0.5, clickPos.getZ() + 0.5);
                case EAST -> machine_gun.setPos(clickPos.getX() + 1.5, clickPos.getY() + 0.5, clickPos.getZ() + 0.5);
                case SOUTH -> machine_gun.setPos(clickPos.getX() + 0.5, clickPos.getY() + 0.5, clickPos.getZ() + 1.5);
                case NORTH -> machine_gun.setPos(clickPos.getX() + 0.5, clickPos.getY() + 0.5, clickPos.getZ() - 0.5);
            }
            switch (playerDirection) {
                case WEST -> machine_gun.setDirection("WEST");
                case EAST -> machine_gun.setDirection("EAST");
                case SOUTH -> machine_gun.setDirection("SOUTH");
                case NORTH -> machine_gun.setDirection("NORTH");
            }
            machine_gun.finalizeSpawn(server, server.getCurrentDifficultyAt(clickPos), MobSpawnType.SPAWN_EGG, null, null);
            server.addFreshEntityWithPassengers(machine_gun);
            if (!entity.getAbilities().instabuild){
                ItemStack itemStack = context.getItemInHand();
                itemStack.setCount(itemStack.getCount() - 1);
            }
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemstack, Player player, LivingEntity livingEntity, InteractionHand interactionHand){
        if (livingEntity instanceof Camel horse && player.level() instanceof ServerLevel server){
            if (horse.isSaddled()){
                HeavyMachineGunEntity machine_gun = ZombieKitEntities.HEAVY_MACHINE_GUN.get().create(server);
                machine_gun.setPos(player.getX(), player.getY(), player.getZ());
                server.addFreshEntity(machine_gun);
                machine_gun.startRiding(horse);
                if (!player.getAbilities().instabuild){
                    itemstack.shrink(1);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
