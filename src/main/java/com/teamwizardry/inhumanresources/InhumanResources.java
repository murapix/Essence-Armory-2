package com.teamwizardry.inhumanresources;

import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;
import com.teamwizardry.inhumanresources.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by SirShadow on 14.8.2016.
 */
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, guiFactory = ModInfo.GUI_FACTORY, dependencies = ModInfo.DEPENDENCIES, useMetadata = true)
public class InhumanResources
{
	@Mod.Instance(ModInfo.MOD_ID)
	public static InhumanResources instance;

	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.SERVER_PROXY)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		proxy.onPreInit(event);
	}

	@Mod.EventHandler
	public void onInit(FMLInitializationEvent event)
	{
		proxy.onInit(event);
	}

	@Mod.EventHandler
	public void onPostInit(FMLPostInitializationEvent event)
	{
		proxy.onPostInit(event);
	}
}
