package escapee.essencearmory2.common.entity.tasks.interactions.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import escapee.essencearmory2.common.entity.mobs.MobBase;
import escapee.essencearmory2.common.entity.tasks.interactions.IInteraction;

public interface InteractionInventory extends IInteraction
{
	public ItemStack[] interact(MobBase mob);
	
	public BlockPos getPos();
}
