package net.minecraft.src.item;

import net.minecraft.src.block.AllBlocks;

public enum ToolType {
	
	SWORD(new AllBlocks[] {}),
	PICKAXE(new AllBlocks[] { AllBlocks.cobblestone, AllBlocks.stairDouble, AllBlocks.stairSingle, AllBlocks.stone, AllBlocks.cobblestoneMossy, AllBlocks.oreIron, AllBlocks.blockIron, AllBlocks.oreCoal, AllBlocks.oreMithril, AllBlocks.blockMithril, AllBlocks.ice }),
	AXE(new AllBlocks[] { AllBlocks.planks, AllBlocks.bookshelf, AllBlocks.wood, AllBlocks.chest }),
	SHOVEL(new AllBlocks[] { AllBlocks.grass, AllBlocks.dirt, AllBlocks.sand, AllBlocks.gravel, AllBlocks.snow });
	
	public final AllBlocks[] blocksEffectiveAgainst;
	
	ToolType(AllBlocks[] blocksEffectiveAgainst) {
		this.blocksEffectiveAgainst = blocksEffectiveAgainst;
	}

}
