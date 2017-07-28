package com.teamwizardry.inhumanresources.common.items;

import java.util.List;

import com.teamwizardry.inhumanresources.common.utils.IUpgradable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ItemMistwroughtBow extends ItemBow implements IUpgradable
{

	@Override
	public void onAttackEntity(LivingHurtEvent event, EntityLivingBase attacker, EntityLivingBase target, ItemStack weapon, ItemStack offhand)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public boolean runActive(EntityPlayer player, ItemStack weapon, ItemStack offhand, boolean runAsMainhand)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getActives()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getActiveUpgrades(String active)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getActiveCooldown(String active, String activeUpgrade)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getOffensives()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getOffensiveUpgrades(String offensive)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDefensives()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDefensiveUpgrades(String defensive)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
