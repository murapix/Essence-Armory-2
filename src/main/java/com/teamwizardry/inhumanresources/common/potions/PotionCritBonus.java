package com.teamwizardry.inhumanresources.common.potions;

import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.inhumanresources.init.PotionRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionCritBonus extends PotionIhR
{
	public PotionCritBonus(String name, boolean badEffect, int color, int iconIndex)
	{
		super(name, badEffect, color, iconIndex);
		registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "51137cdd-6efc-4cf7-81c8-42f25050d131", 2.5, 0);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public boolean isReady(int duration, int amplifier)
	{
		return true;
	}
	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		Entity sourceEntity = event.getSource().getTrueSource();
		if (sourceEntity instanceof EntityLivingBase)
		{
			EntityLivingBase attacker = (EntityLivingBase) sourceEntity;
			PotionEffect effect = attacker.getActivePotionEffect(PotionRegistry.CRIT_BONUS);
			if (effect != null && Util.isCritting(attacker))
				event.setAmount(event.getAmount() / 1.5F);
		}
	}
}
