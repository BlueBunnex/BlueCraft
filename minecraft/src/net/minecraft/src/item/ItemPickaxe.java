package net.minecraft.src.item;

import net.minecraft.src.Material;
import net.minecraft.src.block.Block;

public class ItemPickaxe extends ItemTool {
	
	private static Block[] blocksEffectiveAgainst = new Block[] { Block.cobblestone, Block.stairDouble, Block.stairSingle, Block.stone, Block.cobblestoneMossy, Block.oreIron, Block.blockIron, Block.oreCoal, Block.oreMithril, Block.blockMithril, Block.ice };
	private int harvestLevel;

	public ItemPickaxe(int var1, int var2) {
		super(var1, 2, var2, blocksEffectiveAgainst);
		this.harvestLevel = var2;
	}

	public boolean canHarvestBlock(Block block) {
		
		if (block == Block.obsidian)
			return this.harvestLevel == 3;
			
		if (block == Block.blockMithril || block == Block.oreMithril)
			return this.harvestLevel >= 2;
			
		if (block == Block.blockIron || block == Block.oreIron)
			return this.harvestLevel >= 1;
		
		return block.material == Material.rock;
	}
}
