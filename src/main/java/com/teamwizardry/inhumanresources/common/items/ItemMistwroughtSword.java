package com.teamwizardry.inhumanresources.common.items;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import com.teamwizardry.inhumanresources.InhumanResources;
import com.teamwizardry.inhumanresources.common.utils.IUpgradable;
import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;
import com.teamwizardry.inhumanresources.init.DamageRegistry;
import com.teamwizardry.inhumanresources.init.PotionRegistry;
import com.teamwizardry.librarianlib.features.base.item.ItemModSword;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ItemMistwroughtSword extends ItemModSword implements IUpgradable
{
	private static final String LEAP_STRIKE = "leap strike"; // Jump forwards, or towards the entity moused over
	private static final String LEAP_RANGE = "long jump"; // Longer max Leap Strike distance
	private static final String LEAP_SLAM = "leap slam"; // Leap Strike hits in an AoE
	private static final String BLOOD_RAGE = "blood rage"; // 100% crit chance for duration
	private static final String BLOOD_FURY = "blood fury"; // Gain movespeed during Blood Rage
	private static final String EVER_RAGE = "everlasting rage"; // Blood Rage duration increased

	private static final String VOID_DAMAGE = "voidfire"; // Voidfire: Crits gain 25% of base as unblockable damage
	private static final String STUN_HIT = "stunning blows"; // Crits stun for 1 sec
	private static final String ARMOR_PIERCE = "precise strikes"; // Precision: Crits ignore 50% of armor
	private static final String TIME_DAMAGE = "sunder"; // Crits give "Extra damage for 4 seconds, no crit multi"
	private static final String DAMAGE_OVERLAP = "break"; // Sunder effect stacks
	private static final String CRIT_TIME = "shatter"; // Sunder no longer removes crit multi

	private static final String LIFE_LINK = "life link"; // Crits give a Regeneration effect
	private static final String LIFE_WARD = "life ward"; // Life Link gives a Resistance effect too
	private static final String LIFE_TETHER = "life tether"; // Life Link Regeneration effect improved
	private static final String PRESSURE = "pressure"; // Crits push knock back nearby entities
	private static final String COMPRESSION = "compression"; // Larger knock back radius
	private static final String EXPULSION = "expulsion"; // Knock back slows as well
	
	private static final float RADIUS = 3;
	private static final float BIG_RADIUS = 5;

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
//			if (stack.getTagCompound().hasKey(ModInfo.MOD_ID))
//				return;
		}
		else itemTag = new NBTTagCompound();
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString(ACTIVE, BLOOD_RAGE);
		compound.setString(ACTIVE_UPGRADE, BLOOD_FURY);
		compound.setInteger(ACTIVE_COOLDOWN, 0);
		compound.setString(OFFENSIVE, VOID_DAMAGE);
		compound.setString(OFFENSIVE_UPGRADE, ARMOR_PIERCE);
		compound.setString(DEFENSIVE, LIFE_LINK);
		compound.setString(DEFENSIVE_UPGRADE, LIFE_WARD);
		itemTag.setTag(ModInfo.MOD_ID, compound);
		stack.setTagCompound(itemTag);
	}
	
	@Override
	public boolean runActive(EntityPlayer player, ItemStack weapon, ItemStack offhand, boolean runAsMainhand)
	{
		InhumanResources.logger.info((player.world.isRemote ? "Client" : "Server") + " running active");
		
		if (!runAsMainhand)
			return false;
		if (weapon.getItem() != this)
			return false;
		switch(getActive(weapon))
		{
			case LEAP_STRIKE:
				break;
			case BLOOD_RAGE:
				int amplifier = 0;
				int duration = 5;
				switch(getActiveUpgrade(weapon))
				{
					case BLOOD_FURY:
						amplifier = 1;
					case EVER_RAGE:
						duration = 10;
				}
				player.addPotionEffect(new PotionEffect(PotionRegistry.MAX_CRIT, duration * 20, amplifier));
				break;
		}
		return true;
	}
	
	@Override
	public void onAttackEntity(LivingHurtEvent event, EntityLivingBase attacker, EntityLivingBase target, ItemStack weapon, ItemStack offhand)
	{
		if (Util.isCritting(attacker))
		{
			float baseDamage = event.getAmount();
			switch (getOffensive(weapon))
			{
				case VOID_DAMAGE:
					target.hurtResistantTime = 0;
					target.attackEntityFrom(DamageRegistry.VOIDFIRE, baseDamage * 0.25F);
					switch (getOffensiveUpgrade(weapon))
					{
						case STUN_HIT:
							target.addPotionEffect(new PotionEffect(PotionRegistry.STUN, target instanceof EntityPlayer ? 10 : 20, 0));
							break;
						case ARMOR_PIERCE:
							target.hurtResistantTime = 0;
							target.attackEntityFrom(DamageSource.GENERIC.setDamageBypassesArmor(), baseDamage * 0.5F);
							event.setAmount(baseDamage * 0.5F);
							target.hurtResistantTime = 0;
							break;
					}
					break;
				case TIME_DAMAGE:
					PotionEffect effect = attacker.getActivePotionEffect(PotionRegistry.CRIT_BONUS);
					if (effect == null)
						attacker.addPotionEffect(new PotionEffect(PotionRegistry.CRIT_BONUS, 80, 0));
					switch (getOffensiveUpgrade(weapon))
					{
						case DAMAGE_OVERLAP:
							if (effect == null)
								break;
							if (effect.getAmplifier() < 4)
								attacker.addPotionEffect(new PotionEffect(PotionRegistry.CRIT_BONUS, 80, effect.getAmplifier() + 1));
							break;
						case CRIT_TIME:
							if (effect == null)
								break;
							event.setAmount(event.getAmount() * 1.5F);
							break;
					}
					break;
			}
			switch (getDefensive(weapon))
			{
				case LIFE_LINK:
					attacker.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 0));
					switch (getDefensiveUpgrade(weapon))
					{
						case LIFE_WARD:
							attacker.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 0));
							break;
						case LIFE_TETHER:
							attacker.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 2));
							break;
					}
					break;
				case PRESSURE:
					float radius = RADIUS;
					boolean slow = false;
					switch (getDefensiveUpgrade(weapon))
					{
						case COMPRESSION:
							radius = BIG_RADIUS;
							break;
						case EXPULSION:
							slow = true;
							break;
					}
					AxisAlignedBB axis = new AxisAlignedBB(attacker.posX, attacker.posY, attacker.posZ, attacker.posX, attacker.posY, attacker.posZ).grow(radius);
					List<Entity> entities = attacker.world.getEntitiesInAABBexcluding(attacker, axis, entity -> entity instanceof EntityLivingBase);
					for (Entity entity : entities)
					{
						Vec3d dir = attacker.getPositionVector().subtract(entity.getPositionVector()).normalize();
						((EntityLivingBase) entity).knockBack(null, radius, dir.x, dir.z);
						if (slow) ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 1));
					}
					break;
			}
		}
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
	
	@Override
	public int getActiveCooldown(String active, String activeUpgrade)
	{
		switch(active)
		{
			case LEAP_STRIKE:
				return 100;
			case BLOOD_RAGE:
				return 600;
		}
		return -1;
	}
	
	@Nonnull
	@Override
	public List<String> getOffensives()
	{
		return Arrays.asList(VOID_DAMAGE, TIME_DAMAGE);
	}

	@Nonnull
	@Override
	public List<String> getOffensiveUpgrades(String offensive)
	{
		switch (offensive)
		{
			case VOID_DAMAGE:
				return Arrays.asList(STUN_HIT, ARMOR_PIERCE);
			case TIME_DAMAGE:
				return Arrays.asList(DAMAGE_OVERLAP, CRIT_TIME);
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
