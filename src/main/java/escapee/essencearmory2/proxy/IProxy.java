package escapee.essencearmory2.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * Created by SirShadow on 14.8.2016.
 */
public interface IProxy
{
    void onServerStart(FMLServerStartingEvent event);

    void onPreInit(FMLPreInitializationEvent event);

    void onInit(FMLInitializationEvent event);

    void onPostInit(FMLPostInitializationEvent event);
}
