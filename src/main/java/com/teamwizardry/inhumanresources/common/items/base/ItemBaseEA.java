package com.teamwizardry.inhumanresources.common.items.base;

import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.librarianlib.common.base.item.ItemMod;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class ItemBaseEA extends ItemMod
{
	public ItemBaseEA(String name)
	{
		super(name);
		setCreativeTab(Util.tabEssence);
	}
}
