package com.teamwizardry.inhumanresources.common.blocks.tile;

import javax.annotation.Nonnull;

import com.teamwizardry.inhumanresources.init.ItemRegistry;
import com.teamwizardry.librarianlib.features.autoregister.TileRegister;
import com.teamwizardry.librarianlib.features.base.block.TileModTickable;
import com.teamwizardry.librarianlib.features.saving.CapabilityProvide;
import com.teamwizardry.librarianlib.features.saving.Save;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

@TileRegister("bedrock_bore")
public class TEBedrockBore extends TileModTickable
{	
	private static final int NUM_SLOTS = 2;

	private static final int FUEL_SLOT = 0;
	private static final int OUTPUT_SLOT = 1;

	private static final int FUEL_TIME = 1600;
	private static final int TICKS_PER_OUTPUT = 200;

	@Save
	private int burnTime = 0;
	
	@Save
	private ItemStack[] items = new ItemStack[NUM_SLOTS];
	
	public TEBedrockBore()
	{
		for (int i = 0; i < NUM_SLOTS; i++)
			items[i] = ItemStack.EMPTY;
	}
	
	@CapabilityProvide(sides = { EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST })
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
			if (slot == FUEL_SLOT && !ItemStack.areItemsEqual(stack, new ItemStack(Items.DRAGON_BREATH)))
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
	        if (slot == FUEL_SLOT)
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

			if (Math.random() < 1.0 / TICKS_PER_OUTPUT)
			{
				if (items[OUTPUT_SLOT].equals(ItemStack.EMPTY))
					items[OUTPUT_SLOT] = new ItemStack(ItemRegistry.voidParticle, 1, 0);
				else if (items[OUTPUT_SLOT].getCount() < items[OUTPUT_SLOT].getMaxStackSize())
					items[OUTPUT_SLOT].grow(1);
			}
		}
		else if (items[FUEL_SLOT].getCount() > 0)
		{
			if (items[OUTPUT_SLOT].equals(ItemStack.EMPTY) || items[OUTPUT_SLOT].getCount() < items[OUTPUT_SLOT].getMaxStackSize())
			{
				burnTime = FUEL_TIME;
				items[FUEL_SLOT].shrink(1);
				EntityPlayer player = Minecraft.getMinecraft().player;
				if (player != null && !world.isRemote)
						player.sendMessage(new TextComponentString("Fuel Count: " + items[FUEL_SLOT].getCount()));

			}
		}
	}
}