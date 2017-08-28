package com.teamwizardry.inhumanresources.common.items;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import com.teamwizardry.inhumanresources.common.utils.IUpgradable;
import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;
import com.teamwizardry.librarianlib.features.base.item.ItemModSword;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ItemMistwroughtClaw extends ItemModSword implements IUpgradable
{
	private static final String FURY_SWIPES = "fury swipes"; // Fury Swipes: Gain +1 damage every time you hit an enemy, as long as you hit it recently
	private static final String FRENZIED_STRIKES = "frenzied strikes"; // Weapon has increased attack speed
	private static final String RAGING_STRIKES = "raging strikes"; // Fury Swipes now keeps across enemies, and drops of if you haven't hit something recently
	private static final String SLICE_AND_DICE = "slice and dice"; // Slice and Dice: Hits deal a stacking damage over time
	private static final String BRUTAL_CUTS = "brutal cuts"; // Gain bonus hit damage against bleeding targets
	private static final String HAMSTRING = "hamstring"; // Slice and Dice also slows targets
	
	private static final String LEECH = "leech"; // Gain life on hit
	private static final String MORE_LEECH = "more leech"; // Gain more life on hit
	private static final String BLOOD_SHELL = "blood shell"; // Leeched life can overheal you for a temporary shield, proportional to your max hp
	private static final String DRAINING_CUTS = "draining cuts"; // Targets hit lose a small amount of damage
	private static final String WEAKENING_STRIKES = "weakening strikes"; // Targets lose even more damage per hit
	private static final String LETHARGY = "lethargy";// Targets lose attack speed while drained

	public ItemMistwroughtClaw(String name, ToolMaterial toolMaterial)
	{
		super(name, toolMaterial);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		NBTTagCompound itemTag;
		if (stack.hasTagCompound())
		{
			itemTag = stack.getTagCompound();
			if (stack.getTagCompound().hasKey(ModInfo.MOD_ID))
				return;
		}
		else itemTag = new NBTTagCompound();
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString(ACTIVE, "");
		compound.setString(ACTIVE_UPGRADE, "");
		compound.setInteger(ACTIVE_COOLDOWN, 0);
		compound.setString(OFFENSIVE, FURY_SWIPES);
		compound.setString(OFFENSIVE_UPGRADE, RAGING_STRIKES);
		compound.setString(DEFENSIVE, LEECH);
		compound.setString(DEFENSIVE_UPGRADE, MORE_LEECH);
		itemTag.setTag(ModInfo.MOD_ID, compound);
		stack.setTagCompound(itemTag);
	}
	
	@Override
	public void onAttackEntity(LivingHurtEvent event, EntityLivingBase attacker, EntityLivingBase target, ItemStack weapon, ItemStack offhand)
	{
		float baseDamage = event.getAmount();
		switch (getOffensive(weapon))
		{
			case FURY_SWIPES:
				switch (getOffensiveUpgrade(weapon))
				{
					case FRENZIED_STRIKES:
						break;
					case RAGING_STRIKES:
						break;
				}
				break;
			case SLICE_AND_DICE:
				switch (getOffensiveUpgrade(weapon))
				{
					case BRUTAL_CUTS:
						event.setAmount(baseDamage * 0.5F);
						break;
					case HAMSTRING:
						break;
				}
				break;
		}
		switch (getDefensive(weapon))
		{
			case LEECH:
				switch (getDefensiveUpgrade(weapon))
				{
					case MORE_LEECH:
						break;
					case BLOOD_SHELL:
						break;
				}
				break;
			case DRAINING_CUTS:
				switch (getDefensiveUpgrade(weapon))
				{
					case WEAKENING_STRIKES:
						break;
					case LETHARGY:
						break;
				}
				break;
		}
	}

	@Override
	public boolean runActive(EntityPlayer player, ItemStack weapon, ItemStack offhand, boolean runAsMainhand)
	{
		return false;
	}

	@Nonnull
	@Override
	public List<String> getActives()
	{
		return Arrays.asList("", "");
	}

	@Nonnull
	@Override
	public List<String> getActiveUpgrades(String active)
	{
		switch (active)
		{
			case "":
				return Arrays.asList("", "");
			case " ":
				return Arrays.asList("", "");
		}
		return Arrays.asList(new String[] {});
	}

	@Override
	public int getActiveCooldown(String active, String activeUpgrade)
	{
		return 0;
	}

	@Nonnull
	@Override
	public List<String> getOffensives()
	{
		return Arrays.asList(FURY_SWIPES, SLICE_AND_DICE);
	}

	@Nonnull
	@Override
	public List<String> getOffensiveUpgrades(String offensive)
	{
		switch (offensive)
		{
			case FURY_SWIPES:
				return Arrays.asList(FRENZIED_STRIKES, RAGING_STRIKES);
			case SLICE_AND_DICE:
				return Arrays.asList(BRUTAL_CUTS, HAMSTRING);
		}
		return Arrays.asList(new String[] {});
	}

	@Nonnull
	@Override
	public List<String> getDefensives()
	{
		return Arrays.asList(LEECH, DRAINING_CUTS);
	}

	@Nonnull
	@Override
	public List<String> getDefensiveUpgrades(String defensive)
	{
		switch (defensive)
		{
			case LEECH:
				return Arrays.asList(MORE_LEECH, BLOOD_SHELL);
			case DRAINING_CUTS:
				return Arrays.asList(WEAKENING_STRIKES, LETHARGY);
		}
		return Arrays.asList(new String[] {});
	}
}
