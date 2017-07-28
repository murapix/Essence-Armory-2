package com.teamwizardry.inhumanresources.common.utils;

import java.util.List;

import javax.annotation.Nonnull;

import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface IUpgradable
{
	public static final String ACTIVE = "active";
	public static final String ACTIVE_UPGRADE = "active upgrade";
	public static final String ACTIVE_COOLDOWN = "active cooldown";
	public static final String OFFENSIVE = "offensive";
	public static final String OFFENSIVE_UPGRADE = "offenseive upgrade";
	public static final String DEFENSIVE = "defensive";
	public static final String DEFENSIVE_UPGRADE = "defensive upgrade";
	
	/***
	 * Direct method call from {@link LivingHurtEvent} after checks for valid event members. Offhand item is called first
	 * @param event The {@link LivingHurtEvent} that was caught
	 * @param attacker The {@link EntityLivingBase} that performed the attack
	 * @param target The {@link EntityLivingBase} that 
	 * @param weapon The {@link ItemStack} used to perform the attack
	 * @param offhand The {@link ItemStack} held in the attacker's offhand
	 */
	public void onAttackEntity(LivingHurtEvent event, EntityLivingBase attacker, EntityLivingBase target, ItemStack weapon, ItemStack offhand);
	
	/**
	 * Method called to use the ACTIVE ability of the weapon/offhand. Offhand item is called first, then if it returns false, the Mainhand item is called
	 * @param player The player using the active ability
	 * @param weapon The item held in the player's main hand
	 * @param offhand The item held in the player's offhand
	 * @param runAsMainhand TRUE if the item is being run from the hand, FALSE from the offhand
	 * @return TRUE if the item was in the right slot, FALSE if it was not
	 */
	public boolean runActive(EntityPlayer player, ItemStack weapon, ItemStack offhand, boolean runAsMainhand);
	
	/**
	 * Determines from which hand the item's stats should be usable. Set to NULL to signify both
	 * @return MAIN_HAND for the main hand, OFF_HAND for the off hand, and NULL for both
	 */
	default public EnumHand getUsableHand()
	{
		return EnumHand.MAIN_HAND;
	}
	
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
	public int getActiveCooldown(String active, String activeUpgrade);
	
	@Nonnull
	public List<String> getOffensives();
	
	@Nonnull
	public List<String> getOffensiveUpgrades(String offensive);
	
	@Nonnull
	public List<String> getDefensives();
	
	@Nonnull
	public List<String> getDefensiveUpgrades(String defensive);

}
