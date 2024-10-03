package com.scarasol.zombiekit.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.scarasol.zombiekit.client.model.UVlampModel;
import com.scarasol.zombiekit.entity.mechanics.UvLampEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class UvLampRenderer extends MobRenderer<UvLampEntity, UVlampModel<UvLampEntity>> {
    public UvLampRenderer(EntityRendererProvider.Context context) {
        super(context, new UVlampModel(context.bakeLayer(UVlampModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public void render(UvLampEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
        poseStack.scale(1.2f, 1.2f, 1.2f);
        super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
    }


    @Override
    public ResourceLocation getTextureLocation(UvLampEntity entity) {
        if(entity.getPower() > 75 && entity.isHasBattery())return new ResourceLocation("zombiekit:textures/entities/uv_lamp_o1.png");
        else if(entity.getPower() > 50 && entity.isHasBattery())return new ResourceLocation("zombiekit:textures/entities/uv_lamp_o2.png");
        else if(entity.getPower() > 25 && entity.isHasBattery())return new ResourceLocation("zombiekit:textures/entities/uv_lamp_o3.png");
        else if(entity.isHasBattery())return new ResourceLocation("zombiekit:textures/entities/uv_lamp_o4.png");
        else return new ResourceLocation("zombiekit:textures/entities/uv_lamp_o5.png");
    }
}
