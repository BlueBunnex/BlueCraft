package net.minecraft.client.gui;

import net.minecraft.client.render.Tessellator;
import net.minecraft.game.item.Item;
import net.minecraft.game.item.ItemStack;
import net.minecraft.game.level.block.Block;

import org.lwjgl.opengl.GL11;
import util.MathHelper;

// TODO add sand-box world button to GuiGameOver and GuiIngameMenu
// TODO add return to main menu button
// TODO add milk peaches and boob bar... probably lol idk (icon is bread + 1)

public final class GuiMainMenu extends GuiScreen {
	
	private String[] splashes = new String[]{ "May contain Blue 40!" };
	private String currentSplash = this.splashes[(int)(Math.random() * (double) this.splashes.length)];

	protected final void keyTyped(char var1, int var2) {}

	public final void initGui() {
		this.controlList.clear();
		
		int commonX = this.width / 2 - 100;
		int commonY = this.height / 4;
		this.controlList.add(new GuiButton(0, commonX, commonY + 48, 98, 20, "New survival world"));
		this.controlList.add(new GuiButton(1, commonX + 102, commonY + 48, 98, 20, "New sandbox world"));
		
		this.controlList.add(new GuiButton(2, commonX, commonY + 72, "Load world..."));
		
		this.controlList.add(new GuiButton(3, commonX, commonY + 108, "Options..."));
		this.controlList.add(new GuiButton(4, commonX, commonY + 132, "Exit game"));
	}

	protected final void actionPerformed(GuiButton var1) {
		
		switch (var1.id) {
		
			case 0:
				this.mc.generateLevel();
				this.mc.displayGuiScreen(null);
				break;
				
			case 1:
				
				this.mc.generateLevel();
				
				this.mc.thePlayer.inventory.setInventorySlotContents(0, new ItemStack(Item.pickaxeDiamond));
				this.mc.thePlayer.inventory.setInventorySlotContents(1, new ItemStack(Item.shovelDiamond));
				this.mc.thePlayer.inventory.setInventorySlotContents(2, new ItemStack(Block.torch, 64));
				
				for (int i=3; i<9; i++)
					this.mc.thePlayer.inventory.setInventorySlotContents(i, new ItemStack(Item.porkCooked));
				
				this.mc.displayGuiScreen(null);
				
				break;
				
			case 2:
				this.mc.displayGuiScreen(new GuiLoadLevel(this));
				break;
				
			case 3:
				this.mc.displayGuiScreen(new GuiOptions(this, this.mc.options));
				break;
				
			case 4:
				this.mc.shutdownMinecraftApplet();
				break;
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
