package com.scarasol.zombiekit.block;

import com.scarasol.zombiekit.init.ZombieKitBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class SpreadLight extends Block {

    public static final EnumProperty<Coordinate> X = EnumProperty.create("x", Coordinate.class);
    public static final EnumProperty<Coordinate> Y = EnumProperty.create("y", Coordinate.class);
    public static final EnumProperty<Coordinate> Z = EnumProperty.create("z", Coordinate.class);
    public static final IntegerProperty LIGHT_LEVEL = IntegerProperty.create("light_level", 1, 7);

    public SpreadLight(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(X);
        builder.add(Y);
        builder.add(Z);
        builder.add(LIGHT_LEVEL);
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState oldState, boolean moving) {
        super.onPlace(blockState, level, blockPos, oldState, moving);
        if (blockState.getBlock() != oldState.getBlock()){
            int x = Integer.parseInt(blockState.getValue(X).toString()) - 3;
            int y = Integer.parseInt(blockState.getValue(Y).toString()) - 3;
            int z = Integer.parseInt(blockState.getValue(Z).toString()) - 3;
            spreadLight(level, blockPos, x, y, z, blockState.getValue(LIGHT_LEVEL));
        }
        level.scheduleTick(blockPos, this, 50);
    }

    @Override
    public void tick(BlockState blockState, ServerLevel level, BlockPos blockPos, Random random) {
        super.tick(blockState, level, blockPos, random);
        int x = Integer.parseInt(blockState.getValue(X).toString()) - 3;
        int y = Integer.parseInt(blockState.getValue(Y).toString()) - 3;
        int z = Integer.parseInt(blockState.getValue(Z).toString()) - 3;
        checkFather(level, blockPos, x, y, z);
        spreadLight(level, blockPos, x, y, z, blockState.getValue(LIGHT_LEVEL));
        level.scheduleTick(blockPos, this, 50);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    public void spreadLight(Level level, BlockPos blockPos, int x, int y, int z, int lightLevel){
        if (Math.abs(x * (lightLevel + 1)) > 3 || Math.abs(y * (lightLevel + 1)) > 3 || Math.abs(z * (lightLevel + 1)) > 3)
            return;
        BlockPos child = blockPos.offset(x, y, z);
        if (level.getBlockState(child).getBlock() instanceof SpreadLight)
            return;
        if (level.getBlockState(child).getMaterial() == Material.AIR)
            level.setBlock(child, ZombieKitBlocks.SPREAD_LIGHT.get().defaultBlockState()
                    .setValue(X, Coordinate.getByInt(x)).setValue(Y, Coordinate.getByInt(y)).setValue(Z, Coordinate.getByInt(z))
                    .setValue(LIGHT_LEVEL, Math.min(lightLevel + 1, 7)), 3);
    }

    public void checkFather(Level level, BlockPos blockPos, int x, int y, int z){
        BlockPos father = blockPos.offset(-x, -y, -z);
        if (level.getBlockState(father).getLightBlock(level, father) != 0 || level.getBlockState(father).getBlock() instanceof AirBlock)
            level.destroyBlock(blockPos, false);
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return true;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(ZombieKitBlocks.SPREAD_LIGHT.get(), renderType -> renderType == RenderType.cutout());
    }


    enum Coordinate implements StringRepresentable {
        NegativeThree("0"),
        NegativeTwo("1"),
        NegativeOne("2"),
        Zero("3"),
        One("4"),
        Two("5"),
        Three("6");

        private final String coordinate;

        private Coordinate(String coordinate) {
            this.coordinate = coordinate;
        }

        public String toString() {
            return this.coordinate;
        }

        public String getSerializedName() {
            return this.coordinate;
        }

        public static Coordinate getByInt(int i){
            return switch (i){
                case -3 -> NegativeThree;
                case -2 -> NegativeTwo;
                case -1 -> NegativeOne;
                case 1 -> One;
                case 2 -> Two;
                case 3 -> Three;
                default -> Zero;
            };
        }
    }



}
