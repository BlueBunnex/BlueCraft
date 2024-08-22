package net.minecraft.game.level;

import java.util.Random;

import net.minecraft.game.level.block.Block;
import net.minecraft.game.level.block.BlockFlower;
import net.minecraft.game.level.gennoise.NoiseGeneratorDistort;
import net.minecraft.game.level.gennoise.NoiseGeneratorOctaves;
import util.IProgressUpdate;
import util.MathHelper;

public final class LevelGenerator {
	
	private IProgressUpdate guiLoading;
	private int width, length, height;
	private Random rand = new Random();
	private byte[] blocksByteArray;
	private int waterLevel;
	private int groundLevel;
	
	private int loadingProgress = 0;
	private static final int maxLoadingProgress = 10;

	public LevelGenerator(IProgressUpdate var1) {
		this.guiLoading = var1;
	}

	public final World generate() {
		
		final int width = 64, length = 64, height = 64;
		
		int var5 = 1;

		this.guiLoading.displayProgressMessage("Generating level");
		
		World world = new World();
		world.waterLevel = this.waterLevel;
		world.groundLevel = this.groundLevel;
		
		this.width = width;
		this.length = length;
		this.height = height;
		this.blocksByteArray = new byte[width * length * height];

		int var7;
		LevelGenerator var9;
		int var21;
		int var25;
		int var31;
		int var45;
		int var51;
		int var52;
		int var53;
		int var56;
		for(var7 = 0; var7 < var5; ++var7) {
			this.waterLevel = height - 32 - var7 * 48;
			this.groundLevel = this.waterLevel - 2;
			int[] var8;
			NoiseGeneratorOctaves var13;
			int var22;
			double var32;
			int[] var46;
			
			this.updateLoadingBar("Making noise...");
			var9 = this;
			NoiseGeneratorDistort var10 = new NoiseGeneratorDistort(new NoiseGeneratorOctaves(this.rand, 8), new NoiseGeneratorOctaves(this.rand, 8));
			NoiseGeneratorDistort var11 = new NoiseGeneratorDistort(new NoiseGeneratorOctaves(this.rand, 8), new NoiseGeneratorOctaves(this.rand, 8));
			NoiseGeneratorOctaves var12 = new NoiseGeneratorOctaves(this.rand, 6);
			var13 = new NoiseGeneratorOctaves(this.rand, 2);
			int[] var14 = new int[this.width * this.length];
			var22 = 0;

			label349:
			while(true) {
				if(var22 >= var9.width) {
					var8 = var14;
					this.updateLoadingBar("Eroding...");
					var46 = var14;
					var9 = this;
					var11 = new NoiseGeneratorDistort(new NoiseGeneratorOctaves(this.rand, 8), new NoiseGeneratorOctaves(this.rand, 8));
					NoiseGeneratorDistort var50 = new NoiseGeneratorDistort(new NoiseGeneratorOctaves(this.rand, 8), new NoiseGeneratorOctaves(this.rand, 8));
					var52 = 0;

					while(true) {
						
						if(var52 >= var9.width)
							break label349;

						for(var53 = 0; var53 < var9.length; ++var53) {
							double var20 = var11.generateNoise((double)(var52 << 1), (double)(var53 << 1)) / 8.0D;
							var22 = var50.generateNoise((double)(var52 << 1), (double)(var53 << 1)) > 0.0D ? 1 : 0;
							if(var20 > 2.0D) {
								int var58 = var46[var52 + var53 * var9.width];
								var58 = ((var58 - var22) / 2 << 1) + var22;
								var46[var52 + var53 * var9.width] = var58;
							}
						}

						++var52;
					}
				}

				for(var25 = 0; var25 < var9.length; ++var25) {
					double var28 = var10.generateNoise((double)((float)var22 * 1.3F), (double)((float)var25 * 1.3F)) / 6.0D + -4.0D;
					double var30 = var11.generateNoise((double)((float)var22 * 1.3F), (double)((float)var25 * 1.3F)) / 5.0D + 10.0D + -4.0D;
					var32 = var12.generateNoise((double)var22, (double)var25) / 8.0D;
					if(var32 > 0.0D) {
						var30 = var28;
					}

					double var34 = Math.max(var28, var30) / 2.0D;
					
					if(var34 < 0.0D) {
						var34 *= 0.8D;
					}

					var14[var22 + var25 * var9.width] = (int)var34;
				}

				++var22;
			}

			this.updateLoadingBar("Soiling...");
			var46 = var8;
			var9 = this;
			int var49 = this.width;
			var51 = this.length;
			var52 = this.height;
			NoiseGeneratorOctaves var54 = new NoiseGeneratorOctaves(this.rand, 8);
			NoiseGeneratorOctaves var55 = new NoiseGeneratorOctaves(this.rand, 8);

			for(var21 = 0; var21 < var49; ++var21) {
				double var57 = Math.abs(((double)var21 / ((double)var49 - 1.0D) - 0.5D) * 2.0D);

				for(int var24 = 0; var24 < var51; ++var24) {
					double var64 = Math.abs(((double)var24 / ((double)var51 - 1.0D) - 0.5D) * 2.0D);
					double var27 = Math.max(var57, var64);
					var27 = var27 * var27 * var27;
					int var29 = (int)(var54.generateNoise((double)var21, (double)var24) / 24.0D) - 4;
					int var72 = var46[var21 + var24 * var49] + var9.waterLevel;
					var31 = var72 + var29;
					var46[var21 + var24 * var49] = Math.max(var72, var31);
					if(var46[var21 + var24 * var49] > var52 - 2) {
						var46[var21 + var24 * var49] = var52 - 2;
					}

					if(var46[var21 + var24 * var49] <= 0) {
						var46[var21 + var24 * var49] = 1;
					}

					var32 = var55.generateNoise((double)var21 * 2.3D, (double)var24 * 2.3D) / 24.0D;
					int var76 = (int)(Math.sqrt(Math.abs(var32)) * Math.signum(var32) * 20.0D) + var9.waterLevel;
					var76 = (int)((double)var76 * (1.0D - var27) + var27 * (double)var9.height);
					if(var76 > var9.waterLevel) {
						var76 = var9.height;
					}

					for(int var35 = 0; var35 < var52; ++var35) {
						int var79 = (var35 * var9.length + var24) * var9.width + var21;
						int var37 = 0;
						if(var35 <= var72) {
							var37 = Block.dirt.blockID;
						}

						if(var35 <= var31) {
							var37 = Block.stone.blockID;
						}

						if(var9.blocksByteArray[var79] == 0) {
							var9.blocksByteArray[var79] = (byte)var37;
						}
					}
				}
			}

			this.updateLoadingBar("Growing...");
			var46 = var8;
			var9 = this;
			var49 = this.width;
			var51 = this.length;
			var13 = new NoiseGeneratorOctaves(this.rand, 8);
			var54 = new NoiseGeneratorOctaves(this.rand, 8);
			var56 = this.waterLevel - 1;

			for(var21 = 0; var21 < var49; ++var21) {
				for(var22 = 0; var22 < var51; ++var22) {
					
					boolean var60 = var13.generateNoise((double)var21, (double)var22) > 8.0D;

					var25 = var46[var21 + var22 * var49];
					int var65 = (var25 * var9.length + var22) * var9.width + var21;
					int var67 = var9.blocksByteArray[((var25 + 1) * var9.length + var22) * var9.width + var21] & 255;

					if(var67 == 0) {
						int var69 = -1;
						if(var25 <= var56 && var60) {
							var69 = Block.sand.blockID;
						}

						if(var9.blocksByteArray[var65] != 0 && var69 > 0) {
							var9.blocksByteArray[var65] = (byte)var69;
						}
					}
				}
			}
		}

		this.updateLoadingBar("Carving...");
		var9 = this;
		var51 = this.width;
		var52 = this.length;
		var53 = this.height;
		var56 = var51 * var52 * var53 / 256 / 64 << 1;

		for(var21 = 0; var21 < var56; ++var21) {
			float var59 = var9.rand.nextFloat() * (float)var51;
			float var63 = var9.rand.nextFloat() * (float)var53;
			float var62 = var9.rand.nextFloat() * (float)var52;
			var25 = (int)((var9.rand.nextFloat() + var9.rand.nextFloat()) * 200.0F);
			float var66 = var9.rand.nextFloat() * (float)Math.PI * 2.0F;
			float var68 = 0.0F;
			float var71 = var9.rand.nextFloat() * (float)Math.PI * 2.0F;
			float var70 = 0.0F;
			float var73 = var9.rand.nextFloat() * var9.rand.nextFloat();

			for(var31 = 0; var31 < var25; ++var31) {
				var59 += MathHelper.sin(var66) * MathHelper.cos(var71);
				var62 += MathHelper.cos(var66) * MathHelper.cos(var71);
				var63 += MathHelper.sin(var71);
				var66 += var68 * 0.2F;
				var68 *= 0.9F;
				var68 += var9.rand.nextFloat() - var9.rand.nextFloat();
				var71 += var70 * 0.5F;
				var71 *= 0.5F;
				var70 *= 12.0F / 16.0F;
				var70 += var9.rand.nextFloat() - var9.rand.nextFloat();
				if(var9.rand.nextFloat() >= 0.25F) {
					float var74 = var59 + (var9.rand.nextFloat() * 4.0F - 2.0F) * 0.2F;
					float var33 = var63 + (var9.rand.nextFloat() * 4.0F - 2.0F) * 0.2F;
					float var77 = var62 + (var9.rand.nextFloat() * 4.0F - 2.0F) * 0.2F;
					float var75 = ((float)var9.height - var33) / (float)var9.height;
					float var80 = 1.2F + (var75 * 3.5F + 1.0F) * var73;
					float var78 = MathHelper.sin((float)var31 * (float)Math.PI / (float)var25) * var80;

					for(var5 = (int)(var74 - var78); var5 <= (int)(var74 + var78); ++var5) {
						for(int var81 = (int)(var33 - var78); var81 <= (int)(var33 + var78); ++var81) {
							for(int var40 = (int)(var77 - var78); var40 <= (int)(var77 + var78); ++var40) {
								float var41 = (float)var5 - var74;
								float var42 = (float)var81 - var33;
								float var48 = (float)var40 - var77;
								var41 = var41 * var41 + var42 * var42 * 2.0F + var48 * var48;
								if(var41 < var78 * var78 && var5 > 0 && var81 > 0 && var40 > 0 && var5 < var9.width - 1 && var81 < var9.height - 1 && var40 < var9.length - 1) {
									var7 = (var81 * var9.length + var40) * var9.width + var5;
									if(var9.blocksByteArray[var7] == Block.stone.blockID) {
										var9.blocksByteArray[var7] = 0;
									}
								}
							}
						}
					}
				}
			}
		}

		{
			int countCoal    = this.populateOre(Block.oreCoal.blockID, 1000, 10, (height << 2) / 5);
			int countIron    = this.populateOre(Block.oreIron.blockID, 800, 8, height * 3 / 5);
			int countGold    = this.populateOre(Block.oreGold.blockID, 500, 6, (height << 1) / 5);
			int countDiamond = this.populateOre(Block.oreDiamond.blockID, 800, 2, height / 5);
			System.out.println("Coal: " + countCoal + ", Iron: " + countIron + ", Gold: " + countGold + ", Diamond: " + countDiamond);
		}
		
		this.groundLevel = this.waterLevel - 9;

		world.skyColor = -16776961; // blue
		world.fogColor = -16777216; // black
		world.cloudColor = 16777215;
		world.cloudHeight = height + 2;
//
//		if(this.levelType == 1) {
//			var6.cloudColor = 2164736;
//			var6.fogColor = 1049600;
//			var6.skyColor = 1049600;
//			var6.skylightSubtracted = var6.skyBrightness = 7;
//			var6.defaultFluid = Block.lavaMoving.blockID;
//		}
//
//		if(this.levelType == 2) {
//			var6.skyColor = 13033215;
//			var6.fogColor = 13033215;
//			var6.cloudColor = 15658751;
//			var6.skylightSubtracted = var6.skyBrightness = 15;
//			var6.skyBrightness = 16;
//			var6.cloudHeight = var4 + 64;
//		}
//
//		if(this.levelType == 3) {
//			var6.skyColor = 7699847;
//			var6.fogColor = 5069403;
//			var6.cloudColor = 5069403;
//			var6.skylightSubtracted = var6.skyBrightness = 12;
//		}

		world.waterLevel = this.waterLevel;
		world.groundLevel = this.groundLevel;
		
		this.updateLoadingBar("Assembling...");
		// TODO what tf does this call do
		world.generate(width, height, length, this.blocksByteArray, null);
		
		this.updateLoadingBar("Building...");
		world.findSpawn();
		generateHouse(world);
		
		this.updateLoadingBar("Planting...");
		this.growGrassOnDirt(world);
		
		this.growTrees(world, 32);

		short flowerDensity = 100;

		this.populateFlowersAndMushrooms(world, Block.plantYellow, flowerDensity);
		this.populateFlowersAndMushrooms(world, Block.plantRed, flowerDensity);
		this.populateFlowersAndMushrooms(world, Block.mushroomBrown, 50);
		this.populateFlowersAndMushrooms(world, Block.mushroomRed, 50);
		
		this.updateLoadingBar("Lighting...");

		for(var7 = 0; var7 < 10000; ++var7) {
			world.updateLighting();
		}

		this.updateLoadingBar("Mobbing it up...");
		MobSpawner mobSpawner = new MobSpawner(world);

		for(int i = 0; i < 1000; i++) {
			mobSpawner.performSpawning();
		}

		world.createTime = System.currentTimeMillis();
		
		return world;
	}

	private static void generateHouse(World world) {
		int var1 = world.xSpawn;
		int var2 = world.ySpawn;
		int var3 = world.zSpawn;

		for(int var4 = var1 - 3; var4 <= var1 + 3; ++var4) {
			for(int var5 = var2 - 2; var5 <= var2 + 2; ++var5) {
				for(int var6 = var3 - 3; var6 <= var3 + 3; ++var6) {
					int var7 = var5 < var2 - 1 ? Block.obsidian.blockID : 0;
					if(var4 == var1 - 3 || var6 == var3 - 3 || var4 == var1 + 3 || var6 == var3 + 3 || var5 == var2 - 2 || var5 == var2 + 2) {
						var7 = Block.stone.blockID;
						if(var5 >= var2 - 1) {
							var7 = Block.planks.blockID;
						}
					}

					if(var6 == var3 - 3 && var4 == var1 && var5 >= var2 - 1 && var5 <= var2) {
						var7 = 0;
					}

					world.setBlockWithNotify(var4, var5, var6, var7);
				}
			}
		}

		world.setBlockWithNotify(var1 - 3 + 1, var2, var3, Block.torch.blockID);
		world.setBlockWithNotify(var1 + 3 - 1, var2, var3, Block.torch.blockID);
	}

	private void growGrassOnDirt(World var1) {
		
		for(int var2 = 0; var2 < this.width; ++var2) {
			for(int var3 = 0; var3 < this.height; ++var3) {
				for(int var4 = 0; var4 < this.length; ++var4) {
					if(var1.getBlockId(var2, var3, var4) == Block.dirt.blockID && var1.getBlockLightValue(var2, var3 + 1, var4) >= 4 && !var1.getBlockMaterial(var2, var3 + 1, var4).getCanBlockGrass()) {
						var1.setBlock(var2, var3, var4, Block.grass.blockID);
					}
				}
			}
		}
	}

	private void growTrees(World var1, int growAttempts) {

		for(int var3 = 0; var3 < growAttempts; ++var3) {

			int var4 = this.rand.nextInt(this.width);
			int var5 = this.rand.nextInt(this.height);
			int var6 = this.rand.nextInt(this.length);

			for(int var7 = 0; var7 < 25; ++var7) {
				int var8 = var4;
				int var9 = var5;
				int var10 = var6;

				for(int var11 = 0; var11 < 20; ++var11) {
					var8 += this.rand.nextInt(12) - this.rand.nextInt(12);
					var9 += this.rand.nextInt(3) - this.rand.nextInt(6);
					var10 += this.rand.nextInt(12) - this.rand.nextInt(12);
					if(var8 >= 0 && var9 >= 0 && var10 >= 0 && var8 < this.width && var9 < this.height && var10 < this.length) {
						var1.growTrees(var8, var9, var10);
					}
				}
			}
		}

	}

	private void populateFlowersAndMushrooms(World var1, BlockFlower var2, int var3) {
		var3 = (int)((long)this.width * (long)this.length * (long)this.height * (long)var3 / 1600000L);

		for(int var4 = 0; var4 < var3; ++var4) {

			int var5 = this.rand.nextInt(this.width);
			int var6 = this.rand.nextInt(this.height);
			int var7 = this.rand.nextInt(this.length);

			for(int var8 = 0; var8 < 10; ++var8) {
				int var9 = var5;
				int var10 = var6;
				int var11 = var7;

				for(int var12 = 0; var12 < 10; ++var12) {
					var9 += this.rand.nextInt(4) - this.rand.nextInt(4);
					var10 += this.rand.nextInt(2) - this.rand.nextInt(2);
					var11 += this.rand.nextInt(4) - this.rand.nextInt(4);
					if(var9 >= 0 && var11 >= 0 && var10 > 0 && var9 < this.width && var11 < this.length && var10 < this.height && var1.getBlockId(var9, var10, var11) == 0 && var2.canBlockStay(var1, var9, var10, var11)) {
						var1.setBlockWithNotify(var9, var10, var11, var2.blockID);
					}
				}
			}
		}

	}

	private int populateOre(int var1, int var2, int var3, int var4) {
		int var5 = 0;
		byte var26 = (byte)var1;
		int var6 = this.width;
		int var7 = this.length;
		int var8 = this.height;
		var2 = var6 * var7 * var8 / 256 / 64 * var2 / 100;

		for(int var9 = 0; var9 < var2; ++var9) {
			float var10 = this.rand.nextFloat() * (float)var6;
			float var11 = this.rand.nextFloat() * (float)var8;
			float var12 = this.rand.nextFloat() * (float)var7;
			if(var11 <= (float)var4) {
				int var13 = (int)((this.rand.nextFloat() + this.rand.nextFloat()) * 75.0F * (float)var3 / 100.0F);
				float var14 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
				float var15 = 0.0F;
				float var16 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
				float var17 = 0.0F;

				for(int var18 = 0; var18 < var13; ++var18) {
					var10 += MathHelper.sin(var14) * MathHelper.cos(var16);
					var12 += MathHelper.cos(var14) * MathHelper.cos(var16);
					var11 += MathHelper.sin(var16);
					var14 += var15 * 0.2F;
					var15 *= 0.9F;
					var15 += this.rand.nextFloat() - this.rand.nextFloat();
					var16 += var17 * 0.5F;
					var16 *= 0.5F;
					var17 *= 0.9F;
					var17 += this.rand.nextFloat() - this.rand.nextFloat();
					float var19 = MathHelper.sin((float)var18 * (float)Math.PI / (float)var13) * (float)var3 / 100.0F + 1.0F;

					for(int var20 = (int)(var10 - var19); var20 <= (int)(var10 + var19); ++var20) {
						for(int var21 = (int)(var11 - var19); var21 <= (int)(var11 + var19); ++var21) {
							for(int var22 = (int)(var12 - var19); var22 <= (int)(var12 + var19); ++var22) {
								float var23 = (float)var20 - var10;
								float var24 = (float)var21 - var11;
								float var25 = (float)var22 - var12;
								var23 = var23 * var23 + var24 * var24 * 2.0F + var25 * var25;
								if(var23 < var19 * var19 && var20 > 0 && var21 > 0 && var22 > 0 && var20 < this.width - 1 && var21 < this.height - 1 && var22 < this.length - 1) {
									int var27 = (var21 * this.length + var22) * this.width + var20;
									if(this.blocksByteArray[var27] == Block.stone.blockID) {
										this.blocksByteArray[var27] = var26;
										++var5;
									}
								}
							}
						}
					}
				}
			}
		}

		return var5;
	}

	private void updateLoadingBar(String message) {
		
		this.guiLoading.displayProgressMessage(message);
		
		this.loadingProgress++;
		
		int progress = (int) (this.loadingProgress * 100.0F / maxLoadingProgress);
		this.guiLoading.setLoadingProgress(progress);
	}
}
