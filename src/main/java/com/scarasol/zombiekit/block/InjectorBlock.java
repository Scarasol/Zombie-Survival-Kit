package com.scarasol.zombiekit.block;

import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.zombiekit.block.entity.InjectorBlockEntity;
import com.scarasol.zombiekit.init.ZombieKitBlocks;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Vector3f;

import java.util.*;

public class InjectorBlock extends Block implements EntityBlock {

    public static final IntegerProperty BOTTLE0 = IntegerProperty.create("bottle0", 0, 5);
    public static final IntegerProperty BOTTLE1 = IntegerProperty.create("bottle1", 0, 5);
    public static final IntegerProperty BOTTLE2 = IntegerProperty.create("bottle2", 0, 5);
    public static final IntegerProperty BOTTLE3 = IntegerProperty.create("bottle3", 0, 5);
    public static final IntegerProperty COOLDOWN = IntegerProperty.create("cooldown", 0, 5);
    public static final BooleanProperty FULL = BooleanProperty.create("full");
    private IntegerProperty[] bottles = {BOTTLE0, BOTTLE1, BOTTLE2, BOTTLE3};
    private static Map<Item, Integer> items;
    private DustParticleOptions nitricAcid = new DustParticleOptions(new Vector3f(Vec3.fromRGB24(15329627).toVector3f()), 1.0f);
//	private Vector3f glowInkColor = new Vector3f(Vec3.fromRGB24(442879));

    public InjectorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FULL, false).setValue(COOLDOWN, 0).setValue(BOTTLE0, 0).setValue(BOTTLE1, 0).setValue(BOTTLE2, 0).setValue(BOTTLE3, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BOTTLE0);
        builder.add(BOTTLE1);
        builder.add(BOTTLE2);
        builder.add(BOTTLE3);
        builder.add(COOLDOWN);
        builder.add(FULL);
    }

    @Override
    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockstate, world, pos, entity, hand, hit);
        return (rightClickInjector(world, pos, blockstate, entity, hand) ? InteractionResult.SUCCESS : InteractionResult.PASS);
    }

    public static void init(){
        if (items == null){
            items = new HashMap<>();
            items.put(ZombieKitItems.ALCOHOL.get(), 1);
            items.put(Items.POWDER_SNOW_BUCKET, 2);
            items.put(ZombieKitItems.HYDROGEN_NITRATE.get(), 3);
            items.put(Items.GLOW_INK_SAC, 4);
            items.put(Items.SLIME_BALL, 5);
        }
    }

    public boolean rightClickInjector(Level world, BlockPos pos, BlockState blockstate, Player player, InteractionHand hand){
        boolean result = items.containsKey(player.getItemInHand(hand).getItem());
        if (result){
            load(world, pos, blockstate, player.getItemInHand(hand), player, false);
        }
        return result;
    }

    public boolean load(Level world, BlockPos pos, BlockState blockstate, ItemStack itemStack, Player player, boolean auto){
        for (int i = 0; i < 4; i++){
            if (blockstate.getValue(bottles[i]) == 0){
                int cooldown = blockstate.getValue(COOLDOWN);
                if (auto && cooldown > 0){
                    if (i == 3){
                        world.setBlock(pos, blockstate.setValue(COOLDOWN, cooldown - 1).setValue(FULL, true).setValue(bottles[i], items.get(itemStack.getItem())), 3);
                    }else {
                        world.setBlock(pos, blockstate.setValue(COOLDOWN, cooldown - 1).setValue(bottles[i], items.get(itemStack.getItem())), 3);
                    }
                }else {
                    if (i == 3){
                        world.setBlock(pos, blockstate.setValue(FULL, true).setValue(bottles[i], items.get(itemStack.getItem())), 3);
                    }else {
                        world.setBlock(pos, blockstate.setValue(bottles[i], items.get(itemStack.getItem())), 3);
                    }
                }
                if (player != null && !player.getAbilities().instabuild){
                    if (itemStack.is(Items.POWDER_SNOW_BUCKET)){
                        ItemStack setstack = new ItemStack(Items.BUCKET);
                        setstack.setCount(1);
                        ItemHandlerHelper.giveItemToPlayer(player, setstack);
                    }
                    itemStack.setCount(itemStack.getCount() - 1);
                }
                return true;
            }
        }
        return false;
    }

    public boolean autoLoad(Level world, BlockPos pos, BlockState blockstate){
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof InjectorBlockEntity _injectorBlockEntity){
            InjectorBlockEntity injector = _injectorBlockEntity;
            NonNullList<ItemStack> itemLoad = injector.getLoadItems();
            ItemStack itemStack = itemLoad.get(0);
            if (!itemStack.isEmpty() && load(world, pos, blockstate, itemStack, null, true)){
                injector.clearItems();
                return true;
            }
        }
        return false;
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
        super.stepOn(world, pos, blockstate, entity);
        stepOnInjector(world, pos, blockstate, entity);
    }

    public void stepOnInjector(Level world, BlockPos pos, BlockState blockstate, Entity entity){
        if (blockstate.getValue(COOLDOWN) == 0 && entity instanceof LivingEntity _living){
            LivingEntity livingEntity = _living;
            for (int i = 3; i >= 0; i--){
                if (blockstate.getValue(bottles[i]) != 0){
                    if (i == 3){
                        world.setBlock(pos, blockstate.setValue(bottles[i], 0).setValue(FULL, false).setValue(COOLDOWN, 2), 3);
                    }else {
                        world.setBlock(pos, blockstate.setValue(bottles[i], 0).setValue(COOLDOWN, 2), 3);
                    }
                    releaseInjector(world, pos, blockstate, livingEntity, blockstate.getValue(bottles[i]));
                    break;
                }
            }
        }
    }

    public void releaseInjector(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, int value){
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 1.5;
        double z = pos.getZ() + 0.5;
        if (world instanceof ServerLevel _level){
            ServerLevel server = _level;
            spawnAreaEffectCloud(server, value, x, y - 0.5, z);
            if (value == 1){
                world.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1, 1);
                server.sendParticles(ParticleTypes.LAVA, x, y, z, 100, 0, 1, 0, 1);
                entity.addEffect(new MobEffectInstance(SonaMobEffects.IGNITION.get(), 140, 3));
            }else if (value == 2){
                world.playSound(null, pos, SoundEvents.POWDER_SNOW_BREAK, SoundSource.BLOCKS, 1, 1);
                server.sendParticles(ParticleTypes.SNOWFLAKE, x, y, z, 500, 0, 0.5, 0, 0.1);
                entity.addEffect(new MobEffectInstance(SonaMobEffects.FROST.get(), 140, 3));
            }else if (value == 3){
                world.playSound(null, pos, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:acid_spray")), SoundSource.BLOCKS, 1, 1);
                server.sendParticles(nitricAcid, x, y, z, 500, 0.2, 0.5, 0.2, 0.1);
                entity.addEffect(new MobEffectInstance(SonaMobEffects.CORROSION.get(), 400, 2));
            }else if (value == 4){
                world.playSound(null, pos, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:acid_spray")), SoundSource.BLOCKS, 1, 1);
                server.sendParticles(ParticleTypes.GLOW_SQUID_INK, x, y, z, 100, 0, 0.5, 0, 0.1);
                entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1200, 0));
                entity.addEffect(new MobEffectInstance(SonaMobEffects.CONFUSION.get(), 90, 0));
            }else if (value == 5){
                world.playSound(null, pos, SoundEvents.SLIME_ATTACK, SoundSource.BLOCKS, 1, 1);
                server.sendParticles(ParticleTypes.ITEM_SLIME, x, y, z, 500, 0, 0.5, 0, 0.1);
                entity.addEffect(new MobEffectInstance(SonaMobEffects.SLIMINESS.get(), 600, 0));
            }
        }
    }

    public void spawnAreaEffectCloud(Level world, int value, double x, double y, double z){
        AreaEffectCloud areaEffectCloud = new AreaEffectCloud(world, x, y, z);
        areaEffectCloud.setRadius(0.5f);
        areaEffectCloud.setDuration(60);
        areaEffectCloud.setRadiusPerTick((1.5f - areaEffectCloud.getRadius()) / (float)areaEffectCloud.getDuration());
        if (value == 1){
            areaEffectCloud.setParticle(ParticleTypes.FLAME);
            areaEffectCloud.addEffect(new MobEffectInstance(SonaMobEffects.IGNITION.get(), 140, 3));
        }else if (value == 2){
            areaEffectCloud.setParticle(ParticleTypes.SNOWFLAKE);
            areaEffectCloud.addEffect(new MobEffectInstance(SonaMobEffects.FROST.get(), 140, 3));
        }else if (value == 3){
            areaEffectCloud.setParticle(nitricAcid);
            areaEffectCloud.addEffect(new MobEffectInstance(SonaMobEffects.CORROSION.get(), 400, 2));
        }else if (value == 4){
            areaEffectCloud.setParticle(ParticleTypes.GLOW_SQUID_INK);
            areaEffectCloud.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1200, 0));
            areaEffectCloud.addEffect(new MobEffectInstance(SonaMobEffects.CONFUSION.get(), 90, 0));
        }else if (value == 5){
            areaEffectCloud.setParticle(ParticleTypes.ITEM_SLIME);
            areaEffectCloud.addEffect(new MobEffectInstance(SonaMobEffects.SLIMINESS.get(), 600, 0));
        }
        world.addFreshEntity(areaEffectCloud);
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(blockstate, world, pos, random);
        int cooldown = blockstate.getValue(COOLDOWN);
        if (!blockstate.getValue(FULL)){
            if (!autoLoad(world, pos, blockstate) && cooldown > 0){
                world.setBlock(pos, blockstate.setValue(COOLDOWN, cooldown - 1), 3);
            }
        }else if (cooldown > 0){
            world.setBlock(pos, blockstate.setValue(COOLDOWN, cooldown - 1), 3);
        }
        world.scheduleTick(pos, this, 20);
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 20);
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
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
            return tieredItem.getTier().getLevel() >= 0;
        return false;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new InjectorBlockEntity(pos, state);
    }

    @Override
    public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
        super.triggerEvent(state, world, pos, eventID, eventParam);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof InjectorBlockEntity be) {
                Containers.dropContents(world, pos, be);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
        BlockEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof InjectorBlockEntity be)
            return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
        else
            return 0;
    }
}
