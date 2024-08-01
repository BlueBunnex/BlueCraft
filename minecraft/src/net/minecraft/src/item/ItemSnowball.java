package net.minecraft.src.item;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySnowball;
import net.minecraft.src.world.World;

public class ItemSnowball extends Item {
	public ItemSnowball(int var1) {
		super(var1);
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		--var1.stackSize;
		var2.playSoundAtEntity(var3, "random.bow", 1.0F, 0.6F / (rand.nextFloat() * 0.4F + 0.8F));
		var2.spawnEntityInWorld(new EntitySnowball(var2, var3));
		return var1;
	}
}
