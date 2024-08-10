package net.minecraft.src.entity.render;

import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntitySheep;
import net.minecraft.src.entity.model.ModelBase;

public class RenderSheep extends RenderLiving {
	public RenderSheep(ModelBase var1, ModelBase var2, float var3) {
		super(var1, var3);
		this.setRenderPassModel(var2);
	}

	protected boolean renderFur(EntitySheep var1, int var2) {
		this.loadTexture("/mob/sheep_fur.png");
		return var2 == 0 && !var1.sheared;
	}

	protected boolean shouldRenderPass(EntityLiving var1, int var2) {
		return this.renderFur((EntitySheep)var1, var2);
	}
}
