package net.minecraft.src.block;

import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.world.World;

public class BlockWorkbench extends Block {
	
	protected BlockWorkbench(int var1) {
		super(var1, Material.wood);
		this.blockIndexInTexture = 43;
	}

	public int getBlockTextureFromSide(int side) {
		
		if (side == 0)
			return AllBlocks.planks.block.blockIndexInTexture;
		
		if (side == 1)
			return this.blockIndexInTexture;
		
		return this.blockIndexInTexture + (side % 2 == 0 ? 1 : 2);
	}

	public boolean onBlockInteract(World var1, int var2, int var3, int var4, EntityPlayer var5) {
		var5.displayWorkbenchGUI();
		return true;
	}
}
