package escapee.essencearmory2.lib;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityBossBase extends EntityMob
{
	private ArrayList<BossAttack> attacks;
	
	public static final String TAG_ATTACKS = "Attacks";

	@Override
	public void entityInit()
	{
		super.entityInit();
		//dataWatcher.addObject(20, 0);
	}

	public EntityBossBase(World world)
	{
		super(world);
		isImmuneToFire = true;
	}

	public void setAttacks(BossAttack... attacks)
	{
		this.attacks = new ArrayList<BossAttack>();
		for (BossAttack attack : attacks)
			this.attacks.add(attack);
	}

	public boolean spawn(EntityPlayer player, World world, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public boolean canDespawn()
	{
		return false;
	}

	@Override
	public void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
	{

	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (!worldObj.isRemote && worldObj.getDifficulty() == EnumDifficulty.PEACEFUL) setDead();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);

		NBTTagList attackList = new NBTTagList();
		for (BossAttack attack : attacks)
			attackList.appendTag(attack.writeAttackToNBT());

		compound.setTag(TAG_ATTACKS, attackList);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		ArrayList<BossAttack> attacks = new ArrayList<BossAttack>();
		NBTTagList attackList = (NBTTagList) compound.getTag(TAG_ATTACKS);
		for (int i = 0; i < attackList.tagCount(); i++)
		{
			try
			{
				NBTTagCompound tag = attackList.getCompoundTagAt(i);
				String name = tag.getString("Name");
				Constructor<?> c = AttackRegistry.getAttack(name);
				BossAttack attack = (BossAttack) c.newInstance(this);
				attacks.add(attack.readAttackFromNBT(attackList.getCompoundTagAt(i)));
			}
			catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
		BossAttack[] attackArray = new BossAttack[attacks.size()];
		for (int i = 0; i < attacks.size(); i++)
			attackArray[i] = attacks.get(i);
		setAttacks(attackArray);
	}
}