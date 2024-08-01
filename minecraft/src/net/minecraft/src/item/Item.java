package net.minecraft.src.item;

import java.util.Random;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraft.src.block.Block;

public class Item {
	
	protected static Random rand = new Random();
	public static Item[] itemsList = new Item[1024];
	
	public static Item
	swordWood      = new ItemSword(12, 0).setIconIndex(0),
	shovelWood     = new ItemSpade(13, 0).setIconIndex(16),
	pickaxeWood    = new ItemPickaxe(14, 0).setIconIndex(32),
	axeWood        = new ItemAxe(15, 0).setIconIndex(48),
	hoeWood        = new ItemHoe(34, 0).setIconIndex(64),
	
	swordStone     = new ItemSword(16, 1).setIconIndex(1),
	shovelStone    = new ItemSpade(17, 1).setIconIndex(17),
	pickaxeStone   = new ItemPickaxe(18, 1).setIconIndex(33),
	axeStone       = new ItemAxe(19, 1).setIconIndex(49),
	hoeStone       = new ItemHoe(35, 1).setIconIndex(65),
	
	swordIron      = new ItemSword(11, 2).setIconIndex(2),
	shovelIron     = new ItemSpade(0, 2).setIconIndex(18),
	pickaxeIron    = new ItemPickaxe(1, 2).setIconIndex(34),
	axeIron        = new ItemAxe(2, 2).setIconIndex(50),
	hoeIron        = new ItemHoe(36, 2).setIconIndex(66),
	
	swordMithril   = new ItemSword(20, 3).setIconIndex(3),
	shovelMithril  = new ItemSpade(21, 3).setIconIndex(19),
	pickaxeMithril = new ItemPickaxe(22, 3).setIconIndex(35),
	axeMithril     = new ItemAxe(23, 3).setIconIndex(51),
	hoeMithril     = new ItemHoe(37, 3).setIconIndex(67),
	
	helmetIron     = new ItemArmor(50, 2, 2, 0).setIconIndex(2),
	plateIron      = new ItemArmor(51, 2, 2, 1).setIconIndex(18),
	legsIron       = new ItemArmor(52, 2, 2, 2).setIconIndex(34),
	bootsIron      = new ItemArmor(53, 2, 2, 3).setIconIndex(50),
	helmetMithril  = new ItemArmor(54, 3, 3, 0).setIconIndex(3),
	plateMithril   = new ItemArmor(55, 3, 3, 1).setIconIndex(19),
	legsMithril    = new ItemArmor(56, 3, 3, 2).setIconIndex(35),
	bootsMithril   = new ItemArmor(57, 3, 3, 3).setIconIndex(51),
	
	ingotIron      = new Item(9).setIconIndex(23),
	ingotMithril   = new Item(8).setIconIndex(39),
	
	flintAndSteel  = new ItemFlintAndSteel(3).setIconIndex(5),
	appleRed       = new ItemFood(4, 4).setIconIndex(10),
	bow            = new ItemBow(5).setIconIndex(21),
	arrow          = new Item(6).setIconIndex(37),
	coal           = new Item(7).setIconIndex(7),
	stick          = new Item(24).setIconIndex(53),
	bowlEmpty      = new Item(25).setIconIndex(71),
	bowlSoup       = new ItemSoup(26, 10).setIconIndex(72),
	silk           = new Item(31).setIconIndex(8),
	feather        = new Item(32).setIconIndex(24),
	gunpowder      = new Item(33).setIconIndex(40),
	seeds          = new ItemSeeds(39, Block.crops.blockID).setIconIndex(9),
	wheat          = new Item(40).setIconIndex(25),
	bread          = new ItemFood(41, 5).setIconIndex(41),
	flint          = new Item(62).setIconIndex(6),
	porkRaw        = new ItemFood(63, 3).setIconIndex(87),
	porkCooked     = new ItemFood(64, 8).setIconIndex(88),
	painting       = new ItemPainting(65).setIconIndex(26),
	appleGold      = new ItemFood(66, 42).setIconIndex(11),
	sign           = new ItemSign(67).setIconIndex(42),
	doorWood       = new ItemDoor(68).setIconIndex(43),
	bucketEmpty    = new ItemBucket(69, 0).setIconIndex(74),
	bucketWater    = new ItemBucket(70, Block.waterMoving.blockID).setIconIndex(75),
	bucketLava     = new ItemBucket(71, Block.lavaMoving.blockID).setIconIndex(76),
	minecartEmpty  = new ItemMinecart(72).setIconIndex(135),
	
	snowball       = new ItemSnowball(76).setIconIndex(14),
	cd             = new ItemSnowball(77).setIconIndex(90);
	
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

	public Item setIconIndex(int iconIndex) {
		this.iconIndex = iconIndex;
		return this;
	}

	public int getIconIndex(ItemStack var1) {
		return this.iconIndex;
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
