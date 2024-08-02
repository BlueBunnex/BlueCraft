package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.Vec3D;
import net.minecraft.src.item.Item;
import net.minecraft.src.world.World;

public class BlockDoor extends Block {
	
	protected BlockDoor(int var1, Material var2) {
		super(var1, var2);
		this.blockIndexInTexture = 97;
		if(var2 == Material.iron) {
			++this.blockIndexInTexture;
		}

		float var3 = 0.5F;
		float var4 = 1.0F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
	}

	public int getBlockTextureFromSideAndMetadata(int var1, int var2) {
		if(var1 != 0 && var1 != 1) {
			int var3 = this.getState(var2);
			if((var3 == 0 || var3 == 2) ^ var1 <= 3) {
				return this.blockIndexInTexture;
			} else {
				int var4 = var3 / 2 + (var1 & 1 ^ var3);
				var4 += (var2 & 4) / 4;
				int var5 = this.blockIndexInTexture - (var2 & 8) * 2;
				if((var4 & 1) != 0) {
					var5 = -var5;
				}

				return var5;
			}
		} else {
			return this.blockIndexInTexture;
		}
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 7;
	}

	public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
		this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
		return super.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
		this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
		return super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
		this.setDoorRotation(this.getState(var1.getBlockMetadata(var2, var3, var4)));
	}

	public void setDoorRotation(int var1) {
		float var2 = 3.0F / 16.0F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
		if(var1 == 0) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
		}

		if(var1 == 1) {
			this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}

		if(var1 == 2) {
			this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
		}

		if(var1 == 3) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
		}

	}

	public boolean onBlockInteract(World var1, int var2, int var3, int var4, EntityPlayer var5) {
		return this.openDoor(var1, var2, var3, var4, var5);
	}

	private boolean openDoor(World var1, int var2, int var3, int var4, EntityPlayer var5) {
		
		int var6 = var1.getBlockMetadata(var2, var3, var4);
		
		if((var6 & 8) != 0) {
			if(var1.getBlockId(var2, var3 - 1, var4) == this.blockID) {
				this.openDoor(var1, var2, var3 - 1, var4, var5);
			}

			return true;
		} else {
			if(var1.getBlockId(var2, var3 + 1, var4) == this.blockID) {
				var1.setBlockMetadataWithNotify(var2, var3 + 1, var4, (var6 ^ 4) + 8);
			}

			var1.setBlockMetadataWithNotify(var2, var3, var4, var6 ^ 4);
			var1.markBlocksDirty(var2, var3 - 1, var4, var2, var3, var4);
			if(Math.random() < 0.5D) {
				var1.playSoundEffect("random.door_open", (double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, 1.0F, var1.rand.nextFloat() * 0.1F + 0.9F);
			} else {
				var1.playSoundEffect("random.door_close", (double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, 1.0F, var1.rand.nextFloat() * 0.1F + 0.9F);
			}

			return true;
		}
	}

	public int idDropped(int var1, Random var2) {
		return Item.doorWood.shiftedIndex;
	}

	public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3D var5, Vec3D var6) {
		this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
		return super.collisionRayTrace(var1, var2, var3, var4, var5, var6);
	}

	public int getState(int var1) {
		return (var1 & 4) == 0 ? var1 - 1 & 3 : var1 & 3;
	}

	public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
		return var3 >= 127 ? false : var1.isBlockNormalCube(var2, var3 - 1, var4) && super.canPlaceBlockAt(var1, var2, var3, var4) && super.canPlaceBlockAt(var1, var2, var3 + 1, var4);
	}
}
