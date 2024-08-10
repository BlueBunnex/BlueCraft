package net.minecraft.src.entity.render;

import net.minecraft.src.MathHelper;

public class ModelBiped extends Model {
	
	public ModelRenderer bipedHead;
	public ModelRenderer bipedHeadwear;
	public ModelRenderer bipedBody;
	public ModelRenderer bipedRightArm;
	public ModelRenderer bipedLeftArm;
	public ModelRenderer bipedRightLeg;
	public ModelRenderer bipedLeftLeg;

	public ModelBiped() {
		this(0.0F);
	}

	public ModelBiped(float var1) {
		this(var1, 0.0F);
	}

	public ModelBiped(float inflate, float vertOff) {
		
		this.bipedHead = new ModelRenderer(0, 0);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, inflate);
		this.bipedHead.setRotationPoint(0.0F, 0.0F + vertOff, 0.0F);
		this.bipedHeadwear = new ModelRenderer(32, 0);
		this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, inflate + 0.5F);
		this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + vertOff, 0.0F);
		this.bipedBody = new ModelRenderer(16, 16);
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, inflate);
		this.bipedBody.setRotationPoint(0.0F, 0.0F + vertOff, 0.0F);
		this.bipedRightArm = new ModelRenderer(40, 16);
		this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, inflate);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + vertOff, 0.0F);
		this.bipedLeftArm = new ModelRenderer(40, 16);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, inflate);
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + vertOff, 0.0F);
		this.bipedRightLeg = new ModelRenderer(0, 16);
		this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, inflate);
		this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F + vertOff, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(0, 16);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, inflate);
		this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F + vertOff, 0.0F);
	}

	public void render(float moveT, float moveAmount, float idleT, float lookYaw, float lookPitch, float var6) {
		this.setRotationAngles(moveT, moveAmount, idleT, lookYaw, lookPitch, var6);
		this.bipedHead.render(var6);
		this.bipedBody.render(var6);
		this.bipedRightArm.render(var6);
		this.bipedLeftArm.render(var6);
		this.bipedRightLeg.render(var6);
		this.bipedLeftLeg.render(var6);
		this.bipedHeadwear.render(var6);
	}
	
	public void setRotationAngles(float moveT, float moveAmount, float idleT, float lookYaw, float lookPitch, float var6) {
		
		this.bipedHead.rotateAngleY = lookYaw   / (180.0F / (float) Math.PI);
		this.bipedHead.rotateAngleX = lookPitch / (180.0F / (float) Math.PI);
		this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
		this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
		
		this.bipedRightArm.rotateAngleX = MathHelper.cos(moveT * 0.8F + (float) Math.PI) * 1.8F * moveAmount;
		this.bipedRightArm.rotateAngleZ = 0;
		this.bipedLeftArm.rotateAngleX = MathHelper.cos(moveT * 0.8F) * 1.8F * moveAmount;
		this.bipedLeftArm.rotateAngleZ = 0;
		
		this.bipedRightLeg.rotateAngleX = MathHelper.cos(moveT * 0.8F) * 1.4F * moveAmount;
		this.bipedLeftLeg.rotateAngleX = MathHelper.cos(moveT * 0.8F + (float) Math.PI) * 1.4F * moveAmount;
		this.bipedRightLeg.rotateAngleY = 0.0F;
		this.bipedLeftLeg.rotateAngleY = 0.0F;
		
		if(this.isRiding) {
			this.bipedRightArm.rotateAngleX += (float)Math.PI * -0.2F;
			this.bipedLeftArm.rotateAngleX += (float)Math.PI * -0.2F;
			this.bipedRightLeg.rotateAngleX = (float)Math.PI * -0.4F;
			this.bipedLeftLeg.rotateAngleX = (float)Math.PI * -0.4F;
			this.bipedRightLeg.rotateAngleY = (float)Math.PI * 0.1F;
			this.bipedLeftLeg.rotateAngleY = (float)Math.PI * -0.1F;
		}

		// idle/wobble
		this.bipedRightArm.rotateAngleZ += MathHelper.cos(idleT * 0.09F) * 0.05F + 0.05F;
		this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(idleT * 0.09F) * 0.05F + 0.05F;
		this.bipedRightArm.rotateAngleX += MathHelper.sin(idleT * 0.067F) * 0.05F;
		this.bipedLeftArm.rotateAngleX -= MathHelper.sin(idleT * 0.067F) * 0.05F;
	}
}
