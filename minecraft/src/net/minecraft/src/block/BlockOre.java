package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.item.ItemEntry;

public class BlockOre extends BlockBreakToCobble {
	
	public BlockOre(int blockID, int indexInTexture) {
		super(blockID, indexInTexture);
	}

	public int idDropped(int var1, Random var2) {
		
		if (this.blockID == BlockEntry.oreCoal.block.blockID)
			return ItemEntry.coal.item.shiftedIndex;
		
		if (this.blockID == BlockEntry.oreIron.block.blockID)
			return ItemEntry.ingotIron.item.shiftedIndex;
		
		if (this.blockID == BlockEntry.oreMithril.block.blockID)
			return ItemEntry.ingotMithril.item.shiftedIndex;
		
		return this.blockID;
	}

	public int quantityDropped(Random random) {
		return (int) (Math.random() * 3) + 1;
	}
}
