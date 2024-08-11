package net.minecraft.src.item;

import net.minecraft.src.block.Block;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityLiving;

public class ItemTool extends Item {
	
	private float efficiencyOnProperMaterial = 4.0F;
	private int damageVsEntity;
	
	public final ToolLevel level;
	public final ToolType type;

	public ItemTool(int index, ToolLevel level, ToolType type) {
		super(index);
		
		this.level = level;
		this.type = type;
		
		this.maxStackSize = 1;
		
		// calculate durability
		this.maxDamage = 32 << level.ordinal();
		
		if(level.ordinal() == 3)
			this.maxDamage *= 4;

		// calculate mining efficiency
		this.efficiencyOnProperMaterial = (float) ((level.ordinal() + 1) * 2);
		
		// calculate damage
		if (type == ToolType.SWORD) {
			
			this.damageVsEntity = level.ordinal() * 2 + 4;
		} else {
			this.damageVsEntity = level.ordinal();
		}
	}

	public float getStrVsBlock(ItemStack var1, Block block) {
		
		for (int i = 0; i < this.type.blocksEffectiveAgainst.length; i++) {
			
			if (this.type.blocksEffectiveAgainst[i] == block)
				return this.efficiencyOnProperMaterial;
		}

		return 1.0F;
	}

	public void hitEntity(ItemStack itemStack, EntityLiving hitEntity) {
		itemStack.damageItem(1);
	}

	public void onBlockDestroyed(ItemStack itemStack, int var2, int var3, int var4, int var5) {
		itemStack.damageItem(1);
	}

	public int getDamageVsEntity(Entity var1) {
		return this.damageVsEntity;
	}
}
