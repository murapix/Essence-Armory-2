package escapee.essencearmory2.common.entity.mobs;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class MobZombie extends MobBase
{
	public MobZombie(World world)
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
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2);
	}
}
