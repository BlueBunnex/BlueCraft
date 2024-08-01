package net.minecraft.src;

import net.minecraft.src.block.Block;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;

public class RecipesTools {
	private String[][] recipePatterns = new String[][]{{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}};
	private Object[][] recipeItems = new Object[][] {
		{Block.planks, Block.cobblestone, Item.ingotIron, Item.ingotMithril},
		{Item.pickaxeWood, Item.pickaxeStone, Item.pickaxeIron, Item.pickaxeMithril},
		{Item.shovelWood, Item.shovelStone, Item.shovelIron, Item.shovelMithril},
		{Item.axeWood, Item.axeStone, Item.axeIron, Item.axeMithril},
		{Item.hoeWood, Item.hoeStone, Item.hoeIron, Item.hoeMithril}
	};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('#'), Item.stick, Character.valueOf('X'), var3});
			}
		}

	}
}
