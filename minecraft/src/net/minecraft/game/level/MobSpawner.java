package net.minecraft.game.level;

import net.minecraft.game.entity.Entity;
import net.minecraft.game.entity.EntityLiving;
import net.minecraft.game.entity.animal.EntityAnimal;
import net.minecraft.game.entity.animal.EntityPig;
import net.minecraft.game.entity.monster.EntityCreeper;
import net.minecraft.game.entity.monster.EntityMob;
import net.minecraft.game.entity.monster.EntitySkeleton;
import net.minecraft.game.entity.monster.EntitySpider;
import net.minecraft.game.entity.monster.EntityZombie;

public class MobSpawner {
	
	private World world;

	public MobSpawner(World world) {
		this.world = world;
	}

	public final void performSpawning() {
		int var1 = this.world.width * this.world.length * this.world.height * 20 / 64 / 64 / 64;
		var1 /= 2;
		if(this.world.difficultySetting == 0) {
			var1 = 0 / 4;
		}

		if(this.world.difficultySetting == 1) {
			var1 = var1 * 3 / 4;
		}

		if(this.world.difficultySetting == 2) {
			var1 = (var1 << 2) / 4;
		}

		if(this.world.difficultySetting == 3) {
			var1 = var1 * 6 / 4;
		}

		int var2 = this.world.width * this.world.length / 4000;
		int var3 = this.world.entitiesInLevelList(EntityMob.class);

		int var4;
		EntityLiving var5;
		int var6;
		int var7;
		int var8;
		int var9;
		int var10;
		int var11;
		int var12;
		int var13;
		int var14;
		int var15;
		float var16;
		float var17;
		float var18;
		float var19;
		float var20;
		float var21;
		MobSpawner var22;
		Object var23;
		for(var4 = 0; var4 < 4; ++var4) {
			if(var3 < var1) {
				var5 = this.world.playerEntity;
				var22 = this;
				var6 = 0;
				var7 = this.world.random.nextInt(5);
				var8 = this.world.random.nextInt(this.world.width);
				var9 = (int)(Math.min(this.world.random.nextFloat(), this.world.random.nextFloat()) * (float)this.world.height);
				var10 = this.world.random.nextInt(this.world.length);

				for(var11 = 0; var11 < 2; ++var11) {
					var12 = var8;
					var13 = var9;
					var14 = var10;

					for(var15 = 0; var15 < 3; ++var15) {
						var12 += var22.world.random.nextInt(6) - var22.world.random.nextInt(6);
						var13 += var22.world.random.nextInt(1) - var22.world.random.nextInt(1);
						var14 += var22.world.random.nextInt(6) - var22.world.random.nextInt(6);
						if(var12 >= 0 && var14 > 0 && var13 >= 0 && var13 < var22.world.height - 2 && var12 < var22.world.width && var14 < var22.world.length) {
							var16 = (float)var12 + 0.5F;
							var17 = (float)var13 + 0.5F;
							var18 = (float)var14 + 0.5F;
							if(var5 != null) {
								var19 = var16 - var5.posX;
								var20 = var17 - var5.posY;
								var21 = var18 - var5.posZ;
								var19 = var19 * var19 + var20 * var20 + var21 * var21;
								if(var19 < 1024.0F) {
									continue;
								}
							} else {
								var19 = var16 - (float)var22.world.xSpawn;
								var20 = var17 - (float)var22.world.ySpawn;
								var21 = var18 - (float)var22.world.zSpawn;
								var19 = var19 * var19 + var20 * var20 + var21 * var21;
								if(var19 < 1024.0F) {
									continue;
								}
							}

							var23 = null;
							if(var7 == 0) {
								var23 = new EntitySkeleton(var22.world);
							}

							if(var7 == 1) {
								var23 = new EntityCreeper(var22.world);
							}

							if(var7 == 2) {
								var23 = new EntitySpider(var22.world);
							}

							if(var7 == 3) {
								var23 = new EntityZombie(var22.world);
							}

							if(var23 instanceof EntityMob && var22.world.difficultySetting == 0) {
								var23 = null;
							}

							if(var23 != null && !var22.world.isBlockNormalCube(var12, var13, var14) && var22.world.isBlockNormalCube(var12, var13 - 1, var14) && ((EntityLiving)var23).getCanSpawnHere(var16, var17, var18)) {
								var21 = var22.world.random.nextFloat() * 360.0F;
								((EntityLiving)var23).setPositionAndRotation(var16, var17, var18, var21, 0.0F);
								++var6;
								var22.world.spawnEntityInWorld((Entity)var23);
							}
						}
					}
				}

				var3 += var6;
			}
		}

		var4 = this.world.entitiesInLevelList(EntityAnimal.class);

		for(var1 = 0; var1 < 4; ++var1) {
			if(var4 < var2) {
				var5 = this.world.playerEntity;
				var22 = this;
				var6 = 0;
				
				var7 = this.world.random.nextInt(1); // was [0,1] but we got rid of sheep so now only pigs lol
				
				var8 = this.world.random.nextInt(this.world.width);
				var9 = this.world.random.nextInt(this.world.height);
				var10 = this.world.random.nextInt(this.world.length);

				for(var11 = 0; var11 < 2; ++var11) {
					var12 = var8;
					var13 = var9;
					var14 = var10;

					for(var15 = 0; var15 < 3; ++var15) {
						var12 += var22.world.random.nextInt(6) - var22.world.random.nextInt(6);
						var13 += var22.world.random.nextInt(1) - var22.world.random.nextInt(1);
						var14 += var22.world.random.nextInt(6) - var22.world.random.nextInt(6);
						if(var12 >= 0 && var14 > 0 && var13 >= 0 && var13 < var22.world.height - 2 && var12 < var22.world.width && var14 < var22.world.length) {
							var16 = (float)var12 + 0.5F;
							var17 = (float)var13 + 0.5F;
							var18 = (float)var14 + 0.5F;
							if(var5 != null) {
								var19 = var16 - var5.posX;
								var20 = var17 - var5.posY;
								var21 = var18 - var5.posZ;
								var19 = var19 * var19 + var20 * var20 + var21 * var21;
								if(var19 < 1024.0F) {
									continue;
								}
							} else {
								var19 = var16 - (float)var22.world.xSpawn;
								var20 = var17 - (float)var22.world.ySpawn;
								var21 = var18 - (float)var22.world.zSpawn;
								var19 = var19 * var19 + var20 * var20 + var21 * var21;
								if(var19 < 1024.0F) {
									continue;
								}
							}

							var23 = null;
							if(var7 == 0) {
								var23 = new EntityPig(var22.world);
							}

							if(var23 != null && !var22.world.isBlockNormalCube(var12, var13, var14) && var22.world.isBlockNormalCube(var12, var13 - 1, var14) && ((EntityLiving)var23).getCanSpawnHere(var16, var17, var18)) {
								var21 = var22.world.random.nextFloat() * 360.0F;
								((EntityLiving)var23).setPositionAndRotation(var16, var17, var18, var21, 0.0F);
								++var6;
								var22.world.spawnEntityInWorld((Entity)var23);
							}
						}
					}
				}

				var4 += var6;
			}
		}

	}
}
