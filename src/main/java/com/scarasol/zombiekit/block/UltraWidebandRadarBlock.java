package com.scarasol.zombiekit.block;

import com.scarasol.zombiekit.config.CommonConfig;
import com.scarasol.zombiekit.entity.mechanics.Mechanics;
import com.scarasol.zombiekit.init.ZombieKitBlocks;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.*;
import java.util.stream.Collectors;

public class UltraWidebandRadarBlock extends Block {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final BooleanProperty RADAR_OPEN = BooleanProperty.create("radar_open");
    public static final IntegerProperty RADAR_ELE = IntegerProperty.create("radar_ele", 1, 5);
    public static final IntegerProperty RADAR_POWER = IntegerProperty.create("radar_power", 0, 101);

    public UltraWidebandRadarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(RADAR_OPEN, false).setValue(RADAR_ELE, 5).setValue(RADAR_POWER, 0));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.zombiekit.uwr.description_1"));
        list.add(Component.translatable("block.zombiekit.uwr.description_2"));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(RADAR_OPEN);
        builder.add(RADAR_ELE);
        builder.add(RADAR_POWER);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace());
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        if (state.getValue(RADAR_POWER) > 0){
            ItemStack itemStack = new ItemStack(ZombieKitItems.BATTERY.get(), 1);
            itemStack.setDamageValue(101 - state.getValue(RADAR_POWER));
            return Arrays.asList(new ItemStack(this, 1), itemStack);
        }
        return Collections.singletonList(new ItemStack(this, 1));
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, CommonConfig.RADAR_POWER.get());

    }

    @Override
    public void tick(BlockState blockState, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(blockState, world, pos, random);
        if (blockState.getValue(RADAR_POWER) <= 1){
            world.setBlock(pos, blockState.setValue(RADAR_OPEN, false).setValue(RADAR_ELE, updateEle(blockState.getValue(RADAR_POWER))), 3);
            return;
        }
        if (blockState.getValue(RADAR_OPEN)){
            highLightEntity(blockState, world, pos);
            int power = blockState.getValue(RADAR_POWER);
            world.setBlock(pos, blockState.setValue(RADAR_POWER, Math.max(1, power - 1)).setValue(RADAR_ELE, updateEle(Math.max(1, power - 1))), 3);
        }

        world.scheduleTick(pos, this, CommonConfig.RADAR_POWER.get());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            default -> box(2, 2, 0, 14, 14, 6);
            case NORTH -> box(2, 2, 10, 14, 14, 16);
            case EAST -> box(0, 2, 2, 6, 14, 14);
            case WEST -> box(10, 2, 2, 16, 14, 14);
            case UP -> box(2, 0, 2, 14, 6, 14);
            case DOWN -> box(2, 10, 2, 14, 16, 14);
        };
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    public void highLightEntity(BlockState blockState, ServerLevel world, BlockPos pos) {
        BlockPos blockPos;
        int range = CommonConfig.RADAR_RANGE.get();
        switch (blockState.getValue(FACING)) {
            case UP -> blockPos = pos.below(range / 2);
            case WEST -> blockPos = pos.east(range / 2);
            case EAST -> blockPos = pos.west(range / 2);
            case NORTH -> blockPos = pos.south(range / 2);
            case SOUTH -> blockPos = pos.north(range / 2);
            default -> blockPos = pos.above(range / 2);
        }
        Vec3 _center = new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        List<LivingEntity> entFound = world.getEntitiesOfClass(LivingEntity.class, new AABB(_center, _center).inflate(range), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
        for (LivingEntity entityIterator : entFound) {
            if (entityIterator instanceof ArmorStand || entityIterator instanceof Mechanics)
                continue;
            switch (blockState.getValue(FACING)) {
                case WEST, EAST -> {
                    if (Math.abs(entityIterator.getY() - blockPos.getY()) > 8 || Math.abs(entityIterator.getZ() - blockPos.getZ()) > 8)
                        continue;
                }
                case NORTH, SOUTH -> {
                    if (Math.abs(entityIterator.getY() - blockPos.getY()) > 8 || Math.abs(entityIterator.getX() - blockPos.getX()) > 8)
                        continue;
                }
                default -> {
                    if (Math.abs(entityIterator.getZ() - blockPos.getZ()) > 8 || Math.abs(entityIterator.getX() - blockPos.getX()) > 8)
                        continue;
                }
            }
            entityIterator.addEffect(new MobEffectInstance(MobEffects.GLOWING, CommonConfig.RADAR_POWER.get() + 10, 0, false, false));
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockState, world, pos, entity, hand, hit);
        if (blockState.getValue(RADAR_POWER) < 1 && entity.getOffhandItem().getItem() == ZombieKitItems.WRENCH.get() && entity.getMainHandItem().getItem() == ZombieKitItems.BATTERY.get()){
            world.setBlock(pos, blockState.setValue(RADAR_POWER, 101 - entity.getMainHandItem().getDamageValue()).setValue(RADAR_ELE, updateEle(101 - entity.getMainHandItem().getDamageValue())), 3);
            if (!entity.isCreative()){
                entity.getMainHandItem().shrink(1);
            }
            return InteractionResult.SUCCESS;
        }else if(blockState.getValue(RADAR_POWER) > 0 && entity.getOffhandItem().getItem() == ZombieKitItems.WRENCH.get()){
            ItemStack itemStack = new ItemStack(ZombieKitItems.BATTERY.get(), 1);
            itemStack.setDamageValue(101 - blockState.getValue(RADAR_POWER));
            ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
            itemEntity.setPickUpDelay(10);
            world.addFreshEntity(itemEntity);
            world.setBlock(pos, blockState.setValue(RADAR_POWER, 0).setValue(RADAR_ELE, updateEle(0)), 3);
            return InteractionResult.SUCCESS;
        }else if (blockState.getValue(RADAR_POWER) > 1){
            world.setBlock(pos, blockState.setValue(RADAR_OPEN, !blockState.getValue(RADAR_OPEN)), 3);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public int updateEle(int power){
        if (power <= 1){
            return 5;
        }else if (power < 26){
            return 4;
        }else if (power < 51){
            return 3;
        }else if (power < 76){
            return 2;
        }else{
            return 1;
        }
    }
}
