package com.scarasol.zombiekit.block;

import com.scarasol.zombiekit.config.CommonConfig;
import com.scarasol.zombiekit.init.ZombieKitBlocks;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;
import java.util.List;

public class BarbedWireBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 2);

    public BarbedWireBlock(Properties properties, int stage) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(STAGE, stage));
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(STAGE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
        return BlockPathTypes.WALKABLE;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return switch (state.getValue(STAGE)){
            case 1 -> Collections.singletonList(new ItemStack(ZombieKitItems.BARBED_WIRE_BROKEN.get(), 1));
            case 2 -> Collections.singletonList(new ItemStack(ZombieKitItems.BARBED_WIRE_EXTREMELY_BROKEN.get(), 1));
            default -> Collections.singletonList(new ItemStack(ZombieKitItems.BARBED_WIRE.get(), 1));
        };
    }

    @Override
    public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
        super.entityInside(blockstate, world, pos, entity);
        switch (blockstate.getValue(STAGE)){
            case 0 -> entity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0.25, 0.05, 0.25));
            case 1 -> entity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0.525, 0.4, 0.525));
            case 2 -> entity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0.8, 0.75, 0.8));
        }
        double x = Math.abs(entity.getX() - entity.xOld);
        double y = Math.abs(entity.getZ() - entity.zOld);
        double z = Math.abs(entity.getY() - entity.yOld);
        if (x >= 0.003 || y >= 0.003 || z >= 0.003){
            if ((!(entity instanceof Player player) || !player.isCreative()) && entity.isAlive()){
                entity.hurt(new DamageSource("barbedWire").bypassArmor(), 1);
                if (world.getRandom().nextDouble() < CommonConfig.BROKEN_CHANCE.get()){
                    breakBarbedWire(world, blockstate, pos);
                }
            }
        }
    }

    @Override
    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockstate, world, pos, entity, hand, hit);
        if (fixBarbedWire(world, blockstate, pos, entity)){
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public void breakBarbedWire(Level level, BlockState blockState, BlockPos blockPos){
        if (level.isClientSide())
            return;
        int stage = blockState.getValue(STAGE);
        switch (stage){
            case 0 -> level.setBlock(blockPos, ZombieKitBlocks.BARBED_WIRE_BROKEN.get().defaultBlockState(), 3);
            case 1 -> level.setBlock(blockPos, ZombieKitBlocks.BARBED_WIRE_EXTREMELY_BROKEN.get().defaultBlockState(), 3);
            case 2 -> level.destroyBlock(blockPos, false);
        }
    }

    public boolean fixBarbedWire(Level level, BlockState blockState, BlockPos blockPos, Player player){
        int stage = blockState.getValue(STAGE);
        if (stage == 0 || player.getMainHandItem().getItem() != ZombieKitItems.IRON_WIRE.get() || player.getOffhandItem().getItem() != ZombieKitItems.WRENCH.get())
            return false;
        switch (stage){
            case 1 -> level.setBlock(blockPos, ZombieKitBlocks.BARBED_WIRE.get().defaultBlockState(), 3);
            case 2 -> level.setBlock(blockPos, ZombieKitBlocks.BARBED_WIRE_BROKEN.get().defaultBlockState(), 3);
        }
        if (level instanceof ServerLevel serverLevel)
            serverLevel.sendParticles(ParticleTypes.COMPOSTER, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 10, 0.2, 0.2, 0.2, 0.05);
        if (!player.isCreative())
            player.getMainHandItem().shrink(1);
        return true;
    }

}
