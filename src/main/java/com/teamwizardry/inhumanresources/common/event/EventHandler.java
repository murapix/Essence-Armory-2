package com.teamwizardry.inhumanresources.common.event;

import com.teamwizardry.inhumanresources.common.entity.mobs.MobBase;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
}
