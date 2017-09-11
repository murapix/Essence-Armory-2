package com.teamwizardry.inhumanresources.proxy;

import com.teamwizardry.inhumanresources.InhumanResources;
import com.teamwizardry.inhumanresources.client.gui.GuiHandler;
import com.teamwizardry.inhumanresources.common.event.EventHandler;
import com.teamwizardry.inhumanresources.common.upgrade.UpgradeRegistry;
import com.teamwizardry.inhumanresources.common.utils.helper.TextHelper;
import com.teamwizardry.inhumanresources.init.BlockRegistry;
import com.teamwizardry.inhumanresources.init.ItemRegistry;
import com.teamwizardry.inhumanresources.init.ModEntityRegistry;
import com.teamwizardry.inhumanresources.init.PotionRegistry;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy
{
	public void onPreInit(FMLPreInitializationEvent event)
	{
		eventRegistry();
		PotionRegistry.init();
		ItemRegistry.init();
		BlockRegistry.init();
		TextHelper.addColorsAndComponents();
	}

	public void onInit(FMLInitializationEvent event)
	{
		register();
		ModEntityRegistry.registerEntities();
		UpgradeRegistry.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(InhumanResources.instance, new GuiHandler());
	}

	public void onPostInit(FMLPostInitializationEvent event)
	{

	}

	private void eventRegistry()
	{
		MinecraftForge.EVENT_BUS.register(EventHandler.INSTANCE);
	}

	private void register()
	{
//		NetworkRegistry.INSTANCE.registerGuiHandler(InhumanResources.instance, new GuiHandler());
	}
}
