package com.teamwizardry.inhumanresources.common.entity.ai;

import com.teamwizardry.inhumanresources.common.blocks.tile.TEMobController;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobBase;
import com.teamwizardry.inhumanresources.common.entity.tasks.Task;
import com.teamwizardry.inhumanresources.common.entity.tasks.interactions.IInteraction;
import com.teamwizardry.inhumanresources.common.entity.tasks.interactions.inventory.InteractionInventory;
import com.teamwizardry.inhumanresources.common.entity.tasks.interactions.movement.InteractionMovement;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;

public class MobAIGetNextTarget extends EntityAIBase
{
	private MobBase mob;
	private TEMobController controller;
	private PathNavigate pathfinder;
	private int pathRecalcTime;
	private float waterCost;
	private double followSpeed;
	private IInteraction currentGoal;

	public MobAIGetNextTarget(MobBase mob, double followSpeed)
	{
		this.mob = mob;
		this.pathfinder = mob.getNavigator();
		this.followSpeed = followSpeed;
		this.setMutexBits(3);

		if (!(mob.getNavigator() instanceof PathNavigateGround))
			throw new IllegalArgumentException("Unsupported mob type for GetNextTarget");
	}

	@Override
	public boolean shouldExecute()
	{
		controller = this.mob.getController();
		if (controller == null)
			return false;
		Task task = controller.getTask(mob);
		if (task == null)
			return false;
		currentGoal = task.getNextTask();

		return task.getNextTask().isMobValid(mob);
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return !this.pathfinder.noPath();
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
		this.controller = null;
		this.pathfinder.clearPathEntity();
		this.mob.setPathPriority(PathNodeType.WATER, this.waterCost);
	}

	@Override
	public void updateTask()
	{
		if (currentGoal instanceof InteractionInventory)
		{
			InteractionInventory inter = (InteractionInventory) currentGoal;
			BlockPos target = inter.getPos();
			double x = target.getX() + 0.5;
			double y = target.getY() + 0.5;
			double z = target.getZ() + 0.5;
			this.mob.getLookHelper().setLookPosition(x, y, z, 10, this.mob.getVerticalFaceSpeed());

			if (mob.getDistanceSq(x, y, z) <= mob.interactDistance * mob.interactDistance)
			{
				inter.interact(mob);
				this.pathfinder.clearPathEntity();
				if (this.controller == null)
					return;
				Task task = this.controller.getTask(mob);
				if (task == null)
					return;
				if (task.shouldCycle())
					task.cycleFirstTask();
			}
			else if (--this.pathRecalcTime <= 0)
			{
				this.pathRecalcTime = 10;
				this.pathfinder.tryMoveToXYZ(x, y, z, followSpeed);
			}
		}
		else if (currentGoal instanceof InteractionMovement)
		{
			InteractionMovement inter = (InteractionMovement) currentGoal;
			BlockPos target = inter.getCurrentPos();
			double x = target.getX() + 0.5;
			double y = target.getY() + 0.5;
			double z = target.getZ() + 0.5;
			this.mob.getLookHelper().setLookPosition(x, y, z, 10, this.mob.getVerticalFaceSpeed());

			if (mob.getDistanceSq(x, y, z) <= mob.interactDistance * mob.interactDistance)
			{
				inter.cyclePos();
				this.pathfinder.clearPathEntity();
				if (this.controller == null)
					return;
				Task task = this.controller.getTask(mob);
				if (task == null)
					return;
				if (task.shouldCycle())
					task.cycleFirstTask();
			}
			else if (--this.pathRecalcTime <= 0)
			{
				this.pathRecalcTime = 10;
				this.pathfinder.tryMoveToXYZ(x, y, z, followSpeed);
			}
		}
		else
		{
			double x = controller.getPos().getX() + 0.5;
			double y = controller.getPos().getY() + 0.5;
			double z = controller.getPos().getZ() + 0.5;
			this.mob.getLookHelper().setLookPosition(x, y, z, 10, this.mob.getVerticalFaceSpeed());

			if (--this.pathRecalcTime <= 0)
			{
				this.pathRecalcTime = 10;
				this.pathfinder.tryMoveToXYZ(x, y, z, followSpeed);
			}
		}
	}
}
