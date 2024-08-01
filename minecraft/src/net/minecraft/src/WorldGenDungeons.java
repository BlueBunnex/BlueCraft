package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.block.Block;
import net.minecraft.src.block.TileEntityChest;
import net.minecraft.src.block.TileEntityMobSpawner;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;

public class WorldGenDungeons extends WorldGenerator {
	
	public boolean generate(World world, Random rand, int x, int y, int z) {
		byte var6 = 3;
		int var7 = rand.nextInt(2) + 2;
		int var8 = rand.nextInt(2) + 2;
		int var9 = 0;

		int world0;
		int world1;
		int world2;
		for(world0 = x - var7 - 1; world0 <= x + var7 + 1; ++world0) {
			for(world1 = y - 1; world1 <= y + var6 + 1; ++world1) {
				for(world2 = z - var8 - 1; world2 <= z + var8 + 1; ++world2) {
					Material world3 = world.getBlockMaterial(world0, world1, world2);
					if(world1 == y - 1 && !world3.isSolid()) {
						return false;
					}

					if(world1 == y + var6 + 1 && !world3.isSolid()) {
						return false;
					}

					if((world0 == x - var7 - 1 || world0 == x + var7 + 1 || world2 == z - var8 - 1 || world2 == z + var8 + 1) && world1 == y && world.getBlockId(world0, world1, world2) == 0 && world.getBlockId(world0, world1 + 1, world2) == 0) {
						++var9;
					}
				}
			}
		}

		if(var9 >= 1 && var9 <= 5) {
			for(world0 = x - var7 - 1; world0 <= x + var7 + 1; ++world0) {
				for(world1 = y + var6; world1 >= y - 1; --world1) {
					for(world2 = z - var8 - 1; world2 <= z + var8 + 1; ++world2) {
						if(world0 != x - var7 - 1 && world1 != y - 1 && world2 != z - var8 - 1 && world0 != x + var7 + 1 && world1 != y + var6 + 1 && world2 != z + var8 + 1) {
							world.setBlockWithNotify(world0, world1, world2, 0);
						} else if(world1 >= 0 && !world.getBlockMaterial(world0, world1 - 1, world2).isSolid()) {
							world.setBlockWithNotify(world0, world1, world2, 0);
						} else if(world.getBlockMaterial(world0, world1, world2).isSolid()) {
							if(world1 == y - 1 && rand.nextInt(4) != 0) {
								world.setBlockWithNotify(world0, world1, world2, Block.cobblestoneMossy.blockID);
							} else {
								world.setBlockWithNotify(world0, world1, world2, Block.cobblestone.blockID);
							}
						}
					}
				}
			}

			label110:
			for(world0 = 0; world0 < 2; ++world0) {
				for(world1 = 0; world1 < 3; ++world1) {
					world2 = x + rand.nextInt(var7 * 2 + 1) - var7;
					int world4 = z + rand.nextInt(var8 * 2 + 1) - var8;
					if(world.getBlockId(world2, y, world4) == 0) {
						int world5 = 0;
						if(world.getBlockMaterial(world2 - 1, y, world4).isSolid()) {
							++world5;
						}

						if(world.getBlockMaterial(world2 + 1, y, world4).isSolid()) {
							++world5;
						}

						if(world.getBlockMaterial(world2, y, world4 - 1).isSolid()) {
							++world5;
						}

						if(world.getBlockMaterial(world2, y, world4 + 1).isSolid()) {
							++world5;
						}

						if(world5 == 1) {
							world.setBlockWithNotify(world2, y, world4, Block.chest.blockID);
							TileEntityChest world6 = (TileEntityChest)world.getBlockTileEntity(world2, y, world4);
							int world7 = 0;

							while(true) {
								if(world7 >= 8) {
									continue label110;
								}

								ItemStack world8 = this.pickCheckLootItem(rand);
								if(world8 != null) {
									world6.setInventorySlotContents(rand.nextInt(world6.getSizeInventory()), world8);
								}

								++world7;
							}
						}
					}
				}
			}

			world.setBlockWithNotify(x, y, z, Block.mobSpawner.blockID);
			TileEntityMobSpawner world9 = (TileEntityMobSpawner)world.getBlockTileEntity(x, y, z);
			world9.mobID = this.pickMobSpawner(rand);
			return true;
		} else {
			return false;
		}
	}

	private ItemStack pickCheckLootItem(Random random) {
		
		switch (random.nextInt(11)) {
			case 0: return new ItemStack(Item.cd);
			case 1: return new ItemStack(Item.ingotIron, random.nextInt(4) + 1);
			case 2: return new ItemStack(Item.bread);
			case 3: return new ItemStack(Item.wheat, random.nextInt(4) + 1);
			case 4: return new ItemStack(Item.gunpowder, random.nextInt(4) + 1);
			case 5: return new ItemStack(Item.silk, random.nextInt(4) + 1);
			case 6: return new ItemStack(Item.bucketEmpty);
			case 7: return random.nextInt(10) == 0 ? new ItemStack(Item.appleGold) : new ItemStack(Item.appleRed);
			default: return null;
		}
	}

	private String pickMobSpawner(Random random) {
		
		switch (random.nextInt(4)) {
			case 0:
				return "Skeleton";
			case 1:
			case 2:
				return "Zombie";
			case 3:
				return "Spider";
			default:
				return "";
		}
	}
}
