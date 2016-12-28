package com.teamwizardry.inhumanresources.proxy;

import com.teamwizardry.inhumanresources.InhumanResources;
import com.teamwizardry.inhumanresources.client.GuiHandler;
import com.teamwizardry.inhumanresources.common.capability.EACapabilityManeger;
import com.teamwizardry.inhumanresources.common.event.EventHandler;
import com.teamwizardry.inhumanresources.common.network.EAPacketHandler;
import com.teamwizardry.inhumanresources.common.upgrade.UpgradeRegistry;
import com.teamwizardry.inhumanresources.common.utils.handler.ConfigHandler;
import com.teamwizardry.inhumanresources.common.utils.helper.TextHelper;
import com.teamwizardry.inhumanresources.init.BlockRegistry;
import com.teamwizardry.inhumanresources.init.ModEntityRegistry;
import com.teamwizardry.inhumanresources.init.ItemRegistry;
import com.teamwizardry.inhumanresources.init.PotionRegistry;
import com.teamwizardry.inhumanresources.init.RecipeRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class CommonProxy
{
	public void onPreInit(FMLPreInitializationEvent event)
	{
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		eventRegistry();
		PotionRegistry.init();
		ItemRegistry.init();
		BlockRegistry.init();
		BlockRegistry.tileEntityRegisty();
		EAPacketHandler.register();
		EACapabilityManeger.registerCapability();
		TextHelper.addColorsAndComponents();
	}

	public void onInit(FMLInitializationEvent event)
	{
		register();
		ModEntityRegistry.registerEntities();
		RecipeRegistry.registerRecipes();
		UpgradeRegistry.init();
	}

	public void onPostInit(FMLPostInitializationEvent event)
	{

	}

	private void eventRegistry()
	{
		MinecraftForge.EVENT_BUS.register(EventHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(new ConfigHandler());
		MinecraftForge.EVENT_BUS.register(new EACapabilityManeger());
	}

	private void register()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(InhumanResources.instance, new GuiHandler());
	}
}
