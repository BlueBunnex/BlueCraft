package net.minecraft.src.item;

import net.minecraft.src.block.Block;

public enum ToolType {
	
	SWORD(new Block[] {}),
	PICKAXE(new Block[] { Block.cobblestone, Block.stairDouble, Block.stairSingle, Block.stone, Block.cobblestoneMossy, Block.oreIron, Block.blockIron, Block.oreCoal, Block.oreMithril, Block.blockMithril, Block.ice }),
	AXE(new Block[] { Block.planks, Block.bookshelf, Block.wood, Block.chest }),
	SHOVEL(new Block[] { Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow });
	
	public final Block[] blocksEffectiveAgainst;
	
	ToolType(Block[] blocksEffectiveAgainst) {
		this.blocksEffectiveAgainst = blocksEffectiveAgainst;
	}

}
