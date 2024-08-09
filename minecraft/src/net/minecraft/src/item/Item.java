package net.minecraft.src.item;

import java.util.Random;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.block.Block;
import net.minecraft.src.world.World;

public class Item {
	
	protected static Random rand = new Random();
	public static Item[] itemsList = new Item[1024];
	
	public static Item
	pointyClub     = new ItemPickaxe(0, 1).setIconIndex(33),
	
	swordIron      = new ItemSword(1, 2).setIconIndex(2),
	shovelIron     = new ItemSpade(2, 2).setIconIndex(18),
	pickaxeIron    = new ItemPickaxe(3, 2).setIconIndex(34),
	axeIron        = new ItemAxe(4, 2).setIconIndex(50),
	hoeIron        = new ItemHoe(5, 2).setIconIndex(66),
	
	swordMithril   = new ItemSword(6, 3).setIconIndex(3),
	shovelMithril  = new ItemSpade(7, 3).setIconIndex(19),
	pickaxeMithril = new ItemPickaxe(8, 3).setIconIndex(35),
	axeMithril     = new ItemAxe(9, 3).setIconIndex(51),
	hoeMithril     = new ItemHoe(10, 3).setIconIndex(67),
	
	helmetIron     = new ItemArmor(11, 2, 2, 0).setIconIndex(80),
	plateIron      = new ItemArmor(12, 2, 2, 1).setIconIndex(81),
	legsIron       = new ItemArmor(13, 2, 2, 2).setIconIndex(82),
	bootsIron      = new ItemArmor(14, 2, 2, 3).setIconIndex(83),
	
	helmetMithril  = new ItemArmor(15, 3, 3, 0).setIconIndex(96),
	plateMithril   = new ItemArmor(16, 3, 3, 1).setIconIndex(97),
	legsMithril    = new ItemArmor(17, 3, 3, 2).setIconIndex(98),
	bootsMithril   = new ItemArmor(18, 3, 3, 3).setIconIndex(99),
	
	ingotIron      = new Item(19).setIconIndex(23),
	ingotMithril   = new Item(20).setIconIndex(39),
	
	flintAndSteel  = new ItemFlintAndSteel(21).setIconIndex(5),
	appleRed       = new ItemFood(22, 4).setIconIndex(10),
	bow            = new ItemBow(23).setIconIndex(21),
	arrow          = new Item(24).setIconIndex(37),
	coal           = new Item(25).setIconIndex(7),
	stick          = new Item(26).setIconIndex(53),
	bowlEmpty      = new Item(27).setIconIndex(71),
	bowlSoup       = new ItemSoup(28, 10).setIconIndex(72),
	silk           = new Item(29).setIconIndex(8),
	feather        = new Item(30).setIconIndex(24),
	gunpowder      = new Item(31).setIconIndex(40),
	seeds          = new ItemSeeds(32, Block.crops.blockID).setIconIndex(9),
	wheat          = new Item(33).setIconIndex(25),
	bread          = new ItemFood(34, 5).setIconIndex(41),
	flint          = new Item(35).setIconIndex(6),
	porkRaw        = new ItemFood(36, 3).setIconIndex(87),
	porkCooked     = new ItemFood(37, 8).setIconIndex(88),
	painting       = new ItemPainting(38).setIconIndex(26),
	appleGold      = new ItemFood(39, 20).setIconIndex(11),
	sign           = new ItemSign(40).setIconIndex(42),
	doorWood       = new ItemDoor(41).setIconIndex(43),
	bucketEmpty    = new ItemBucket(42, 0).setIconIndex(74),
	bucketWater    = new ItemBucket(43, Block.waterMoving.blockID).setIconIndex(75),
	bucketLava     = new ItemBucket(44, Block.lavaMoving.blockID).setIconIndex(76),
	minecartEmpty  = new ItemMinecart(45).setIconIndex(135),
	snowball       = new ItemSnowball(46).setIconIndex(14),
	cd             = new Item(47).setIconIndex(90).setMaxStackSize(1),
	
	rock           = new Item(48).setIconIndex(103),
	oreIron        = new Item(49).setIconIndex(104),
	oreMithril     = new Item(50).setIconIndex(105);
	
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
