package escapee.essencearmory2.common.entity.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import escapee.essencearmory2.common.entity.mobs.MobSkeleton;

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
