package escapee.essencearmory2.common.tile;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.Constants.NBT;
import escapee.essencearmory2.common.capability.knowledge.KnowledgeProvider;
import escapee.essencearmory2.common.utils.Util;
import escapee.essencearmory2.lib.NBTTags;

public class TEChipCreator extends TileEntity implements ITickable, IInventory
{
	private static final int OPERATION_TIME = 200;
	private int operationProgress = 0;
	
	private ItemStack[] inventory;
	
	public static final byte RESEARCH_SLOT = 0;
	public static final byte OUTPUT_SLOT = 1;
	public static final byte MOB_SLOT = 2;
	public static final byte REDSTONE_SLOT = 3;
	public static final byte GOLD_SLOT = 4;
	public static final byte QUARTZ_SLOT = 5;

	public TEChipCreator()
	{
		inventory = new ItemStack[getSizeInventory()];
	}
	
	@Override
	public void update()
	{
		ItemStack researchLog = getStackInSlot(RESEARCH_SLOT);
		if (researchLog != null)
		{
			EntityPlayer player = Util.getPlayerFromUUID(researchLog.getTagCompound().getUniqueId(NBTTags.PLAYER_ID_TAG));
			if (KnowledgeProvider.get(player).hasKnowledge(EntityZombie.class))
			{
				if (operationProgress >= OPERATION_TIME)
				{
					operationProgress = 0;
					player.addChatMessage(new TextComponentString("Research Verified"));
				}
				else
				{
					operationProgress++;
				}
			}
		}
	}

	@Override
	public String getName()
	{
		return "container.chip_creator";
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}
	
	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentString(getName());
	}

	@Override
	public int getSizeInventory()
	{
		return 6;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		if (index < 0 || index >= getSizeInventory())
			return null;
		return inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if (getStackInSlot(index) != null)
		{
			ItemStack stack;
			if (getStackInSlot(index).stackSize <= count)
			{
				stack = getStackInSlot(index);
				setInventorySlotContents(index, null);
				markDirty();
				return stack;
			}
			stack = getStackInSlot(index).splitStack(count);
			if (getStackInSlot(index).stackSize <= 0)
				setInventorySlotContents(index, null);
			else setInventorySlotContents(index, getStackInSlot(index));
			markDirty();
			return stack;
		}
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		if (index < 0 || index >= getSizeInventory())
			return null;
		
		ItemStack stack = getStackInSlot(index);
		setInventorySlotContents(index, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		if (index < 0 || index >= getSizeInventory())
			return;
		
		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
		
		if (stack != null && stack.stackSize == 0)
			stack = null;
		
		inventory[index] = stack;
		markDirty();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getTileEntity(getPos()) == this && player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{}

	@Override
	public void closeInventory(EntityPlayer player)
	{}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return true;
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < getSizeInventory(); i++)
			setInventorySlotContents(i, null);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); i++)
		{
			if (getStackInSlot(i) != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				getStackInSlot(i).writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		compound.setTag("Items", list);
		
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		NBTTagList list = compound.getTagList("Items", NBT.TAG_COMPOUND);
		for (int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound tag = list.getCompoundTagAt(i);
			setInventorySlotContents(tag.getByte("Slot"), ItemStack.loadItemStackFromNBT(tag));
		}
	}
}
