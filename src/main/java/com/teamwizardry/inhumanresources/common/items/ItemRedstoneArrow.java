package com.teamwizardry.inhumanresources.common.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;
import com.teamwizardry.inhumanresources.common.entity.EntityRedstoneArrow;
import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.librarianlib.common.base.item.ItemModArrow;

public class ItemRedstoneArrow extends ItemModArrow
{
	public ItemRedstoneArrow(String name, String... variants)
	{
		super(name, variants);
		setCreativeTab(Util.tabEssence);
	}

	public EntityArrow createArrow(World world, EntityLivingBase shooter)
	{
		return new EntityRedstoneArrow(world, shooter);
	}
}
