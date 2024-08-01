package net.minecraft.src;

import java.util.Random;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import net.minecraft.src.block.Block;

public class GuiMainMenu extends GuiScreen {
	
	private static final Random rand = new Random();
	String[] logoBlockLayers = new String[]{" *   * * *   * *** *** *** *** *** ***", " ** ** * **  * *   *   * * * * *    * ", " * * * * * * * **  *   **  *** **   * ", " *   * * *  ** *   *   * * * * *    * ", " *   * * *   * *** *** * * * * *    * "};
	private LogoEffectRandomizer[][] logoEffects;
	private String[] splashes = new String[]{ "Sponsored by say gex!" };
	private String currentSplash = this.splashes[(int)(Math.random() * (double)this.splashes.length)];

	public void updateScreen() {
		
		if (this.logoEffects != null) {
			for (int var1 = 0; var1 < this.logoEffects.length; ++var1) {
				for (int var2 = 0; var2 < this.logoEffects[var1].length; ++var2) {
					this.logoEffects[var1][var2].updateLogoEffects();
				}
			}
		}
	}

	protected void keyTyped(char var1, int var2) {
	}

	public void initGui() {
		
		this.controlList.clear();
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 48, "Play"));
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, "Options..."));
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.id == 0) {
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.options));
		}

		if(var1.id == 1) {
			this.mc.displayGuiScreen(new GuiSelectWorld(this));
		}

	}

	public void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();
		Tessellator var4 = Tessellator.instance;
		this.drawLogo(var3);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/logo.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		var4.setColorOpaque_I(16777215);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)(this.width / 2 + 90), 70.0F, 0.0F);
		GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
		float var5 = 1.8F - MathHelper.abs(MathHelper.sin((float)(System.currentTimeMillis() % 1000L) / 1000.0F * (float)Math.PI * 2.0F) * 0.1F);
		var5 = var5 * 100.0F / (float)(this.fontRenderer.getStringWidth(this.currentSplash) + 32);
		GL11.glScalef(var5, var5, var5);
		this.drawCenteredString(this.fontRenderer, this.currentSplash, 0, -8, 16776960);
		GL11.glPopMatrix();
		String var6 = "Copyright Mojang Specifications. Do not distribute.";
		this.drawString(this.fontRenderer, var6, this.width - this.fontRenderer.getStringWidth(var6) - 2, this.height - 10, 16777215);
		long var7 = Runtime.getRuntime().maxMemory();
		long var9 = Runtime.getRuntime().totalMemory();
		long var11 = Runtime.getRuntime().freeMemory();
		long var13 = var7 - var11;
		var6 = "Free memory: " + var13 * 100L / var7 + "% of " + var7 / 1024L / 1024L + "MB";
		this.drawString(this.fontRenderer, var6, this.width - this.fontRenderer.getStringWidth(var6) - 2, 2, 8421504);
		var6 = "Allocated memory: " + var9 * 100L / var7 + "% (" + var9 / 1024L / 1024L + "MB)";
		this.drawString(this.fontRenderer, var6, this.width - this.fontRenderer.getStringWidth(var6) - 2, 12, 8421504);
		super.drawScreen(var1, var2, var3);
	}

	private void drawLogo(float var1) {
		int var3;
		if(this.logoEffects == null) {
			this.logoEffects = new LogoEffectRandomizer[this.logoBlockLayers[0].length()][this.logoBlockLayers.length];

			for(int var2 = 0; var2 < this.logoEffects.length; ++var2) {
				for(var3 = 0; var3 < this.logoEffects[var2].length; ++var3) {
					this.logoEffects[var2][var3] = new LogoEffectRandomizer(this, var2, var3);
				}
			}
		}

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		ScaledResolution var14 = new ScaledResolution(this.mc.displayWidth, this.mc.displayHeight);
		var3 = 120 * var14.scaleFactor;
		GLU.gluPerspective(70.0F, (float)this.mc.displayWidth / (float)var3, 0.05F, 100.0F);
		GL11.glViewport(0, this.mc.displayHeight - var3, this.mc.displayWidth, var3);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glDepthMask(true);

		for(int var4 = 0; var4 < 3; ++var4) {
			GL11.glPushMatrix();
			GL11.glTranslatef(0.4F, 0.6F, -12.0F);
			if(var4 == 0) {
				GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
				GL11.glTranslatef(0.0F, -0.4F, 0.0F);
				GL11.glScalef(0.98F, 1.0F, 1.0F);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			}

			if(var4 == 1) {
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
			}

			if(var4 == 2) {
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
			}

			GL11.glScalef(1.0F, -1.0F, 1.0F);
			GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(0.89F, 1.0F, 0.4F);
			GL11.glTranslatef((float)(-this.logoBlockLayers[0].length()) * 0.5F, (float)(-this.logoBlockLayers.length) * 0.5F, 0.0F);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/terrain.png"));
			if(var4 == 0) {
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/title/black.png"));
			}

			RenderBlocks var5 = new RenderBlocks();

			for(int var6 = 0; var6 < this.logoBlockLayers.length; ++var6) {
				for(int var7 = 0; var7 < this.logoBlockLayers[var6].length(); ++var7) {
					char var8 = this.logoBlockLayers[var6].charAt(var7);
					if(var8 != 32) {
						GL11.glPushMatrix();
						LogoEffectRandomizer var9 = this.logoEffects[var7][var6];
						float var10 = (float)(var9.prevHeight + (var9.height - var9.prevHeight) * (double)var1);
						float var11 = 1.0F;
						float var12 = 1.0F;
						float var13 = 0.0F;
						if(var4 == 0) {
							var11 = var10 * 0.04F + 1.0F;
							var12 = 1.0F / var11;
							var10 = 0.0F;
						}

						GL11.glTranslatef((float)var7, (float)var6, var10);
						GL11.glScalef(var11, var11, var11);
						GL11.glRotatef(var13, 0.0F, 1.0F, 0.0F);
						var5.renderBlockAsItem(Block.stone, var12);
						GL11.glPopMatrix();
					}
				}
			}

			GL11.glPopMatrix();
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPopMatrix();
		GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	static Random getRandom() {
		return rand;
	}
}
