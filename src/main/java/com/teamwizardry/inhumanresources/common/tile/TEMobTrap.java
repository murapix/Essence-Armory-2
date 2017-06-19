package com.teamwizardry.inhumanresources.common.tile;

import io.netty.util.internal.ThreadLocalRandom;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Created by SirShadow on 17. 08. 2016.
 */
public class TEMobTrap extends TileEntity implements ITickable
{
	private EntityLivingBase trappedEntity = null;

	@Override
	public void update()
	{
		AxisAlignedBB axis = new AxisAlignedBB(pos.add(0, 1, 0), pos.add(1, 3, 1));
		Vec3d center = new Vec3d(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
		
		List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, axis, Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>()
				{
			public boolean apply(@Nullable Entity entity)
			{
				return !(entity instanceof EntityPlayer);
			}
				}));
				
		if (!entities.contains(trappedEntity) && entities.size() > 0)
		{
			EntityLivingBase closest = entities.get(0);
			double closestDist = center.squareDistanceTo(((Entity) closest).getPositionVector());
			for (EntityLivingBase entity : entities)
			{
				double entityDist = center.squareDistanceTo(entity.getPositionVector());
				if (entityDist < closestDist)
				{
					closestDist = entityDist;
					closest = entity;
				}
			}
			trappedEntity = closest;
		}
		else if (trappedEntity != null)
		{
			trappedEntity.setPosition(center.x, center.y, center.z);
			// Debug
			world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, trappedEntity.posX, trappedEntity.posY + trappedEntity.height, trappedEntity.posZ, ThreadLocalRandom.current().nextDouble(0.01, 0.02), ThreadLocalRandom.current().nextDouble(0.1, 0.2), ThreadLocalRandom.current().nextDouble(0.01, 0.02));
		}

	}
}