package com.scarasol.zombiekit.network;


import com.scarasol.zombiekit.item.armor.ExoArmor;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ExoPacket {

    private final int mode;

    public ExoPacket(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public static ExoPacket decode(FriendlyByteBuf buf) {
        return new ExoPacket(buf.readInt());
    }

    public static void encode(ExoPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.getMode());
    }

    public static void handler(ExoPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (msg != null) {
                context.get().enqueueWork(() -> {
                    if (context.get().getDirection().getReceptionSide().isServer()){
                        ServerPlayer player = context.get().getSender();
                        ExoArmor.switchMode(player, msg.getMode());
                    }
                });
            }
        });
        context.get().setPacketHandled(true);
    }
}
