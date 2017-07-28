package com.teamwizardry.inhumanresources.common.potions;

import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.inhumanresources.init.PotionRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionMaxCrit extends PotionIhR
{
	public PotionMaxCrit(String name, boolean badEffect, int color, int iconIndex)
	{
		super(name, badEffect, color, iconIndex);
		registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "2837dd8e-5df5-4f09-b872-f95c196e4092", 0.2, 2);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void applyAttributesModifiersToEntity(EntityLivingBase entity, AbstractAttributeMap map, int amplifier)
	{
		if (amplifier > 0)
			super.applyAttributesModifiersToEntity(entity, map, amplifier - 1);
	}
		
	@SubscribeEvent
	public void onLivingHurtStart(LivingHurtEvent event)
	{
		Entity sourceEntity = event.getSource().getTrueSource();
		if (sourceEntity instanceof EntityLivingBase)
		{
			EntityLivingBase attacker = (EntityLivingBase) sourceEntity;
			PotionEffect effect = attacker.getActivePotionEffect(PotionRegistry.MAX_CRIT);
			if (effect != null && !Util.isCritting(attacker))
				event.setAmount(event.getAmount() * 1.5F);
		}
	}
}
