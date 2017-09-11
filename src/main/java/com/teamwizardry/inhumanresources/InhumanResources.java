package com.teamwizardry.inhumanresources;

import org.apache.logging.log4j.Logger;

import com.teamwizardry.inhumanresources.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = InhumanResources.MOD_ID, name = InhumanResources.MOD_NAME, version = InhumanResources.MOD_VERSION, dependencies = InhumanResources.DEPENDENCIES, useMetadata = true)
public class InhumanResources
{
	public static final String MOD_ID =  "inhumanresources";
	public static final String MOD_NAME = "Inhuman Resources";
	public static final String MOD_VERSION = "0.1";
	public static final String CLIENT_PROXY = "com.teamwizardry.inhumanresources.proxy.ClientProxy";
	public static final String SERVER_PROXY = "com.teamwizardry.inhumanresources.proxy.ServerProxy";
	public static final String DEPENDENCIES = "required-after:librarianlib";
	
	@Mod.Instance(InhumanResources.MOD_ID)
	public static InhumanResources instance;
	
	public static Logger logger;

	@SidedProxy(clientSide = InhumanResources.CLIENT_PROXY, serverSide = InhumanResources.SERVER_PROXY)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
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
