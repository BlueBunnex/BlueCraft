package net.minecraft.client;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;

import net.minecraft.src.CanvasMinecraftApplet;
import net.minecraft.src.Minecraft;

public class MinecraftApplet extends Applet {
	
	private Canvas mcCanvas;
	private Minecraft mc;
	private Thread mcThread = null;

	public void init() {
		this.mcCanvas = new CanvasMinecraftApplet(this);

		this.mc = new MinecraftAppletImpl(this, this, this.mcCanvas, this, this.getWidth(), this.getHeight(), false);
		this.mc.minecraftUri = this.getDocumentBase().getHost();
		if(this.getDocumentBase().getPort() > 0) {
			this.mc.minecraftUri = this.mc.minecraftUri + ":" + this.getDocumentBase().getPort();
		}

		this.mc.appletMode = true;
		this.setLayout(new BorderLayout());
		this.add(this.mcCanvas, "Center");
		this.mcCanvas.setFocusable(true);
		this.validate();
	}

	public void startMainThread() {
		if(this.mcThread == null) {
			this.mcThread = new Thread(this.mc, "Minecraft main thread");
			this.mcThread.start();
		}
	}

	public void start() {
		if(this.mc != null) {
			this.mc.isGamePaused = false;
		}

	}

	public void stop() {
		if(this.mc != null) {
			this.mc.isGamePaused = true;
		}

	}

	public void destroy() {
		this.shutdown();
	}

	public void shutdown() {
		if(this.mcThread != null) {
			this.mc.shutdown();

			try {
				this.mcThread.join(10000L);
			} catch (InterruptedException var4) {
				try {
					this.mc.shutdownMinecraftApplet();
				} catch (Exception var3) {
					var3.printStackTrace();
				}
			}

			this.mcThread = null;
		}
	}

	public void clearApplet() {
		this.mcCanvas = null;
		this.mc = null;
		this.mcThread = null;

		try {
			this.removeAll();
			this.validate();
		} catch (Exception e) {}

	}
	
	// I wanna get rid of this soo bad but stupid fuckin MCPHacker
	class MinecraftAppletImpl extends Minecraft {

		public MinecraftAppletImpl(MinecraftApplet var1, Component var2, Canvas var3, MinecraftApplet var4, int var5, int var6, boolean var7) {
			super(var1, var2, var3, var4, var5, var6, var7);
		}
	}
}
