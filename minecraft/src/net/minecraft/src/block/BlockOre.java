package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.Material;
import net.minecraft.src.item.Item;

public class BlockOre extends Block {
	
	public BlockOre(int var1, int var2) {
		super(var1, var2, Material.rock);
	}

	public int idDropped(int var1, Random var2) {
		return this.blockID == Block.oreCoal.blockID ? Item.coal.shiftedIndex : this.blockID;
	}

	public int quantityDropped(Random var1) {
		return 1;
	}
}
