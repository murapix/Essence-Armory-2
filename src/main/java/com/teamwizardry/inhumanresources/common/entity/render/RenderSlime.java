package com.teamwizardry.inhumanresources.common.entity.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobSlime;

public class RenderSlime extends RenderLiving<MobSlime>
{
	private static final ResourceLocation SLIME_TEXTURES = new ResourceLocation("textures/entity/slime/slime.png");

	public RenderSlime()
	{
		super(Minecraft.getMinecraft().getRenderManager(), new ModelSlime(16), 0);
		this.addLayer(new LayerSlimeGel(this));
	}

	@Override
	public void doRender(MobSlime entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		this.shadowSize = 0.25F * entity.getSlimeSize();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected void preRenderCallback(MobSlime entity, float partialTicks)
	{
		float f = 0.999F;
		GlStateManager.scale(f, f, f);
		float f1 = entity.getSlimeSize();
		float f2 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * partialTicks) / (f1 * 0.5F + 1);
		float f3 = 1F / (f2 + 1);
		GlStateManager.scale(f3 * f1, 1F / f3 * f1, f3 * f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(MobSlime entity)
	{
		return SLIME_TEXTURES;
	}
}
