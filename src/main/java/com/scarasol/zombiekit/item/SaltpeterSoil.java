package com.scarasol.zombiekit.item;

import com.scarasol.zombiekit.init.ZombieKitBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Random;

public class SaltpeterSoil extends Item {
    public SaltpeterSoil(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (blockState.getBlock() == Blocks.WATER_CAULDRON && blockState.getValue(BlockStateProperties.LEVEL_CAULDRON) == 3){
            level.setBlock(blockPos, ZombieKitBlocks.SALTPETER_CAULDRON.get().defaultBlockState(), 3);
            if (!context.getPlayer().getAbilities().instabuild){
                ItemStack itemStack = context.getItemInHand();
                itemStack.shrink(1);
            }
            Random random = level.getRandom();
            for (int i = 0; i < 10; ++i) {
                double d4 = random.nextGaussian() * 0.02;
                double d5 = random.nextGaussian() * 0.02;
                double d6 = random.nextGaussian() * 0.02;
                double d = 0.95;
                level.addParticle(ParticleTypes.COMPOSTER, (double)blockPos.getX() + 0.13124999403953552 + 0.737500011920929 * (double)random.nextFloat(), (double)blockPos.getY() + d + (double)random.nextFloat() * (1.0 - d), (double)blockPos.getZ() + 0.13124999403953552 + 0.737500011920929 * (double)random.nextFloat(), d4, d5, d6);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
