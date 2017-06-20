package com.teamwizardry.inhumanresources.common.items;

import com.teamwizardry.inhumanresources.common.entity.EntityRedstoneArrow;
import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.librarianlib.features.base.item.ItemModArrow;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemRedstoneArrow extends ItemModArrow
{
	public ItemRedstoneArrow(String name, String... variants)
	{
		super(name, variants);
		setCreativeTab(Util.tabEssence);
	}

	@Override
	public EntityArrow generateArrowEntity(World world, ItemStack arrow, Vec3d dir, EntityLivingBase shooter)
	{
		return new EntityRedstoneArrow(world, shooter);
	}

	@Override
	public boolean isInfinite(ItemStack arrow, ItemStack bow, EntityPlayer player)
	{
		int enchant = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bow);
		return enchant > 0 && this instanceof ItemArrow;
	}
}
