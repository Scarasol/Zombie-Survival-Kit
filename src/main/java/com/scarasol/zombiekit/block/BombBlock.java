package com.scarasol.zombiekit.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public interface BombBlock {

    default void exploded(Level level, int x, int y, int z, int amplifier){
        level.explode(null, x, y, z, amplifier, Explosion.BlockInteraction.NONE);
        level.destroyBlock(new BlockPos(x, y, z), false);
        for (int i = x - 2; i <= x + 2; i++){
            for (int j = y - 2; j <= y + 2; j++){
                for (int k = z - 2; k <= z + 2; k++){
                    if (level.getBlockState(new BlockPos(i, j, k)).getBlock() instanceof BombBlock block)
                        exploded(level, i, j, k, block.getExplodeLevel());
                }
            }
        }
    }

    int getExplodeLevel();
}
