package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.Material;
import net.minecraft.src.item.Item;

public class BlockCobble extends Block {
	
	public BlockCobble(int blockID, int indexInTexture) {
		super(blockID, indexInTexture, Material.rock);
	}
	
	public int idDropped(int var1, Random random) {
		return Item.rock.shiftedIndex;
	}
	
	public int quantityDropped(Random random) {
		return random.nextInt(3) + 1;
	}

}
