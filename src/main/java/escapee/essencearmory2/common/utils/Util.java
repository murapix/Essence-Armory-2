package escapee.essencearmory2.common.utils;

import java.util.UUID;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import escapee.essencearmory2.init.ItemRegistry;
import escapee.essencearmory2.lib.ModInfo;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class Util
{
	public static CreativeTabs tabEssence = new CreativeTabs(ModInfo.MOD_ID)
	{
		@Override
		public String getTabLabel()
		{
			return ModInfo.MOD_ID;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return ItemRegistry.itemResearchLog;
		}
	};

	public static EntityPlayer getPlayerFromUUID(UUID uuid)
	{
		if (uuid == null) return null;
		
		Entity entity = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityFromUuid(uuid);
		if (entity instanceof EntityPlayer) return (EntityPlayer) entity;
		
		return null;
	}
}
