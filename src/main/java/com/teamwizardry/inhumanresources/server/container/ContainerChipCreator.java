package com.teamwizardry.inhumanresources.server.container;

import javax.annotation.Nullable;
import com.teamwizardry.inhumanresources.common.tile.TEChipCreator;
import com.teamwizardry.inhumanresources.init.ItemRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerChipCreator extends Container
{
	private TEChipCreator te;
	
	public ContainerChipCreator(IInventory playerInventory, TEChipCreator te)
	{
		this.te = te;
		addOwnSlots();
		addPlayerSlots(playerInventory);
	}
	
	private void addPlayerSlots(IInventory playerInventory)
	{
		for (int row = 0; row < 3; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				int x = 9 + col * 18;
				int y = row * 18 + 70;
				this.addSlotToContainer(new Slot(playerInventory, col + (row+1)*9, x, y));
			}
		}
		
		for (int col = 0; col < 9; col++)
		{
			int x = 9 + col * 18;
			int y = 58 + 70;
			this.addSlotToContainer(new Slot(playerInventory, col, x, y));
		}
	}
	
	private void addOwnSlots()
	{
		IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		int x = 9; int y = 6;
		
		// Research Slot
		this.addSlotToContainer(new SlotRestrictedItemHandler(itemHandler, 0, x, y, ItemRegistry.itemResearchLog));
		x+=18;
		// Output Slot
		this.addSlotToContainer(new SlotRestrictedItemHandler(itemHandler, 1, x, y));
		x+=18;
		// Mob Slot
		this.addSlotToContainer(new SlotRestrictedItemHandler(itemHandler, 2, x, y, Items.ROTTEN_FLESH, Items.GUNPOWDER, Items.BONE));
		x+=18;
		// Redstone Slot
		this.addSlotToContainer(new SlotRestrictedItemHandler(itemHandler, 3, x, y, Items.REDSTONE));
		x+=18;
		// Gold Slot
		this.addSlotToContainer(new SlotRestrictedItemHandler(itemHandler, 4, x, y, Items.GOLD_INGOT));
		x+=18;
		// Quartz Slot
		this.addSlotToContainer(new SlotRestrictedItemHandler(itemHandler, 5, x, y, Items.QUARTZ));
	}
	
	@Nullable
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index)
	{
		ItemStack itemStack = null;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack())
		{
			ItemStack temp = slot.getStack();
			itemStack = temp.copy();
			
			if (index < TEChipCreator.SIZE)
			{
				if (!this.mergeItemStack(temp, TEChipCreator.SIZE, this.inventorySlots.size(), false))
					return null;
			}
			else if (!this.mergeItemStack(temp, 0, TEChipCreator.SIZE, false))
				return null;
			
			if (temp.isEmpty())
				slot.putStack(null);
			
			else slot.onSlotChanged();
		}
		
		return itemStack;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return te.canInteractWith(player);
	}
}
