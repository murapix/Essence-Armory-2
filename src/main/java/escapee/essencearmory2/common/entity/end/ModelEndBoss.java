package escapee.essencearmory2.common.entity.end;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEndBoss extends ModelBase
{
	// fields
	ModelRenderer[] core = new ModelRenderer[18];
	ModelRenderer[] leftLimb = new ModelRenderer[3];
	ModelRenderer[] leftWing = new ModelRenderer[3];
	ModelRenderer[] rightLimb = new ModelRenderer[3];
	ModelRenderer[] rightWing = new ModelRenderer[3];
	/**
	 * Eye, Top, Bottom, Left, Right, Socket
	 */
	ModelRenderer[] eye = new ModelRenderer[6];
	ModelRenderer[] topLeft = new ModelRenderer[2];
	ModelRenderer[] topRight = new ModelRenderer[2];
	ModelRenderer[] bottomLeft = new ModelRenderer[2];
	ModelRenderer[] bottomRight = new ModelRenderer[2];

	public ModelEndBoss()
	{
		textureWidth = 512;
		textureHeight = 256;

		core[0] = new ModelRenderer(this, 76, 0);
		core[0].addBox(-10F, -10F, -16F, 20, 20, 18);
		core[0].setRotationPoint(0F, 8F, 16F);
		core[0].setTextureSize(512, 256);
		core[0].mirror = true;
		setRotation(core[0], 0F, 0F, 0F);
		core[1] = new ModelRenderer(this, 0, 0);
		core[1].addBox(-11F, -11F, 0F, 22, 22, 16);
		core[1].setRotationPoint(0F, 8F, 16F);
		core[1].setTextureSize(512, 256);
		core[1].mirror = true;
		setRotation(core[1], 0F, 0F, 0F);
		core[2] = new ModelRenderer(this, 0, 39);
		core[2].addBox(-10F, -10F, -2F, 20, 20, 18);
		core[2].setRotationPoint(0F, 8F, 32F);
		core[2].setTextureSize(512, 256);
		core[2].mirror = true;
		setRotation(core[2], 0F, 0F, 0F);
		core[3] = new ModelRenderer(this, 76, 39);
		core[3].addBox(-9F, -9F, -2F, 18, 18, 18);
		core[3].setRotationPoint(0F, 8F, 48F);
		core[3].setTextureSize(512, 256);
		core[3].mirror = true;
		setRotation(core[3], 0F, 0F, 0F);
		core[4] = new ModelRenderer(this, 152, 0);
		core[4].addBox(-8F, -8F, -2F, 16, 16, 18);
		core[4].setRotationPoint(0F, 8F, 64F);
		core[4].setTextureSize(512, 256);
		core[4].mirror = true;
		setRotation(core[4], 0F, 0F, 0F);
		core[5] = new ModelRenderer(this, 148, 42);
		core[5].addBox(-7F, -7F, -2F, 14, 14, 18);
		core[5].setRotationPoint(0F, 8F, 80F);
		core[5].setTextureSize(512, 256);
		core[5].mirror = true;
		setRotation(core[5], 0F, 0F, 0F);
		core[6] = new ModelRenderer(this, 0, 78);
		core[6].addBox(-6F, -6F, -2F, 12, 12, 18);
		core[6].setRotationPoint(0F, 8F, 96F);
		core[6].setTextureSize(512, 256);
		core[6].mirror = true;
		setRotation(core[6], 0F, 0F, 0F);
		core[7] = new ModelRenderer(this, 60, 78);
		core[7].addBox(-5.5F, -5.5F, -2F, 11, 11, 18);
		core[7].setRotationPoint(0F, 8F, 112F);
		core[7].setTextureSize(512, 256);
		core[7].mirror = true;
		setRotation(core[7], 0F, 0F, 0F);
		core[8] = new ModelRenderer(this, 118, 78);
		core[8].addBox(-5F, -5F, -2F, 10, 10, 18);
		core[8].setRotationPoint(0F, 8F, 128F);
		core[8].setTextureSize(512, 256);
		core[8].mirror = true;
		setRotation(core[8], 0F, 0F, 0F);
		core[9] = new ModelRenderer(this, 174, 78);
		core[9].addBox(-4.5F, -4.5F, -2F, 9, 9, 18);
		core[9].setRotationPoint(0F, 8F, 144F);
		core[9].setTextureSize(512, 256);
		core[9].mirror = true;
		setRotation(core[9], 0F, 0F, 0F);
		core[10] = new ModelRenderer(this, 174, 106);
		core[10].addBox(-4F, -4F, -2F, 8, 8, 18);
		core[10].setRotationPoint(0F, 8F, 160F);
		core[10].setTextureSize(512, 256);
		core[10].mirror = true;
		setRotation(core[10], 0F, 0F, 0F);
		core[11] = new ModelRenderer(this, 118, 107);
		core[11].addBox(-3.5F, -3.5F, -2F, 7, 7, 18);
		core[11].setRotationPoint(0F, 8F, 176F);
		core[11].setTextureSize(512, 256);
		core[11].mirror = true;
		setRotation(core[11], 0F, 0F, 0F);
		core[12] = new ModelRenderer(this, 60, 108);
		core[12].addBox(-3F, -3F, -2F, 6, 6, 18);
		core[12].setRotationPoint(0F, 8F, 192F);
		core[12].setTextureSize(512, 256);
		core[12].mirror = true;
		setRotation(core[12], 0F, 0F, 0F);
		core[13] = new ModelRenderer(this, 0, 109);
		core[13].addBox(-2.5F, -2.5F, -2F, 5, 5, 18);
		core[13].setRotationPoint(0F, 8F, 208F);
		core[13].setTextureSize(512, 256);
		core[13].mirror = true;
		setRotation(core[13], 0F, 0F, 0F);
		core[14] = new ModelRenderer(this, 0, 133);
		core[14].addBox(-2F, -2F, -2F, 4, 4, 18);
		core[14].setRotationPoint(0F, 8F, 224F);
		core[14].setTextureSize(512, 256);
		core[14].mirror = true;
		setRotation(core[14], 0F, 0F, 0F);
		core[15] = new ModelRenderer(this, 44, 133);
		core[15].addBox(-1.5F, -1.5F, -2F, 3, 3, 18);
		core[15].setRotationPoint(0F, 8F, 240F);
		core[15].setTextureSize(512, 256);
		core[15].mirror = true;
		setRotation(core[15], 0F, 0F, 0F);
		core[16] = new ModelRenderer(this, 86, 133);
		core[16].addBox(-1F, -1F, -2F, 2, 2, 18);
		core[16].setRotationPoint(0F, 8F, 256F);
		core[16].setTextureSize(512, 256);
		core[16].mirror = true;
		setRotation(core[16], 0F, 0F, 0F);
		core[17] = new ModelRenderer(this, 126, 133);
		core[17].addBox(-0.5F, -0.5F, -2F, 1, 1, 18);
		core[17].setRotationPoint(0F, 8F, 272F);
		core[17].setTextureSize(512, 256);
		core[17].mirror = true;
		setRotation(core[17], 0F, 0F, 0F);
		leftLimb[0] = new ModelRenderer(this, 0, 240);
		leftLimb[0].addBox(-1F, -4F, -4F, 65, 8, 8);
		leftLimb[0].setRotationPoint(11F, 8F, 24F);
		leftLimb[0].setTextureSize(512, 256);
		leftLimb[0].mirror = true;
		setRotation(leftLimb[0], 0F, -0.0872665F, 0F);
		leftLimb[1] = new ModelRenderer(this, 146, 246);
		leftLimb[1].addBox(-1F, -2.5F, -2.5F, 65, 5, 5);
		leftLimb[1].setRotationPoint(75F, 8F, 24F);
		leftLimb[1].setTextureSize(512, 256);
		leftLimb[1].mirror = true;
		setRotation(leftLimb[1], 0F, -0.1745329F, 0F);
		leftLimb[2] = new ModelRenderer(this, 286, 252);
		leftLimb[2].addBox(-1F, -1F, -1F, 65, 2, 2);
		leftLimb[2].setRotationPoint(138F, 8F, 24F);
		leftLimb[2].setTextureSize(512, 256);
		leftLimb[2].mirror = true;
		setRotation(leftLimb[2], 0F, -0.2617994F, 0F);
		leftWing[0] = new ModelRenderer(this, -32, 202);
		leftWing[0].addBox(0F, 0F, 0F, 64, 0, 32);
		leftWing[0].setRotationPoint(11F, 8F, 24F);
		leftWing[0].setTextureSize(512, 256);
		leftWing[0].mirror = true;
		setRotation(leftWing[0], 0F, -0.0872665F, 0F);
		leftWing[1] = new ModelRenderer(this, -24, 177);
		leftWing[1].addBox(0F, 0F, 0F, 64, 0, 24);
		leftWing[1].setRotationPoint(75F, 8F, 24F);
		leftWing[1].setTextureSize(512, 256);
		leftWing[1].mirror = true;
		setRotation(leftWing[1], 0F, -0.1745329F, 0F);
		leftWing[2] = new ModelRenderer(this, -16, 160);
		leftWing[2].addBox(0F, 0F, 0F, 64, 0, 16);
		leftWing[2].setRotationPoint(138F, 8F, 24F);
		leftWing[2].setTextureSize(512, 256);
		leftWing[2].mirror = true;
		setRotation(leftWing[2], 0F, -0.2617994F, 0F);
		rightLimb[0] = new ModelRenderer(this, 286, 235);
		rightLimb[0].addBox(-64F, -4F, -4F, 65, 8, 8);
		rightLimb[0].setRotationPoint(-11F, 8F, 24F);
		rightLimb[0].setTextureSize(512, 256);
		rightLimb[0].mirror = true;
		setRotation(rightLimb[0], 0F, 0.0872665F, 0F);
		rightLimb[1] = new ModelRenderer(this, 146, 235);
		rightLimb[1].addBox(-64F, -2.5F, -2.5F, 65, 5, 5);
		rightLimb[1].setRotationPoint(-75F, 8F, 24F);
		rightLimb[1].setTextureSize(512, 256);
		rightLimb[1].mirror = true;
		setRotation(rightLimb[1], 0F, 0.1745329F, 0F);
		rightLimb[2] = new ModelRenderer(this, 12, 235);
		rightLimb[2].addBox(-64F, -1F, -1F, 65, 2, 2);
		rightLimb[2].setRotationPoint(-138F, 8F, 24F);
		rightLimb[2].setTextureSize(512, 256);
		rightLimb[2].mirror = true;
		setRotation(rightLimb[2], 0F, 0.2617994F, 0F);
		rightWing[0] = new ModelRenderer(this, 128, 202);
		rightWing[0].addBox(-64F, 0F, 0F, 64, 0, 32);
		rightWing[0].setRotationPoint(-11F, 8F, 24F);
		rightWing[0].setTextureSize(512, 256);
		rightWing[0].mirror = true;
		setRotation(rightWing[0], 0F, 0.0872665F, 0F);
		rightWing[1] = new ModelRenderer(this, 136, 177);
		rightWing[1].addBox(-64F, 0F, 0F, 64, 0, 24);
		rightWing[1].setRotationPoint(-75F, 8F, 24F);
		rightWing[1].setTextureSize(512, 256);
		rightWing[1].mirror = true;
		setRotation(rightWing[1], 0F, 0.1745329F, 0F);
		rightWing[2] = new ModelRenderer(this, 144, 160);
		rightWing[2].addBox(-64F, 0F, 0F, 64, 0, 16);
		rightWing[2].setRotationPoint(-138F, 8F, 24F);
		rightWing[2].setTextureSize(512, 256);
		rightWing[2].mirror = true;
		setRotation(rightWing[2], 0F, 0.2617994F, 0F);
		eye[0] = new ModelRenderer(this, 266, 23);
		eye[0].addBox(-4F, -4F, -4F, 8, 8, 4);
		eye[0].setRotationPoint(0F, 8F, 0F);
		eye[0].setTextureSize(512, 256);
		eye[0].mirror = true;
		setRotation(eye[0], 0F, 0F, 0F);
		eye[1] = new ModelRenderer(this, 266, 0);
		eye[1].addBox(-4F, -2F, -18F, 8, 4, 19);
		eye[1].setRotationPoint(0F, 1F, 0F);
		eye[1].setTextureSize(512, 256);
		eye[1].mirror = true;
		setRotation(eye[1], -0.7853982F, 0F, 0F);
		eye[2] = new ModelRenderer(this, 266, 50);
		eye[2].addBox(-4F, -2F, -18F, 8, 4, 19);
		eye[2].setRotationPoint(0F, 15F, 0F);
		eye[2].setTextureSize(512, 256);
		eye[2].mirror = true;
		setRotation(eye[2], 0.7853982F, 0F, 0F);
		eye[3] = new ModelRenderer(this, 220, 23);
		eye[3].addBox(-2F, -4F, -18F, 4, 8, 19);
		eye[3].setRotationPoint(7F, 8F, 0F);
		eye[3].setTextureSize(512, 256);
		eye[3].mirror = true;
		setRotation(eye[3], 0F, -0.7853982F, 0F);
		eye[4] = new ModelRenderer(this, 320, 23);
		eye[4].addBox(-2F, -4F, -18F, 4, 8, 19);
		eye[4].setRotationPoint(-7F, 8F, 0F);
		eye[4].setTextureSize(512, 256);
		eye[4].mirror = true;
		setRotation(eye[4], 0F, 0.7853982F, 0F);
		eye[5] = new ModelRenderer(this, 296, 38);
		eye[5].addBox(-5F, -5F, -2F, 10, 10, 2);
		eye[5].setRotationPoint(0F, 8F, 0F);
		eye[5].setTextureSize(512, 256);
		eye[5].mirror = true;
		setRotation(eye[5], 0F, 0F, 0F);
		topLeft[0] = new ModelRenderer(this, 439, 219);
		topLeft[0].addBox(-1F, -1F, -3F, 2, 2, 35);
		topLeft[0].setRotationPoint(8F, 0F, 72F);
		topLeft[0].setTextureSize(512, 256);
		topLeft[0].mirror = true;
		setRotation(topLeft[0], 0.7853982F, 0F, 0.7853982F);
		topRight[0] = new ModelRenderer(this, 439, 182);
		topRight[0].addBox(-1F, -1F, -3F, 2, 2, 35);
		topRight[0].setRotationPoint(-8F, 0F, 72F);
		topRight[0].setTextureSize(512, 256);
		topRight[0].mirror = true;
		setRotation(topRight[0], 0.7853982F, 0F, -0.7853982F);
		bottomLeft[0] = new ModelRenderer(this, 439, 145);
		bottomLeft[0].addBox(-1F, -1F, -3F, 2, 2, 35);
		bottomLeft[0].setRotationPoint(8F, 16F, 72F);
		bottomLeft[0].setTextureSize(512, 256);
		bottomLeft[0].mirror = true;
		setRotation(bottomLeft[0], -0.7853982F, 0F, -0.7853982F);
		bottomRight[0] = new ModelRenderer(this, 439, 108);
		bottomRight[0].addBox(-1F, -1F, -3F, 2, 2, 35);
		bottomRight[0].setRotationPoint(-8F, 16F, 72F);
		bottomRight[0].setTextureSize(512, 256);
		bottomRight[0].mirror = true;
		setRotation(bottomRight[0], -0.7853982F, 0F, 0.7853982F);
		topLeft[1] = new ModelRenderer(this, 449, 60);
		topLeft[1].addBox(0F, 0F, 0F, 0, 16, 32);
		topLeft[1].setRotationPoint(8F, 0F, 72F);
		topLeft[1].setTextureSize(512, 256);
		topLeft[1].mirror = true;
		setRotation(topLeft[1], 0.7853982F, 0F, 0.7853982F);
		topRight[1] = new ModelRenderer(this, 449, 43);
		topRight[1].addBox(0F, 0F, 0F, 0, 16, 32);
		topRight[1].setRotationPoint(-8F, 0F, 72F);
		topRight[1].setTextureSize(512, 256);
		topRight[1].mirror = true;
		setRotation(topRight[1], 0.7853982F, 0F, -0.7853982F);
		bottomLeft[1] = new ModelRenderer(this, 449, 26);
		bottomLeft[1].addBox(0F, -16F, 0F, 0, 16, 32);
		bottomLeft[1].setRotationPoint(8F, 16F, 72F);
		bottomLeft[1].setTextureSize(512, 256);
		bottomLeft[1].mirror = true;
		setRotation(bottomLeft[1], -0.7853982F, 0F, -0.7853982F);
		bottomRight[1] = new ModelRenderer(this, 449, 9);
		bottomRight[1].addBox(0F, -16F, 0F, 0, 16, 32);
		bottomRight[1].setRotationPoint(-8F, 16F, 72F);
		bottomRight[1].setTextureSize(512, 256);
		bottomRight[1].mirror = true;
		setRotation(bottomRight[1], -0.7853982F, 0F, 0.7853982F);

		// core[1] - core[0] - eyeSocket - eye
		// core[1] - core[0] - eyelids
		// core[1] - core[2] - core[3] - core[4]
		// core[4] - corner wing limbs - corner wings
		// core[4] - core[5] - core[6] - ... - core[17]
		// core[1] - wingLimb1
		// wingLimb1 - wingWing1
		// wingLimb1 - wingLimb2
		// wingLimb2 - wingWing2
		// wingLimb2 - wingLimb3
		// wingLimb3 - wingWing3

		addChild(eye[5], eye[0]);
		addChild(core[0], eye[5]);
		addChild(core[0], eye[4]);
		addChild(core[0], eye[3]);
		addChild(core[0], eye[2]);
		addChild(core[0], eye[1]);
		addChild(core[1], core[0]);

		addChild(core[16], core[17]);
		addChild(core[15], core[16]);
		addChild(core[14], core[15]);
		addChild(core[13], core[14]);
		addChild(core[12], core[13]);
		addChild(core[11], core[12]);
		addChild(core[10], core[11]);
		addChild(core[9], core[10]);
		addChild(core[8], core[9]);
		addChild(core[7], core[8]);
		addChild(core[6], core[7]);
		addChild(core[5], core[6]);
		addChild(core[4], core[5]);

		addChild(bottomLeft[0], bottomLeft[1]);
		addChild(bottomRight[0], bottomRight[1]);
		addChild(topLeft[0], topLeft[1]);
		addChild(topRight[0], topRight[1]);
		addChild(core[4], bottomRight[0]);
		addChild(core[4], topLeft[0]);
		addChild(core[4], bottomLeft[0]);
		addChild(core[4], topRight[0]);

		addChild(core[3], core[4]);
		addChild(core[2], core[3]);
		addChild(core[1], core[2]);

		addChild(leftLimb[2], leftWing[2]);
		addChild(leftLimb[1], leftLimb[2]);
		addChild(leftLimb[1], leftWing[1]);
		addChild(leftLimb[0], leftLimb[1]);
		addChild(leftLimb[0], leftWing[0]);
		addChild(core[1], leftLimb[0]);

		addChild(rightLimb[2], rightWing[2]);
		addChild(rightLimb[1], rightLimb[2]);
		addChild(rightLimb[1], rightWing[1]);
		addChild(rightLimb[0], rightLimb[1]);
		addChild(rightLimb[0], rightWing[0]);
		addChild(core[1], rightLimb[0]);
	}

	private static final int eyeCycleDuration = 100;
	private static final int tailCycleDuration = 40;
	private static final int wingCycleDuration = 40;
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		float eyeCycle = ageInTicks % eyeCycleDuration;
		float tailCycle = ageInTicks % tailCycleDuration;
		float wingCycle = ageInTicks % wingCycleDuration;
		
		float eyeRotation = MathHelper.cos(eyeCycle * 3.1415927F / (eyeCycleDuration / 2)) * 0.5235988F;
		setRotation(eye[1], -0.2617994F - eyeRotation, 0, 0);	// X (-45 <-> 15)
		setRotation(eye[2], 0.2617994F + eyeRotation, 0, 0);	// X (45 <-> -15)
		setRotation(eye[3], 0, -0.2617994F - eyeRotation, 0);	// Y (-45 <-> 15)
		setRotation(eye[4], 0, 0.2617994F + eyeRotation, 0);	// Y (45 <-> -15)
		
		for (int i = 2; i < core.length; i++)
		{
			float rotation = MathHelper.cos((tailCycle - 2*i) * 3.1415927F / (tailCycleDuration / 2)) * (0.2617994F * (i-2) / (core.length - 2));
			this.setRotation(core[i], 0, rotation, 0);
		}
		
		float[] wingRotation = new float[3];
		wingRotation[0] = MathHelper.cos(wingCycle * 3.1415927F / (wingCycleDuration / 2)) * 0.7853982F;
		if (wingCycle < (wingCycleDuration / 2))
			wingRotation[1] = wingRotation[2] = MathHelper.sin(wingCycle * 3.1415927F / (wingCycleDuration / 2)) * 0.0872665F;
		else
		{
			wingRotation[1] = MathHelper.sin(wingCycle * 3.1415927F / (wingCycleDuration / 2)) * 0.5235988F;
			wingRotation[2] = MathHelper.sin(wingCycle * 3.1415927F / (wingCycleDuration / 2)) * 0.2617994F;
		}
		setRotation(leftLimb[0], 0, -0.0872665F, -wingRotation[0]);
		setRotation(leftLimb[1], 0, -0.1745329F, -wingRotation[1]);
		setRotation(leftLimb[2], 0, -0.2617994F, -wingRotation[2]);
		setRotation(rightLimb[0], 0, 0.0872665F, wingRotation[0]);
		setRotation(rightLimb[1], 0, 0.1745329F, wingRotation[1]);
		setRotation(rightLimb[2], 0, 0.2617994F, wingRotation[2]);
		
		core[1].render(1F / 16F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	private void addChild(ModelRenderer parent, ModelRenderer child)
	{
		child.rotationPointX -= parent.rotationPointX;
		child.rotationPointY -= parent.rotationPointY;
		child.rotationPointZ -= parent.rotationPointZ;
		child.rotateAngleX -= parent.rotateAngleX;
		child.rotateAngleY -= parent.rotateAngleY;
		child.rotateAngleZ -= parent.rotateAngleZ;
		parent.addChild(child);
	}
}
