package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.src.block.Block;
import net.minecraft.src.entity.EntityAnimal;
import net.minecraft.src.entity.EntityCreeper;
import net.minecraft.src.entity.EntityMob;
import net.minecraft.src.entity.EntityPig;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.entity.EntitySheep;
import net.minecraft.src.entity.EntitySkeleton;
import net.minecraft.src.entity.EntitySpider;
import net.minecraft.src.entity.EntityZombie;
import net.minecraft.src.entity.spawning.SpawnerAnimals;
import net.minecraft.src.entity.spawning.SpawnerMonsters;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.world.World;

public class PlayerController {
	
	private int curBlockX = -1;
	private int curBlockY = -1;
	private int curBlockZ = -1;
	private float curBlockDamage = 0.0F;
	private float prevBlockDamage = 0.0F;
	private float blockDestroySoundCounter = 0.0F;
	private int blockHitWait = 0;
	private SpawnerAnimals monsterSpawner = new SpawnerMonsters(this, 200, EntityMob.class, new Class[]{EntityZombie.class, EntitySkeleton.class, EntityCreeper.class, EntitySpider.class});
	private SpawnerAnimals animalSpawner = new SpawnerAnimals(20, EntityAnimal.class, new Class[]{EntitySheep.class, EntityPig.class});
	
	protected final Minecraft mc;

	public PlayerController(Minecraft var1) {
		this.mc = var1;
	}

	public void initController() {
	}

	public void onWorldChange(World world) {}

	private boolean sendBlockRemovedOld(int var1, int var2, int var3) {
		this.mc.effectRenderer.addBlockDestroyEffects(var1, var2, var3);
		World var4 = this.mc.theWorld;
		Block var5 = Block.blocksList[var4.getBlockId(var1, var2, var3)];
		int var6 = var4.getBlockMetadata(var1, var2, var3);
		boolean var7 = var4.setBlockWithNotify(var1, var2, var3, 0);
		if(var5 != null && var7) {
			this.mc.sndManager.playSound(var5.stepSound.getBreakSound(), (float)var1 + 0.5F, (float)var2 + 0.5F, (float)var3 + 0.5F, (var5.stepSound.getVolume() + 1.0F) / 2.0F, var5.stepSound.getPitch() * 0.8F);
			var5.onBlockDestroyedByPlayer(var4, var1, var2, var3, var6);
		}

		return var7;
	}
	
	public boolean sendBlockRemoved(int var1, int var2, int var3) {
		int var4 = this.mc.theWorld.getBlockId(var1, var2, var3);
		int var5 = this.mc.theWorld.getBlockMetadata(var1, var2, var3);
		boolean var6 = this.sendBlockRemovedOld(var1, var2, var3);
		ItemStack var7 = this.mc.thePlayer.getCurrentEquippedItem();
		if(var7 != null) {
			var7.onDestroyBlock(var4, var1, var2, var3);
			if(var7.stackSize == 0) {
				var7.onItemDestroyedByUse(this.mc.thePlayer);
				this.mc.thePlayer.destroyCurrentEquippedItem();
			}
		}

		if(var6 && Block.blocksList[var4].canHarvestBlock(this.mc.thePlayer)) {
			Block.blocksList[var4].dropBlockAsItem(this.mc.theWorld, var1, var2, var3, var5);
		}

		return var6;
	}

	public void sendBlockRemoving(int x, int y, int z, int side) {
		
		if (this.blockHitWait > 0) {
			
			this.blockHitWait--;
			
		} else {
			
			// is hitting same block as was last call
			if (x == this.curBlockX && y == this.curBlockY && z == this.curBlockZ) {
				
				int blockID = this.mc.theWorld.getBlockId(x, y, z);
				
				if (blockID == 0)
					return;

				Block block = Block.blocksList[blockID];
				this.curBlockDamage += block.blockStrength(this.mc.thePlayer);
				
				// handle sound
				if (this.blockDestroySoundCounter % 4.0F == 0.0F && block != null) {
					
					this.mc.sndManager.playSound(
							block.stepSound.getStepSound(),
							(float) x + 0.5F,
							(float) y + 0.5F,
							(float) z + 0.5F,
							(block.stepSound.getVolume() + 1.0F) / 8.0F,
							block.stepSound.getPitch() * 0.5F
						);
				}
				this.blockDestroySoundCounter++;
				
				// destroy block
				if (this.curBlockDamage >= 1.0F) {
					this.sendBlockRemoved(x, y, z);
					this.curBlockDamage = 0.0F;
					this.prevBlockDamage = 0.0F;
					this.blockDestroySoundCounter = 0.0F;
					this.blockHitWait = 5;
				}
				
			// started hitting new block
			} else {
				this.curBlockDamage = 0.0F;
				this.prevBlockDamage = 0.0F;
				this.blockDestroySoundCounter = 0.0F;
				this.curBlockX = x;
				this.curBlockY = y;
				this.curBlockZ = z;
			}

		}
	}

	public void resetBlockRemoving() {
		this.curBlockDamage = 0.0F;
		this.blockHitWait = 0;
	}

	public void setPartialTime(float var1) {
		
		if(this.curBlockDamage <= 0.0F) {
			this.mc.ingameGUI.damageGuiPartialTime = 0.0F;
			this.mc.renderGlobal.damagePartialTime = 0.0F;
		} else {
			float var2 = this.prevBlockDamage + (this.curBlockDamage - this.prevBlockDamage) * var1;
			this.mc.ingameGUI.damageGuiPartialTime = var2;
			this.mc.renderGlobal.damagePartialTime = var2;
		}
	}

	public float getBlockReachDistance() {
		return 4.0F; // was 5, weird
	}

	public void flipPlayer(EntityPlayer player) {
		player.rotationYaw = -180.0F;
	}

	public void onUpdate() {
		this.prevBlockDamage = this.curBlockDamage;
		this.monsterSpawner.onUpdate(this.mc.theWorld);
		this.animalSpawner.onUpdate(this.mc.theWorld);
	}

	public boolean shouldDrawHUD() {
		return true;
	}

	public void onRespawn(EntityPlayer var1) {
	}
}
