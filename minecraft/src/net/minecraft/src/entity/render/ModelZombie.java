package net.minecraft.src.entity.render;

import net.minecraft.src.MathHelper;

public class ModelZombie extends ModelBiped {
	
	public void setRotationAngles(float moveT, float moveAmount, float idleT, float lookYaw, float lookPitch, float var6) {
		
		// div2 to make walk not run
		// negative pitch because for some reason the zombie's is passed inverted
		super.setRotationAngles(moveT / 2, moveAmount / 2, idleT, lookYaw, -lookPitch, var6);
		
		float var7 = MathHelper.sin(this.swingProgress * (float)Math.PI);
		float var8 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
		this.bipedRightArm.rotateAngleZ = 0.0F;
		this.bipedLeftArm.rotateAngleZ = 0.0F;
		this.bipedRightArm.rotateAngleY = -(0.1F - var7 * 0.6F);
		this.bipedLeftArm.rotateAngleY = 0.1F - var7 * 0.6F;
		this.bipedRightArm.rotateAngleX = (float)Math.PI * -0.5F;
		this.bipedLeftArm.rotateAngleX = (float)Math.PI * -0.5F;
		this.bipedRightArm.rotateAngleX -= var7 * 1.2F - var8 * 0.4F;
		this.bipedLeftArm.rotateAngleX -= var7 * 1.2F - var8 * 0.4F;
		this.bipedRightArm.rotateAngleZ += MathHelper.cos(idleT * 0.09F) * 0.05F + 0.05F;
		this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(idleT * 0.09F) * 0.05F + 0.05F;
		this.bipedRightArm.rotateAngleX += MathHelper.sin(idleT * 0.067F) * 0.05F;
		this.bipedLeftArm.rotateAngleX -= MathHelper.sin(idleT * 0.067F) * 0.05F;
	}
}
