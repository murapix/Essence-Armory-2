package com.teamwizardry.inhumanresources.common.entity.ai;

import com.teamwizardry.inhumanresources.common.entity.mobs.MobRanged;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.util.EnumHand;

public class MobAIRangedAttack extends EntityAIBase
{
	private final MobRanged entity;
	private final double moveSpeedAmp;
	private int attackCooldown;
	private final float maxAttackDistance;
	private int attackTime = -1;
	private int seeTime;
	
	public MobAIRangedAttack(MobRanged mob, double speedAmplifier, int delay, float maxDistance)
	{
		this.entity = mob;
		this.moveSpeedAmp = speedAmplifier;
		this.attackCooldown = delay;
		this.maxAttackDistance = maxDistance * maxDistance;
		this.setMutexBits(3);
	}
	
	public void setAttackCooldown(int cooldown)
	{
		this.attackCooldown = cooldown;
	}
	
	public boolean shouldExecute()
	{
		return this.entity.getAttackTarget() == null ? false : this.isBowInMainhand();
	}
	
	protected boolean isBowInMainhand()
	{
		return this.entity.getHeldItemMainhand() != null && this.entity.getHeldItemMainhand().getItem() == Items.BOW;
	}
	
	public boolean continueExecuting()
	{
		return (this.shouldExecute() || !this.entity.getNavigator().noPath()) && this.isBowInMainhand();
	}
	
	public void startExecuting()
	{
		super.startExecuting();
		this.entity.setSwingingArms(true);
	}
	
	public void resetTask()
	{
		super.resetTask();
		this.entity.setSwingingArms(false);
		this.seeTime = 0;
		this.attackTime = -1;
		this.entity.resetActiveHand();
	}
	
	public void updateTask()
	{
		EntityLivingBase entity = this.entity.getAttackTarget();
		
		if (entity != null)
		{
			double dist = this.entity.getDistanceSq(entity.posX, entity.getEntityBoundingBox().minY, entity.posZ);
			boolean flag = this.entity.getEntitySenses().canSee(entity);
			boolean flag1 = this.seeTime > 0;
			
			if (flag != flag1)
				this.seeTime = 0;
			if (flag)
				this.seeTime++;
			else
				this.seeTime--;
			
			if (dist <= (double)this.maxAttackDistance && this.seeTime >= 20)
				this.entity.getNavigator().clearPathEntity();
			else
				this.entity.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeedAmp);
			
			this.entity.getLookHelper().setLookPositionWithEntity(entity, 30, 30);
			
			if (this.entity.isHandActive())
			{
				if (!flag && this.seeTime < -60)
					this.entity.resetActiveHand();
				else if (flag)
				{
					int i = this.entity.getItemInUseMaxCount();
					
					if (i >= 20)
					{
						this.entity.resetActiveHand();
						this.entity.attackEntityWithRangedAttack(entity, ItemBow.getArrowVelocity(i));
						this.attackTime = this.attackCooldown;
					}
				}
			}
			else if (--this.attackTime <= 0 && this.seeTime >= 60)
				this.entity.setActiveHand(EnumHand.MAIN_HAND);
		}
	}
}
