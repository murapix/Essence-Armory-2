package com.teamwizardry.inhumanresources.common.potions;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.teamwizardry.inhumanresources.init.PotionRegistry;

public class PotionStun extends PotionIhR
{
	public PotionStun(String name, boolean badEffect, int color, int iconIndex)
	{
		super(name, badEffect, color, iconIndex);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void performEffect(EntityLivingBase entity, int amplifier)
	{
		if (entity instanceof EntityCreature)
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

	@Override
	public void removeAttributesModifiersFromEntity(EntityLivingBase entity, AbstractAttributeMap attributeMap, int amplifier)
	{
		if (entity instanceof EntityCreature)
			((EntityLiving) entity).setNoAI(false);
	}

	@SubscribeEvent
	public void onEntityJump(LivingJumpEvent event)
	{
		PotionEffect effect = event.getEntityLiving().getActivePotionEffect(PotionRegistry.STUN);
		if (effect == null) return;
		EntityLivingBase living = event.getEntityLiving();
		living.motionX = 0;
		living.motionY = -1;
		living.motionZ = 0;
		living.isAirBorne = false;
		living.setPosition(living.prevPosX, living.prevPosY, living.prevPosZ);
	}

	@SubscribeEvent
	public void onLivingTick(LivingUpdateEvent event)
	{
		PotionEffect effect = event.getEntityLiving().getActivePotionEffect(PotionRegistry.STUN);
		if (effect == null) return;
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if (player.capabilities.isFlying && !player.isCreative())
				player.capabilities.isFlying = false;
		}
	}

	@SubscribeEvent
	public void onLivingHurt(LivingAttackEvent event)
	{
		if (event.getSource() == null) return;
		if (event.getSource().getTrueSource() == null) return;
		if (event.getSource().getTrueSource() instanceof EntityLivingBase)
		{
			PotionEffect effect = ((EntityLivingBase) event.getSource().getTrueSource()).getActivePotionEffect(PotionRegistry.STUN);
			if (effect == null) return;
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onItemUse(LivingEntityUseItemEvent.Start event)
	{
		stopItemUse(event);
	}

	@SubscribeEvent
	public void onItemUse(LivingEntityUseItemEvent.Tick event)
	{
		stopItemUse(event);
	}

	private void stopItemUse(LivingEntityUseItemEvent event)
	{
		PotionEffect effect = event.getEntityLiving().getActivePotionEffect(PotionRegistry.STUN);
		if (effect == null) return;
		event.setCanceled(true);
	}
}
