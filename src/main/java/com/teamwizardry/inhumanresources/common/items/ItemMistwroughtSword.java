package com.teamwizardry.inhumanresources.common.items;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import com.teamwizardry.inhumanresources.common.utils.IUpgradable;
import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;
import com.teamwizardry.inhumanresources.init.DamageRegistry;
import com.teamwizardry.librarianlib.features.base.item.ItemModSword;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemMistwroughtSword extends ItemModSword implements IUpgradable
{
	private static final String LEAP_STRIKE = "leap strike"; // Jump forwards, or towards the entity moused over
	private static final String LEAP_RANGE = "long jump"; // Longer max Leap Strike distance
	private static final String LEAP_SLAM = "leap slam"; // Leap Strike hits in an AoE
	private static final String BLOOD_RAGE = "blood rage"; // 100% crit chance for duration
	private static final String BLOOD_FURY = "blood fury"; // Gain movespeed during Blood Rage
	private static final String EVER_RAGE = "everlasting rage"; // Blood Rage duration increased

	private static final String VOIDFIRE = "voidfire"; // Crits gain 25% of base as unblockable damage
	private static final String STUN_HIT = "stunning blows"; // Crits stun for 1 sec
	private static final String PRECISION = "precise strikes"; // Crits ignore 50% of armor
	private static final String SUNDER = "sunder"; // Crits give "Extra damage for 4 seconds, no crit multi"
	private static final String BREAK = "break"; // Sunder effect stacks
	private static final String SHATTER = "shatter"; // Sunder no longer removes crit multi

	private static final String LIFE_LINK = "life link"; // Crits give a Regeneration effect
	private static final String LIFE_WARD = "life ward"; // Life Link gives a Resistance effect too
	private static final String LIFE_TETHER = "life tether"; // Life Link Regeneration effect improved
	private static final String PRESSURE = "pressure"; // Crits push knock back nearby entities
	private static final String COMPRESSION = "compression"; // Larger knock back radius
	private static final String EXPULSION = "expulsion"; // Knock back slows as well

	public ItemMistwroughtSword(String name, ToolMaterial toolMaterial)
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
		compound.setString(OFFENSIVE, VOIDFIRE);
		compound.setString(OFFENSIVE_UPGRADE, PRECISION);
		compound.setString(DEFENSIVE, LIFE_LINK);
		compound.setString(DEFENSIVE_UPGRADE, LIFE_WARD);
		itemTag.setTag(ModInfo.MOD_ID, compound);
		stack.setTagCompound(itemTag);
	}
	
	@SubscribeEvent
	public void onEntityAttacked(LivingHurtEvent event)
	{
		Entity sourceEntity = event.getSource().getTrueSource();
		if (sourceEntity instanceof EntityLivingBase)
		{
			EntityLivingBase target = event.getEntityLiving();
			EntityLivingBase attacker = (EntityLivingBase) sourceEntity;
			ItemStack weapon = attacker.getHeldItemMainhand();
			if (weapon.getItem() instanceof ItemMistwroughtSword)
			{
				if (isCritting(attacker))
				{
					float baseDamage = event.getAmount();
					switch (getOffensive(weapon))
					{
						case VOIDFIRE:
							target.hurtResistantTime = 0;
							target.attackEntityFrom(DamageRegistry.VOIDFIRE, baseDamage * 0.25F);
							switch (getOffensiveUpgrade(weapon))
							{
								case STUN_HIT:
									break;
								case PRECISION:
									target.hurtResistantTime = 0;
									target.attackEntityFrom(DamageSource.GENERIC.setDamageBypassesArmor(), baseDamage * 0.5F);
									event.setAmount(baseDamage * 0.5F);
									target.hurtResistantTime = 0;
									break;
							}
							break;
						case SUNDER:
							switch (getOffensiveUpgrade(weapon))
							{
								case BREAK:
									break;
								case SHATTER:
									break;
							}
							break;
					}
					/*switch (getDefensive(weapon))
					{
						case LIFE_LINK:
							attacker.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));
							switch (getDefensiveUpgrade(weapon))
							{
								case LIFE_WARD:
									attacker.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 0));
									break;
								case LIFE_TETHER:
									attacker.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 3));
									break;
							}
							break;
						case PRESSURE:
							switch (getDefensiveUpgrade(weapon))
							{
								case COMPRESSION:
									break;
								case EXPULSION:
									break;
							}
							break;
					}*/
				}
			}
		}
	}

	private boolean isCritting(EntityLivingBase entity)
	{
		if (entity.fallDistance <= 0) return false;
		if (entity.onGround) return false;
		if (entity.isOnLadder()) return false;
		if (entity.isInWater()) return false;
		if (entity.isPotionActive(MobEffects.BLINDNESS)) return false;
		return !entity.isRiding();
	}

	@Nonnull
	@Override
	public List<String> getActives()
	{
		return Arrays.asList(LEAP_STRIKE, BLOOD_RAGE);
	}

	@Nonnull
	@Override
	public List<String> getActiveUpgrades(String active)
	{
		switch (active)
		{
			case LEAP_STRIKE:
				return Arrays.asList(LEAP_RANGE, LEAP_SLAM);
			case BLOOD_RAGE:
				return Arrays.asList(BLOOD_FURY, EVER_RAGE);
		}
		return Arrays.asList(new String[] {});
	}
	
	@Nonnull
	@Override
	public List<String> getOffensives()
	{
		return Arrays.asList(VOIDFIRE, SUNDER);
	}

	@Nonnull
	@Override
	public List<String> getOffensiveUpgrades(String offensive)
	{
		switch (offensive)
		{
			case VOIDFIRE:
				return Arrays.asList(STUN_HIT, PRECISION);
			case SUNDER:
				return Arrays.asList(BREAK, SHATTER);
		}
		return Arrays.asList(new String[] {});
	}

	@Nonnull
	@Override
	public List<String> getDefensives()
	{
		return Arrays.asList(LIFE_LINK, PRESSURE);
	}

	@Nonnull
	@Override
	public List<String> getDefensiveUpgrades(String defensive)
	{
		switch (defensive)
		{
			case LIFE_LINK:
				return Arrays.asList(LIFE_WARD, LIFE_TETHER);
			case PRESSURE:
				return Arrays.asList(COMPRESSION, EXPULSION);
		}
		return Arrays.asList(new String[] {});
	}
}
