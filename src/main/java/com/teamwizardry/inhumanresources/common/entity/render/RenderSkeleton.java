package com.teamwizardry.inhumanresources.common.entity.render;

import com.teamwizardry.inhumanresources.common.entity.mobs.MobSkeleton;
import com.teamwizardry.inhumanresources.common.entity.models.ModelSkeleton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;

public class RenderSkeleton extends RenderLiving<MobSkeleton>
{
	public RenderSkeleton()
	{
		super(Minecraft.getMinecraft().getRenderManager(), new ModelSkeleton(), 0);
	}
	
	public ResourceLocation getEntityTexture(MobSkeleton entity)
	{
		return new ResourceLocation("textures/entity/skeleton/skeleton.png");
	}
}
