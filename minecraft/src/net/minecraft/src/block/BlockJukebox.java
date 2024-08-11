package net.minecraft.src.block;

import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.world.World;

public class BlockJukebox extends Block {
	
	private String audioSourceID; 

	protected BlockJukebox(int blockID) {
		super(blockID, 17, Material.wood); // TODO add jukebox texture
	}
	
	// furnace's
//	public int getBlockTextureFromSide(int var1) {
//		return var1 == 1 ? Block.stone.blockID : (var1 == 0 ? Block.stone.blockID : (var1 == 3 ? this.blockIndexInTexture - 1 : this.blockIndexInTexture));
//	}
	
	public boolean onBlockInteract(World world, int x, int y, int z, EntityPlayer player) {
		
		if (audioSourceID == null) {
			
			audioSourceID = world.playMusic("calm", x, y, z);
			
			System.out.println("playing at: " + audioSourceID);
			
		} else {
			
			System.out.println("stopping: " + audioSourceID);
			
			world.stopAudioSource(audioSourceID);
			audioSourceID = null;
			
		}
		
		return true;
	}

}
