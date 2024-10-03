package com.scarasol.zombiekit.network;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import static com.scarasol.zombiekit.ZombieKitMod.MODID;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

    public static <T> void addNetworkMessage() {
        PACKET_HANDLER.registerMessage(messageID++, ExoPacket.class, ExoPacket::encode, ExoPacket::decode, ExoPacket::handler);
        PACKET_HANDLER.registerMessage(messageID++, SavedDataSyncPacket.class, SavedDataSyncPacket::encode, SavedDataSyncPacket::decode, SavedDataSyncPacket::handler);

    }
}
