package net.minecraft.src.item;

import java.util.Random;

import net.minecraft.src.block.Block;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.world.World;

public class Item {
	
	protected static Random rand = new Random();
	public static Item[] itemsList = new Item[1024];
	
	public final int shiftedIndex;
	protected int maxStackSize = 64;
	protected int maxDamage = 32;
	protected int iconIndex;
	
	//private static int indexCounter = 0;

	protected Item(int index) {
		
		this.shiftedIndex = 256 + index;
		
		if (itemsList[256 + index] != null)
			System.err.println("CONFLICT @ " + index);
		
		System.out.println(index + 256 + " " + this);

		itemsList[256 + index] = this;
	}

	protected Item setIconIndex(int iconIndex) {
		this.iconIndex = iconIndex;
		return this;
	}

	public int getIconIndex(ItemStack var1) {
		return this.iconIndex;
	}

	protected Item setMaxStackSize(int value) {
		maxStackSize = value;
		return this;
	}
	
	public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7) {
		return false;
	}

	public float getStrVsBlock(Block block) {
		return 1.0F;
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		return var1;
	}

	public int getItemStackLimit() {
		return this.maxStackSize;
	}

	public int getMaxDamage() {
		return this.maxDamage;
	}

	public void hitEntity(ItemStack var1, EntityLiving var2) {}

	public void onBlockDestroyed(ItemStack var1, int var2, int var3, int var4, int var5) {}

	public int getDamageVsEntity(Entity var1) {
		return 1;
	}
	
	static {
		for (AllItems a : AllItems.values()) {
			itemsList[a.item.shiftedIndex - 256] = a.item;
		}
	}
}
