package net.minecraft.src.item;

import net.minecraft.src.block.BlockEntry;
import net.minecraft.src.block.Block;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.world.World;

public class ItemBlock extends Item {
	
	private int blockID;

	public ItemBlock(Block block) {
		super(block.blockID - 256);
		this.blockID = block.blockID;
		this.setIconIndex(block.getBlockTextureFromSide(2));
	}

	public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7) {
		
		// TODO wtf does this do
		if(var3.getBlockId(var4, var5, var6) == BlockEntry.snow.block.blockID) {
			var7 = 0;
		} else {
			if(var7 == 0) {
				--var5;
			}

			if(var7 == 1) {
				++var5;
			}

			if(var7 == 2) {
				--var6;
			}

			if(var7 == 3) {
				++var6;
			}

			if(var7 == 4) {
				--var4;
			}

			if(var7 == 5) {
				++var4;
			}
		}

		if(var1.stackSize == 0) {
			return false;
		} else {
			if(var3.canBlockBePlacedAt(this.blockID, var4, var5, var6, false)) {
				Block var8 = Block.blocksList[this.blockID];
				if(var3.setBlockWithNotify(var4, var5, var6, this.blockID)) {
					Block.blocksList[this.blockID].onBlockPlaced(var3, var4, var5, var6, var7);
					var3.playSoundEffect(var8.stepSound.getStepSound(), (double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), (var8.stepSound.getVolume() + 1.0F) / 2.0F, var8.stepSound.getPitch() * 0.8F);
					--var1.stackSize;
				}
			}

			return true;
		}
	}
}
