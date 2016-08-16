package escapee.essencearmory2.common.utils.handler;

import escapee.essencearmory2.lib.LibMain;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.io.File;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class ConfigHandler
{
	public static Configuration configuration;

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
		if (event.getModID().equalsIgnoreCase(LibMain.ModInfo.MOD_ID))
		{
			loadConfigs();
		}
	}
}
