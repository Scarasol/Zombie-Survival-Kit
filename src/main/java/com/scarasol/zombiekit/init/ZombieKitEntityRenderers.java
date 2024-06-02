package com.scarasol.zombiekit.init;

import com.scarasol.zombiekit.client.renderer.FlareGunRenderer;
import com.scarasol.zombiekit.client.renderer.WrenchRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ZombieKitEntityRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ZombieKitEntities.MOLOTOV_COCKTAIL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ZombieKitEntities.POTION_JAR.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ZombieKitEntities.BILE_JAR.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ZombieKitEntities.FIRECRACKER.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ZombieKitEntities.WRENCH.get(), WrenchRenderer::new);
        event.registerEntityRenderer(ZombieKitEntities.FLARES.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ZombieKitEntities.FLARE_GUN.get(), FlareGunRenderer::new);
    }

}
