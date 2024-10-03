package com.scarasol.zombiekit.block;

import com.scarasol.zombiekit.init.ZombieKitBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LandmineBlock extends Block implements BombBlock {

    public List<MobEffectInstance> areaEffectCloudEffect = new ArrayList<>();

    public LandmineBlock(Properties properties, List<MobEffectInstance> areaEffectCloudEffect) {
        super(properties);
        this.areaEffectCloudEffect.addAll(areaEffectCloudEffect);
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
        Vec3 offset = state.getOffset(world, pos);
        return box(0, 0, 0, 16, 1, 16).move(offset.x, offset.y, offset.z);
    }

    @Override
    public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
        if (worldIn instanceof LevelAccessor world) {
            return world.getBlockState(pos.below()).canOcclude();
        }
        return super.canSurvive(blockstate, worldIn, pos);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
        return BlockPathTypes.WALKABLE;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public int getExplodeLevel() {
        return areaEffectCloudEffect.isEmpty() ? 4 : 2;
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        if (player.getInventory().getSelected().getItem() instanceof ShovelItem tieredItem)
            return tieredItem.getTier().getLevel() >= 1;
        return false;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
        boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
        if (entity.getMainHandItem().getItem() instanceof ShovelItem || entity.isCreative())
            return retval;
        exploded(world, pos.getX(), pos.getY(), pos.getZ(), getExplodeLevel());
        return retval;
    }

    @Override
    public void wasExploded(Level world, BlockPos pos, Explosion e) {
        super.wasExploded(world, pos, e);
        exploded(world, pos.getX(), pos.getY(), pos.getZ(), getExplodeLevel());
        if (!areaEffectCloudEffect.isEmpty())
            spawnAreaEffectCloud(world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void attack(BlockState blockstate, Level world, BlockPos pos, Player entity) {
        super.attack(blockstate, world, pos, entity);
        if (entity.getMainHandItem().getItem() instanceof ShovelItem || entity.isCreative())
            return;
        exploded(world, pos.getX(), pos.getY(), pos.getZ(), getExplodeLevel());
        if (!areaEffectCloudEffect.isEmpty())
            spawnAreaEffectCloud(world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
        super.entityInside(blockstate, world, pos, entity);
        exploded(world, pos.getX(), pos.getY(), pos.getZ(), getExplodeLevel());
        if (!areaEffectCloudEffect.isEmpty())
            spawnAreaEffectCloud(world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockstate, world, pos, entity, hand, hit);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        exploded(world, x, y, z, getExplodeLevel());
        if (!areaEffectCloudEffect.isEmpty())
            spawnAreaEffectCloud(world, x, y, z);
        return InteractionResult.SUCCESS;
    }


    public void spawnAreaEffectCloud(Level level, int x, int y, int z){
        AreaEffectCloud areaEffectCloud = new AreaEffectCloud(level, x, y, z);
        areaEffectCloud.setDuration(200);
        areaEffectCloud.setRadius(4);
        for (MobEffectInstance effectInstance : areaEffectCloudEffect){
            areaEffectCloud.addEffect(effectInstance);
        }
        level.addFreshEntity(areaEffectCloud);
    }

}
