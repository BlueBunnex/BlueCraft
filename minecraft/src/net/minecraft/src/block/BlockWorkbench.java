package net.minecraft.src.block;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockWorkbench extends Block {
	protected BlockWorkbench(int var1) {
		super(var1, Material.wood);
		this.blockIndexInTexture = 59;
	}

	public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? this.blockIndexInTexture - 16 : (var1 == 0 ? Block.planks.getBlockTextureFromSide(0) : (var1 != 2 && var1 != 4 ? this.blockIndexInTexture : this.blockIndexInTexture + 1));
	}

	public void onBlockInteract(World var1, int var2, int var3, int var4, EntityPlayer var5) {
		var5.displayWorkbenchGUI();
	}
}
