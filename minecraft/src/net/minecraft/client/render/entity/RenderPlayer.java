package net.minecraft.client.render.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.game.entity.Entity;
import net.minecraft.game.entity.EntityLiving;
import net.minecraft.game.entity.player.EntityPlayer;
import net.minecraft.game.entity.player.InventoryPlayer;
import net.minecraft.game.item.Item;
import net.minecraft.game.item.ItemArmor;
import net.minecraft.game.item.ItemStack;

public final class RenderPlayer extends RenderLiving {
	
	private ModelBiped modelBiped;

	public RenderPlayer() {
		super(new ModelBiped(0.0F), 0.5F);
		
		modelBiped = (ModelBiped) this.mainModel;
	}

	private void renderPlayer(EntityPlayer var1, float var2, float var3, float var4, float var5, float var6) {
		super.a(var1, var2, var3 - var1.yOffset, var4, var5, var6);
	}

	public final void drawFirstPersonHand() {
		this.modelBiped.bipedRightArm.render(1.0F);
	}

	public final void a(EntityLiving var1, float var2, float var3, float var4, float var5, float var6) {
		this.renderPlayer((EntityPlayer)var1, var2, var3, var4, var5, var6);
	}

	public final void doRender(Entity var1, float var2, float var3, float var4, float var5, float var6) {
		this.renderPlayer((EntityPlayer)var1, var2, var3, var4, var5, var6);
	}
}
