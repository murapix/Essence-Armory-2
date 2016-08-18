package escapee.essencearmory2.common.tile;

import io.netty.util.internal.ThreadLocalRandom;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

/**
 * Created by SirShadow on 17. 08. 2016.
 */
public class TEMobTrap extends TileEntity implements ITickable
{

	@Override
	public void update()
	{
		AxisAlignedBB axis = new AxisAlignedBB(pos.add(0, 1, 0), pos.add(1, 3, 1)).expand(1, 1, 1);
		List<EntityLivingBase> zombies = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
		for (EntityLivingBase zomby : zombies)
		{
			if (!(zomby instanceof EntityPlayer))
			{
				zomby.setPosition(zomby.prevPosX, zomby.prevPosY, zomby.prevPosZ);
				worldObj.spawnParticle(EnumParticleTypes.DRAGON_BREATH, zomby.posX, zomby.posY + zomby.height, zomby.posZ, ThreadLocalRandom.current().nextDouble(0.01, 0.02), ThreadLocalRandom.current().nextDouble(0.1, 0.2), ThreadLocalRandom.current().nextDouble(0.01, 0.02));
			}
		}
	}
}