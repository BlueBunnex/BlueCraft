package net.minecraft.src.world.gen;

import java.util.Random;

import net.minecraft.src.block.Block;
import net.minecraft.src.block.BlockFlower;
import net.minecraft.src.world.World;

public class WorldGenFlowers extends WorldGenerator {
	
	private int plantBlockID;

	public WorldGenFlowers(int plantBlockID) {
		this.plantBlockID = plantBlockID;
	}

	public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
		for(int var6 = 0; var6 < 64; ++var6) {
			int var7 = var3 + var2.nextInt(8) - var2.nextInt(8);
			int var8 = var4 + var2.nextInt(4) - var2.nextInt(4);
			int var9 = var5 + var2.nextInt(8) - var2.nextInt(8);
			if(var1.getBlockId(var7, var8, var9) == 0 && ((BlockFlower)Block.blocksList[this.plantBlockID]).canBlockStay(var1, var7, var8, var9)) {
				var1.setBlock(var7, var8, var9, this.plantBlockID);
			}
		}

		return true;
	}
}
