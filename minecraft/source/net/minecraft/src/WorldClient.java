package net.minecraft.src;

import java.io.File;

public class WorldClient extends World {
	private NetClientHandler sendQueue;
	private ChunkProviderClient clientChunkProvider;

	public WorldClient(NetClientHandler var1) {
		super("MpServer");
		this.sendQueue = var1;
		this.spawnX = 8;
		this.spawnY = 64;
		this.spawnZ = 8;
	}

	public void tick() {
		this.sendQueue.processReadPackets();
		super.tick();
	}

	protected IChunkProvider getChunkProvider(File var1) {
		this.clientChunkProvider = new ChunkProviderClient(this);
		return this.clientChunkProvider;
	}

	public void setSpawnLocation() {
		this.spawnX = 8;
		this.spawnY = 64;
		this.spawnZ = 8;
	}

	public Chunk doPreChunk(int var1, int var2) {
		Chunk var3 = this.getChunkFromChunkCoords(var1, var2);
		if(var3.isChunkRendered) {
			var3 = this.clientChunkProvider.loadChunk(var1, var2);
		}

		return var3;
	}

	protected void updateBlocksAndPlayCaveSounds() {
	}

	public void scheduleBlockUpdate(int var1, int var2, int var3, int var4) {
	}

	public boolean tickUpdates(boolean var1) {
		return false;
	}

	public void setChunkData(int var1, int var2, int var3, int var4, int var5, int var6, byte[] var7) {
		int var8 = var1 >> 4;
		int var9 = var3 >> 4;
		int var10 = var1 + var4 - 1 >> 4;
		int var11 = var3 + var6 - 1 >> 4;

		for(int var12 = var8; var12 <= var10; ++var12) {
			for(int var13 = var9; var13 <= var11; ++var13) {
				this.doPreChunk(var12, var13);
			}
		}

		super.setChunkData(var1, var2, var3, var4, var5, var6, var7);
	}
}
