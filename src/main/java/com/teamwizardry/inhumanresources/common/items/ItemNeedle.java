package com.teamwizardry.inhumanresources.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.inhumanresources.init.PotionRegistry;
import com.teamwizardry.librarianlib.common.base.item.ItemMod;

public class ItemNeedle extends ItemMod
{
	public ItemNeedle(String name, String... variants)
	{
		super(name, variants);
		setCreativeTab(Util.tabEssence);
	}
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (stack.getItemDamage() == 1 && entity instanceof EntityLivingBase) // Redstone needle
		{
			EntityLivingBase target = (EntityLivingBase) entity;
			Potion redstoneNeedle = PotionRegistry.REDSTONE_NEEDLE;
			PotionEffect prevEffect = target.getActivePotionEffect(redstoneNeedle);
			if (prevEffect != null)
			{
				int prevAmplifier = prevEffect.getAmplifier();
				if (prevAmplifier < 4)
					target.addPotionEffect(new PotionEffect(redstoneNeedle, 600, prevAmplifier + 1));
				else
					target.addPotionEffect(new PotionEffect(redstoneNeedle, 600, prevAmplifier));
			}
			target.addPotionEffect(new PotionEffect(redstoneNeedle, 600, 0));
			player.inventory.decrStackSize(player.inventory.getSlotFor(stack), 1);
			return true;
		}
		return false;
	}
}
