package com.teamwizardry.inhumanresources.common.tile;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.teamwizardry.inhumanresources.init.ItemRegistry;

public class TEBrainExtractor extends TileEntity implements ITickable
{
	private IMob currentEntity;
	private static final int OPERATION_TIME = 200;
	private int operationProgress = 0;

	private static final Predicate<Entity> VALID_KNOWLEDGE_ENTITY = new Predicate<Entity>()
	{
		public boolean apply(@Nullable Entity entity)
		{
			return entity instanceof IMob;
		}
	};

	@Override
	public void update()
	{
		AxisAlignedBB axis = new AxisAlignedBB(pos, pos.down(2)).expand(1, 1, 1);
		List<EntityLivingBase> entities = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis, Predicates.and(EntitySelectors.NOT_SPECTATING, VALID_KNOWLEDGE_ENTITY));
		Vec3d center = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
		if (currentEntity != null && entities.contains(currentEntity))
		{
			if (operationProgress >= OPERATION_TIME)
			{
				operationProgress = 0;
				((EntityLivingBase) currentEntity).setDead();
				if (!worldObj.isRemote)
				{
					EntityItem item = new EntityItem(worldObj);
					item.setPosition(center.xCoord, center.yCoord + 0.5, center.zCoord);
					Class<? extends IMob> entityClass = currentEntity.getClass();

					if (entityClass == EntityEnderman.class)
					{
						item.setEntityItemStack(new ItemStack(ItemRegistry.endermanBrain));
						worldObj.spawnEntityInWorld(item);
					}
					else if (entityClass == EntitySpider.class)
					{
						item.setEntityItemStack(new ItemStack(ItemRegistry.spiderBrain));
						worldObj.spawnEntityInWorld(item);
					}
					else if (entityClass == EntityPigZombie.class)
					{
						item.setEntityItemStack(new ItemStack(ItemRegistry.pigZombieBrain));
						worldObj.spawnEntityInWorld(item);
					}
					else if (entityClass == EntityBlaze.class)
					{
						item.setEntityItemStack(new ItemStack(ItemRegistry.blazeBrain));
						worldObj.spawnEntityInWorld(item);
					}
					else if (entityClass == EntityCreeper.class)
					{
						item.setEntityItemStack(new ItemStack(ItemRegistry.creeperBrain));
						worldObj.spawnEntityInWorld(item);
					}
					else if (entityClass == EntityEndermite.class)
					{
						item.setEntityItemStack(new ItemStack(ItemRegistry.endermiteBrain));
						worldObj.spawnEntityInWorld(item);
					}
					else if (entityClass == EntityGuardian.class)
					{
						item.setEntityItemStack(new ItemStack(ItemRegistry.guardianBrain));
						worldObj.spawnEntityInWorld(item);
					}
					else if (entityClass == EntitySilverfish.class)
					{
						item.setEntityItemStack(new ItemStack(ItemRegistry.silverfishBrain));
						worldObj.spawnEntityInWorld(item);
					}
					else if (entityClass == EntitySkeleton.class)
					{
						item.setEntityItemStack(new ItemStack(ItemRegistry.skeletonBrain));
						worldObj.spawnEntityInWorld(item);
					}
					else if (entityClass == EntitySlime.class)
					{
						item.setEntityItemStack(new ItemStack(ItemRegistry.slimeBrain));
						worldObj.spawnEntityInWorld(item);
					}
					else if (entityClass == EntityZombie.class)
					{
						item.setEntityItemStack(new ItemStack(ItemRegistry.zombieBrain));
						worldObj.spawnEntityInWorld(item);
					}
				}
				currentEntity = null;
			}
			else operationProgress++;
		}
		else if (entities.size() > 0)
		{
			operationProgress = 0;
			IMob closest = (IMob) entities.get(0);
			double closestDist = center.squareDistanceTo(((Entity) closest).getPositionVector());
			for (EntityLivingBase entity : entities)
			{
				double entityDist = center.squareDistanceTo(entity.getPositionVector());
				if (entityDist < closestDist)
				{
					closestDist = entityDist;
					closest = (IMob) entity;
				}
			}
			currentEntity = closest;
		}
		else
		{
			operationProgress = 0;
			currentEntity = null;
		}
	}
}
