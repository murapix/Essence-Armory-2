package com.teamwizardry.inhumanresources.common.entity.tasks.interactions;

import com.teamwizardry.inhumanresources.common.entity.mobs.MobBase;

public interface IInteraction
{
	public boolean isMobValid(MobBase mob);
	
	public boolean shouldCycle();
	
	public void setShouldCycle(boolean shouldCycle);
}