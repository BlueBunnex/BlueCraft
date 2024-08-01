package net.minecraft.src;

import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;

public class RecipesArmor {
	private String[][] recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
	private Object[][] recipeItems = new Object[][] {
		{ Item.ingotIron, Item.diamond },
		{ Item.helmetIron, Item.helmetDiamond },
		{ Item.plateIron, Item.plateDiamond },
		{ Item.legsIron, Item.legsDiamond },
		{ Item.bootsIron, Item.bootsDiamond }
	};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('X'), var3});
			}
		}

	}
}
