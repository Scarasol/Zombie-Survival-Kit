package com.scarasol.zombiekit.block;

import com.scarasol.zombiekit.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface BombBlock {

    default void exploded(Level level, int x, int y, int z, int amplifier){
        level.explode(null, x, y, z, amplifier, Level.ExplosionInteraction.NONE);
        level.destroyBlock(new BlockPos(x, y, z), false);
        if (CommonConfig.LANDMINE_CHAIN.get()){
            int range = CommonConfig.CHAIN_RANGE.get();
            for (int i = x - range; i <= x + range; i++){
                for (int j = y - range; j <= y + range; j++){
                    for (int k = z - range; k <= z + range; k++){
                        if (level.getBlockState(new BlockPos(i, j, k)).getBlock() instanceof BombBlock block)
                            exploded(level, i, j, k, block.getExplodeLevel());
                    }
                }
            }
        }

    }

    int getExplodeLevel();
}
