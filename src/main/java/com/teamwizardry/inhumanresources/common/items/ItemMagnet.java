package com.teamwizardry.inhumanresources.common.items;

import java.util.List;

import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.librarianlib.features.base.item.ItemMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemMagnet extends ItemMod
{
	public enum EnumMagnetType implements IStringSerializable
	{
		MINE,
		MELEE,
		RANGED,
		ALL;

		@Override
		public String getName()
		{
			return name().toLowerCase();
		}
	}
	
	public ItemMagnet(String name)
	{
		super(name, Util.enumNames(EnumMagnetType.class, name));
	}
	
	public static void magnetItems(World world, EntityPlayer player, List<ItemStack> items)
	{
		for (ItemStack item : items)
		{
			ItemHandlerHelper.giveItemToPlayer(player, item);
		}
	}
}
