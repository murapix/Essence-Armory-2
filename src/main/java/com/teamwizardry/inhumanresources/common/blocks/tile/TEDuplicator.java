package com.teamwizardry.inhumanresources.common.blocks.tile;

import static net.minecraft.util.EnumFacing.DOWN;
import static net.minecraft.util.EnumFacing.EAST;
import static net.minecraft.util.EnumFacing.NORTH;
import static net.minecraft.util.EnumFacing.SOUTH;
import static net.minecraft.util.EnumFacing.UP;
import static net.minecraft.util.EnumFacing.WEST;

import com.teamwizardry.inhumanresources.common.utils.FilteredItemHandler;
import com.teamwizardry.inhumanresources.init.ItemRegistry;
import com.teamwizardry.librarianlib.features.autoregister.TileRegister;
import com.teamwizardry.librarianlib.features.base.block.TileModTickable;
import com.teamwizardry.librarianlib.features.saving.CapabilityProvide;
import com.teamwizardry.librarianlib.features.saving.Save;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

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
	private final ItemStack[] items = new ItemStack[NUM_SLOTS];
	
	public TEDuplicator()
	{
		for (int i = 0; i < NUM_SLOTS; i++)
			items[i] = ItemStack.EMPTY;
	}
	
	@CapabilityProvide(sides = { UP, DOWN, NORTH, SOUTH, EAST, WEST })
	private IItemHandler inventory = new FilteredItemHandler(items)
	{
		@Override
		protected boolean canInsert(int slot, ItemStack stack)
		{
			if (slot == FUEL_SLOT)
				return ItemStack.areItemsEqual(stack, new ItemStack(ItemRegistry.voidParticle, 1, 1));
			if (slot == INPUT_SLOT)
				return stack.getItem() != ItemRegistry.voidParticle;
			return slot != OUTPUT_SLOT;
		}

		@Override
		protected boolean canExtract(int slot)
		{
			return slot == OUTPUT_SLOT;
		}

		@Override
		protected void onSlotChanged()
		{
			markDirty();
		}
	};
	
	@Override
	public void tick()
	{
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
