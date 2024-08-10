package net.minecraft.src.entity.render;

public abstract class Model {
	
	public float swingProgress;
	public boolean isRiding = false;

	public void render(float moveT, float moveAmount, float idleT, float lookYaw, float lookPitch, float var6) {}

	/**
	 * 
	 * @param moveT timer variable for movement animation.
	 * @param moveAmount used to smooth between moving and idle (1=completely moving, 0=completely idle).
	 * @param idleT timer variable for idle animation.
	 * @param var4
	 * @param var5
	 * @param var6
	 */
	public void setRotationAngles(float moveT, float moveAmount, float idleT, float var4, float var5, float var6) {}
	
}
