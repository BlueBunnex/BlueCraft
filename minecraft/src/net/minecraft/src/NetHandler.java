package net.minecraft.src;

public class NetHandler {
	public void handleMapChunk(Packet10MapChunk var1) {
	}

	public void registerPacket(Packet var1) {
	}

	public void handleErrorMessage(String var1) {
	}

	public void handleKickDisconnect(Packet255KickDisconnect var1) {
		this.registerPacket(var1);
	}

	public void handleLogin(Packet0Handshake var1) {
		this.registerPacket(var1);
	}

	public void handleFlying(Packet1PlayerLookMove var1) {
		this.registerPacket(var1);
	}
}
