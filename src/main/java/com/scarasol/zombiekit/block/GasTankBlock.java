package com.scarasol.zombiekit.block;

import com.scarasol.zombiekit.init.ZombieKitBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GasTankBlock extends FallingBlock implements BombBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty BESIDE_FIRE = BooleanProperty.create("beside_fire");

    public GasTankBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BESIDE_FIRE, false));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(BESIDE_FIRE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(BESIDE_FIRE, checkNeighbor(context.getLevel(), context.getClickedPos()));
    }

    @Override
    public int getExplodeLevel() {
        return 5;
    }

    @Override
    protected void falling(FallingBlockEntity fallingBlockEntity) {
        fallingBlockEntity.setHurtsEntities(2.0f, 40);
    }

    @Override
    public void onLand(Level level, BlockPos blockPos, BlockState blockState, BlockState blockState2, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            level.levelEvent(1031, blockPos, 0);
        }
        exploded(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), getExplodeLevel());
    }

    @Override
    public void wasExploded(Level level, BlockPos blockPos, Explosion e) {
        exploded(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), getExplodeLevel());
    }

    @Override
    public void onProjectileHit(Level level, BlockState blockstate, BlockHitResult hit, Projectile entity) {
        exploded(level, hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ(), getExplodeLevel());
    }

    public boolean checkNeighbor(Level world, BlockPos pos){
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        boolean besideFire = false;
        for (double i = x - 1; i <= x + 1; i++){
            for (double j = y - 1; j <= y + 1; j++){
                for (double k = z - 1; k <= z + 1; k++){
                    if (world.getBlockState(new BlockPos(i, j, k)).getBlock() == Blocks.SOUL_FIRE || world.getBlockState(new BlockPos(i, j, k)).getBlock() == Blocks.FIRE || (world.getBlockState(new BlockPos(i, j, k)).getBlock().getStateDefinition().getProperty("lit") instanceof BooleanProperty _getbp2 && world.getBlockState(new BlockPos(i, j, k)).getValue(_getbp2)) == true){
                        besideFire = true;
                        break;
                    }
                }
            }
        }
        return besideFire;
    }

    @Override
    public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
        world.setBlock(pos, blockstate.setValue(BESIDE_FIRE, checkNeighbor(world, pos)), 3);
        super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 3);
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel level, BlockPos blockPos, RandomSource random) {
        super.tick(blockstate, level, blockPos, random);
        if (blockstate.getValue(BESIDE_FIRE) && random.nextDouble() < 0.01){
            exploded(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), getExplodeLevel());
        }
        level.scheduleTick(blockPos, this, 3);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }
    
}
