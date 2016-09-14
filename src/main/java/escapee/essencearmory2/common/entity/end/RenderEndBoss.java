package escapee.essencearmory2.common.entity.end;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import escapee.essencearmory2.lib.ModInfo;

public class RenderEndBoss extends RenderLiving<EntityEndBoss>
{
	public RenderEndBoss()
	{
		super(Minecraft.getMinecraft().getRenderManager(), new ModelEndBoss(), 0);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityEndBoss entity)
	{
		return new ResourceLocation(ModInfo.MOD_ID + ":textures/entity/boss/end.png");
	}
}
