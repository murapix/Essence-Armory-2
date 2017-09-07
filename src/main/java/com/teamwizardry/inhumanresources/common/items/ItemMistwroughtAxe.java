package com.teamwizardry.inhumanresources.common.items;

import java.util.List;

import com.teamwizardry.inhumanresources.common.utils.IUpgradable;
import com.teamwizardry.librarianlib.features.base.item.ItemModTool;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ItemMistwroughtAxe extends ItemModTool implements IUpgradable
{
	public ItemMistwroughtAxe(String name, ToolMaterial toolMaterial)
	{
		super(name, toolMaterial, "axe");
	}

	@Override
	public void onAttackEntity(LivingHurtEvent event, EntityLivingBase attacker, EntityLivingBase target, ItemStack weapon, ItemStack offhand)
	{
		
	}

	@Override
	public boolean runActive(EntityPlayer player, ItemStack weapon, ItemStack offhand, boolean runAsMainhand)
	{
		return false;
	}

	@Override
	public List<String> getActives()
	{
		return null;
	}

	@Override
	public List<String> getActiveUpgrades(String active)
	{
		return null;
	}

	@Override
	public int getActiveCooldown(String active, String activeUpgrade)
	{
		return 0;
	}

	@Override
	public List<String> getOffensives()
	{
		return null;
	}

	@Override
	public List<String> getOffensiveUpgrades(String offensive)
	{
		return null;
	}

	@Override
	public List<String> getDefensives()
	{
		return null;
	}

	@Override
	public List<String> getDefensiveUpgrades(String defensive)
	{
		return null;
	}
}
