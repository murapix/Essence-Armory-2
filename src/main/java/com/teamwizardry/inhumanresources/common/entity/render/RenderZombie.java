package com.teamwizardry.inhumanresources.common.entity.render;

import com.teamwizardry.inhumanresources.common.entity.mobs.MobZombie;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;

public class RenderZombie extends RenderLiving<MobZombie>
{
	public RenderZombie()
	{
		super(Minecraft.getMinecraft().getRenderManager(), new ModelZombie(), 0);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(MobZombie entity)
	{
		return new ResourceLocation("textures/entity/zombie/zombie.png");
	}
}
