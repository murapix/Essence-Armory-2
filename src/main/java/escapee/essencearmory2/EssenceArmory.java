
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
@Mod(modid = LibMain.ModInfo.mod_id,name = LibMain.ModInfo.mod_name,version = LibMain.ModInfo.mod_version,guiFactory = LibMain.ModInfo.gui_factory,useMetadata = true)
public class EssenceArmory
{
    @Mod.Instance(LibMain.ModInfo.mod_id)
    public static EssenceArmory instance;

    @SidedProxy(clientSide = LibMain.ModInfo.client_proxy_class,serverSide = LibMain.ModInfo.server_proxy_class)
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
