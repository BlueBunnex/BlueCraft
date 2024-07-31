package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet1PlayerLookMove extends Packet {
	public double xPosition;
	public double yPosition;
	public double zPosition;
	public float yaw;
	public float pitch;

	public int getPacketId() {
		return 1;
	}

	public void processPacket(NetHandler var1) {
		System.out.println("HANDLING A SPAWN PACKET!");
		var1.handleFlying(this);
	}

	public void readPacketData(DataInputStream var1) throws IOException {
		this.xPosition = var1.readDouble();
		this.yPosition = var1.readDouble();
		this.zPosition = var1.readDouble();
		this.yaw = var1.readFloat();
		this.pitch = var1.readFloat();
	}

	public void writePacket(DataOutputStream var1) throws IOException {
		var1.writeDouble(this.xPosition);
		var1.writeDouble(this.yPosition);
		var1.writeDouble(this.zPosition);
		var1.writeFloat(this.yaw);
		var1.writeFloat(this.pitch);
	}
}
