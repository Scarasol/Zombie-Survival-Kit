package com.scarasol.zombiekit.block;

import com.scarasol.zombiekit.init.ZombieKitBlocks;
import com.scarasol.zombiekit.init.ZombieKitTags;
import com.scarasol.zombiekit.network.MapVariables;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class ShortwaveRadioBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty TURN_ON = BooleanProperty.create("turn_on");
    public static final IntegerProperty TIME = IntegerProperty.create("time", 0, 1200);

    private static final Set<BlockPos> workRadios = new HashSet<>();
    private final Map<Mob, BlockPos> survivorsNeedMove = new HashMap<>();

    public ShortwaveRadioBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TIME, 0).setValue(TURN_ON, false));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent(new TranslatableComponent("block.zombiekit.shortwave_radio.description_1").getString()));
        list.add(new TextComponent(new TranslatableComponent("block.zombiekit.shortwave_radio.description_2").getString()));
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, Random random) {
        super.tick(blockstate, world, pos, random);
        moveSurvivors();
        if (blockstate.getValue(TURN_ON)){
            world.playSound(null, pos, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:radio_static")), SoundSource.BLOCKS, 1, 1);
            int time = blockstate.getValue(TIME);
            if (world.canSeeSkyFromBelowWater(pos) && world.isDay() && !world.getLevelData().isThundering()){
                if (time < 600){
                    if (random.nextDouble() < 0.000001 * time){
                        if (spawnSurvivors(world, pos, random))
                            world.setBlock(pos, blockstate.setValue(TIME, 0), 3);
                    }
                    world.setBlock(pos, blockstate.setValue(TIME, time + 1), 3);
                }else if (time < 1200) {
                    if (random.nextDouble() < 0.0006 + 0.000015 * (time - 600)){
                        if (spawnSurvivors(world, pos, random))
                            world.setBlock(pos, blockstate.setValue(TIME, 0), 3);
                    }
                    world.setBlock(pos, blockstate.setValue(TIME, time + 1), 3);
                }else {
                    if (random.nextDouble() < 0.01){
                        if (spawnSurvivors(world, pos, random))
                            world.setBlock(pos, blockstate.setValue(TIME, 0), 3);
                    }
                }
            }

        }
        world.scheduleTick(pos, this, 20);
    }

    public static BlockPos findNearestRadio(BlockPos pos, Level world){
        if (workRadios.size() == 0)
            return null;
        BlockPos nearest = null;
        int nearestDistance = Integer.MAX_VALUE;
        int x = pos.getX();
        int z = pos.getZ();
        for (BlockPos radio : workRadios){
            if (!(world.getBlockState(radio).getBlock() == ZombieKitBlocks.SHORTWAVE_RADIO.get())){
                removeRadio(radio, world);
                continue;
            }
            int distance = Math.abs(x - radio.getX()) + Math.abs(z - radio.getZ());
            if (distance < nearestDistance){
                nearestDistance = distance;
                nearest = radio;
            }
        }
        return nearest;
    }

    public static void saveRadioString(Level world){
        String workRadioToString = "";
        if (workRadios.size() != 0){
            for (BlockPos pos : workRadios){
                String buffer = pos.getX() + "," + pos.getY() + "," + pos.getZ() + ";";
                workRadioToString += buffer;
            }
        }
        MapVariables.get(world).radio_location = workRadioToString;
        MapVariables.get(world).syncData(world);
    }

    public static void loadRadioString(LevelAccessor world){
        String workRadioToString = MapVariables.get(world).radio_location;
        if (!"".equals(workRadioToString)){
            for (String posStr : workRadioToString.split(";")){
                String[] pos = posStr.split(",");
                workRadios.add(new BlockPos(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), Integer.parseInt(pos[2])));
            }
        }
    }

    public static void addRadio(BlockPos pos, Level world){
        workRadios.add(pos);
        saveRadioString(world);
    }

    public static void removeRadio(BlockPos pos, Level world){
        workRadios.remove(pos);
        saveRadioString(world);
    }

    public boolean spawnSurvivors(ServerLevel world, BlockPos pos, Random random){
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int tx;
        int tz;
        int dx;
        int dz;
        int dr;
        for (int index0 = 0; index0 < 100; index0++) {
            dr = Mth.nextInt(new Random(), 48, 64);
            dx = Mth.nextInt(new Random(), 0, dr);
            dr = dr - dx;
            dz = dr;
            if (Mth.nextInt(new Random(), 1, 2) == 1) {
                tx = x + dx;
            } else {
                tx = x - dx;
            }
            if (Mth.nextInt(new Random(), 1, 2) == 1) {
                tz = z + dz;
            } else {
                tz = z - dz;
            }

            BlockPos spawnPos = world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, new BlockPos(tx, y, tz));
            BlockState blockState = world.getBlockState(spawnPos);
            BlockState blockState2 = world.getBlockState(spawnPos.below());
            if (!NaturalSpawner.isValidEmptySpawnBlock(world, spawnPos, blockState, blockState.getFluidState(), EntityType.PILLAGER) && blockState2.isValidSpawn(world, spawnPos.below(), EntityType.VILLAGER)) {
                continue;
            }
            if (spawnPillagers(world, pos, spawnPos, random)){
                return true;
            }
            Entity entity = ForgeRegistries.ENTITIES.tags().getTag(ZombieKitTags.SURVIVORS).getRandomElement(new Random()).get().spawn(world, null, null, spawnPos, MobSpawnType.REINFORCEMENT, false, false);
            if (entity instanceof Mob _living){
                survivorsNeedMove.put(_living, pos);
            }
            return true;
        }
        return false;
    }

    public boolean spawnPillagers(ServerLevel world, BlockPos radioPos, BlockPos spawnPos, Random random){
        switch (world.getDifficulty()){
            case PEACEFUL:
                return false;
            case EASY, NORMAL:
                if (random.nextDouble() < 0.02){
                    for (int i = 0; i < 5; i++){
                        PatrollingMonster patrollingMonster = EntityType.PILLAGER.create(world);
                        if (i == 0){
                            patrollingMonster.setPatrolLeader(true);
                            patrollingMonster.setPatrolTarget(radioPos);
                        }
                        patrollingMonster.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                        patrollingMonster.finalizeSpawn(world, world.getCurrentDifficultyAt(radioPos), MobSpawnType.PATROL, null, null);
                        world.addFreshEntityWithPassengers(patrollingMonster);
                    }
                    return true;
                }
                break;
            case HARD:
                if (random.nextDouble() < 0.05){
                    for (int i = 0; i < 8; i++){
                        PatrollingMonster patrollingMonster;
                        if (random.nextDouble() < 0.3){
                            patrollingMonster = EntityType.VINDICATOR.create(world);
                        }else {
                            patrollingMonster = EntityType.PILLAGER.create(world);
                        }
                        if (i == 0){
                            patrollingMonster.setPatrolLeader(true);
                            patrollingMonster.setPatrolTarget(radioPos);
                        }
                        patrollingMonster.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                        patrollingMonster.finalizeSpawn(world, world.getCurrentDifficultyAt(radioPos), MobSpawnType.PATROL, null, null);
                        world.addFreshEntityWithPassengers(patrollingMonster);
                    }
                    return true;
                }
                break;
        }
        return false;
    }

    public void moveSurvivors(){
        if (!survivorsNeedMove.isEmpty()){
            for(Map.Entry<Mob, BlockPos> survivor : survivorsNeedMove.entrySet()){
                int x = survivor.getValue().getX();
                int y = survivor.getValue().getY();
                int z = survivor.getValue().getZ();
                Mob mob = survivor.getKey();
                mob.getNavigation().moveTo(x, y, z, 0.8);
            }
            survivorsNeedMove.clear();
        }
    }

    @Override
    public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block block, BlockPos blockPos2, boolean bl) {
        boolean bl2 = world.hasNeighborSignal(pos) || world.hasNeighborSignal(pos.above());
        if (!bl2 && blockstate.getValue(TURN_ON)){
            removeRadio(pos, world);
            if (blockstate.getValue(TIME) == 0){
                world.setBlock(pos, blockstate.setValue(TURN_ON, false).setValue(TIME, 1), 3);
            }else {
                world.setBlock(pos, blockstate.setValue(TURN_ON, false).setValue(TIME, 0), 3);
            }
        }else if (bl2 && !blockstate.getValue(TURN_ON)){
            addRadio(pos, world);
            world.setBlock(pos, blockstate.setValue(TURN_ON, true), 3);
            world.playSound(null, pos, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:radio_static")), SoundSource.BLOCKS, 1, 1);
        }
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 20);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            if(state.getValue(TURN_ON))
                removeRadio(pos, world);
            super.onRemove(state, world, pos, newState, isMoving);
        }else if(state.getValue(TURN_ON) && !newState.getValue(TURN_ON)){
            removeRadio(pos, world);
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
        return switch (state.getValue(FACING)) {
            default -> box(0, 0, 0, 16, 12, 16);
            case NORTH -> box(0, 0, 0, 16, 12, 16);
            case EAST -> box(0, 0, 0, 16, 12, 16);
            case WEST -> box(0, 0, 0, 16, 12, 16);
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(TIME);
        builder.add(TURN_ON);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getLevel().hasNeighborSignal(context.getClickedPos()) || context.getLevel().hasNeighborSignal(context.getClickedPos().above())){
            addRadio(context.getClickedPos(), context.getLevel());
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(TURN_ON, true);
        }
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(TURN_ON, false);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
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

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(ZombieKitBlocks.SHORTWAVE_RADIO.get(), renderType -> renderType == RenderType.cutout());
    }
}

