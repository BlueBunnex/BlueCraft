package net.minecraft.src.block;

import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.item.ItemTool;
import net.minecraft.src.item.ToolLevel;
import net.minecraft.src.item.ToolType;

public class BlockToolSensitive extends Block {
	
	private ToolLevel minToolLevel;
	private ToolType requiredToolType;
	
	protected BlockToolSensitive(int blockID, int indexInTexture, Material material, ToolLevel minToolLevel, ToolType requiredToolType) {
		super(blockID, indexInTexture, material);
		
		this.minToolLevel = minToolLevel;
		this.requiredToolType = requiredToolType;
	}

	public boolean canHarvestBlock(EntityPlayer player) {
		
		ItemStack itemStack = player.inventory.getStackInSlot(player.inventory.currentItem);
		
		if (itemStack == null || !(itemStack.getItem() instanceof ItemTool))
			return false;
		
		ItemTool tool = (ItemTool) itemStack.getItem();
		
		return tool.type == requiredToolType && tool.level.ordinal() >= minToolLevel.ordinal();
	}

}
