package com.teamwizardry.inhumanresources.common.items;

import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.librarianlib.features.base.item.ItemMod;

public class ItemComponent extends ItemMod
{
	public ItemComponent(String name, String... variants)
	{
		super(name, variants);
		setCreativeTab(Util.tabEssence);
	}
}
