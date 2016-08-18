package escapee.essencearmory2.common.tile;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class TEBrainExtractor extends TileEntity implements ITickable
{
	private EntityLivingBase currentEntity;
	private static final int OPERATION_TIME = 200;
	private int operationProgress = 0;
	
	@Override
	public void update()
	{
		AxisAlignedBB axis = new AxisAlignedBB(pos, pos.down(2)).expand(1, 1, 1);
		List<EntityLivingBase> entities = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
		Vec3d center = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
		if (currentEntity != null && entities.contains(currentEntity))
		{
			if (operationProgress >= OPERATION_TIME)
			{
				operationProgress = 0;
				EntityItem item = new EntityItem(worldObj);
				item.setPosition(center.xCoord, center.yCoord + 0.5, center.zCoord);
				if (currentEntity instanceof EntityZombie)
				{
					item.setEntityItemStack(new ItemStack(Items.ROTTEN_FLESH));
					worldObj.spawnEntityInWorld(item);
				}
				else if (currentEntity instanceof EntitySkeleton)
				{
					item.setEntityItemStack(new ItemStack(Items.BONE));
					worldObj.spawnEntityInWorld(item);
				}
				else if (currentEntity instanceof EntityCreeper)
				{
					item.setEntityItemStack(new ItemStack(Items.GUNPOWDER));
					worldObj.spawnEntityInWorld(item);
				}
			}
			else operationProgress++;
		}
		else if (entities.size() > 0)
		{
			EntityLivingBase closest = entities.get(0);
			double closestDist = center.squareDistanceTo(closest.getPositionVector());
			for (EntityLivingBase entity : entities)
			{
				double entityDist = center.squareDistanceTo(entity.getPositionVector());
				if (entityDist < closestDist)
				{
					closestDist = entityDist;
					closest = entity;
				}
			}
			currentEntity = closest;
		}
		else currentEntity = null;
	}
}
