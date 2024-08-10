package net.minecraft.src.entity;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.item.Item;
import net.minecraft.src.world.World;

public class EntityPig extends EntityAnimal {

	public EntityPig(World world) {
		super(world);
		this.texture = "/mob/pig.png";
		this.setSize(0.9F, 0.9F);
	}

	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
	}

	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
	}

	protected String getLivingSound() {
		return "mob.pig";
	}

	protected String getHurtSound() {
		return "mob.pig";
	}

	protected String getDeathSound() {
		return "mob.pigdeath";
	}

	public boolean interact(EntityPlayer player) {
		player.mountEntity(this);
		return true;
	}

	protected int getDropItemId() {
		return this.fire > 0 ? Item.porkCooked.shiftedIndex : Item.porkRaw.shiftedIndex;
	}
}
