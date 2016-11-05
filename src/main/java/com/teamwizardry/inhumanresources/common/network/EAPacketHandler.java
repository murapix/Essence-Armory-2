package com.teamwizardry.inhumanresources.common.network;

import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class EAPacketHandler
{
	public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);

	private static int id = 0;

	public static void register()
	{
		registerMessage(KnowledgeUpdateMessage.class,Side.CLIENT);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void registerMessage(Class clazz, Side side)
	{
		INSTANCE.registerMessage(clazz, clazz, id++, side);
	}
}
