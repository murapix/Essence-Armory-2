package com.teamwizardry.inhumanresources.common.utils;

import java.util.UUID;

import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;
import com.teamwizardry.inhumanresources.init.ItemRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
		public ItemStack getTabIconItem()
		{
			return new ItemStack(ItemRegistry.itemResearchLog);
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
