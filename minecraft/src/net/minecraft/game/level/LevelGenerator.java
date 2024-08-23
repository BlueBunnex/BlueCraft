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
	
	private int loadingProgress = 0;
	private static final int maxLoadingProgress = 10;
	private static final int WORLD_DIM = 64;

	public LevelGenerator(IProgressUpdate progress) {
		this.guiLoading = progress;
	}
	
	private void updateLoadingBar(String message) {
		
		this.guiLoading.displayProgressMessage(message);
		
		this.loadingProgress++;
		
		int progress = (int) (this.loadingProgress * 100.0F / maxLoadingProgress);
		this.guiLoading.setLoadingProgress(progress);
	}

	public final World generate() {
		
		Random random = new Random();
		int waterLevel = 0;
		int groundLevel = 0;
		
		int var5 = 1;

		this.guiLoading.displayProgressMessage("Generating level");
		
		World world = new World();
		world.waterLevel = waterLevel;
		world.groundLevel = groundLevel;
		
		byte[] blocksByteArray = new byte[WORLD_DIM * WORLD_DIM * WORLD_DIM];
		
		

		int var7;
		int var21;
		int var25;
		int var31;
		int var51;
		int var52;
		int var53;
		int var56;
		for(var7 = 0; var7 < var5; ++var7) {
			waterLevel = WORLD_DIM - 32 - var7 * 48;
			groundLevel = waterLevel - 2;
			int[] var8;
			NoiseGeneratorOctaves var13;
			int var22;
			double var32;
			int[] var46;
			
			this.updateLoadingBar("Making noise...");
			
			NoiseGeneratorDistort var10 = new NoiseGeneratorDistort(new NoiseGeneratorOctaves(random, 8), new NoiseGeneratorOctaves(random, 8));
			NoiseGeneratorDistort var11 = new NoiseGeneratorDistort(new NoiseGeneratorOctaves(random, 8), new NoiseGeneratorOctaves(random, 8));
			NoiseGeneratorOctaves var12 = new NoiseGeneratorOctaves(random, 6);
			var13 = new NoiseGeneratorOctaves(random, 2);
			int[] var14 = new int[WORLD_DIM * WORLD_DIM];
			var22 = 0;

			label349:
			while(true) {
				if(var22 >= WORLD_DIM) {
					var8 = var14;
					this.updateLoadingBar("Eroding...");
					var46 = var14;
					var11 = new NoiseGeneratorDistort(new NoiseGeneratorOctaves(random, 8), new NoiseGeneratorOctaves(random, 8));
					NoiseGeneratorDistort var50 = new NoiseGeneratorDistort(new NoiseGeneratorOctaves(random, 8), new NoiseGeneratorOctaves(random, 8));
					var52 = 0;

					while(true) {
						
						if(var52 >= WORLD_DIM)
							break label349;

						for(var53 = 0; var53 < WORLD_DIM; ++var53) {
							double var20 = var11.generateNoise((double)(var52 << 1), (double)(var53 << 1)) / 8.0D;
							var22 = var50.generateNoise((double)(var52 << 1), (double)(var53 << 1)) > 0.0D ? 1 : 0;
							if(var20 > 2.0D) {
								int var58 = var46[var52 + var53 * WORLD_DIM];
								var58 = ((var58 - var22) / 2 << 1) + var22;
								var46[var52 + var53 * WORLD_DIM] = var58;
							}
						}

						++var52;
					}
				}

				for(var25 = 0; var25 < WORLD_DIM; ++var25) {
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

					var14[var22 + var25 * WORLD_DIM] = (int)var34;
				}

				++var22;
			}

			this.updateLoadingBar("Soiling...");
			var46 = var8;
			int var49 = WORLD_DIM;
			var51 = WORLD_DIM;
			var52 = WORLD_DIM;
			NoiseGeneratorOctaves var54 = new NoiseGeneratorOctaves(random, 8);
			NoiseGeneratorOctaves var55 = new NoiseGeneratorOctaves(random, 8);

			for(var21 = 0; var21 < var49; ++var21) {
				double var57 = Math.abs(((double)var21 / ((double)var49 - 1.0D) - 0.5D) * 2.0D);

				for(int var24 = 0; var24 < var51; ++var24) {
					double var64 = Math.abs(((double)var24 / ((double)var51 - 1.0D) - 0.5D) * 2.0D);
					double var27 = Math.max(var57, var64);
					var27 = var27 * var27 * var27;
					int var29 = (int)(var54.generateNoise((double)var21, (double)var24) / 24.0D) - 4;
					int var72 = var46[var21 + var24 * var49] + waterLevel;
					var31 = var72 + var29;
					var46[var21 + var24 * var49] = Math.max(var72, var31);
					if(var46[var21 + var24 * var49] > var52 - 2) {
						var46[var21 + var24 * var49] = var52 - 2;
					}

					if(var46[var21 + var24 * var49] <= 0) {
						var46[var21 + var24 * var49] = 1;
					}

					var32 = var55.generateNoise((double)var21 * 2.3D, (double)var24 * 2.3D) / 24.0D;
					int var76 = (int)(Math.sqrt(Math.abs(var32)) * Math.signum(var32) * 20.0D) + waterLevel;
					var76 = (int)((double)var76 * (1.0D - var27) + var27 * (double) WORLD_DIM);
					if(var76 > waterLevel) {
						var76 = WORLD_DIM;
					}

					for(int var35 = 0; var35 < var52; ++var35) {
						int var79 = (var35 * WORLD_DIM + var24) * WORLD_DIM + var21;
						int var37 = 0;
						if(var35 <= var72) {
							var37 = Block.dirt.blockID;
						}

						if(var35 <= var31) {
							var37 = Block.stone.blockID;
						}

						if(blocksByteArray[var79] == 0) {
							blocksByteArray[var79] = (byte)var37;
						}
					}
				}
			}

			this.updateLoadingBar("Growing...");
			var46 = var8;
			var49 = WORLD_DIM;
			var51 = WORLD_DIM;
			var13 = new NoiseGeneratorOctaves(random, 8);
			var54 = new NoiseGeneratorOctaves(random, 8);
			var56 = waterLevel - 1;

			for(var21 = 0; var21 < var49; ++var21) {
				for(var22 = 0; var22 < var51; ++var22) {
					
					boolean var60 = var13.generateNoise((double)var21, (double)var22) > 8.0D;

					var25 = var46[var21 + var22 * var49];
					int var65 = (var25 * WORLD_DIM + var22) * WORLD_DIM + var21;
					int var67 = blocksByteArray[((var25 + 1) * WORLD_DIM + var22) * WORLD_DIM + var21] & 255;

					if(var67 == 0) {
						int var69 = -1;
						if(var25 <= var56 && var60) {
							var69 = Block.sand.blockID;
						}

						if(blocksByteArray[var65] != 0 && var69 > 0) {
							blocksByteArray[var65] = (byte)var69;
						}
					}
				}
			}
		}

		// generate ~caves (dungeons) and ores
		this.updateLoadingBar("Carving...");
		{
			populateDungeon(random, blocksByteArray, 50);

			int countCoal    = populateOre(random, blocksByteArray, (byte) Block.oreCoal.blockID, 1000, 10, (WORLD_DIM << 2) / 5);
			int countIron    = populateOre(random, blocksByteArray, (byte) Block.oreIron.blockID, 800, 8, WORLD_DIM * 3 / 5);
			int countGold    = populateOre(random, blocksByteArray, (byte) Block.oreGold.blockID, 500, 6, (WORLD_DIM << 1) / 5);
			int countDiamond = populateOre(random, blocksByteArray, (byte) Block.oreDiamond.blockID, 800, 2, WORLD_DIM / 5);
			
			System.out.println("Coal: " + countCoal + ", Iron: " + countIron + ", Gold: " + countGold + ", Diamond: " + countDiamond);
		}
		
		// TODO shouldn't this stuff be up where World is initialized?
		groundLevel = waterLevel - 9;

		world.skyColor = -16776961; // blue
		world.fogColor = -16777216; // black
		world.cloudColor = 16777215;
		world.cloudHeight = WORLD_DIM + 2;

		world.waterLevel = waterLevel;
		world.groundLevel = groundLevel;
		
		this.updateLoadingBar("Assembling...");
		world.initializeEmptyWorld(WORLD_DIM, WORLD_DIM, WORLD_DIM, blocksByteArray, null);
		
		// build the indev house (spawn)
		this.updateLoadingBar("Building...");
		world.findSpawn();
		generateHouse(world);
		
		// grow grass, trees, and foliage (flowers + mushrooms)
		this.updateLoadingBar("Planting...");
		
		growGrassOnDirt(world);
		
		growTrees(random, world, 32);

		growFoliage(random, world, Block.plantYellow, 100);
		growFoliage(random, world, Block.plantRed, 100);
		growFoliage(random, world, Block.mushroomBrown, 50);
		growFoliage(random, world, Block.mushroomRed, 50);
		
		// update lighting
		this.updateLoadingBar("Lighting...");

		for(int i = 0; i < 10000; i++)
			world.updateLighting();

		// spawn mobs
		this.updateLoadingBar("Mobbing it up...");
		
		MobSpawner mobSpawner = new MobSpawner(world);

		for(int i = 0; i < 1000; i++) {
			mobSpawner.performSpawning();
		}

		// finalize
		world.createTime = System.currentTimeMillis();
		
		return world;
	}

	private static void populateDungeon(Random random, byte[] blocksByteArray, int count) {
		
		for (int i=0; i<count; i++) {
			
			// 0 => short = x, long = z
			// 1 => short = z, long = x
			int direction = random.nextInt(2);
			
			int shortStart = random.nextInt(WORLD_DIM / 4) * 4;
			int longStart  = random.nextInt(WORLD_DIM / 4) * 4;
			
			int xStart = direction == 0 ? shortStart : longStart,    maxX = xStart + (direction == 0 ? 3 : 16);
			int yStart = random.nextInt((WORLD_DIM - 40) / 4) * 4,   maxY = yStart + 3;
			int zStart = direction == 1 ? shortStart : longStart,    maxZ = zStart + (direction == 1 ? 3 : 16);
			
			for (int x = xStart; x < maxX; x++) {
			for (int y = yStart; y < maxY; y++) {
			for (int z = zStart; z < maxZ; z++) {

				int index = get3DArrayIndex(x, y, z);
				
				if (index < blocksByteArray.length) {
					
					// replace stone with a regular path
					if (blocksByteArray[index] == Block.stone.blockID) {
						
						// struts
						int shortPos = direction == 0 ? x : z;
						int longPos  = direction == 1 ? x : z;
						
						if (longPos % 6 == 0 && (shortPos != shortStart + 1 || y == yStart + 2)) {
							blocksByteArray[index] = (byte) Block.planks.blockID;
						} else {
							blocksByteArray[index] = 0;
						}
						
					// replace existing paths (the struts) with just air (to prevent struts at junctions)
					} else if (blocksByteArray[index] == Block.planks.blockID) {
						
						blocksByteArray[index] = 0;
					}
				}
			}}}
		}
	}
	
	private static int get3DArrayIndex(int x, int y, int z) {
		return (y * WORLD_DIM + z) * WORLD_DIM + x;
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

	private static void growGrassOnDirt(World var1) {
		
		for(int var2 = 0; var2 < WORLD_DIM; ++var2) {
			for(int var3 = 0; var3 < WORLD_DIM; ++var3) {
				for(int var4 = 0; var4 < WORLD_DIM; ++var4) {
					if(var1.getBlockId(var2, var3, var4) == Block.dirt.blockID && var1.getBlockLightValue(var2, var3 + 1, var4) >= 4 && !var1.getBlockMaterial(var2, var3 + 1, var4).getCanBlockGrass()) {
						var1.setBlock(var2, var3, var4, Block.grass.blockID);
					}
				}
			}
		}
	}

	private static void growTrees(Random random, World world, int growAttempts) {

		for(int var3 = 0; var3 < growAttempts; ++var3) {

			int var4 = random.nextInt(WORLD_DIM);
			int var5 = random.nextInt(WORLD_DIM);
			int var6 = random.nextInt(WORLD_DIM);

			for(int var7 = 0; var7 < 25; ++var7) {
				int var8 = var4;
				int var9 = var5;
				int var10 = var6;

				for(int var11 = 0; var11 < 20; ++var11) {
					var8 += random.nextInt(12) - random.nextInt(12);
					var9 += random.nextInt(3) - random.nextInt(6);
					var10 += random.nextInt(12) - random.nextInt(12);
					if(var8 >= 0 && var9 >= 0 && var10 >= 0 && var8 < WORLD_DIM && var9 < WORLD_DIM && var10 < WORLD_DIM) {
						world.growTrees(var8, var9, var10);
					}
				}
			}
		}

	}

	private static void growFoliage(Random random, World var1, BlockFlower foliageBlock, int growAttempts) {

		for(int i = 0; i < growAttempts; i++) {

			int x = random.nextInt(WORLD_DIM);
			int y = random.nextInt(WORLD_DIM);
			int z = random.nextInt(WORLD_DIM);

			for (int var8 = 0; var8 < 10; ++var8) {
				int var9 = x;
				int var10 = y;
				int var11 = z;

				for (int var12 = 0; var12 < 10; ++var12) {
					var9 += random.nextInt(4) - random.nextInt(4);
					var10 += random.nextInt(2) - random.nextInt(2);
					var11 += random.nextInt(4) - random.nextInt(4);
					
					if (
							   var9 >= 0
							&& var11 >= 0
							&& var10 > 0
							&& var9 < WORLD_DIM
							&& var11 < WORLD_DIM
							&& var10 < WORLD_DIM
							&& var1.getBlockId(var9, var10, var11) == 0
							&& foliageBlock.canBlockStay(var1, var9, var10, var11)
							) {
						
						var1.setBlockWithNotify(var9, var10, var11, foliageBlock.blockID);
					}
				}
			}
		}

	}

	private static int populateOre(Random random, byte[] blocksByteArray, byte blockID, int var2, int var3, int var4) {
		int var5 = 0;
		int var6 = WORLD_DIM;
		int var7 = WORLD_DIM;
		int var8 = WORLD_DIM;
		var2 = var6 * var7 * var8 / 256 / 64 * var2 / 100;

		for(int var9 = 0; var9 < var2; ++var9) {
			float var10 = random.nextFloat() * (float)var6;
			float var11 = random.nextFloat() * (float)var8;
			float var12 = random.nextFloat() * (float)var7;
			if(var11 <= (float)var4) {
				int var13 = (int)((random.nextFloat() + random.nextFloat()) * 75.0F * (float)var3 / 100.0F);
				float var14 = random.nextFloat() * (float)Math.PI * 2.0F;
				float var15 = 0.0F;
				float var16 = random.nextFloat() * (float)Math.PI * 2.0F;
				float var17 = 0.0F;

				for(int var18 = 0; var18 < var13; ++var18) {
					var10 += MathHelper.sin(var14) * MathHelper.cos(var16);
					var12 += MathHelper.cos(var14) * MathHelper.cos(var16);
					var11 += MathHelper.sin(var16);
					var14 += var15 * 0.2F;
					var15 *= 0.9F;
					var15 += random.nextFloat() - random.nextFloat();
					var16 += var17 * 0.5F;
					var16 *= 0.5F;
					var17 *= 0.9F;
					var17 += random.nextFloat() - random.nextFloat();
					float var19 = MathHelper.sin((float)var18 * (float)Math.PI / (float)var13) * (float)var3 / 100.0F + 1.0F;

					for(int var20 = (int)(var10 - var19); var20 <= (int)(var10 + var19); ++var20) {
						for(int var21 = (int)(var11 - var19); var21 <= (int)(var11 + var19); ++var21) {
							for(int var22 = (int)(var12 - var19); var22 <= (int)(var12 + var19); ++var22) {
								float var23 = (float)var20 - var10;
								float var24 = (float)var21 - var11;
								float var25 = (float)var22 - var12;
								var23 = var23 * var23 + var24 * var24 * 2.0F + var25 * var25;
								if(var23 < var19 * var19 && var20 > 0 && var21 > 0 && var22 > 0 && var20 < WORLD_DIM - 1 && var21 < WORLD_DIM - 1 && var22 < WORLD_DIM - 1) {
									
									int index = get3DArrayIndex(var20, var21, var22);
									
									if(blocksByteArray[index] == Block.stone.blockID) {
										blocksByteArray[index] = blockID;
										var5++;
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
}
