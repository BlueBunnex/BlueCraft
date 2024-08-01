package net.minecraft.src;

import net.minecraft.src.block.Block;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;

public class RecipesIngots {
	private Object[][] recipeItems = new Object[][] {
		{ Block.blockIron, Item.ingotIron },
		{ Block.blockMithril, Item.ingotMithril }
	};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems.length; ++var2) {
			Block var3 = (Block)this.recipeItems[var2][0];
			Item var4 = (Item)this.recipeItems[var2][1];
			var1.addRecipe(new ItemStack(var3), new Object[]{"###", "###", "###", Character.valueOf('#'), var4});
			var1.addRecipe(new ItemStack(var4, 9), new Object[]{"#", Character.valueOf('#'), var3});
		}

	}
}
