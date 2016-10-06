package escapee.essencearmory2.common.entity.tasks.interactions.movement;

import net.minecraft.util.math.BlockPos;
import escapee.essencearmory2.common.entity.tasks.interactions.IInteraction;

public interface InteractionMovement extends IInteraction
{
	public BlockPos getCurrentPos();
	
	public BlockPos cyclePos();
}
