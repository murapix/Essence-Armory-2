package com.teamwizardry.inhumanresources.common.event;

import com.teamwizardry.inhumanresources.common.entity.mobs.MobBase;
import com.teamwizardry.inhumanresources.common.utils.IUpgradable;
import com.teamwizardry.inhumanresources.network.PacketUseActive;
import com.teamwizardry.inhumanresources.proxy.ClientProxy;
import com.teamwizardry.librarianlib.features.network.PacketHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventHandler
{
	public static EventHandler INSTANCE = new EventHandler();
	
	@SubscribeEvent
	public void onEntityRightClicked(EntityInteract event)
	{
		if (event.getTarget() instanceof MobBase)
		{
			MobBase mob = (MobBase) event.getTarget();
			if (mob.getOwnerId() == null) mob.setOwner(event.getEntityPlayer());
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(receiveCanceled=true)
	public void onKeyPress(KeyInputEvent event)
	{
		if (ClientProxy.mistwroughtActive.isPressed())
		{
			EntityPlayer player = Minecraft.getMinecraft().player;
			ItemStack mainhand = player.getHeldItemMainhand();
			ItemStack offhand = player.getHeldItemOffhand();
			if (mainhand == null && offhand == null)
				return;
			if (offhand.getItem() instanceof IUpgradable)
			{
				if (((IUpgradable) offhand.getItem()).runActive(player, mainhand, offhand, false))
					PacketHandler.NETWORK.sendToServer(new PacketUseActive());
				else if (((IUpgradable) mainhand.getItem()).runActive(player, mainhand, offhand, true))
					PacketHandler.NETWORK.sendToServer(new PacketUseActive());
			}
			else if (mainhand.getItem() instanceof IUpgradable)
				if (((IUpgradable) mainhand.getItem()).runActive(player, mainhand, offhand, true))
					PacketHandler.NETWORK.sendToServer(new PacketUseActive());
		}
	}
	
	@SubscribeEvent
	public void onEntityAttacked(LivingHurtEvent event)
	{
		Entity sourceEntity = event.getSource().getTrueSource();
		if (sourceEntity instanceof EntityLivingBase)
		{
			EntityLivingBase target = event.getEntityLiving();
			if (target == null)
				return;
			EntityLivingBase attacker = (EntityLivingBase) sourceEntity;
			ItemStack weapon = attacker.getHeldItemMainhand();
			ItemStack offhand = attacker.getHeldItemOffhand();
			if (offhand != null && offhand.getItem() instanceof IUpgradable)
				((IUpgradable) weapon.getItem()).onAttackEntity(event, attacker, target, weapon, offhand);
			if (weapon != null && weapon.getItem() instanceof IUpgradable)
				((IUpgradable) weapon.getItem()).onAttackEntity(event, attacker, target, weapon, offhand);
		}
	}
}
