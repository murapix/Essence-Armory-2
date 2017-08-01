package com.teamwizardry.inhumanresources.common.utils;

import javax.annotation.Nonnull;

import com.teamwizardry.librarianlib.features.saving.Save;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public abstract class FilteredItemHandler implements IItemHandler
{
	@Save
	private final ItemStack[] slots;

	public FilteredItemHandler(ItemStack[] slots)
	{
		this.slots = slots;
		for (int i = 0; i < slots.length; i++)
			if (slots[i] == null)
				slots[i] = ItemStack.EMPTY;
	}

	@Override
	public int getSlots()
	{
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return slots[slot];
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
	{
		if (stack.isEmpty()) return ItemStack.EMPTY;

		validateSlotIndex(slot);

		if (!canInsert(slot, stack)) return stack;

		ItemStack existing = slots[slot];
		int limit = getStackLimit(slot, stack);

		if (!existing.isEmpty())
		{
			if (!ItemHandlerHelper.canItemStacksStack(stack, existing)) return stack;
			limit -= existing.getCount();
		}

		if (limit <= 0) return stack;

		boolean reachedLimit = stack.getCount() > limit;

		if (!simulate)
		{
			if (existing.isEmpty())
				slots[slot] = reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack;
			else existing.grow(reachedLimit ? limit : stack.getCount());
		}
		
		return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - limit) : ItemStack.EMPTY;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate)
	{
		if (amount == 0) return ItemStack.EMPTY;

		validateSlotIndex(slot);

		if (!canExtract(slot)) return ItemStack.EMPTY;

		ItemStack existing = slots[slot];

		if (existing.isEmpty()) return ItemStack.EMPTY;

		int toExtract = Math.min(amount, existing.getMaxStackSize());

		if (existing.getCount() <= toExtract)
		{
			if (!simulate) slots[slot] = ItemStack.EMPTY;
			return existing;
		}
		else
		{
			if (!simulate) slots[slot] = ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract);
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
		if (slot < 0 || slot >= slots.length)
			throw new RuntimeException("Slot " + slot + " not in valid range - [0," + slots.length + ")");
	}

	protected int getStackLimit(int slot, @Nonnull ItemStack stack)
	{
		return Math.min(getSlotLimit(slot), stack.getMaxStackSize());
	}

	abstract protected boolean canInsert(int slot, ItemStack stack);

	abstract protected boolean canExtract(int slot);
}
