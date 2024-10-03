package com.scarasol.zombiekit.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.network.PacketDistributor;

public class MapVariables extends SavedData {
    public static final String DATA_NAME = "zombiekit_mapvars";
    public String radio_location = "";

    public static MapVariables load(CompoundTag tag) {
        MapVariables data = new MapVariables();
        data.read(tag);
        return data;
    }

    public void read(CompoundTag nbt) {
        radio_location = nbt.getString("radio_location");
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putString("radio_location", radio_location);
        return nbt;
    }

    public void syncData(LevelAccessor world) {
        this.setDirty();
        if (world instanceof Level && !world.isClientSide())
            NetworkHandler.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncPacket(this));
    }

    static MapVariables clientSide = new MapVariables();

    public static MapVariables get(LevelAccessor world) {
        if (world instanceof ServerLevelAccessor serverLevelAcc) {
            return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e), MapVariables::new, DATA_NAME);
        } else {
            return clientSide;
        }
    }

}
