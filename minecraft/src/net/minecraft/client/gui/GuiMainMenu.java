package net.minecraft.client.gui;

import net.minecraft.client.render.Tessellator;
import org.lwjgl.opengl.GL11;
import util.MathHelper;

public final class GuiMainMenu extends GuiScreen {
	
	private String[] splashes = new String[]{ "May contain Blue 40!" };
	private String currentSplash = this.splashes[(int)(Math.random() * (double) this.splashes.length)];

	protected final void keyTyped(char var1, int var2) {
	}

	public final void initGui() {
		this.controlList.clear();
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 48, "Generate new level..."));
		this.controlList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 72, "Load level.."));
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, "Options..."));
	}

	protected final void actionPerformed(GuiButton var1) {
		
		if (var1.id == 0) {
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.options));
		}

		if (var1.id == 1) {
			this.mc.generateLevel();
			this.mc.displayGuiScreen(null);
		}

		if (var1.id == 2) {
			this.mc.displayGuiScreen(new GuiLoadLevel(this));
		}
	}

	public final void drawScreen(int var1, int var2, float var3) {
		
		this.drawDefaultBackground();
		Tessellator var4 = Tessellator.instance;
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/logo.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		var4.setColorOpaque_I(16777215);
		this.drawTexturedModalRect((this.width - 256) / 2, 30, 0, 0, 256, 49);
		
		// splash
		GL11.glPushMatrix();
		GL11.glTranslatef((float)(this.width / 2 + 90), 70.0F, 0.0F);
		GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
		float var15 = 1.8F - MathHelper.abs(MathHelper.sin((float)(System.currentTimeMillis() % 1000L) / 1000.0F * (float)Math.PI * 2.0F) * 0.1F);
		var15 = var15 * 100.0F / (float)(this.fontRenderer.getStringWidth(this.currentSplash) + 32);
		GL11.glScalef(var15, var15, var15);
		drawCenteredString(this.fontRenderer, this.currentSplash, 0, -8, -16711681); // cyan
		GL11.glPopMatrix();
		
		super.drawScreen(var1, var2, var3);
	}
}
