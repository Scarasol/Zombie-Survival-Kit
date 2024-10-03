package com.scarasol.zombiekit.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.scarasol.zombiekit.client.model.HeavyMachineGunModel;
import com.scarasol.zombiekit.entity.mechanics.HeavyMachineGunEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HeavyMachineGunRenderer extends GeoEntityRenderer<HeavyMachineGunEntity> {
    public HeavyMachineGunRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HeavyMachineGunModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public RenderType getRenderType(HeavyMachineGunEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void preRender(PoseStack poseStack, HeavyMachineGunEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red,
                          float green, float blue, float alpha) {
        float scale = 1f;
        this.scaleHeight = scale;
        this.scaleWidth = scale;
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}

