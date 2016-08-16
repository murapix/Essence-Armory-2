package escapee.essencearmory2;

import escapee.essencearmory2.lib.LibMain;
import escapee.essencearmory2.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * Created by SirShadow on 14.8.2016.
 */
@Mod(modid = LibMain.ModInfo.MOD_ID, name = LibMain.ModInfo.MOD_NAME, version = LibMain.ModInfo.MOD_VERSION, guiFactory = LibMain.ModInfo.GUI_FACTORY, useMetadata = true)
public class EssenceArmory
{
	@Mod.Instance(LibMain.ModInfo.MOD_ID)
	public static EssenceArmory instance;

	@SidedProxy(clientSide = LibMain.ModInfo.CLIENT_PROXY, serverSide = LibMain.ModInfo.SERVER_PROXY)
	public static IProxy proxy;

	@Mod.EventHandler
	public void onServerStart(FMLServerStartingEvent event)
	{
		proxy.onServerStart(event);
	}

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
