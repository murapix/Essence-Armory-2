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

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

@TileRegister("bedrock_bore")
public class TEBedrockBore extends TileModTickable
{	
	public static final int NUM_SLOTS = 2;

	public static final int FUEL_SLOT = 0;
	public static final int OUTPUT_SLOT = 1;

	public static final int FUEL_TIME = 1600;
	public static final int TICKS_PER_OUTPUT = 200;

	@Save
	public int burnTime = 0;
	
	@Save
	public ItemStack[] items = new ItemStack[NUM_SLOTS];
	
	public TEBedrockBore()
	{
		for (int i = 0; i < NUM_SLOTS; i++)
			items[i] = ItemStack.EMPTY;
	}
	
	@Save
	@CapabilityProvide(sides = { UP, DOWN, NORTH, SOUTH, EAST, WEST })
	public IItemHandler inventory = new FilteredItemHandler(items)
	{	
		@Override
		protected boolean canInsert(int slot, ItemStack stack)
		{
			return slot == FUEL_SLOT ? ItemStack.areItemsEqual(stack, new ItemStack(Items.DRAGON_BREATH)) : slot != OUTPUT_SLOT;
		}

		@Override
		protected boolean canExtract(int slot)
		{
			return slot == OUTPUT_SLOT;
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
			}
		}
	}
}