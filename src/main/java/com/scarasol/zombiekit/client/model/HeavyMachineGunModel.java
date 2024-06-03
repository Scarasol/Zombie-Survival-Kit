package com.scarasol.zombiekit.client.model;

import com.scarasol.zombiekit.entity.mechanics.HeavyMachineGunEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class HeavyMachineGunModel extends AnimatedGeoModel<HeavyMachineGunEntity> {
    @Override
    public ResourceLocation getAnimationFileLocation(HeavyMachineGunEntity entity) {
        return new ResourceLocation("zombiekit", "animations/m2_machinegun_bedrock.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(HeavyMachineGunEntity entity) {
        return new ResourceLocation("zombiekit", "geo/m2_machinegun_bedrock.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HeavyMachineGunEntity entity) {
        return new ResourceLocation("zombiekit", "textures/entities/" + entity.getTexture() + ".png");
    }

    @Override
    public void setCustomAnimations(HeavyMachineGunEntity animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = this.getAnimationProcessor().getBone("head");
        EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        AnimationData manager = animatable.getFactory().getOrCreateAnimationData(instanceId);
        int unpausedMultiplier = !Minecraft.getInstance().isPaused() || manager.shouldPlayWhilePaused ? 1 : 0;
        head.setRotationX(head.getRotationX() + extraData.headPitch * ((float) Math.PI / 180F) * unpausedMultiplier);
        head.setRotationY(head.getRotationY() + extraData.netHeadYaw * ((float) Math.PI / 180F) * unpausedMultiplier);
    }
}

