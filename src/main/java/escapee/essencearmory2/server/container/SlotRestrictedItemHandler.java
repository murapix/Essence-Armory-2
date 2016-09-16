package escapee.essencearmory2.server.container;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotRestrictedItemHandler extends SlotItemHandler
{
	private final List<ItemStack> allowedItems;
	
	public SlotRestrictedItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, Object... allowedItems)
	{
		super(itemHandler, index, xPosition, yPosition);
		this.allowedItems = new ArrayList<>();
		for (Object o : allowedItems)
		{
			if (o instanceof ItemStack)
				this.allowedItems.add((ItemStack) o);
			if (o instanceof Item)
				this.allowedItems.add(new ItemStack((Item) o));
			if (o instanceof Block)
				this.allowedItems.add(new ItemStack((Block) o));
		}
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		boolean valid = super.isItemValid(stack);
		if (!valid) return false;
		
		for (ItemStack item : allowedItems)
		{
			if (ItemStack.areItemsEqual(item, stack))
				return true;
		}
		
		return false;
	}
}
