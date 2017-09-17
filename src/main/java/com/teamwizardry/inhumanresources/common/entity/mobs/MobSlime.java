package com.teamwizardry.inhumanresources.common.entity.mobs;

import com.teamwizardry.inhumanresources.common.entity.ai.MobAIFollowOwner;
import com.teamwizardry.inhumanresources.common.entity.ai.MobAIGetNextTarget;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class MobSlime extends MobBase
{
	public static final DataParameter<Integer> SLIME_SIZE = EntityDataManager.<Integer> createKey(MobSlime.class, DataSerializers.VARINT);
	public float squishAmount;
	public float squishFactor;
	public float prevSquishFactor;
	private boolean wasOnGround;

	public MobSlime(World world)
	{
		super(world);
		this.moveHelper = new SlimeMoveHelper(this);
	}

	@Override
	protected void initEntityAI()
	{
		this.tasks.addTask(1, new MobSlime.AISlimeFloat(this));
		this.tasks.addTask(5, new MobSlime.AISlimeHop(this));
		this.tasks.addTask(6, new MobAIGetNextTarget(this, 1));
		this.targetTasks.addTask(7, new MobAIFollowOwner(this, 1, 4, 32));
		this.targetTasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 32));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(SLIME_SIZE, Integer.valueOf(1));
	}

	protected void setSlimeSize(int size)
	{
		this.dataManager.set(SLIME_SIZE, Integer.valueOf(size));
		this.setSize(0.5F * size, 0.5F * size);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(size * size);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2 + 0.1 * size);
		this.setHealth(this.getMaxHealth());
		this.experienceValue = size;
	}

	public int getSlimeSize()
	{
		return this.dataManager.get(SLIME_SIZE).intValue();
	}

	public static void func_189758_c(DataFixer p_189758_0_)
	{
		EntityLiving.registerFixesMob(p_189758_0_, MobSlime.class);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setInteger("Size", this.getSlimeSize() - 1);
		compound.setBoolean("wasOnGround", this.wasOnGround);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		int i = compound.getInteger("Size");
		this.setSlimeSize(i < 0 ? 1 : i + 1);
		this.wasOnGround = compound.getBoolean("wasOnGround");
	}

	public boolean isSmallSlime()
	{
		return this.getSlimeSize() <= 1;
	}

	protected EnumParticleTypes getParticleType()
	{
		return EnumParticleTypes.SLIME;
	}

	public void onUpdate()
	{
		this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5;
		this.prevSquishFactor = this.squishFactor;
		super.onUpdate();

		if (this.onGround && !this.wasOnGround)
		{
			int i = this.getSlimeSize();
			if (spawnCustomParticles())
				i = 0;
			for (int j = 0; j < i * 8; j++)
			{
				float f = this.rand.nextFloat() * 2 * (float) Math.PI;
				float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
				float f2 = MathHelper.sin(f) * i * 0.5F * f1;
				float f3 = MathHelper.cos(f) * i * 0.5F * f1;
				World world = this.world;
				EnumParticleTypes particleType = this.getParticleType();
				double d0 = this.posX + f2;
				double d1 = this.posZ + f3;
				world.spawnParticle(particleType, d0, this.getEntityBoundingBox().minY, d1, 0, 0, 0, new int[0]);
			}

			this.playSound(this.getSquishSound(), (float) this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.1F) / 0.8F);
			this.squishAmount = -0.5F;
		}
		else if (!this.onGround && this.wasOnGround)
			this.squishAmount = 1;

		this.wasOnGround = this.onGround;
		this.alterSquishAmount();
	}

	protected void alterSquishAmount()
	{
		this.squishAmount *= 0.6;
	}

	protected int getJumpDelay()
	{
		return this.rand.nextInt(20) + 10;
	}

	protected EntitySlime createInstance()
	{
		return new EntitySlime(this.world);
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key)
	{
		if (SLIME_SIZE.equals(key))
		{
			int i = this.getSlimeSize();
			this.setSize(0.5F * i, 0.5F * i);
			this.rotationYaw = this.rotationYawHead;
			this.renderYawOffset = this.rotationYawHead;
		}

		super.notifyDataManagerChange(key);
	}

	@Override
	public void applyEntityCollision(Entity entity)
	{
		super.applyEntityCollision(entity);

		if (entity instanceof EntityItem)
		{
			entity.setDead();
		}
	}

	@Override
	public float getEyeHeight()
	{
		return 0.625F * this.height;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource)
	{
		return this.isSmallSlime() ? SoundEvents.ENTITY_SMALL_SLIME_HURT : SoundEvents.ENTITY_SLIME_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return this.isSmallSlime() ? SoundEvents.ENTITY_SMALL_SLIME_DEATH : SoundEvents.ENTITY_SLIME_DEATH;
	}

	protected SoundEvent getSquishSound()
	{
		return this.isSmallSlime() ? SoundEvents.ENTITY_SMALL_SLIME_SQUISH : SoundEvents.ENTITY_SLIME_SQUISH;
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F * this.getSlimeSize();
	}

	@Override
	public int getVerticalFaceSpeed()
	{
		return 0;
	}

	protected boolean makesSoundOnJump()
	{
		return this.getSlimeSize() > 0;
	}

	@Override
	protected void jump()
	{
		this.motionY = 0.42;
		this.isAirBorne = true;
	}

	protected SoundEvent getJumpSound()
	{
		return this.isSmallSlime() ? SoundEvents.ENTITY_SMALL_SLIME_JUMP : SoundEvents.ENTITY_SLIME_JUMP;
	}

	protected boolean spawnCustomParticles()
	{
		return false;
	}

	static class AISlimeFloat extends EntityAIBase
	{
		private final MobSlime slime;

		public AISlimeFloat(MobSlime slime)
		{
			this.slime = slime;
			this.setMutexBits(4);
			((PathNavigateGround) slime.getNavigator()).setCanSwim(true);
		}

		@Override
		public boolean shouldExecute()
		{
			return this.slime.isInWater() || this.slime.isInLava();
		}

		@Override
		public void updateTask()
		{
			if (this.slime.getRNG().nextFloat() < 0.8F)
				this.slime.getJumpHelper().setJumping();
			MobSlime.SlimeMoveHelper moveHelper = (MobSlime.SlimeMoveHelper) this.slime.getMoveHelper();
			moveHelper.setSpeed(1.2);
			EntityLookHelper lookHelper = this.slime.getLookHelper();
			float yaw = (float) MathHelper.atan2(lookHelper.getLookPosZ(), lookHelper.getLookPosX());
			moveHelper.setDirection(yaw, false);
		}
	}

	static class AISlimeHop extends EntityAIBase
	{
		private final MobSlime slime;

		public AISlimeHop(MobSlime slime)
		{
			this.slime = slime;
			this.setMutexBits(4);
		}

		@Override
		public boolean shouldExecute()
		{
			return !this.slime.getNavigator().noPath();
		}

		@Override
		public void updateTask()
		{
			Path path = this.slime.getNavigator().getPath();
			if (path == null)
			{
				this.slime.getNavigator().clearPathEntity();
				return;
			}
			PathPoint point = path.getPathPointFromIndex(path.getCurrentPathIndex());
			double xDir = (point.x+ 0.5) - this.slime.posX;
			double zDir = (point.z+ 0.5) - this.slime.posZ;
			MobSlime.SlimeMoveHelper moveHelper = (MobSlime.SlimeMoveHelper) this.slime.getMoveHelper();
			moveHelper.setDirection((float) MathHelper.atan2(zDir, xDir), false);
			moveHelper.setSpeed(1);
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString(""));
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Target X: " + Integer.toString(point.x)));
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Target Z: " + Integer.toString(point.z)));
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Current X: " + Integer.toString((int) this.slime.posX)));
			Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Current Z: " + Integer.toString((int) this.slime.posZ)));
		}
	}

	static class SlimeMoveHelper extends EntityMoveHelper
	{
		private int jumpDelay;
		private final MobSlime slime;

		public SlimeMoveHelper(MobSlime slime)
		{
			super(slime);
			this.slime = slime;
		}

		public void setDirection(float rotation, boolean isAggressive)
		{
			this.slime.setPositionAndRotation(this.slime.posX, this.slime.posY, this.slime.posZ, rotation, this.slime.rotationPitch);
		}

		public void setSpeed(double speed)
		{
			this.speed = speed;
			this.action = EntityMoveHelper.Action.MOVE_TO;
		}

		public void onUpdateMoveHelper()
		{
			this.entity.rotationYawHead = this.entity.rotationYaw;
			this.entity.renderYawOffset = this.entity.rotationYaw;
			
			if (this.action != EntityMoveHelper.Action.MOVE_TO)
				this.entity.setMoveForward(0);
			else
			{
				this.action = EntityMoveHelper.Action.WAIT;

				if (this.entity.onGround)
				{
					this.entity.setAIMoveSpeed((float) (this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
					if (this.jumpDelay-- <= 0)
					{
						this.jumpDelay = this.slime.getJumpDelay();

						this.slime.getJumpHelper().setJumping();

						if (this.slime.makesSoundOnJump())
						{
							this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), ((this.slime.getRNG().nextFloat() * 0.2F + 1) * 0.8F));
						}
					}
					else
					{
						this.slime.moveStrafing = 0;
						this.slime.moveForward = 0;
						this.entity.setAIMoveSpeed(0);
					}
				}
				else
				{
					this.entity.setAIMoveSpeed((float) (this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
				}
			}
		}
	}
}
