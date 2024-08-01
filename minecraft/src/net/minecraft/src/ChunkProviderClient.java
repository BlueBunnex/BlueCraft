package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.src.world.World;

public class ChunkProviderClient implements IChunkProvider {
	private Chunk blankChunk;
	private Map chunkMapping = new HashMap();
	private List chunkListing = new ArrayList();
	private World worldObj;
	int a = -999999999;
	int b = -999999999;
	private Chunk g;

	public ChunkProviderClient(World var1) {
		this.blankChunk = new Chunk(var1, new byte[-Short.MIN_VALUE], 0, 0);
		this.blankChunk.isChunkRendered = true;
		this.blankChunk.neverSave = true;
		this.worldObj = var1;
	}

	public boolean chunkExists(int var1, int var2) {
		if(var1 == this.a && var2 == this.b && this.g != null) {
			return true;
		} else {
			ChunkCoordinates var3 = new ChunkCoordinates(var1, var2);
			return this.chunkMapping.containsKey(var3);
		}
	}

	public Chunk loadChunk(int var1, int var2) {
		ChunkCoordinates var3 = new ChunkCoordinates(var1, var2);
		this.g = null;
		Chunk var4 = new Chunk(this.worldObj, new byte[-Short.MIN_VALUE], var1, var2);
		this.chunkMapping.put(var3, var4);
		return var4;
	}

	public Chunk provideChunk(int var1, int var2) {
		if(var1 == this.a && var2 == this.b && this.g != null) {
			return this.g;
		} else {
			ChunkCoordinates var3 = new ChunkCoordinates(var1, var2);
			Chunk var4 = (Chunk)this.chunkMapping.get(var3);
			if(var4 == null) {
				return this.blankChunk;
			} else {
				this.a = var1;
				this.b = var2;
				this.g = var4;
				return var4;
			}
		}
	}

	public boolean saveChunks(boolean var1, IProgressUpdate var2) {
		return true;
	}

	public boolean unload100OldestChunks() {
		return false;
	}

	public boolean canSave() {
		return false;
	}

	public void populate(IChunkProvider var1, int var2, int var3) {
	}
}
