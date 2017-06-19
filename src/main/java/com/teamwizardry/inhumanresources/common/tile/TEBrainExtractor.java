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
		List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, axis, Predicates.and(EntitySelectors.NOT_SPECTATING, VALID_KNOWLEDGE_ENTITY));
		Vec3d center = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
		if (currentEntity != null && entities.contains(currentEntity))
		{
			if (operationProgress >= OPERATION_TIME)
			{
				operationProgress = 0;
				((EntityLivingBase) currentEntity).setDead();
				if (!world.isRemote)
				{
					EntityItem item = new EntityItem(world);
					item.setPosition(center.x, center.y + 0.5, center.z);
					Class<? extends IMob> entityClass = currentEntity.getClass();

					if (entityClass == EntityEnderman.class)
					{
						item.setItem(new ItemStack(ItemRegistry.endermanBrain));
						world.spawnEntity(item);
					}
					else if (entityClass == EntitySpider.class)
					{
						item.setItem(new ItemStack(ItemRegistry.spiderBrain));
						world.spawnEntity(item);
					}
					else if (entityClass == EntityPigZombie.class)
					{
						item.setItem(new ItemStack(ItemRegistry.pigZombieBrain));
						world.spawnEntity(item);
					}
					else if (entityClass == EntityBlaze.class)
					{
						item.setItem(new ItemStack(ItemRegistry.blazeBrain));
						world.spawnEntity(item);
					}
					else if (entityClass == EntityCreeper.class)
					{
						item.setItem(new ItemStack(ItemRegistry.creeperBrain));
						world.spawnEntity(item);
					}
					else if (entityClass == EntityEndermite.class)
					{
						item.setItem(new ItemStack(ItemRegistry.endermiteBrain));
						world.spawnEntity(item);
					}
					else if (entityClass == EntityGuardian.class)
					{
						item.setItem(new ItemStack(ItemRegistry.guardianBrain));
						world.spawnEntity(item);
					}
					else if (entityClass == EntitySilverfish.class)
					{
						item.setItem(new ItemStack(ItemRegistry.silverfishBrain));
						world.spawnEntity(item);
					}
					else if (entityClass == EntitySkeleton.class)
					{
						item.setItem(new ItemStack(ItemRegistry.skeletonBrain));
						world.spawnEntity(item);
					}
					else if (entityClass == EntitySlime.class)
					{
						item.setItem(new ItemStack(ItemRegistry.slimeBrain));
						world.spawnEntity(item);
					}
					else if (entityClass == EntityZombie.class)
					{
						item.setItem(new ItemStack(ItemRegistry.zombieBrain));
						world.spawnEntity(item);
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
