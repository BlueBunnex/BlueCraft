package net.minecraft.src.item;

import net.minecraft.src.block.Block;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityLiving;

public class ItemTool extends Item {
	
	private Block[] blocksEffectiveAgainst;
	private float efficiencyOnProperMaterial = 4.0F;
	private int damageVsEntity;

	public ItemTool(int index, ToolLevel level, ToolType type) {
		super(index);
		
		this.blocksEffectiveAgainst = type.blocksEffectiveAgainst;
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
		
		for (int i = 0; i < this.blocksEffectiveAgainst.length; i++) {
			
			if (this.blocksEffectiveAgainst[i] == block) {
				return this.efficiencyOnProperMaterial;
			}
		}

		return 1.0F;
	}

	public void hitEntity(ItemStack var1, EntityLiving var2) {
		var1.damageItem(1);
	}

	public void onBlockDestroyed(ItemStack var1, int var2, int var3, int var4, int var5) {
		var1.damageItem(1);
	}

	public int getDamageVsEntity(Entity var1) {
		return this.damageVsEntity;
	}
}
