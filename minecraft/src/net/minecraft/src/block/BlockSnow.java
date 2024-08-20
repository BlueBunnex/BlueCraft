package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EnumSkyBlock;
import net.minecraft.src.item.Item;
import net.minecraft.src.world.IBlockAccess;
import net.minecraft.src.world.World;

public class BlockSnow extends Block {
	
	protected BlockSnow(int var1, int var2) {
		super(var1, var2, Material.snow);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F / 16.0F, 1.0F);
		this.setTickOnLoad(true);
	}
	
	public int quantityDropped(Random random) {
		return 0;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
		return null;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
		return var1.getBlockMaterial(var2, var3 - 1, var4).getIsSolid();
	}

	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
		this.canSnowStay(var1, var2, var3, var4);
	}

	private boolean canSnowStay(World var1, int var2, int var3, int var4) {
		if(!this.canPlaceBlockAt(var1, var2, var3, var4)) {
			this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4));
			var1.setBlockWithNotify(var2, var3, var4, 0);
			return false;
		} else {
			return true;
		}
	}

	public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		if(var1.getSavedLightValue(EnumSkyBlock.Block, var2, var3, var4) > 11) {
			this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4));
			var1.setBlockWithNotify(var2, var3, var4, 0);
		}

	}

	public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
		Material var6 = var1.getBlockMaterial(var2, var3, var4);
		return var6 == this.material ? false : (var5 == 1 ? true : super.shouldSideBeRendered(var1, var2, var3, var4, var5));
	}
}
