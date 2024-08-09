package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.Material;
import net.minecraft.src.world.World;

public class BlockBreakToCobble extends Block {
	
	public BlockBreakToCobble(int blockID, int indexInTexture) {
		super(blockID, indexInTexture, Material.rock);
	}
	
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int SOMETHING) {
		world.setBlock(x, y, z, Block.cobblestone.blockID);
		world.playSoundEffect("random.glass", x, y, z, 0.6F, (float) Math.random() * 0.3F + 0.9F);
	}

	public int idDropped(int var1, Random var2) {
		return 0;
	}
}
