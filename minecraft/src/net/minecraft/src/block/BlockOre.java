package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.item.AllItems;

public class BlockOre extends BlockBreakToCobble {
	
	public BlockOre(int blockID, int indexInTexture) {
		super(blockID, indexInTexture);
	}

	public int idDropped(int var1, Random var2) {
		
		if (this.blockID == AllBlocks.oreCoal.block.blockID)
			return AllItems.coal.item.shiftedIndex;
		
		if (this.blockID == AllBlocks.oreIron.block.blockID)
			return AllItems.ingotIron.item.shiftedIndex;
		
		if (this.blockID == AllBlocks.oreMithril.block.blockID)
			return AllItems.ingotMithril.item.shiftedIndex;
		
		return this.blockID;
	}

	public int quantityDropped(Random random) {
		return (int) (Math.random() * 3) + 1;
	}
}
