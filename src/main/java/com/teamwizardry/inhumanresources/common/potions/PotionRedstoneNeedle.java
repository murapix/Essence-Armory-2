package com.teamwizardry.inhumanresources.common.potions;

import java.util.UUID;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.teamwizardry.inhumanresources.init.PotionRegistry;

public class PotionRedstoneNeedle extends PotionIhR
{
	public PotionRedstoneNeedle(String name, boolean badEffect, int color, int iconIndex)
	{
		super(name, badEffect, color, iconIndex);
		registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, UUID.randomUUID().toString(), -0.2, 2);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void performEffect(EntityLivingBase entity, int amplifier)
	{
		if (amplifier >= 4 && entity instanceof EntityCreature)
		{
			((EntityCreature) entity).setNoAI(true);
			if (entity instanceof EntityCreeper)
				((EntityCreeper) entity).setCreeperState(-1);
		}
	}

	@Override
	public boolean isReady(int duration, int amplifier)
	{
		return true;
	}

	@SubscribeEvent
	public void onEntityJump(LivingJumpEvent event)
	{
		PotionEffect effect = event.getEntityLiving().getActivePotionEffect(PotionRegistry.REDSTONE_NEEDLE);
		if (effect == null)
			return;
		if (effect.getAmplifier() >= 4)
		{
			event.getEntityLiving().motionX = 0;
			event.getEntityLiving().motionY = 0;
			event.getEntityLiving().motionZ = 0;
			event.getEntityLiving().isAirBorne = false;
		}
	}
}
