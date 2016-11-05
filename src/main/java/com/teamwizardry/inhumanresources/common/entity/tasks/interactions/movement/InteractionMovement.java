package com.teamwizardry.inhumanresources.common.entity.tasks.interactions.movement;

import com.teamwizardry.inhumanresources.common.entity.tasks.interactions.IInteraction;
import net.minecraft.util.math.BlockPos;

public interface InteractionMovement extends IInteraction
{
	public BlockPos getCurrentPos();
	
	public BlockPos cyclePos();
}
