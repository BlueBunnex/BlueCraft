package net.minecraft.src.item;

public enum ItemEntry {
	
	// ingots
	ingotBronze    (new Item(0).setIconIndex(0)),
	ingotIron      (new Item(1).setIconIndex(16)),
	ingotMithril   (new Item(2).setIconIndex(32)),
	
	// tools
	swordBronze    (new ItemTool(3,  ToolLevel.BRONZE, ToolType.SWORD)   .setIconIndex(1)),
	pickaxeBronze  (new ItemTool(4,  ToolLevel.BRONZE, ToolType.PICKAXE) .setIconIndex(2)),
	axeBronze      (new ItemTool(5,  ToolLevel.BRONZE, ToolType.AXE)     .setIconIndex(3)),
	shovelBronze   (new ItemTool(6,  ToolLevel.BRONZE, ToolType.SHOVEL)  .setIconIndex(4)),
	
	swordIron      (new ItemTool(7,  ToolLevel.IRON, ToolType.SWORD)     .setIconIndex(17)),
	pickaxeIron    (new ItemTool(8,  ToolLevel.IRON, ToolType.PICKAXE)   .setIconIndex(18)),
	axeIron        (new ItemTool(9,  ToolLevel.IRON, ToolType.AXE)       .setIconIndex(19)),
	shovelIron     (new ItemTool(10, ToolLevel.IRON, ToolType.SHOVEL)    .setIconIndex(20)),
	
	swordMithril   (new ItemTool(11, ToolLevel.MITHRIL, ToolType.SWORD)  .setIconIndex(33)),
	pickaxeMithril (new ItemTool(12, ToolLevel.MITHRIL, ToolType.PICKAXE).setIconIndex(34)),
	axeMithril     (new ItemTool(13, ToolLevel.MITHRIL, ToolType.AXE)    .setIconIndex(35)),
	shovelMithril  (new ItemTool(14, ToolLevel.MITHRIL, ToolType.SHOVEL) .setIconIndex(36)),
	
	// armor
	helmetBronze   (new ItemArmor(15, 1, 1, 0).setIconIndex(5)),
	plateBronze    (new ItemArmor(16, 1, 1, 1).setIconIndex(6)),
	legsBronze     (new ItemArmor(17, 1, 1, 2).setIconIndex(7)),
	bootsBronze    (new ItemArmor(18, 1, 1, 3).setIconIndex(8)),
	
	helmetIron     (new ItemArmor(19, 2, 2, 0).setIconIndex(21)),
	plateIron      (new ItemArmor(20, 2, 2, 1).setIconIndex(22)),
	legsIron       (new ItemArmor(21, 2, 2, 2).setIconIndex(23)),
	bootsIron      (new ItemArmor(22, 2, 2, 3).setIconIndex(24)),
	
	helmetMithril  (new ItemArmor(23, 3, 3, 0).setIconIndex(37)),
	plateMithril   (new ItemArmor(24, 3, 3, 1).setIconIndex(38)),
	legsMithril    (new ItemArmor(25, 3, 3, 2).setIconIndex(39)),
	bootsMithril   (new ItemArmor(26, 3, 3, 3).setIconIndex(40)),
	
	flintAndSteel  (new ItemFlintAndSteel(27).setIconIndex(128)),
	appleRed       (new ItemFood(28, 4).setIconIndex(161)),
	appleGold      (new ItemFood(29, 20).setIconIndex(162)),
	coal           (new Item(30).setIconIndex(144)),
	ruby           (new Item(31).setIconIndex(145)),
	stick          (new Item(32).setIconIndex(130)),
	bread          (new ItemFood(33, 5).setIconIndex(160)),
	porkRaw        (new ItemFood(34, 3).setIconIndex(163)),
	porkCooked     (new ItemFood(35, 8).setIconIndex(164)),
	cd             (new Item(36).setIconIndex(129).setMaxStackSize(1));

	public final Item item;
	
	ItemEntry(Item item) {
		this.item = item;
	}
	
	static {
		for (ItemEntry a : ItemEntry.values())
			Item.itemsList[a.item.shiftedIndex] = a.item;
	}
	
}
