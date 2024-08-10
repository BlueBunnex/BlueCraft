package net.minecraft.src.entity;

import net.minecraft.src.item.Item;
import net.minecraft.src.world.World;

public class EntityGuard extends EntityMob {
	
	public EntityGuard(World world) {
		super(world);
		this.texture = "/mob/guard.png";
		//this.moveSpeed = 0.5F;
		this.attackStrength = 5;
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
		return Item.ingotIron.shiftedIndex;
	}
}
