package com.scarasol.zombiekit.item;

import com.scarasol.zombiekit.block.ShortwaveRadioBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraftforge.registries.ForgeRegistries;

public class PocketRadio extends Item {
    public static final TagKey<ConfiguredStructureFeature<?, ?>> SHELTER = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation("zombiekit:shelter"));

    public PocketRadio(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getObject();
        findNearestShelter(entity, itemstack);
        return ar;
    }

    public void findNearestShelter(Player entity, ItemStack itemstack) {
        if (entity == null)
            return;
        if (!entity.level.isClientSide){
            ServerLevel level = (ServerLevel) entity.level;
            BlockPos blockpos;
            BlockPos entityPos = new BlockPos(entity.getBlockX(), entity.getBlockY(), entity.getBlockZ());
            BlockPos structurePos = level.findNearestMapFeature(SHELTER, entityPos, 100, false);
            BlockPos shortwaveRadio = ShortwaveRadioBlock.findNearestRadio(entityPos, level);
            double distance1 = Double.MAX_VALUE;
            double distance2 = Double.MAX_VALUE;
            double distance;
            if (structurePos != null){
                distance1 = Math.pow(entity.getX() - structurePos.getX(), 2) + Math.pow(entity.getZ() - structurePos.getZ(), 2);
            }
            if (shortwaveRadio != null){
                distance2 = Math.pow(entity.getX() - shortwaveRadio.getX(), 2) + Math.pow(entity.getZ() - shortwaveRadio.getZ(), 2);
            }
            if (distance1 < distance2){
                distance = distance1;
                blockpos = structurePos;
            }else {
                distance = distance2;
                blockpos = shortwaveRadio;
            }
            if (distance <= 250000 && blockpos != null){
                entity.sendMessage(new TranslatableComponent("zombiekit.message.response1", blockpos.getX(), blockpos.getZ()), entity.getUUID());
                level.playSound(null, entityPos, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:radio_response")), SoundSource.PLAYERS, 1.2F, 1);
            } else {
                entity.sendMessage(new TranslatableComponent("zombiekit.message.response_none"), entity.getUUID());
                level.playSound(null, entityPos, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zombiekit:radio_static_long")), SoundSource.PLAYERS, 1, 1);
            }
            entity.getCooldowns().addCooldown(itemstack.getItem(), 100);
        }
    }
}
