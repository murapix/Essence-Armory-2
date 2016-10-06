package escapee.essencearmory2.init;

import java.awt.Color;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import escapee.essencearmory2.EssenceArmory;
import escapee.essencearmory2.common.entity.end.EntityEndBoss;
import escapee.essencearmory2.common.entity.end.RenderEndBoss;
import escapee.essencearmory2.common.entity.mobs.MobZombie;
import escapee.essencearmory2.common.entity.render.RenderZombie;

public class EAEntityRegistry
{
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("deprecation")
	public static void initRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityEndBoss.class, new RenderEndBoss());
		RenderingRegistry.registerEntityRenderingHandler(MobZombie.class, new RenderZombie());
	}
	
	public static void registerEntities()
	{
		EntityRegistry.registerModEntity(EntityEndBoss.class, "endBoss", 0, EssenceArmory.instance, 64, 3, true, Color.MAGENTA.getRGB(), Color.PINK.getRGB());
		EntityRegistry.registerModEntity(MobZombie.class, "mobZombie", 1, EssenceArmory.instance, 64, 3, true, Color.green.getRGB(), Color.MAGENTA.getRGB());
	}
}
