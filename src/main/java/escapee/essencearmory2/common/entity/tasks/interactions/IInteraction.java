package escapee.essencearmory2.common.entity.tasks.interactions;

import escapee.essencearmory2.common.entity.mobs.MobBase;

public interface IInteraction
{
	public boolean isMobValid(MobBase mob);
	
	public boolean shouldCycle();
	
	public void setShouldCycle(boolean shouldCycle);
}