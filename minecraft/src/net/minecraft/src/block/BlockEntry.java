package net.minecraft.src.block;

import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemBlock;
import net.minecraft.src.item.ToolLevel;
import net.minecraft.src.item.ToolType;

public enum BlockEntry {

	stone       ((new BlockBreakToCobble(1, 1)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep)),
	cobblestone (new BlockToolSensitive(4, 16, Material.rock, ToolLevel.BRONZE, ToolType.PICKAXE).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep)),
	bedrock     ((new Block(7, 17, Material.rock)).setHardness(-1.0F).setResistance(6000000.0F).setStepSound(Block.soundStoneFootstep)),
	grass       ((BlockGrass)(new BlockGrass(2)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep)),
	dirt        ((new BlockDirt(3, 2)).setHardness(0.5F).setStepSound(Block.soundGravelFootstep)),
	planks      ((new Block(5, 4, Material.wood)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep)),
	sapling     ((new BlockSapling(6, 15)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep)),
	sand        ((new BlockSand(12, 18)).setHardness(0.5F).setStepSound(Block.soundSandFootstep)),
	gravel      ((new Block(13, 19, Material.sand)).setHardness(0.6F).setStepSound(Block.soundGravelFootstep)),
	
	oreCoal ((new BlockOre(16, 32)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep)),
	oreIron ((new BlockOre(15, 33)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep)),
	oreMithril ((new BlockOre(56, 34)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep)),
	
	wood ((new BlockLog(17)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep)),
	leaves ((BlockLeaves)(new BlockLeaves(18, 52)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep)),
	sponge ((new Block(19, 48, Material.sponge)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep)),
	glass ((new BlockGlass(20, 49, Material.glass, false)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep)),
	cloth ((new Block(35, 64, Material.cloth)).setHardness(0.8F).setStepSound(Block.soundClothFootstep)),
	plantYellow ((BlockFlower)(new BlockFlower(37, 13)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep)),
	plantRed ((BlockFlower)(new BlockFlower(38, 12)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep)),
	mushroomBrown ((BlockFlower)(new BlockMushroom(39, 29)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setLightValue(2.0F / 16.0F)),
	mushroomRed ((BlockFlower)(new BlockMushroom(40, 28)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep)),
	
	blockBronze ((new BlockOreBlock(82, 22)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep)),
	blockIron ((new BlockOreBlock(42, 38)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep)),
	blockMithril ((new BlockOreBlock(57, 54)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep)),
	
	stairDouble ((new BlockStep(43, true)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep)),
	stairSingle ((new BlockStep(44, false)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep)),
	brick ((new Block(45, 7, Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep)),
	tnt ((new BlockTNT(46, 8)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep)),
	bookshelf ((new BlockBookshelf(47, 35)).setHardness(1.5F).setStepSound(Block.soundWoodFootstep)),
	cobblestoneMossy ((new Block(48, 36, Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep)),
	obsidian ((new Block(49, 37, Material.rock)).setHardness(10.0F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep)),
	torch ((new BlockTorch(50, 80)).setHardness(0.0F).setLightValue(15.0F / 16.0F).setStepSound(Block.soundWoodFootstep)),
	fire ((BlockFire) new BlockFire(51, 31).setHardness(0.0F).setLightValue(1.0F).setStepSound(Block.soundWoodFootstep)),
	mobSpawner ((new BlockMobSpawner(52, 65)).setHardness(5.0F).setStepSound(Block.soundMetalFootstep)),
	stairCompactWood (new BlockStairs(53, planks.block)),
	chest ((new BlockChest(54)).setHardness(2.5F).setStepSound(Block.soundWoodFootstep)),
	
	workbench (new BlockWorkbench(58).setHardness(2.5F).setStepSound(Block.soundWoodFootstep)),
	ladder ((new BlockLadder(65, 83)).setHardness(0.4F).setStepSound(Block.soundWoodFootstep)),
	stairCompactStone (new BlockStairs(67, cobblestone.block)),
	
	snow ((new BlockSnow(78, 66)).setHardness(0.1F).setStepSound(Block.soundClothFootstep)),
	ice ((new Block(79, 67, Material.ice)).setHardness(0.5F).setLightOpacity(3).setStepSound(Block.soundGlassFootstep)),
	blockSnow ((new BlockSnowBlock(80, 66)).setHardness(0.2F).setStepSound(Block.soundClothFootstep)),
	
	jukebox (new BlockJukebox(81).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep));
	
	public final Block block;
	public final Item item;
	
	BlockEntry(Block block) {
		
		this.block = block;
		this.item = new ItemBlock(block);
		
		Item.itemsList[this.block.blockID] = this.item;
	}
}
