package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.item.Item;
import net.minecraft.src.world.World;

public class BlockOre extends BlockBreakToCobble {
	
	public BlockOre(int blockID, int indexInTexture) {
		super(blockID, indexInTexture);
	}

	public int idDropped(int var1, Random var2) {
		
		if (this.blockID == Block.oreCoal.blockID)
			return Item.coal.shiftedIndex;
		
		if (this.blockID == Block.oreIron.blockID)
			return Item.ingotIron.shiftedIndex;
		
		if (this.blockID == Block.oreMithril.blockID)
			return Item.ingotMithril.shiftedIndex;
		
		return this.blockID;
	}

	public int quantityDropped(Random var1) {
		return 1;
	}
}
