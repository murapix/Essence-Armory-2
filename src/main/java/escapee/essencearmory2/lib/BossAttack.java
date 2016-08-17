package escapee.essencearmory2.lib;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BossAttack
{
	// FINAL VARIABLES
	protected EntityBossBase boss;
	protected DamageSource damageType;
	protected float damage;
	protected int attackTimer;
	protected int duration;
	
	// Non-Final Variables
	protected int cooldown;
	
	public BossAttack(EntityBossBase boss)
	{
		this.boss = boss;
	}
	
	public BossAttack(EntityBossBase boss, DamageSource damageType, float damage, int attackTimer, int duration)
	{
		this.boss = boss;
		this.damageType = damageType;
		this.damage = damage;
		this.attackTimer = attackTimer;
		this.cooldown = attackTimer;
		this.duration = duration;
	}
	
	public abstract void update(List<EntityPlayer> players);
	
	@SideOnly(Side.CLIENT)
	public abstract void drawParticles();
	
	public abstract void onBossDeath();
	
	public abstract int getCrystalColor();

	public abstract NBTTagCompound writeAttackToNBT();
	
	public abstract BossAttack readAttackFromNBT(NBTTagCompound compound);

	public abstract String getName();
}