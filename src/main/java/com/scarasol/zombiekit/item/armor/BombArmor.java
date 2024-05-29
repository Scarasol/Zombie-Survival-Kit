package com.scarasol.zombiekit.item.armor;

import com.scarasol.zombiekit.client.model.BombSuitModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import java.util.UUID;

public class BombArmor extends ArmorItem {

    public static AttributeModifier ATTACK_SPEED = new AttributeModifier(UUID.fromString("0753E5E5-B0B5-6828-0B38-350A5885B144"), "bombArmorAttackSpeed", -0.3, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static AttributeModifier SLOWNESS = new AttributeModifier(UUID.fromString("CE35FE84-FF47-B780-0EC6-9164DCF1CED6"), "bombArmorSlowness", -0.3, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static AttributeModifier HEALTH_BOOST = new AttributeModifier(UUID.fromString("bec5f226-4b78-5014-b906-2fb397957925"), "bombArmorHealthBoost", 20, AttributeModifier.Operation.ADDITION);

    public BombArmor(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @Override
    public void onArmorTick(ItemStack itemStack, Level level, Player player){
        if (getSlot() == EquipmentSlot.CHEST){

        }
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot slot, String type) {
        return "zombiekit:textures/entities/bomb_suit.png";
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
            case HEAD -> map.put("head", new BombSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BombSuitModel.LAYER_LOCATION)).Head);
            case CHEST -> {
                map.put("body", new BombSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BombSuitModel.LAYER_LOCATION)).Body);
                map.put("right_arm", new BombSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BombSuitModel.LAYER_LOCATION)).RightArm);
                map.put("left_arm", new BombSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BombSuitModel.LAYER_LOCATION)).LeftArm);
            }
            case LEGS -> {
                map.put("right_leg", new BombSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BombSuitModel.LAYER_LOCATION)).RightLeg);
                map.put("left_leg", new BombSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BombSuitModel.LAYER_LOCATION)).LeftLeg);
            }
            default -> {
                map.put("right_leg", new BombSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BombSuitModel.LAYER_LOCATION)).RightShoes);
                map.put("left_leg", new BombSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BombSuitModel.LAYER_LOCATION)).LeftShoes);
            }
        }
        return new HumanoidModel(new ModelPart(Collections.emptyList(), Map.copyOf(map)));
    }

    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = BombArmor.this.getArmorModel();
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        });
    }

    public static void updateModifier(LivingEntity livingEntity){
        int count = numberOfSuit(livingEntity);
        if (count == 0){
            livingEntity.getAttribute(Attributes.MAX_HEALTH).removeModifier(HEALTH_BOOST);
            livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SLOWNESS);
            if (livingEntity instanceof Player)
                livingEntity.getAttribute(Attributes.ATTACK_SPEED).removeModifier(ATTACK_SPEED);
        }else if (count == 4){
            livingEntity.getAttribute(Attributes.MAX_HEALTH).removeModifier(HEALTH_BOOST);
            livingEntity.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(HEALTH_BOOST);
        }else if (count == 1){
            livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SLOWNESS);
            livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(SLOWNESS);
            if (livingEntity instanceof Player){
                livingEntity.getAttribute(Attributes.ATTACK_SPEED).removeModifier(ATTACK_SPEED);
                livingEntity.getAttribute(Attributes.ATTACK_SPEED).addPermanentModifier(ATTACK_SPEED);

            }
        }else if (count == 3){
            livingEntity.getAttribute(Attributes.MAX_HEALTH).removeModifier(HEALTH_BOOST);
        }
    }

    public static int numberOfSuit(LivingEntity livingEntity){
        int count = 0;
        for (ItemStack itemStack : livingEntity.getArmorSlots()){
            if (itemStack.getItem() instanceof BombArmor)
                count++;
        }
        return count;
    }
}
