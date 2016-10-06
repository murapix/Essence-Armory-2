package escapee.essencearmory2.common.entity.tasks.interactions.movement;

import java.util.LinkedList;
import java.util.Queue;
import net.minecraft.util.math.BlockPos;
import escapee.essencearmory2.common.entity.mobs.MobBase;
import escapee.essencearmory2.common.entity.mobs.MobSkeleton;

public class InteractionPatrol implements InteractionMovement
{
	private Queue<BlockPos> positions = new LinkedList<>();
	private boolean shouldCycle = false;
	
	@Override
	public boolean isMobValid(MobBase mob)
	{
		return mob instanceof MobSkeleton;
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
