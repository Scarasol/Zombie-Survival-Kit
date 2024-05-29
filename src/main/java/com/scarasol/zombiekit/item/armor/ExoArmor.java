package com.scarasol.zombiekit.item.armor;

import com.scarasol.zombiekit.client.model.ExoSuitModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExoArmor extends ArmorItem {

    public ExoArmor(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @Override
    public void onArmorTick(ItemStack itemStack, Level level, Player player){

        if (getSlot() == EquipmentSlot.CHEST){
            if (player.getLevel() instanceof ServerLevel serverLevel){
                if (!player.isInWaterOrBubble() && !player.isFallFlying() && player.fallDistance > 5 && !player.hasEffect(MobEffects.SLOW_FALLING)){
                    serverLevel.sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 100, 0.5, 0.2, 0.5, 0.1);
                    player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0, false, false));
                }
                if (!player.isOnGround() && player.hasEffect(MobEffects.SLOW_FALLING) && serverLevel.getGameTime() % 10 == 0){
                    serverLevel.sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 5, 0, 0.2, 0, 0.05);
                }
            }

        }
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return true;
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (entity.getLevel() instanceof ServerLevel serverLevel){
            if (entity.isSprinting() && flightTicks % 10 == 0){
                FireworkRocketEntity rocketEntity = new FireworkRocketEntity(serverLevel, stack, entity);
                rocketEntity.setSilent(true);
                serverLevel.addFreshEntity(rocketEntity);
            }else if (entity.isShiftKeyDown()){
                return false;
            }

        }
        return true;
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot slot, String type) {
        return "zombiekit:textures/entities/exo_suit.png";
    }



    public HumanoidModel getArmorModel(LivingEntity livingEntity){
        Map<String, ModelPart> map = new HashMap<>(Map.of(
                "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
        ));
        if (!livingEntity.hasEffect(MobEffects.INVISIBILITY)){
            switch (getSlot()){
                case HEAD -> map.put("head", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).Head);
                case CHEST -> {
                    map.put("body", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).Body);
                    map.put("right_arm", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).RightArm);
                    map.put("left_arm", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).LeftArm);
                }
                case LEGS -> {
                    map.put("right_leg", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).RightLeg);
                    map.put("left_leg", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).LeftLeg);
                }
                default -> {
                    map.put("right_leg", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).RightShoes);
                    map.put("left_leg", new ExoSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ExoSuitModel.LAYER_LOCATION)).LeftShoes);
                }
            }
        }
        return new HumanoidModel(new ModelPart(Collections.emptyList(), Map.copyOf(map)));
    }

    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = ExoArmor.this.getArmorModel(living);
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        });
    }
}
