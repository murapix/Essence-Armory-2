package escapee.essencearmory2.common.entity.mobs;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class MobSkeleton extends MobRanged
{
	public MobSkeleton(World world)
	{
		super(world);
	}
	
	@Override
	protected void initEntityAI()
	{
		super.initEntityAI();
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
	}
}
