package com.scarasol.zombiekit.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SavedDataSyncPacket {
    public SavedData data;

    public SavedDataSyncPacket(SavedData data) {
        this.data = data;
    }


    public static void encode(SavedDataSyncPacket message, FriendlyByteBuf buffer) {
        buffer.writeNbt(message.data.save(new CompoundTag()));
    }

    public static SavedDataSyncPacket decode(FriendlyByteBuf buf){
        MapVariables mapVariables = new MapVariables();
        mapVariables.read(buf.readNbt());
        return new SavedDataSyncPacket(mapVariables);
    }

    public static void handler(SavedDataSyncPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (!context.getDirection().getReceptionSide().isServer()) {
                MapVariables.clientSide = (MapVariables) message.data;
            }
        });
        context.setPacketHandled(true);
    }

}
