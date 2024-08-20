package net.minecraft.src.entity;

import net.minecraft.src.MathHelper;
import net.minecraft.src.item.ItemEntry;
import net.minecraft.src.world.World;

public class EntityZombie extends EntityMob {
	
	public EntityZombie(World world) {
		super(world);
		this.texture = "/mob/zombie.png";
		this.moveSpeed = 0.5F;
		this.attackStrength = 5;
	}

	public void onLivingUpdate() {
		
		// burn in daylight
		if (this.worldObj.isDaytime()) {
			
			float brightness = this.getBrightness(1.0F);
			if (
					brightness > 0.5F
					&& this.worldObj.canBlockSeeTheSky(
							MathHelper.floor_double(this.posX),
							MathHelper.floor_double(this.posY),
							MathHelper.floor_double(this.posZ)
							)
				) {
				this.fire = 300;
			}
		}

		super.onLivingUpdate();
	}

	protected String getLivingSound() {
		return "mob.zombie";
	}

	protected String getHurtSound() {
		return "mob.zombiehurt";
	}

	protected String getDeathSound() {
		return "mob.zombiedeath";
	}

	protected int getDropItemId() {
		return ItemEntry.ruby.item.shiftedIndex;
	}
}
