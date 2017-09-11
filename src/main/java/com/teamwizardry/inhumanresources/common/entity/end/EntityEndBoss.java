package com.teamwizardry.inhumanresources.common.entity.end;

import java.util.Random;

import com.teamwizardry.inhumanresources.InhumanResources;
import com.teamwizardry.inhumanresources.common.utils.helper.TextHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityEndBoss extends EntityMob
{
	public float range = 64;
	// public static final DataParameter<type> name = EntityDataManager.<type>createKey(EndBoss.class, DataSerializers.TYPE);

	private Random random = new Random();
	
	public SoundEvent ambientSound;
	public SoundEvent hurtSound;
	public SoundEvent dieSound;
	
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
	
	public EntityEndBoss(World world)
	{
		super(world);
		setSize(2, 4);
		this.noClip = true;
		this.isAirBorne = true;
		this.experienceValue = 400;
		ambientSound = new SoundEvent(new ResourceLocation(InhumanResources.MOD_ID + ":endBossAmbient"));
		hurtSound = new SoundEvent(new ResourceLocation(InhumanResources.MOD_ID + ":endBossHurt"));
		dieSound = new SoundEvent(new ResourceLocation(InhumanResources.MOD_ID + ":endBossDie"));
	}
	
	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentString(TextHelper.PURPLE + "Facet of the End");
	}
	
	@Override
	public boolean isNonBoss()
	{
		return false;
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
//		getDataManager().register(name, Type.valueOf(default));
	}
	
	@Override
	public void collideWithEntity(Entity entity)
	{
		if (this.getAttackTarget() != null && this.getHealth() > 0)
		{
			if (entity.getUniqueID().compareTo(this.getAttackTarget().getUniqueID()) == 0)
			{
				EntityLivingBase target = (EntityLivingBase) entity;
				target.attackEntityFrom(new DamageSource("endBoss"), 20);
				float magnitude = MathHelper.sqrt((float) (motionX*motionX + motionY*motionY));
				target.knockBack(this, magnitude, -motionX / magnitude, -motionZ / magnitude);
				target.attackEntityAsMob(this);
				target.setRevengeTarget(this);
			}
		}
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;
		this.posX = this.prevPosX;
		this.posY = this.prevPosY;
		this.posZ = this.prevPosZ;
	}
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source)
	{
		return false;
	}
	
	@Override
	public int getBrightnessForRender(float partialTicks)
	{
		return 255;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		getEntityWorld().playSound(posX, posY, posZ, hurtSound, SoundCategory.NEUTRAL, random.nextFloat()*0.1F + 0.95F, random.nextFloat()*0.1F + 0.95F, false);
		if (source.getTrueSource() instanceof EntityLivingBase)
		{
			this.setAttackTarget((EntityLivingBase) source.getTrueSource());
		}
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	public void damageEntity(DamageSource source, float amount)
	{
		if (this.getHealth() - amount <= 0)
		{
			this.setHealth(1);
			this.bossInfo.setPercent(0);
			
			if (source.getTrueSource() instanceof EntityPlayer)
			{
//				EntityPlayer player = (EntityPlayer) source.getEntity();
//				if (!player.hasAchievement(AchievementRegistry.endBoss))
//				{
//					PlayerManager.addAchievement(player, AchievementRegistry.endBoss);
//				}
			}
		}
		else
		{
			super.damageEntity(source, amount);
			this.bossInfo.setPercent(getHealth() / getMaxHealth());
		}
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		if (entity instanceof EntityLivingBase)
		{
			this.setAttackTarget((EntityLivingBase) entity);
		}
		return super.attackEntityAsMob(entity);
	}
	
	@Override
	public boolean isAIDisabled()
	{
		return false;
	}
	
	@Override
	public void setDead()
	{
		super.setDead();
		this.bossInfo.setVisible(false);
		getEntityWorld().playSound(posX, posY, posZ, dieSound, SoundCategory.NEUTRAL, random.nextFloat()*0.1F + 0.95F, random.nextFloat()*0.1F + 0.95F, false);
	}
	
	@Override
	public boolean canDespawn()
	{
		return false;
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(Short.MAX_VALUE);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
	}
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
	}
	
	@Override
	public void addTrackingPlayer(EntityPlayerMP player)
	{
		super.addTrackingPlayer(player);
		bossInfo.addPlayer(player);
	}
	
	@Override
	public void removeTrackingPlayer(EntityPlayerMP player)
	{
		super.removeTrackingPlayer(player);
		bossInfo.removePlayer(player);
	}
}