package com.teamwizardry.inhumanresources.common.utils;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;

public interface IUpgradable
{
	public static final String ACTIVE = "active";
	public static final String ACTIVE_UPGRADE = "active upgrade";
	public static final String OFFENSIVE = "offensive";
	public static final String OFFENSIVE_UPGRADE = "offenseive upgrade";
	public static final String DEFENSIVE = "defensive";
	public static final String DEFENSIVE_UPGRADE = "defensive upgrade";
	
	@Nonnull
	public String getActive(ItemStack item);
	@Nonnull
	public List<String> getActives();
	@Nonnull
	public String getActiveUpgrade(ItemStack item);
	@Nonnull
	public List<String> getActiveUpgrades(String active);
	@Nonnull
	public String getOffensive(ItemStack item);
	@Nonnull
	public List<String> getOffensives();
	@Nonnull
	public String getOffensiveUpgrade(ItemStack item);
	@Nonnull
	public List<String> getOffensiveUpgrades(String offensive);
	@Nonnull
	public String getDefensive(ItemStack item);
	@Nonnull
	public List<String> getDefensives();
	@Nonnull
	public String getDefensiveUpgrade(ItemStack item);
	@Nonnull
	public List<String> getDefensiveUpgrades(String defensive);
}
