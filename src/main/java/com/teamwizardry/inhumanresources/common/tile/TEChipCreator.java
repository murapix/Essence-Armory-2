package com.teamwizardry.inhumanresources.common.tile;

import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.inhumanresources.common.utils.lib.NBTTags;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TEChipCreator extends TileEntity implements ITickable
{
	private static final int OPERATION_TIME = 200;
	private int operationProgress = 0;
	
	public static final int SIZE = 6;
	
	public static final byte RESEARCH_SLOT = 0;
	public static final byte OUTPUT_SLOT = 1;
	public static final byte MOB_SLOT = 2;
	public static final byte REDSTONE_SLOT = 3;
	public static final byte GOLD_SLOT = 4;
	public static final byte QUARTZ_SLOT = 5;

	public TEChipCreator()
	{}
	
	private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE)
	{
		@Override
		protected void onContentsChanged(int slot)
		{
			TEChipCreator.this.markDirty();
		}
	};
	
	@Override
	public void update()
	{
		ItemStack researchLog = itemStackHandler.getStackInSlot(RESEARCH_SLOT);
		if (researchLog != null)
		{
			EntityPlayer player;
			try
			{
				player = Util.getPlayerFromUUID(researchLog.getTagCompound().getUniqueId(NBTTags.PLAYER_ID_TAG));
			}
			catch (NullPointerException e)
			{
				itemStackHandler.setStackInSlot(RESEARCH_SLOT, null);
				return;
			}
//			if (KnowledgeProvider.get(player).hasKnowledge(EntityZombie.class))
//			{
				if (operationProgress >= OPERATION_TIME)
				{
					operationProgress = 0;
					player.sendMessage(new TextComponentString("Research Verified"));
				}
				else
				{
					operationProgress++;
				}
//			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setTag("items", itemStackHandler.serializeNBT());
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		if (compound.hasKey("items"))
			itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
	}
	
	public boolean canInteractWith(EntityPlayer player){
		return !isInvalid() && player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) itemStackHandler;
		return super.getCapability(capability, facing);
	}
}
