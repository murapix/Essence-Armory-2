package escapee.essencearmory2.common.utils;

import escapee.essencearmory2.MainRegistry;
import escapee.essencearmory2.lib.LibMain;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class Util
{
	public static CreativeTabs tabEssence = new CreativeTabs(LibMain.ModInfo.MOD_ID)
	{
		@Override
		public String getTabLabel()
		{
			return LibMain.ModInfo.MOD_ID;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return MainRegistry.itemRegistry.itemInfusedIngot;
		}
	};

}
