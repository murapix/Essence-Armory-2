package escapee.essencearmory2.common.event;

import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import escapee.essencearmory2.common.entity.mobs.MobBase;

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
