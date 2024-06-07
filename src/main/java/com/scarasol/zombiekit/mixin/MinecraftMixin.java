package com.scarasol.zombiekit.mixin;

import com.scarasol.zombiekit.entity.mechanics.Mechanics;
import com.scarasol.zombiekit.item.armor.ExoArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow @Nullable public LocalPlayer player;

    @Inject(method = "shouldEntityAppearGlowing", cancellable = true, at = @At("RETURN"))
    private void onShouldEntityAppearGlowing(Entity entity, CallbackInfoReturnable<Boolean> cir){
        if (!cir.getReturnValue() && player != null) {
            if (entity instanceof ArmorStand || entity instanceof Mechanics || player.getUUID().equals(entity.getUUID()))
                return;
            if (ExoArmor.numberOfSuit(player) == 4) {
                if (ExoArmor.getRadar(player.getItemBySlot(EquipmentSlot.CHEST))) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
