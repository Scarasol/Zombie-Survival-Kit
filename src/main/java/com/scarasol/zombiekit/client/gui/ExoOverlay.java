package com.scarasol.zombiekit.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.zombiekit.item.armor.ExoArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class ExoOverlay extends GuiComponent {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void eventHandler(RenderGameOverlayEvent.Pre event) {
        Player entity = Minecraft.getInstance().player;
        if (entity != null && ExoArmor.numberOfSuit(entity) >= 4 && event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            int width = event.getWindow().getGuiScaledWidth();
            int height = event.getWindow().getGuiScaledHeight();
            int posX = width - com.scarasol.zombiekit.config.CommonConfig.EXO_HUD_WIDTH.get();
            int posY = height - com.scarasol.zombiekit.config.CommonConfig.EXO_HUD_HEIGHT.get();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            RenderSystem.setShaderColor(1, 1, 1, 1);
            if (!entity.isSpectator()) {
                ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.CHEST);
                String color;
                switch (ExoArmor.getMode(itemStack)) {
                    case 1 -> color = "blue";
                    case 2 -> color = "red";
                    default -> color = "green";
                }

                RenderSystem.setShaderTexture(0, new ResourceLocation("zombiekit:textures/screens/exo/" + color + "/basic_component.png"));
                GuiComponent.blit(event.getMatrixStack(), posX, posY, 0, 0, 112, 35, 112, 35);

                int power = ExoArmor.getPower(itemStack);

                RenderSystem.setShaderTexture(0, new ResourceLocation("zombiekit:textures/screens/exo/" + color + "/" + power / 100 + ".png"));
                GuiComponent.blit(event.getMatrixStack(), posX + 80, posY + 12, 0, 0, 5, 8, 5, 8);
                RenderSystem.setShaderTexture(0, new ResourceLocation("zombiekit:textures/screens/exo/" + color + "/" + power % 100 / 10 + ".png"));
                GuiComponent.blit(event.getMatrixStack(), posX + 87, posY + 12, 0, 0, 5, 8, 5, 8);
                RenderSystem.setShaderTexture(0, new ResourceLocation("zombiekit:textures/screens/exo/" + color + "/" + power % 10 + ".png"));
                GuiComponent.blit(event.getMatrixStack(), posX + 94, posY + 12, 0, 0, 5, 8, 5, 8);

                RenderSystem.setShaderTexture(0, new ResourceLocation("zombiekit:textures/screens/exo/" + color + "/percent.png"));
                GuiComponent.blit(event.getMatrixStack(), posX + 101, posY + 12, 0, 0, 5, 8, 5, 8);


                for (int i = 0; power / ((i + 1) * 5) > 0; i++){
                    RenderSystem.setShaderTexture(0, new ResourceLocation("zombiekit:textures/screens/exo/" + color + "/power.png"));
                    GuiComponent.blit(event.getMatrixStack(), posX + 103 - 5 * i, posY + 21, 0, 0, 3, 8, 3, 8);
                }

                if (ExoArmor.getRadar(itemStack)){
                    RenderSystem.setShaderTexture(0, new ResourceLocation("zombiekit:textures/screens/exo/" + color + "/radar.png"));
                    GuiComponent.blit(event.getMatrixStack(), posX + 96, posY + 2, 0, 0, 8, 8, 8, 8);
                }

                if (entity.isFallFlying()){
                    RenderSystem.setShaderTexture(0, new ResourceLocation("zombiekit:textures/screens/exo/" + color + "/fall_fly.png"));
                    GuiComponent.blit(event.getMatrixStack(), posX + 86, posY + 2, 0, 0, 8, 8, 8, 8);
                }

                if (ExoArmor.getReactiveArmor(itemStack) >= 0){
                    RenderSystem.setShaderTexture(0, new ResourceLocation("zombiekit:textures/screens/exo/" + color + "/reactive_armor_charge.png"));
                    GuiComponent.blit(event.getMatrixStack(), posX + 76, posY + 2, 0, 0, 8, 8, 8, 8);
                    RenderSystem.setShaderTexture(0, new ResourceLocation("zombiekit:textures/screens/exo/" + color + "/reactive_armor.png"));
                    GuiComponent.blit(event.getMatrixStack(), posX + 76, posY + 2, 0, 0, 8, 8 * ExoArmor.getReactiveArmor(itemStack) / 160, 8, 8);
                }
            }
            RenderSystem.depthMask(true);
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
            RenderSystem.setShaderColor(1, 1, 1, 1);
        }
    }

    public static int getXOffset(int scaledWidth){
        return switch (CommonConfig.INFECTION_OVERLAY_PRESET.get()) {
            case 1 -> 0;
            case 2 -> scaledWidth - 32;
            case 3 -> scaledWidth / 2 - 16;
            default -> CommonConfig.INFECTION_X_OFFSET.get();
        };
    }

    public static int getYOffset(int scaledHeight){
        return switch (CommonConfig.INFECTION_OVERLAY_PRESET.get()) {
            case 1, 2 -> scaledHeight - 32;
            case 3 -> scaledHeight - 65;
            default -> scaledHeight - CommonConfig.INFECTION_Y_OFFSET.get();
        };
    }
}
