package com.scarasol.zombiekit.init;

import com.scarasol.zombiekit.client.model.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ZombieKitModels {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WrenchModel.LAYER_LOCATION, WrenchModel::createBodyLayer);
        event.registerLayerDefinition(TacticalSuitModel.LAYER_LOCATION, TacticalSuitModel::createBodyLayer);
        event.registerLayerDefinition(BombSuitModel.LAYER_LOCATION, BombSuitModel::createBodyLayer);
        event.registerLayerDefinition(RiotSuitModel.LAYER_LOCATION, RiotSuitModel::createBodyLayer);
        event.registerLayerDefinition(DroneModel.LAYER_LOCATION, DroneModel::createBodyLayer);
        event.registerLayerDefinition(ExoSuitModel.LAYER_LOCATION, ExoSuitModel::createBodyLayer);
        event.registerLayerDefinition(UVlampModel.LAYER_LOCATION, UVlampModel::createBodyLayer);
        event.registerLayerDefinition(FlaresModel.LAYER_LOCATION, FlaresModel::createBodyLayer);
        event.registerLayerDefinition(SkiingSuitModel.LAYER_LOCATION, SkiingSuitModel::createBodyLayer);
    }

}
