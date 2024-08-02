package net.minecraft.src.block;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.world.World;

public class BlockJukebox extends Block {

	protected BlockJukebox(int blockID) {
		super(blockID, 17, Material.wood); // TODO add jukebox texture
	}
	
	// furnace's
//	public int getBlockTextureFromSide(int var1) {
//		return var1 == 1 ? Block.stone.blockID : (var1 == 0 ? Block.stone.blockID : (var1 == 3 ? this.blockIndexInTexture - 1 : this.blockIndexInTexture));
//	}
	
	public boolean onBlockInteract(World world, int x, int y, int z, EntityPlayer player) {
		
		world.playMusic("calm", x, y, z);
		
		return true;
	}

}
