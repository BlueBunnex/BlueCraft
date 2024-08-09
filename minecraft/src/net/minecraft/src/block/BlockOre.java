package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.Material;
import net.minecraft.src.item.Item;
import net.minecraft.src.world.World;

public class BlockOre extends BlockBreakToCobble {
	
	public BlockOre(int blockID, int indexInTexture) {
		super(blockID, indexInTexture);
	}

	public int idDropped(int var1, Random var2) {
		return this.blockID == Block.oreCoal.blockID ? Item.coal.shiftedIndex : this.blockID;
	}

	public int quantityDropped(Random var1) {
		return 1;
	}
}
