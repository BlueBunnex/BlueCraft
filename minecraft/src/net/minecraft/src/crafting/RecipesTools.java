package net.minecraft.src.crafting;

import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;

public class RecipesTools {
	
	private String[][] recipePatterns = new String[][] {
		{"XXX", " # ", " # "},
		{"X", "#", "#"},
		{"XX", "X#", " #"},
		{"XX", " #", " #"},
		{"X", "X", "#"}
	};
	
	private Object[][] recipeItems = new Object[][] {
		{Item.ingotIron, Item.ingotMithril},
		{Item.pickaxeIron, Item.pickaxeMithril},
		{Item.shovelIron, Item.shovelMithril},
		{Item.axeIron, Item.axeMithril},
		{Item.hoeIron, Item.hoeMithril},
		{Item.swordIron, Item.swordMithril}
	};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('#'), Item.stick, Character.valueOf('X'), var3});
			}
		}

		var1.addRecipe(new ItemStack(Item.bow, 1), new Object[]{" #X", "# X", " #X", Character.valueOf('X'), Item.silk, Character.valueOf('#'), Item.stick});
		var1.addRecipe(new ItemStack(Item.arrow, 4), new Object[]{"X", "#", "Y", Character.valueOf('Y'), Item.feather, Character.valueOf('X'), Item.ingotIron, Character.valueOf('#'), Item.stick});
		
		var1.addRecipe(new ItemStack(Item.pointyClub, 1), new Object[]{"#X", " X", Character.valueOf('X'), Item.stick, Character.valueOf('#'), Item.rock});
	}
}
