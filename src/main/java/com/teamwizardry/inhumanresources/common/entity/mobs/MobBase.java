package com.teamwizardry.inhumanresources.common.entity.mobs;

import java.util.UUID;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.google.common.base.Optional;
import com.teamwizardry.inhumanresources.common.entity.ai.MobAIFollowOwner;
import com.teamwizardry.inhumanresources.common.entity.ai.MobAIGetNextTarget;
import com.teamwizardry.inhumanresources.common.tile.TEMobController;
import com.teamwizardry.inhumanresources.common.utils.lib.NBTTags;

public class MobBase extends EntityCreature implements IEntityOwnable
{
	protected static final DataParameter<Optional<UUID>> OWNER_ID = EntityDataManager.<Optional<UUID>> createKey(MobBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Optional<BlockPos>> CONTROLLER_POS = EntityDataManager.<Optional<BlockPos>> createKey(MobBase.class, DataSerializers.OPTIONAL_BLOCK_POS);
	protected ItemStack[] inventory;
	public double interactDistance = 1.5;

	public MobBase(World world)
	{
		super(world);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(OWNER_ID, Optional.<UUID> absent());
		this.dataManager.register(CONTROLLER_POS, Optional.<BlockPos> absent());
		this.inventory = new ItemStack[4];
	}

	@Override
	protected void initEntityAI()
	{
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.targetTasks.addTask(6, new MobAIGetNextTarget(this, 1));
		this.targetTasks.addTask(7, new MobAIFollowOwner(this, 1, 4, 32));
		this.targetTasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 32));
	}
	
	public void setControllerPos(BlockPos pos)
	{
		this.dataManager.set(CONTROLLER_POS, Optional.fromNullable(pos));
	}
	
	public BlockPos getControllerPos()
	{
		return (BlockPos) (this.dataManager.get(CONTROLLER_POS)).orNull();
	}
	
	public void setController(TEMobController controller)
	{
		this.setControllerPos(controller.getPos());
	}
	
	public TEMobController getController()
	{
		BlockPos pos = this.getControllerPos();
		if (pos == null) return null;
		TileEntity te = this.worldObj.getTileEntity(pos);
		if (te instanceof TEMobController)
			return (TEMobController) te;
		return null;
	}

	public void setOwnerId(UUID uuid)
	{
		this.dataManager.set(OWNER_ID, Optional.fromNullable(uuid));
	}

	@Override
	public UUID getOwnerId()
	{
		return (UUID) (this.dataManager.get(OWNER_ID)).orNull();
	}

	public void setOwner(EntityPlayer player)
	{
		setOwnerId(player.getUniqueID());
	}

	@Override
	public EntityPlayer getOwner()
	{
		try
		{
			UUID uuid = this.getOwnerId();
			return uuid == null ? null : this.worldObj.getPlayerEntityByUUID(uuid);
		}
		catch (IllegalArgumentException e)
		{
			return null;
		}
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		if (this.getOwnerId() == null)
			compound.setString(NBTTags.OWNER_ID_TAG, "");
		else compound.setString(NBTTags.OWNER_ID_TAG, this.getOwnerId().toString());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		if (compound.hasKey(NBTTags.OWNER_ID_TAG))
		{
			try
			{
				this.setOwnerId(UUID.fromString(compound.getString(NBTTags.OWNER_ID_TAG)));
			}
			catch (IllegalArgumentException e)
			{}
		}
	}
	
	public ItemStack[] getInventory()
	{
		return this.inventory;
	}
	
	public int getInventorySize()
	{
		return this.inventory.length;
	}
}