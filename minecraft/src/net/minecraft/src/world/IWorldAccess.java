package net.minecraft.src.world;

import net.minecraft.src.Entity;

public interface IWorldAccess {
	
	void markBlockAndNeighborsNeedsUpdate(int var1, int var2, int var3);

	void markBlockRangeNeedsUpdate(int var1, int var2, int var3, int var4, int var5, int var6);

	void playSound(String sound, double x, double y, double z, float volume, float pitch);
	void playMusic(String music, double x, double y, double z);

	void spawnParticle(String var1, double var2, double var4, double var6, double var8, double var10, double var12);

	void obtainEntitySkin(Entity var1);

	void releaseEntitySkin(Entity var1);

	void updateAllRenderers();
}
