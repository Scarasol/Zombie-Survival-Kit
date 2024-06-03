package com.scarasol.zombiekit.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.scarasol.zombiekit.client.model.HeavyMachineGunModel;
import com.scarasol.zombiekit.entity.mechanics.HeavyMachineGunEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HeavyMachineGunRenderer extends GeoEntityRenderer<HeavyMachineGunEntity> {
    public HeavyMachineGunRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HeavyMachineGunModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public RenderType getRenderType(HeavyMachineGunEntity entity, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        stack.scale(1f, 1f, 1f);
        return RenderType.entityTranslucent(getTextureLocation(entity));
    }
}

