package com.teamwizardry.inhumanresources.common.entity.tasks.interactions.inventory;

import java.util.Arrays;
import java.util.List;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobBase;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobZombie;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class InteractionInventoryPull implements InteractionInventory
{
	private boolean isWhitelist;
	private List<ItemStack> itemList;
	private EnumBehavior behavior;
	private BlockPos pos;
	private boolean shouldCycle = false;
	
	public InteractionInventoryPull(BlockPos pos, boolean isWhitelist, EnumBehavior behavior, ItemStack... itemList)
	{
		this.isWhitelist = isWhitelist;
		this.behavior = behavior;
		this.itemList = Arrays.asList(itemList);
		this.pos = pos;
	}
	
	public boolean isMobValid(MobBase mob)
	{
		return mob instanceof MobZombie;
	}
	
	public ItemStack[] interact(MobBase mob)
	{
		TileEntity te = mob.world.getTileEntity(pos);
		if (!(te instanceof IInventory))
			return null;
		IInventory inventory = (IInventory) te;
		ItemStack[] items = mob.getInventory();
		if (items == null) return null;
		for (int i = 0; i < inventory.getSizeInventory(); i++)
		{
			ItemStack item = inventory.getStackInSlot(i);
			if (item == null || item.getCount() == 0 || item.getItem() == null)
				continue;
			
			boolean itemMatched = false;
			int refStackSize = 0;
			if (isWhitelist)
			{
				for (ItemStack stack : itemList)
				{
					if (ItemStack.areItemsEqual(item, stack) && ItemStack.areItemStackTagsEqual(item, stack))
					{
						itemMatched = true;
						refStackSize = stack.getCount();
					}
				}
				if (!itemMatched)
					continue;
			}
			else
			{
				for (ItemStack stack : itemList)
				{
					if (ItemStack.areItemsEqual(item, stack) && ItemStack.areItemStackTagsEqual(item, stack))
					{
						itemMatched = true;
						break;
					}
				}
				if (itemMatched)
					continue;
			}
			
			for (int j = 0; j < items.length; j++)
			{
				if (item == null || item.getCount() == 0 || item.getItem() == null)
					continue;
				
				int transferableAmount = 0;

				if (items[j] != null && items[j].getItem() != null && items[j].getCount() != 0 && !(ItemStack.areItemsEqual(item, items[j]) && ItemStack.areItemStackTagsEqual(item, items[j])))
					continue;
				
				if (items[j] == null)
					transferableAmount = item.getMaxStackSize();
				else if (items[j].getCount() == 0 || items[j].getItem() == null)
				{
					items[j] = null;
					transferableAmount = item.getMaxStackSize();
				}
				else if (ItemStack.areItemsEqual(item, items[j]))
					transferableAmount = item.getMaxStackSize() - items[j].getCount();
				
				switch (behavior)
				{
					case ANY_AMOUNT:
						break;
					case EXACT_COUNT:
						transferableAmount = transferableAmount >= refStackSize ? refStackSize : 0;
						break;
					case AT_LEAST:
						transferableAmount = transferableAmount >= refStackSize ? transferableAmount : 0;
						break;
					case UP_TO:
						transferableAmount = transferableAmount <= refStackSize ? transferableAmount : refStackSize;
						break;
				}
				
				int oldSize = items[j] == null ? 0 : items[j].getCount();
				items[j] = item.splitStack(transferableAmount);
				inventory.decrStackSize(i, transferableAmount);
				if (inventory.getStackInSlot(i) != null && inventory.getStackInSlot(i).getCount() <= 0)
					inventory.removeStackFromSlot(i);
				items[j].grow(oldSize);
				if (items[j].getCount() > items[j].getMaxStackSize())
					items[j].setCount(items[j].getMaxStackSize());
			}
		}
		this.setShouldCycle(true);
		return items;
	}

	@Override
	public BlockPos getPos()
	{
		return this.pos;
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
}
