package com.teamwizardry.inhumanresources.common.entity.ai;

import com.teamwizardry.inhumanresources.common.entity.mobs.MobBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;

public class MobAIFollowOwner extends EntityAIBase
{
	private MobBase mob;
	private EntityPlayer owner;
	private double followSpeed;
	private PathNavigate pathfinder;
	private int pathRecalcTime;
	private float maxDist;
	private float minDist;
	private float waterCost;
	
	public MobAIFollowOwner(MobBase mob, double followSpeed, float minDist, float maxDist)
	{
		this.mob = mob;
		this.followSpeed = followSpeed;
		this.pathfinder = mob.getNavigator();
		this.minDist = minDist;
		this.maxDist = maxDist;
		this.setMutexBits(3);
		
		if (!(mob.getNavigator() instanceof PathNavigateGround))
		{
			throw new IllegalArgumentException("Unsupported mob type for FollowOwner");
		}
	}
	
	@Override
	public boolean shouldExecute()
	{
		Entity entity = this.mob.getOwner();

		if (entity == null) return false;
		if (entity instanceof EntityPlayer)
		{
			if (((EntityPlayer) entity).isSpectator())
				return false;
			double dist = this.mob.getDistanceSqToEntity(entity);
			if (dist < (double) (this.minDist * this.minDist))
				return false;
			if (dist > (double) (this.maxDist * this.maxDist))
				return false;
			this.owner = (EntityPlayer) entity;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean shouldContinueExecuting()
	{
		if (this.pathfinder.noPath())
			return false;
		return this.mob.getDistanceSqToEntity(this.owner) > (double) (this.maxDist * this.maxDist);
	}
	
	@Override
	public void startExecuting()
	{
		this.pathRecalcTime = 0;
		this.waterCost = this.mob.getPathPriority(PathNodeType.WATER);
		this.mob.setPathPriority(PathNodeType.WATER, 0);
	}
	
	@Override
	public void resetTask()
	{
		this.owner = null;
		this.pathfinder.clearPathEntity();
		this.mob.setPathPriority(PathNodeType.WATER, this.waterCost);
	}
	
	@Override
	public void updateTask()
	{
		this.mob.getLookHelper().setLookPositionWithEntity(this.owner, 10, this.mob.getVerticalFaceSpeed());
		
		if (--this.pathRecalcTime <= 0)
		{
			this.pathRecalcTime = 10;
			this.pathfinder.tryMoveToEntityLiving(this.owner, this.followSpeed);
		}
	}
}
