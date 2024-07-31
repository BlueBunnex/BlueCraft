package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Packet {
	public static Packet getNewPacket(int var0) {
		switch(var0) {
		case 0:
			return new Packet0Handshake();
		case 1:
			return new Packet1PlayerLookMove();
		case 10:
			return new Packet10MapChunk();
		case 255:
			return new Packet255KickDisconnect();
		default:
			return null;
		}
	}

	public static Packet readPacket(DataInputStream var0) throws IOException {
		int var1 = var0.read();
		if(var1 == -1) {
			return null;
		} else {
			Packet var2 = getNewPacket(var1);
			if(var2 == null) {
				throw new IOException("Bad packet id " + var1);
			} else {
				var2.readPacketData(var0);
				return var2;
			}
		}
	}

	public static void writePacket(Packet var0, DataOutputStream var1) throws IOException {
		var1.write(var0.getPacketId());
		var0.writePacket(var1);
	}

	public abstract void readPacketData(DataInputStream var1) throws IOException;

	public abstract void writePacket(DataOutputStream var1) throws IOException;

	public abstract int getPacketId() throws IOException;

	public abstract void processPacket(NetHandler var1);
}
