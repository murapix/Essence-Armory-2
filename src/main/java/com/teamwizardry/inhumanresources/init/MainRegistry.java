package com.teamwizardry.inhumanresources.init;

import com.teamwizardry.inhumanresources.common.event.EventHandler;
import com.teamwizardry.inhumanresources.common.utils.helper.TextHelper;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class MainRegistry
{
	public static void register()
	{
		/**
		 * Items
		 */

		/**
		 * Blocks and tile entities
		 */

		/**
		 * Other
		 */

		MinecraftForge.EVENT_BUS.register(EventHandler.INSTANCE);
	}
}
