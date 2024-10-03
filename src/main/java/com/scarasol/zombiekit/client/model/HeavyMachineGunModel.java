package com.scarasol.zombiekit.client.model;

import com.scarasol.zombiekit.entity.mechanics.HeavyMachineGunEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class HeavyMachineGunModel extends GeoModel<HeavyMachineGunEntity> {


    @Override
    public ResourceLocation getAnimationResource(HeavyMachineGunEntity entity) {
        return new ResourceLocation("zombiekit", "animations/m2_machinegun_bedrock.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(HeavyMachineGunEntity entity) {
        return new ResourceLocation("zombiekit", "geo/m2_machinegun_bedrock.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HeavyMachineGunEntity entity) {
        return new ResourceLocation("zombiekit", "textures/entities/" + entity.getTexture() + ".png");
    }

    @Override
    public void setCustomAnimations(HeavyMachineGunEntity animatable, long instanceId, AnimationState animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        if (head != null) {
            EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }

    }

}

