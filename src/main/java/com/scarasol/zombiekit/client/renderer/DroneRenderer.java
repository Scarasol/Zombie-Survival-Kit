package com.scarasol.zombiekit.client.renderer;

import com.scarasol.zombiekit.client.model.DroneModel;
import com.scarasol.zombiekit.entity.mechanics.DroneEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DroneRenderer extends MobRenderer<DroneEntity, DroneModel<DroneEntity>> {
    public DroneRenderer(EntityRendererProvider.Context context) {
        super(context, new DroneModel<>(context.bakeLayer(DroneModel.LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(DroneEntity entity) {
        return new ResourceLocation("zombiekit:textures/entities/drone.png");
    }
}

