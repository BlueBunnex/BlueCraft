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
	
	public static Item
	swordIron      = new ItemSword(0, 2).setIconIndex(0),
	pickaxeIron    = new ItemPickaxe(2, 2).setIconIndex(1),
	axeIron        = new ItemAxe(3, 2).setIconIndex(2),
	shovelIron     = new ItemSpade(1, 2).setIconIndex(3),
	
	swordMithril   = new ItemSword(4, 3).setIconIndex(16),
	pickaxeMithril = new ItemPickaxe(6, 3).setIconIndex(17),
	axeMithril     = new ItemAxe(7, 3).setIconIndex(18),
	shovelMithril  = new ItemSpade(5, 3).setIconIndex(19),
	
	helmetIron     = new ItemArmor(8, 2, 2, 0).setIconIndex(4),
	plateIron      = new ItemArmor(9, 2, 2, 1).setIconIndex(5),
	legsIron       = new ItemArmor(10, 2, 2, 2).setIconIndex(6),
	bootsIron      = new ItemArmor(11, 2, 2, 3).setIconIndex(7),
	
	helmetMithril  = new ItemArmor(12, 3, 3, 0).setIconIndex(20),
	plateMithril   = new ItemArmor(13, 3, 3, 1).setIconIndex(21),
	legsMithril    = new ItemArmor(14, 3, 3, 2).setIconIndex(22),
	bootsMithril   = new ItemArmor(15, 3, 3, 3).setIconIndex(23),
	
	ingotIron      = new Item(16).setIconIndex(145),
	ingotMithril   = new Item(17).setIconIndex(146),
	
	flintAndSteel  = new ItemFlintAndSteel(18).setIconIndex(128),
	appleRed       = new ItemFood(19, 4).setIconIndex(161),
	appleGold      = new ItemFood(20, 20).setIconIndex(162),
	coal           = new Item(21).setIconIndex(144),
	stick          = new Item(22).setIconIndex(130),
	bread          = new ItemFood(23, 5).setIconIndex(160),
	porkRaw        = new ItemFood(24, 3).setIconIndex(163),
	porkCooked     = new ItemFood(25, 8).setIconIndex(164),
	cd             = new Item(26).setIconIndex(129).setMaxStackSize(1);
	
	public final int shiftedIndex;
	protected int maxStackSize = 64;
	protected int maxDamage = 32;
	protected int iconIndex;

	protected Item(int index) {
		
		this.shiftedIndex = 256 + index;
		
		if(itemsList[256 + index] != null) {
			System.out.println("CONFLICT @ " + index);
		}

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

	public float getStrVsBlock(ItemStack var1, Block var2) {
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

	public boolean canHarvestBlock(Block var1) {
		return false;
	}
}
