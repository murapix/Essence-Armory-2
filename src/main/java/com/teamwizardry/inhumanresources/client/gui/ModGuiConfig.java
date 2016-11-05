package com.teamwizardry.inhumanresources.client.gui;

import com.teamwizardry.inhumanresources.common.utils.handler.ConfigHandler;
import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

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
