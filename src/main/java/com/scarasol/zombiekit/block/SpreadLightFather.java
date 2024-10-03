package com.scarasol.zombiekit.block;

import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.init.ZombieKitBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.system.CallbackI;

import java.util.Random;

public class SpreadLightFather extends Block {
    public SpreadLightFather(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState oldState, boolean moving) {
        super.onPlace(blockState, level, blockPos, oldState, moving);
        level.scheduleTick(blockPos, this, 4);
    }

    @Override
    public void tick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        super.tick(blockState, level, blockPos, random);
        spreadLight(level, blockPos);
        level.scheduleTick(blockPos, this, 4);
    }

    public int prime(int a, int b){
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0)
            return a;
        return prime(b, a % b);
    }

    public void spreadLight(Level level, BlockPos blockPos){
        for (int x = -1; x < 2; x++){
            for (int y = -1; y < 2; y++){
                for (int z = -1; z < 2; z++){
                    if (prime(x, y) == 1 || prime(x, z) == 1 || prime(y, z) == 1){
                        BlockPos child = blockPos.offset(x, y, z);
                        if (level.getBlockState(child).isAir()){
                            BlockState light = ZombieKitBlocks.SPREAD_LIGHT.get().defaultBlockState()
                                    .setValue(SpreadLight.X, SpreadLight.Coordinate.getByInt(child.getX() - blockPos.getX()))
                                    .setValue(SpreadLight.Y, SpreadLight.Coordinate.getByInt(child.getY() - blockPos.getY()))
                                    .setValue(SpreadLight.Z, SpreadLight.Coordinate.getByInt(child.getZ() - blockPos.getZ()))
                                    .setValue(SpreadLight.LIGHT_LEVEL, 1);
                            level.setBlock(child, light, 3);
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

}
