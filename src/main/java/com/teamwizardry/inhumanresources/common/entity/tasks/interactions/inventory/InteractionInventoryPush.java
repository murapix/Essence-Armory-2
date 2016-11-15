package com.teamwizardry.inhumanresources.common.entity.tasks.interactions.inventory;

import java.util.Arrays;
import java.util.List;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobBase;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobZombie;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class InteractionInventoryPush implements InteractionInventory
{
	private boolean isWhitelist;
	private List<ItemStack> itemList;
	private EnumBehavior behavior;
	private BlockPos pos;
	private boolean shouldCycle = false;
	
	public InteractionInventoryPush(BlockPos pos, boolean isWhitelist, EnumBehavior behavior, ItemStack... itemList)
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
		TileEntity te = mob.worldObj.getTileEntity(pos);
		if (!(te instanceof IInventory))
			return null;
		IInventory inventory = (IInventory) te;
		ItemStack[] items = mob.getInventory();
		if (items == null) return null;
		for (int i = 0; i < items.length; i++)
		{
			if (items[i] == null || items[i].stackSize == 0 || items[i].getItem() == null)
				continue;
			
			boolean itemMatched = false;
			int refStackSize = 0;
			if (isWhitelist)
			{
				for (ItemStack stack : itemList)
				{
					if (ItemStack.areItemsEqual(items[i], stack) && ItemStack.areItemStackTagsEqual(items[i], stack))
					{
						itemMatched = true;
						refStackSize = stack.stackSize;
					}
				}
				if (!itemMatched)
					continue;
			}
			else
			{
				for (ItemStack stack : itemList)
				{
					if (ItemStack.areItemsEqual(items[i], stack) && ItemStack.areItemStackTagsEqual(items[i], stack))
					{
						itemMatched = true;
						break;
					}
				}
				if (itemMatched)
					continue;
			}
			
			for (int j = 0; j < inventory.getSizeInventory(); j++)
			{
				if (items[i] == null || items[i].stackSize == 0 || items[i].getItem() == null)
					continue;
				
				int transferableAmount = 0;
				
				ItemStack item = inventory.getStackInSlot(j);
				if (item != null && item.getItem() != null && item.stackSize != 0 && !(ItemStack.areItemsEqual(items[i], item) && ItemStack.areItemStackTagsEqual(items[i], item)))
					continue;
				if (item == null || item.getItem() == null)
					transferableAmount = items[i].getMaxStackSize();
				else if (item.stackSize == 0)
				{
					item = null;
					transferableAmount = items[i].getMaxStackSize();
				}
				else if (ItemStack.areItemsEqual(items[i], item))
					transferableAmount = items[i].getMaxStackSize() - item.stackSize;
				
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
				
				int oldSize = item == null ? 0 : item.stackSize;
				item = items[i].splitStack(transferableAmount);
				if (items[i].stackSize == 0) items[i] = null;
				item.stackSize += oldSize;
				if (item.stackSize > item.getMaxStackSize())
					item.stackSize = item.getMaxStackSize();
				inventory.setInventorySlotContents(j, item);
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
