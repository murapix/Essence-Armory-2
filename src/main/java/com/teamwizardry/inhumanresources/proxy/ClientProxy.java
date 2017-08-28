package com.teamwizardry.inhumanresources.proxy;

import com.teamwizardry.inhumanresources.init.ModEntityRegistry;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
//	public static KeyBinding mistwroughtActive;
	
	@Override
    public void onPreInit(FMLPreInitializationEvent event) {
        super.onPreInit(event);
    }

    @Override
    public void onInit(FMLInitializationEvent event) {
        super.onInit(event);
        ModEntityRegistry.initRenderers();
        
//        mistwroughtActive = new KeyBinding("key.mistwrought.desc", Keyboard.KEY_R, "key.inhumanresources.category");
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {
        super.onPostInit(event);
    }
}
