package com.scarasol.zombiekit.block;

import com.scarasol.zombiekit.init.ZombieKitBlocks;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.IPlantable;

import java.util.Collections;
import java.util.List;

public class TrapCoverBlock extends Block {
    public TrapCoverBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return adjacentBlockState.getBlock() == this || super.skipRendering(state, adjacentBlockState, side);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
        super.stepOn(world, pos, blockstate, entity);
        world.destroyBlock(pos, false);
        checkNeighbor(world, pos);
    }

    public void checkNeighbor(Level world, BlockPos pos){
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        for (double i = x - 1; i <= x + 1; i++){
            BlockPos neighbor1 = BlockPos.containing(i, y, z);
            if (world.getBlockState(neighbor1).getBlock() == ZombieKitBlocks.TRAP_COVER.get()){
                world.destroyBlock(neighbor1, false);
                checkNeighbor(world, neighbor1);
            }
        }
        for (double k = z - 1; k <= z + 1; k++){
            BlockPos neighbor2 = BlockPos.containing(x, y, k);
            if (world.getBlockState(neighbor2).getBlock() == ZombieKitBlocks.TRAP_COVER.get()){
                world.destroyBlock(neighbor2, false);
                checkNeighbor(world, neighbor2);
            }
        }
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return box(0, 13, 0, 16, 16, 16);
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
        return BlockPathTypes.WALKABLE;
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction direction, IPlantable plantable) {
        return net.minecraftforge.common.PlantType.PLAINS.equals(plantable.getPlantType(world, pos.relative(direction)));
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

    @OnlyIn(Dist.CLIENT)
    public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
        event.getBlockColors().register((bs, world, pos, index) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D), ZombieKitBlocks.TRAP_COVER.get());
    }

    @OnlyIn(Dist.CLIENT)
    public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register((stack, index) -> GrassColor.get(0.5D, 1.0D), ZombieKitBlocks.TRAP_COVER.get());
    }
}
