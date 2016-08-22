package escapee.essencearmory2;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import escapee.essencearmory2.lib.ModInfo;
import escapee.essencearmory2.proxy.IProxy;

/**
 * Created by SirShadow on 14.8.2016.
 */
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, guiFactory = ModInfo.GUI_FACTORY, useMetadata = true)
public class EssenceArmory
{
	@Mod.Instance(ModInfo.MOD_ID)
	public static EssenceArmory instance;

	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.SERVER_PROXY)
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
