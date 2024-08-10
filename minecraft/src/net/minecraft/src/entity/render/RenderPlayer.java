package net.minecraft.src.entity.render;

import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.entity.model.ModelBiped;

public class RenderPlayer extends RenderLiving {
	
	private ModelBiped modelBipedMain = (ModelBiped)this.mainModel;

	public RenderPlayer() {
		super(new ModelBiped(0.0F), 0.5F);
	}

	public void renderPlayer(EntityPlayer var1, double var2, double var4, double var6, float var8, float var9) {
		super.doRenderLiving(var1, var2, var4 - (double)var1.yOffset, var6, var8, var9);
	}

	public void drawFirstPersonHand() {
		this.modelBipedMain.bipedRightArm.render(1.0F / 16.0F);
	}

	public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
		this.renderPlayer((EntityPlayer)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		this.renderPlayer((EntityPlayer)var1, var2, var4, var6, var8, var9);
	}
}
