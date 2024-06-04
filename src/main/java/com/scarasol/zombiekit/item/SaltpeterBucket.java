package com.scarasol.zombiekit.item;

import com.scarasol.zombiekit.init.ZombieKitBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemHandlerHelper;

public class SaltpeterBucket extends Item {
    public SaltpeterBucket(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (blockState.getBlock() == Blocks.CAULDRON){
            level.playSound(null, blockPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1, 1);
            level.setBlock(blockPos, ZombieKitBlocks.SALTPETER_CAULDRON.get().defaultBlockState(), 3);
            if (!context.getPlayer().getAbilities().instabuild){
                ItemStack itemStack = context.getItemInHand();
                itemStack.shrink(1);
                ItemStack setstack = new ItemStack(Items.BUCKET);
                setstack.setCount(1);
                ItemHandlerHelper.giveItemToPlayer(player, setstack);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean hasCraftingRemainingItem() {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemstack) {
        return new ItemStack(Items.BUCKET);
    }

}
