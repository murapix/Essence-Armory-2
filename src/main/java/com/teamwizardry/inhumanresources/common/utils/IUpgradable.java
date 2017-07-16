package com.teamwizardry.inhumanresources.common.utils;

import java.util.List;

import javax.annotation.Nonnull;

import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IUpgradable
{
	public static final String ACTIVE = "active";
	public static final String ACTIVE_UPGRADE = "active upgrade";
	public static final String OFFENSIVE = "offensive";
	public static final String OFFENSIVE_UPGRADE = "offenseive upgrade";
	public static final String DEFENSIVE = "defensive";
	public static final String DEFENSIVE_UPGRADE = "defensive upgrade";
	
	@Nonnull
	default public String getActive(ItemStack item)
	{
		NBTTagCompound compound = item.getSubCompound(ModInfo.MOD_ID);
		return compound == null ? "" : compound.getString(ACTIVE);
	}
	
	@Nonnull
	default public String getActiveUpgrade(ItemStack item)
	{
		NBTTagCompound compound = item.getSubCompound(ModInfo.MOD_ID);
		return compound == null ? "" : compound.getString(ACTIVE_UPGRADE);
	}
	
	@Nonnull
	default public String getOffensive(ItemStack item)
	{
		NBTTagCompound compound = item.getSubCompound(ModInfo.MOD_ID);
		return compound == null ? "" : compound.getString(OFFENSIVE);
	}
	
	@Nonnull
	default public String getOffensiveUpgrade(ItemStack item)
	{
		NBTTagCompound compound = item.getSubCompound(ModInfo.MOD_ID);
		return compound == null ? "" : compound.getString(OFFENSIVE_UPGRADE);
	}
	
	@Nonnull
	default public String getDefensive(ItemStack item)
	{
		NBTTagCompound compound = item.getSubCompound(ModInfo.MOD_ID);
		return compound == null ? "" : compound.getString(DEFENSIVE);
	}
	
	@Nonnull
	default public String getDefensiveUpgrade(ItemStack item)
	{
		NBTTagCompound compound = item.getSubCompound(ModInfo.MOD_ID);
		return compound == null ? "" : compound.getString(DEFENSIVE_UPGRADE);
	}
	
	@Nonnull
	public List<String> getActives();
	
	@Nonnull
	public List<String> getActiveUpgrades(String active);
	
	@Nonnull
	public List<String> getOffensives();
	
	@Nonnull
	public List<String> getOffensiveUpgrades(String offensive);
	
	@Nonnull
	public List<String> getDefensives();
	
	@Nonnull
	public List<String> getDefensiveUpgrades(String defensive);
}
