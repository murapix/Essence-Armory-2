package com.teamwizardry.inhumanresources.common.utils.handler;

import java.io.File;
import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class ConfigHandler
{
	public static Configuration configuration;

	public static int mobTrapSizeX1, mobTrapSizeY1, mobTrapSizeZ1,
			mobTrapSizeX2, mobTrapSizeY2, mobTrapSizeZ2;

	public static void init(File configFile)
	{
		if (configuration == null)
		{
			configuration = new Configuration(configFile);
			loadConfigs();
		}
	}

	public static void loadConfigs()
	{
		if (configuration.hasChanged())
		{
			configuration.save();
		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		mobTrapSizeX1 = configuration.getInt("x_coord1", Configuration.CATEGORY_GENERAL, 0, 0, 16, "the first extension on z coord");
		mobTrapSizeY1 = configuration.getInt("y_coord1", Configuration.CATEGORY_GENERAL, 0, 0, 16, "the first extension on z coord");
		mobTrapSizeZ1 = configuration.getInt("z_coord1", Configuration.CATEGORY_GENERAL, 0, 0, 16, "the first extension on z coord");
		mobTrapSizeX2 = configuration.getInt("x_coord2", Configuration.CATEGORY_GENERAL, 0, 0, 16, "the second extension on z coord");
		mobTrapSizeY2 = configuration.getInt("y_coord2", Configuration.CATEGORY_GENERAL, 0, 2, 16, "the second extension on z coord");
		mobTrapSizeZ2 = configuration.getInt("z_coord2", Configuration.CATEGORY_GENERAL, 0, 0, 16, "the second extension on z coord");

		if (event.getModID().equalsIgnoreCase(ModInfo.MOD_ID))
		{
			loadConfigs();
		}
	}
}
