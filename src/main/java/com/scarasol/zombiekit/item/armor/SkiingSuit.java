package com.scarasol.zombiekit.item.armor;

import com.scarasol.zombiekit.client.model.SkiingSuitModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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

public class SkiingSuit extends ArmorItem {


    public SkiingSuit(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @Override
    public void onArmorTick(ItemStack itemStack, Level level, Player player){
        if (getSlot() == EquipmentSlot.HEAD){
            player.removeEffect(MobEffects.BLINDNESS);
        }
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack itemStack, LivingEntity livingEntity) {
        return getSlot() == EquipmentSlot.FEET;
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot slot, String type) {
        return "zombiekit:textures/entities/skiing_suit.png";
    }

    public HumanoidModel getArmorModel(){
        Map<String, ModelPart> map = new HashMap<>(Map.of(
                "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
        ));
        switch (getSlot()){
            case HEAD -> map.put("head", new SkiingSuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(SkiingSuitModel.LAYER_LOCATION)).Head);
            case CHEST -> {
                map.put("body", new SkiingSuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(SkiingSuitModel.LAYER_LOCATION)).Body);
                map.put("right_arm", new SkiingSuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(SkiingSuitModel.LAYER_LOCATION)).RightArm);
                map.put("left_arm", new SkiingSuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(SkiingSuitModel.LAYER_LOCATION)).LeftArm);
            }
            case LEGS -> {
                map.put("right_leg", new SkiingSuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(SkiingSuitModel.LAYER_LOCATION)).RightLeg);
                map.put("left_leg", new SkiingSuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(SkiingSuitModel.LAYER_LOCATION)).LeftLeg);
            }
            default -> {
                map.put("right_leg", new SkiingSuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(SkiingSuitModel.LAYER_LOCATION)).RightShoes);
                map.put("left_leg", new SkiingSuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(SkiingSuitModel.LAYER_LOCATION)).LeftShoes);
            }
        }
        return new HumanoidModel(new ModelPart(Collections.emptyList(), map));
    }

    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = SkiingSuit.this.getArmorModel();
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        });
    }


}
