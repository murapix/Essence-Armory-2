package com.teamwizardry.inhumanresources.common.entity.tasks.interactions.movement;

import java.util.LinkedList;
import java.util.Queue;
import net.minecraft.util.math.BlockPos;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobBase;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobSkeleton;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobSlime;

public class InteractionPatrol implements InteractionMovement
{
	private Queue<BlockPos> positions;
	private boolean shouldCycle = false;
	
	public InteractionPatrol(BlockPos... targetPoses)
	{
		this.positions = new LinkedList<>();
		for (BlockPos pos : targetPoses)
			this.positions.add(pos);
	}
	
	@Override
	public boolean isMobValid(MobBase mob)
	{
		return mob instanceof MobSkeleton || mob instanceof MobSlime;
	}

	@Override
	public boolean shouldCycle()
	{
		return shouldCycle;
	}

	@Override
	public void setShouldCycle(boolean shouldCycle)
	{
		this.shouldCycle = shouldCycle;
	}

	@Override
	public BlockPos getCurrentPos()
	{
		return positions.peek();
	}

	@Override
	public BlockPos cyclePos()
	{
		BlockPos pos = positions.remove();
		positions.add(pos);
		return pos;
	}
}
