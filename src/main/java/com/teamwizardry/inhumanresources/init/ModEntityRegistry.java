package com.teamwizardry.inhumanresources.init;

import java.awt.Color;
import com.teamwizardry.inhumanresources.InhumanResources;
import com.teamwizardry.inhumanresources.common.entity.end.EntityEndBoss;
import com.teamwizardry.inhumanresources.common.entity.end.RenderEndBoss;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobSkeleton;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobSlime;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobZombie;
import com.teamwizardry.inhumanresources.common.entity.render.RenderSkeleton;
import com.teamwizardry.inhumanresources.common.entity.render.RenderSlime;
import com.teamwizardry.inhumanresources.common.entity.render.RenderZombie;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntityRegistry
{
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("deprecation")
	public static void initRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityEndBoss.class, new RenderEndBoss());
		RenderingRegistry.registerEntityRenderingHandler(MobZombie.class, new RenderZombie());
		RenderingRegistry.registerEntityRenderingHandler(MobSkeleton.class, new RenderSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(MobSlime.class, new RenderSlime());
	}
	
	public static void registerEntities()
	{
		int index = 0;
		EntityRegistry.registerModEntity(EntityEndBoss.class, "endBoss", index++, InhumanResources.instance, 64, 3, true, Color.MAGENTA.getRGB(), Color.PINK.getRGB());
		EntityRegistry.registerModEntity(MobZombie.class, "mobZombie", index++, InhumanResources.instance, 64, 3, true, Color.GREEN.getRGB(), Color.MAGENTA.getRGB());
		EntityRegistry.registerModEntity(MobSkeleton.class, "mobSkeleton", index++, InhumanResources.instance, 64, 3, true, Color.GRAY.getRGB(), Color.MAGENTA.getRGB());
//		EntityRegistry.registerModEntity(MobSlime.class, "mobSlime", index++, InhumanResources.instance, 64, 3, true, 0x32CD32, Color.MAGENTA.getRGB());
	}
}
