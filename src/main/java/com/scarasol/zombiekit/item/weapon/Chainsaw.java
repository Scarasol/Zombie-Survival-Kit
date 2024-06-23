package com.scarasol.zombiekit.item.weapon;

import com.scarasol.zombiekit.config.CommonConfig;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Chainsaw extends Item {
    public Chainsaw(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.chainsaw.description_1").getString()));
        list.add(new TextComponent(new TranslatableComponent("item.zombiekit.chainsaw.description_2").getString()));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!world.isClientSide()) {
            if (!itemStack.getOrCreateTag().getBoolean("Power")){
                if (player.isShiftKeyDown()){
                    ItemStack itemOther;
                    if (hand == InteractionHand.MAIN_HAND){
                        itemOther = player.getItemInHand(InteractionHand.OFF_HAND);
                    }else {
                        itemOther = player.getItemInHand(InteractionHand.MAIN_HAND);
                    }
                    if (itemOther.is(ZombieKitItems.BATTERY.get())){
                        int damage = itemOther.getDamageValue();
                        itemOther.setDamageValue(itemStack.getDamageValue());
                        itemStack.setDamageValue(damage);
                        player.getCooldowns().addCooldown(itemStack.getItem(), 140);
                        return new InteractionResultHolder(InteractionResult.PASS, itemStack);
                    }
                }else if (itemStack.getDamageValue() >= 100){
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:chainsaw_start_failed")), SoundSource.PLAYERS, 1, 1);
                    return new InteractionResultHolder(InteractionResult.PASS, itemStack);
                }else {
                    itemStack.getOrCreateTag().putBoolean("Power", true);
                    itemStack.getOrCreateTag().putInt("CustomModelData", 1);
                }
            }else if (player.isShiftKeyDown()){
                itemStack.getOrCreateTag().putBoolean("Power", false);
                itemStack.getOrCreateTag().putInt("CustomModelData", 0);
            }else {
                player.startUsingItem(hand);
            }
        }
        return new InteractionResultHolder(InteractionResult.SUCCESS, itemStack);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (entity instanceof LivingEntity livingEntity){
            ItemStack offHandItem = livingEntity.getOffhandItem();
            if ((selected || offHandItem == itemstack) && itemstack.getOrCreateTag().getBoolean("Power")){
                if (world.getGameTime() % 10 == 0){
                    if (livingEntity.isUsingItem() && livingEntity.getUseItem().is(ZombieKitItems.CHAINSAW.get())){
                        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:chainsaw_attack")), SoundSource.PLAYERS, 0.6f, 1);
                    }else {
                        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:chainsaw_idle")), SoundSource.PLAYERS, 0.3f, 1);
                    }
                }
                if (!world.isClientSide() && livingEntity instanceof Player player && !player.getAbilities().instabuild){
                    if (world.getGameTime() % CommonConfig.CHAINSAW_POWER.get() == 0) {
                        if (itemstack.hurt(4, world.getRandom(), null)) {
                            itemstack.shrink(1);
                        }

                        if (itemstack.getDamageValue() >= 100){
                            itemstack.getOrCreateTag().putBoolean("Power", false);
                            itemstack.getOrCreateTag().putInt("CustomModelData", 0);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int duration) {
        double x = livingEntity.getX();
        double y = livingEntity.getY();
        double z = livingEntity.getZ();
        Vec3 vec = livingEntity.getViewVector(1);
        Vec3 _center;
        if (vec.y < 0){
            _center = new Vec3(x + vec.x * 1.5, y + vec.y * 0.5, z + vec.z * 1.5);
        }else {
            _center = new Vec3(x + vec.x * 1.5, y + vec.y + 1, z + vec.z * 1.5);
        }
        List<Entity> _entfound = level.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(0.9d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
        for (Entity entityiterator : _entfound) {
            if (!livingEntity.equals(entityiterator)){
                entityiterator.invulnerableTime = 0;
                entityiterator.getPersistentData().putBoolean("CancelKnockback", true);
                entityiterator.hurt(DamageSource.mobAttack(livingEntity), CommonConfig.CHAINSAW_DAMAGE.get().floatValue());
            }
        }
    }

    @Override
    public boolean isValidRepairItem(ItemStack itemStack, ItemStack itemStack2) {
        return itemStack2.is(ZombieKitItems.BATTERY.get());
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 72000;
    }
}
