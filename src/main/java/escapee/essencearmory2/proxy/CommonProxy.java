package escapee.essencearmory2.proxy;

import escapee.essencearmory2.EssenceArmory;
import escapee.essencearmory2.MainRegistry;
import escapee.essencearmory2.client.GuiHandler;
import escapee.essencearmory2.common.capability.EACapabilityManeger;
import escapee.essencearmory2.common.network.EAPacketHandler;
import escapee.essencearmory2.common.upgrade.UpgradeRegistry;
import escapee.essencearmory2.common.utils.handler.ConfigHandler;
import escapee.essencearmory2.lib.LibMain;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by SirShadow on 14.8.2016.
 */
public abstract class CommonProxy implements IProxy
{
    @Override
    public void onServerStart(FMLServerStartingEvent event)
    {

    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event)
    {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        eventRegistry();
        MainRegistry.register();
        EAPacketHandler.register();
        EACapabilityManeger.registerCapability();
        UpgradeRegistry.init();
        LibMain.LibKnowledge.init();
    }

    @Override
    public void onInit(FMLInitializationEvent event)
    {
        register();
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {

    }

    private void eventRegistry()
    {
        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
        MinecraftForge.EVENT_BUS.register(new EACapabilityManeger());
    }

    private void register()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(EssenceArmory.instance,new GuiHandler());
    }
}
