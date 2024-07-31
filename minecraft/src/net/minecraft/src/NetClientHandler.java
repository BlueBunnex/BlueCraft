package net.minecraft.src;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class NetClientHandler extends NetHandler {
	private boolean disconnected = false;
	private NetworkManager netManager;
	public String loginProgress;
	private Minecraft mc;
	private WorldClient worldClient;

	public NetClientHandler(Minecraft var1, String var2, int var3) throws IOException {
		this.mc = var1;
		Socket var4 = new Socket(InetAddress.getByName(var2), var3);
		this.netManager = new NetworkManager(var4, "Client", this);
	}

	public void processReadPackets() {
		if(!this.disconnected) {
			this.netManager.processReadPackets();
		}
	}

	public void handleLogin(Packet0Handshake var1) {
		this.mc.playerController = new PlayerControllerMP(this.mc);
		this.worldClient = new WorldClient(this);
		this.worldClient.multiplayerWorld = true;
		this.mc.changeWorld1(this.worldClient);
		this.mc.displayGuiScreen(new GuiDownloadTerrain(this));
	}

	public void handleFlying(Packet1PlayerLookMove var1) {
		System.out.println("SPAWN!");
		this.mc.thePlayer.setLocationAndAngles(var1.xPosition, var1.yPosition, var1.zPosition, var1.yaw, var1.pitch);
		this.mc.displayGuiScreen((GuiScreen)null);
	}

	public void handleMapChunk(Packet10MapChunk var1) {
		this.worldClient.setChunkData(var1.xPosition, var1.yPosition, var1.zPosition, var1.xSize, var1.ySize, var1.zSize, var1.chunkData);
	}

	public void handleKickDisconnect(Packet255KickDisconnect var1) {
		this.netManager.networkShutdown("Got kicked");
		this.disconnected = true;
		this.mc.changeWorld1((World)null);
		this.mc.displayGuiScreen(new GuiConnectFailed("Disconnected by server", var1.reason));
	}

	public void handleErrorMessage(String var1) {
		if(!this.disconnected) {
			this.disconnected = true;
			this.mc.changeWorld1((World)null);
			this.mc.displayGuiScreen(new GuiConnectFailed("Connection lost", var1));
		}
	}

	public void addToSendQueue(Packet var1) {
		if(!this.disconnected) {
			this.netManager.addToSendQueue(var1);
		}
	}
}
