package escapee.essencearmory2.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import escapee.essencearmory2.common.utils.handler.ConfigHandler;
import escapee.essencearmory2.lib.ModInfo;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class ModGuiConfig extends GuiConfig
{
	public ModGuiConfig(GuiScreen guiScreen)
	{
		super(guiScreen, new ConfigElement(ConfigHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), ModInfo.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.configuration.toString()));
	}
}
