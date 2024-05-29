package com.scarasol.zombiekit.item.armor;

import com.scarasol.zombiekit.client.model.RiotSuitModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RiotArmor extends CamouflageArmor{
    public RiotArmor(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties, int camouflage) {
        super(armorMaterial, equipmentSlot, properties, camouflage);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot slot, String type) {
        switch (getCamouflage()){
            case 1 -> {
                return "zombiekit:textures/entities/riot_suit_desert.png";
            }
            case 2 -> {
                return "zombiekit:textures/entities/riot_suit_forest.png";
            }
            case 3 -> {
                return "zombiekit:textures/entities/riot_suit_snow.png";
            }
            default -> {
                return "zombiekit:textures/entities/riot_suit_standard.png";
            }
        }
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
            case HEAD -> map.put("head", new RiotSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RiotSuitModel.LAYER_LOCATION)).Head);
            case CHEST -> {
                map.put("body", new RiotSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RiotSuitModel.LAYER_LOCATION)).Body);
                map.put("right_arm", new RiotSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RiotSuitModel.LAYER_LOCATION)).RightArm);
                map.put("left_arm", new RiotSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RiotSuitModel.LAYER_LOCATION)).LeftArm);
            }
            case LEGS -> {
                map.put("right_leg", new RiotSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RiotSuitModel.LAYER_LOCATION)).RightLeg);
                map.put("left_leg", new RiotSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RiotSuitModel.LAYER_LOCATION)).LeftLeg);
            }
            default -> {
                map.put("right_leg", new RiotSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RiotSuitModel.LAYER_LOCATION)).RightShoes);
                map.put("left_leg", new RiotSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RiotSuitModel.LAYER_LOCATION)).LeftShoes);
            }
        }
        return new HumanoidModel(new ModelPart(Collections.emptyList(), Map.copyOf(map)));
    }

    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = RiotArmor.this.getArmorModel();
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        });
    }
}
