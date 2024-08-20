package net.minecraft.src.block;

public class BlockOreBlock extends Block {
	
	public BlockOreBlock(int blockID, int indexInTexture) {
		super(blockID, indexInTexture, Material.iron);
	}

	public int getBlockTextureFromSide(int side) {
		
		if (side == 1)
			return this.blockIndexInTexture;
		
		if (side == 0)
			return this.blockIndexInTexture + 2;
		
		return this.blockIndexInTexture + 1;
	}
}
