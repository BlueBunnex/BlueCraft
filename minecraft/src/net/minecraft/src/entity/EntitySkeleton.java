package net.minecraft.src.entity;

import net.minecraft.io.NBTTagCompound;
import net.minecraft.src.MathHelper;
import net.minecraft.src.item.AllItems;
import net.minecraft.src.item.Item;
import net.minecraft.src.world.World;

public class EntitySkeleton extends EntityMob {
	
	public EntitySkeleton(World world) {
		super(world);
		this.texture = "/mob/skeleton.png";
	}

	protected String getLivingSound() {
		return "mob.skeleton";
	}

	protected String getHurtSound() {
		return "mob.skeletonhurt";
	}

	protected String getDeathSound() {
		return "mob.skeletonhurt";
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

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
	}

	protected int getDropItemId() {
		return AllItems.ruby.item.shiftedIndex;
	}
}
