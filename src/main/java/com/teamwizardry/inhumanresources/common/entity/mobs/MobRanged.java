package com.teamwizardry.inhumanresources.common.entity.mobs;

import com.teamwizardry.inhumanresources.common.entity.ai.MobAIRangedAttack;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class MobRanged extends MobBase
{
	protected static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.<Boolean>createKey(MobSkeleton.class, DataSerializers.BOOLEAN);
	
	public MobRanged(World world)
	{
		super(world);
	}
	
	@Override
	protected void initEntityAI()
	{
		super.initEntityAI();
		this.tasks.addTask(1, new MobAIRangedAttack(this, 1, 20, 15));	
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityDragon>(this, EntityDragon.class, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityGhast>(this, EntityGhast.class, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityMob>(this, EntityMob.class, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityShulker>(this, EntityShulker.class, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntitySlime>(this, EntitySlime.class, true));
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(SWINGING_ARMS, Boolean.valueOf(false));
	}

	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	{
		super.setEquipmentBasedOnDifficulty(difficulty);
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
	}
	
	public void attackEntityWithRangedAttack(EntityLivingBase target, float arrowSpeed)
	{
		EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.worldObj, this);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entitytippedarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        entitytippedarrow.setThrowableHeading(d0, d1 + d3 * 0.2, d2, 1.6F, (float)(14 - this.worldObj.getDifficulty().getDifficultyId() * 4));
        int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.POWER, this);
        int j = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.PUNCH, this);
        DifficultyInstance difficultyinstance = this.worldObj.getDifficultyForLocation(new BlockPos(this));
        entitytippedarrow.setDamage((double)(arrowSpeed * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.getDifficulty().getDifficultyId() * 0.11F));

        if (i > 0)
            entitytippedarrow.setDamage(entitytippedarrow.getDamage() + (double)i * 0.5D + 0.5D);

        if (j > 0)
            entitytippedarrow.setKnockbackStrength(j);

        boolean flag = this.isBurning() && difficultyinstance.func_190083_c() && this.rand.nextBoolean();
        flag = flag || EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FLAME, this) > 0;

        if (flag)
            entitytippedarrow.setFire(100);

        ItemStack itemstack = this.getHeldItem(EnumHand.OFF_HAND);

        if (itemstack != null && itemstack.getItem() == Items.TIPPED_ARROW)
            entitytippedarrow.setPotionEffect(itemstack);

        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entitytippedarrow);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isSwingingArms()
	{
		return ((Boolean) this.dataManager.get(SWINGING_ARMS)).booleanValue();
	}
	
	public void setSwingingArms(boolean swingingArms)
	{
		this.dataManager.set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
	}
}
