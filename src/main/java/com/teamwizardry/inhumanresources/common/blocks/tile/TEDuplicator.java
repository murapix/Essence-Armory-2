package com.teamwizardry.inhumanresources.common.blocks.tile;

import static net.minecraft.util.EnumFacing.DOWN;
import static net.minecraft.util.EnumFacing.EAST;
import static net.minecraft.util.EnumFacing.NORTH;
import static net.minecraft.util.EnumFacing.SOUTH;
import static net.minecraft.util.EnumFacing.UP;
import static net.minecraft.util.EnumFacing.WEST;

import javax.annotation.Nonnull;

import com.teamwizardry.inhumanresources.init.ItemRegistry;
import com.teamwizardry.librarianlib.features.autoregister.TileRegister;
import com.teamwizardry.librarianlib.features.base.block.TileModTickable;
import com.teamwizardry.librarianlib.features.saving.CapabilityProvide;
import com.teamwizardry.librarianlib.features.saving.Save;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

@TileRegister("duplicator")
public class TEDuplicator extends TileModTickable
{
	private static final int NUM_SLOTS = 3;
	
	private static final int FUEL_SLOT = 0;
	private static final int INPUT_SLOT = 1;
	private static final int OUTPUT_SLOT = 2;
	
	private static final int FUEL_TIME = 20;
	
	private static final int[] OUTPUT_CHANCES = {5, 0, 4, 1};
	
	@Save
	private int burnTime = 0;
	
	@Save
	private boolean isActive = false;
	
	@Save
	private ItemStack[] items = new ItemStack[NUM_SLOTS];
	
	public TEDuplicator()
	{
		for (int i = 0; i < NUM_SLOTS; i++)
			items[i] = ItemStack.EMPTY;
	}
	
	@CapabilityProvide(sides = { UP, DOWN, NORTH, SOUTH, EAST, WEST })
	private IItemHandler inventory = new IItemHandler()
	{
		@Override
		public int getSlots()
		{
			return NUM_SLOTS;
		}

		@Override
		public ItemStack getStackInSlot(int slot)
		{
			return items[slot];
		}

		@Override
		public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
		{
			if (stack.isEmpty())
				return ItemStack.EMPTY;
			if (slot == FUEL_SLOT && !ItemStack.areItemsEqual(stack, new ItemStack(ItemRegistry.voidParticle, 1, 1)))
				return stack;
			if (slot == INPUT_SLOT && stack.getItem() == ItemRegistry.voidParticle)
				return stack;
			if (slot == OUTPUT_SLOT)
				return stack;
			
			validateSlotIndex(slot);
			
			ItemStack existing = items[slot];
			int limit = getStackLimit(slot, stack);
			
			if (!existing.isEmpty())
			{
				if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
					return stack;
				limit -= existing.getCount();
			}
			
			if (limit <= 0)
				return stack;
			
			boolean reachedLimit = stack.getCount() > limit;
			
			if (!simulate)
			{
				if (existing.isEmpty())
					items[slot] = reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack;
				else
					existing.grow(reachedLimit ? limit : stack.getCount());
			}
			
			return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - limit) : ItemStack.EMPTY;
		}

		@Override
		public ItemStack extractItem(int slot, int amount, boolean simulate)
		{
	        if (amount == 0)
	            return ItemStack.EMPTY;
	        if (slot != OUTPUT_SLOT)
	        	return ItemStack.EMPTY;

	        validateSlotIndex(slot);

	        ItemStack existing = items[slot];

	        if (existing.isEmpty())
	            return ItemStack.EMPTY;

	        int toExtract = Math.min(amount, existing.getMaxStackSize());

	        if (existing.getCount() <= toExtract)
	        {
	            if (!simulate)
	                items[slot] = ItemStack.EMPTY;
	            return existing;
	        }
	        else
	        {
	            if (!simulate)
	            	items[slot] = ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract);
	            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
	        }
		}

		@Override
		public int getSlotLimit(int slot)
		{
			return 64;
		}
		
	    protected void validateSlotIndex(int slot)
	    {
	        if (slot < 0 || slot >= items.length)
	            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + items.length + ")");
	    }
	    
	    protected int getStackLimit(int slot, @Nonnull ItemStack stack)
	    {
	        return Math.min(getSlotLimit(slot), stack.getMaxStackSize());
	    }
	};
	
	
	@Override
	public void tick()
	{
		if (items == null)
			items = new ItemStack[NUM_SLOTS];
		for (int i = 0; i < NUM_SLOTS; i++)
			if (items[i] == null)
				items[i] = ItemStack.EMPTY;
		
		if (burnTime > 0)
		{
			burnTime--;
			if (ItemStack.areItemsEqual(items[INPUT_SLOT], ItemStack.EMPTY) || !items[INPUT_SLOT].equals(items[OUTPUT_SLOT]) || items[OUTPUT_SLOT].getCount() >= items[OUTPUT_SLOT].getMaxStackSize())
				isActive = false;
		}
		else
		{
			if (isActive)
			{
				isActive = false;
				double rand = Math.random();
				double totalWeight = 0;
				for (int i = 0; i < OUTPUT_CHANCES.length; i++)
					totalWeight += OUTPUT_CHANCES[i];
				for (int i = 0; i < OUTPUT_CHANCES.length; i++)
				{
					if (rand < (OUTPUT_CHANCES[i] / totalWeight))
					{
						ItemStack stack = items[INPUT_SLOT].copy();
						stack.setCount(i);
						inventory.insertItem(OUTPUT_SLOT, stack, false);
						break;
					}
					else
						rand -= (OUTPUT_CHANCES[i] / totalWeight);
				}
			}
			if (items[FUEL_SLOT].getCount() > 0)
			{
				if (!items[INPUT_SLOT].isEmpty())
				{
					if (ItemStack.areItemsEqual(items[OUTPUT_SLOT], ItemStack.EMPTY) || ItemStack.areItemsEqual(items[OUTPUT_SLOT], items[INPUT_SLOT]))
					{
						if (items[OUTPUT_SLOT].getCount() < items[OUTPUT_SLOT].getMaxStackSize())
						{
							burnTime = FUEL_TIME;
							items[FUEL_SLOT].shrink(1);
							isActive = true;
						}
					}
				}
			}
		}
	}
}
