package com.scarasol.zombiekit.block;

import com.scarasol.zombiekit.init.ZombieKitBlocks;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.ToIntFunction;

public class ChargerBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty HAS_BATTERY = BooleanProperty.create("has_battery");
    public static final BooleanProperty RED_STONE_CONNECTED = BooleanProperty.create("red_stone_connected");
    public static final IntegerProperty BATTERY_POWER = IntegerProperty.create("battery_power", 0, 100);

    public ChargerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HAS_BATTERY, false).setValue(RED_STONE_CONNECTED, false).setValue(BATTERY_POWER, 0));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent(new TranslatableComponent("block.zombiekit.charger.description_1").getString()));
        list.add(new TextComponent(new TranslatableComponent("block.zombiekit.charger.description_2").getString()));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    public static ToIntFunction<BlockState> getLightLevel(int light){
        return (blockState) -> blockState.getValue(HAS_BATTERY) && blockState.getValue(RED_STONE_CONNECTED) && blockState.getValue(BATTERY_POWER) < 100 ? 8 : 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(HAS_BATTERY);
        builder.add(RED_STONE_CONNECTED);
        builder.add(BATTERY_POWER);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return true;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        if (state.getValue(HAS_BATTERY)){
            ItemStack itemStack = new ItemStack(ZombieKitItems.BATTERY.get(), 1);
            itemStack.setDamageValue(100 - state.getValue(BATTERY_POWER));
            return Arrays.asList(new ItemStack(this, 1), itemStack);
        }
        return Collections.singletonList(new ItemStack(this, 1));
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockState, world, pos, entity, hand, hit);
        if (!blockState.getValue(HAS_BATTERY) && entity.getOffhandItem().getItem() == ZombieKitItems.WRENCH.get() && entity.getMainHandItem().getItem() == ZombieKitItems.BATTERY.get()){
            world.setBlock(pos, blockState.setValue(HAS_BATTERY, true).setValue(BATTERY_POWER, 100 - entity.getMainHandItem().getDamageValue()), 3);
            if (!entity.isCreative()){
                entity.getMainHandItem().shrink(1);
            }
            return InteractionResult.SUCCESS;
        }else if (blockState.getValue(HAS_BATTERY) && entity.getOffhandItem().getItem() == ZombieKitItems.WRENCH.get()){
            ItemStack itemStack = new ItemStack(ZombieKitItems.BATTERY.get(), 1);
            itemStack.setDamageValue(100 - blockState.getValue(BATTERY_POWER));
            ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
            itemEntity.setPickUpDelay(10);
            world.addFreshEntity(itemEntity);
            world.setBlock(pos, blockState.setValue(HAS_BATTERY, false).setValue(BATTERY_POWER, 0), 3);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
        world.setBlock(pos, blockstate.setValue(RED_STONE_CONNECTED, world.getBestNeighborSignal(pos) > 0), 3);
        super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 120);
    }

    @Override
    public void tick(BlockState blockState, ServerLevel level, BlockPos blockPos, Random random) {
        super.tick(blockState, level, blockPos, random);
        if (blockState.getValue(HAS_BATTERY) && blockState.getValue(RED_STONE_CONNECTED)){
            int power = blockState.getValue(BATTERY_POWER);
            level.setBlock(blockPos, blockState.setValue(BATTERY_POWER, Math.min(power + 1, 100)), 3);
        }
        level.scheduleTick(blockPos, this, 120);
    }


    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, Random random) {
        if (blockState.getValue(HAS_BATTERY) && blockState.getValue(RED_STONE_CONNECTED) && blockState.getValue(BATTERY_POWER) < 100){
            double d0 = (double)blockPos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;
            double d1 = (double)blockPos.getY() + 0.7D + (random.nextDouble() - 0.5D) * 0.2D;
            double d2 = (double)blockPos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;
            level.addParticle(DustParticleOptions.REDSTONE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }




    @OnlyIn(Dist.CLIENT)
    public static void registerRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(ZombieKitBlocks.CHARGER.get(), renderType -> renderType == RenderType.cutout());
    }

}
