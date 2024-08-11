package net.minecraft.src.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.src.ChatLine;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.Material;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.Tessellator;
import net.minecraft.src.entity.render.RenderItem;
import net.minecraft.src.inventory.InventoryPlayer;
import net.minecraft.src.item.ItemStack;

public class GuiIngame extends Gui {
	
	private static RenderItem itemRenderer = new RenderItem();
	private List chatMessageList = new ArrayList();
	private Random rand = new Random();
	private Minecraft mc;
	public String testMessage = null;
	private int updateCounter = 0;
	public float damageGuiPartialTime;
	float prevVignetteBrightness = 1.0F;

	public GuiIngame(Minecraft var1) {
		this.mc = var1;
	}

	public void renderGameOverlay(float var1, boolean var2, int var3, int var4) {
		
		ScaledResolution res = new ScaledResolution(this.mc.displayWidth, this.mc.displayHeight);
		
		int width  = res.getScaledWidth();
		int height = res.getScaledHeight();
		
		FontRenderer fontRenderer = this.mc.fontRenderer;
		
		this.mc.entityRenderer.setupOverlayRendering();
		GL11.glEnable(GL11.GL_BLEND);
		if(this.mc.options.fancyGraphics) {
			this.renderVignette(this.mc.thePlayer.getBrightness(var1), width, height);
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/gui.png"));
		
		InventoryPlayer var9 = this.mc.thePlayer.inventory;
		
		this.zLevel = -90.0F;
		this.drawTexturedModalRect(width / 2 - 91, height - 22, 0, 0, 182, 22);
		this.drawTexturedModalRect(width / 2 - 91 - 1 + var9.currentItem * 20, height - 22 - 1, 0, 22, 24, 22);
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/icons.png"));
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR);
		this.drawTexturedModalRect(width / 2 - 7, height / 2 - 7, 0, 0, 16, 16);
		GL11.glDisable(GL11.GL_BLEND);
		
		boolean var10 = this.mc.thePlayer.heartsLife / 3 % 2 == 1;
		if(this.mc.thePlayer.heartsLife < 10) {
			var10 = false;
		}
		
		if(this.mc.playerController.shouldDrawHUD()) {
			
			int health = this.mc.thePlayer.health;
			int armor = this.mc.thePlayer.getPlayerArmorValue();
			
			int leftX = width / 2 - 91;
			int y = height - 32;
			
			// draw armor
			if(armor > 0) {
				this.drawTexturedModalRect(leftX + 24, y, 34, 9, 9, 9);
			}

			// draw health
			byte var25 = 0;
			if(var10) {
				var25 = 1;
			}

			this.drawTexturedModalRect(leftX, y, 16 + var25 * 9, 0, 9, 9);
			
			if (health <= 10) {
				this.drawTexturedModalRect(leftX, y, 61, 0, 9, 9);
			} else {
				this.drawTexturedModalRect(leftX, y, 52, 0, 9, 9);
			}

			// bubbles
			if(this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
				int var14 = (int) Math.ceil((double)(this.mc.thePlayer.air - 2) * 10.0D / 300.0D);
				int var15 = (int) Math.ceil((double)this.mc.thePlayer.air * 10.0D / 300.0D) - var14;

				for(int i = 0; i < var14 + var15; i++) {
					if(i < var14) {
						this.drawTexturedModalRect(width / 2 - 91 + i * 8, height - 32 - 9, 16, 18, 9, 9);
					} else {
						this.drawTexturedModalRect(width / 2 - 91 + i * 8, height - 32 - 9, 25, 18, 9, 9);
					}
				}
			}
			
			// draw stat text (changes texture so have to do last)
			fontRenderer.drawBorderedString("" + health, leftX + 11, y + 1, -65536, -16777216);
			
			if (armor > 0)
				fontRenderer.drawBorderedString("" + armor, leftX + 35, y + 1, -2236963, -16777216);
			
			// drawing text also messes with color, so revert before drawing hotbar
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glPushMatrix();
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();

		for(int i = 0; i < 9; i++) {
			int var14 = width / 2 - 90 + i * 20 + 2;
			int var15 = height - 16 - 3;
			this.renderInventorySlot(i, var14, var15, var1);
		}

		// draw debug menu
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		
		if (this.mc.options.showDebugMenu) {
			fontRenderer.drawStringWithShadow("Minecraft Alpha v1.0.5 (" + this.mc.debug + ")", 2, 2, 16777215);
			fontRenderer.drawStringWithShadow(this.mc.debugInfoRenders(), 2, 12, 16777215);
			fontRenderer.drawStringWithShadow(this.mc.getEntityDebug(), 2, 22, 16777215);
			fontRenderer.drawStringWithShadow(this.mc.debugInfoEntities(), 2, 32, 16777215);
			long var22 = Runtime.getRuntime().maxMemory();
			long var26 = Runtime.getRuntime().totalMemory();
			long var27 = Runtime.getRuntime().freeMemory();
			long var19 = var26 - var27;
			String var21 = "Used memory: " + var19 * 100L / var22 + "% (" + var19 / 1024L / 1024L + "MB) of " + var22 / 1024L / 1024L + "MB";
			this.drawString(fontRenderer, var21, width - fontRenderer.getStringWidth(var21) - 2, 2, 14737632);
			var21 = "Allocated memory: " + var26 * 100L / var22 + "% (" + var26 / 1024L / 1024L + "MB)";
			this.drawString(fontRenderer, var21, width - fontRenderer.getStringWidth(var21) - 2, 12, 14737632);
		} else {
			fontRenderer.drawStringWithShadow("Minecraft Alpha v1.0.5", 2, 2, 16777215);
		}

		// draw chat
		byte var23 = 10;
		boolean var24 = false;

		for(int i = 0; i < this.chatMessageList.size() && i < var23; i++) {
			if(((ChatLine)this.chatMessageList.get(i)).updateCounter < 200 || var24) {
				fontRenderer.drawStringWithShadow(((ChatLine)this.chatMessageList.get(i)).message, 2, height - 8 - i * 9 - 20, 16777215);
			}
		}

	}

	private void renderVignette(float var1, int var2, int var3) {
		var1 = 1.0F - var1;
		if(var1 < 0.0F) {
			var1 = 0.0F;
		}

		if(var1 > 1.0F) {
			var1 = 1.0F;
		}

		this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(var1 - this.prevVignetteBrightness) * 0.01D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
		GL11.glColor4f(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/misc/vignette.png"));
		Tessellator var4 = Tessellator.instance;
		var4.startDrawingQuads();
		var4.addVertexWithUV(0.0D, (double)var3, -90.0D, 0.0D, 1.0D);
		var4.addVertexWithUV((double)var2, (double)var3, -90.0D, 1.0D, 1.0D);
		var4.addVertexWithUV((double)var2, 0.0D, -90.0D, 1.0D, 0.0D);
		var4.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
		var4.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	private void renderInventorySlot(int var1, int var2, int var3, float var4) {
		ItemStack var5 = this.mc.thePlayer.inventory.mainInventory[var1];
		if(var5 != null) {
			float var6 = (float)var5.animationsToGo - var4;
			if(var6 > 0.0F) {
				GL11.glPushMatrix();
				float var7 = 1.0F + var6 / 5.0F;
				GL11.glTranslatef((float)(var2 + 8), (float)(var3 + 12), 0.0F);
				GL11.glScalef(1.0F / var7, (var7 + 1.0F) / 2.0F, 1.0F);
				GL11.glTranslatef((float)(-(var2 + 8)), (float)(-(var3 + 12)), 0.0F);
			}

			itemRenderer.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, var2, var3);
			if(var6 > 0.0F) {
				GL11.glPopMatrix();
			}

			itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, var2, var3);
		}
	}

	public void updateTick() {
		++this.updateCounter;

		for(int var1 = 0; var1 < this.chatMessageList.size(); ++var1) {
			++((ChatLine)this.chatMessageList.get(var1)).updateCounter;
		}

	}
}
