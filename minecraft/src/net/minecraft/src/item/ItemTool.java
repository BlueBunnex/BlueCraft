package net.minecraft.src.item;

import net.minecraft.src.block.Block;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityLiving;

// TODO rework efficiency (speed and against/'tool type')
public class ItemTool extends Item {
	
	private Block[] blocksEffectiveAgainst;
	private float efficiencyOnProperMaterial = 4.0F;
	private int damageVsEntity;
	protected int toolMaterial;

	public ItemTool(int index, ToolLevel level, ToolType type) {
		super(index);
		this.toolMaterial = level.ordinal();
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

	public float getStrVsBlock(ItemStack var1, Block var2) {
		
		for (int var3 = 0; var3 < this.blocksEffectiveAgainst.length; ++var3) {
			
			if (this.blocksEffectiveAgainst[var3] == var2) {
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
