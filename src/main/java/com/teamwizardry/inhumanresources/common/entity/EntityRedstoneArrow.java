package com.teamwizardry.inhumanresources.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import com.teamwizardry.inhumanresources.init.ItemRegistry;
import com.teamwizardry.inhumanresources.init.PotionRegistry;

public class EntityRedstoneArrow extends EntityArrow
{
	public EntityRedstoneArrow(World world)
	{
		super(world);
	}
	
	public EntityRedstoneArrow(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}
	
	public EntityRedstoneArrow(World world, EntityLivingBase shooter)
	{
		super(world, shooter);
	}
	
	public void onUpdate()
	{
		super.onUpdate();

        if (this.worldObj.isRemote)
        {
            if (this.inGround)
            {
                if (this.timeInGround % 5 == 0)
                {
                    this.spawnPotionParticles(1);
                }
            }
            else
            {
                this.spawnPotionParticles(2);
            }
        }
        else if (this.inGround && this.timeInGround != 0  && this.timeInGround >= 600)
        {
            this.worldObj.setEntityState(this, (byte)0);
        }
	}
	
	private void spawnPotionParticles(int particleCount)
    {
		int i = PotionRegistry.REDSTONE_NEEDLE.getLiquidColor();

        if (i != 0 && particleCount > 0)
        {
            double d0 = (double)(i >> 16 & 255) / 255.0D;
            double d1 = (double)(i >> 8 & 255) / 255.0D;
            double d2 = (double)(i >> 0 & 255) / 255.0D;

            for (int j = 0; j < particleCount; ++j)
            {
                this.worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, d0, d1, d2, new int[0]);
            }
        }
    }
	
	@Override
	protected void arrowHit(EntityLivingBase living)
	{
		super.arrowHit(living);
		Potion redstoneNeedle = PotionRegistry.REDSTONE_NEEDLE;
		PotionEffect prevEffect = living.getActivePotionEffect(redstoneNeedle);
		if (prevEffect != null)
		{
			int prevAmplifier = prevEffect.getAmplifier();
			if (prevAmplifier < 4)
				living.addPotionEffect(new PotionEffect(redstoneNeedle, 600, prevAmplifier + 1));
			else
				living.addPotionEffect(new PotionEffect(redstoneNeedle, 600, prevAmplifier));
		}
		living.addPotionEffect(new PotionEffect(redstoneNeedle, 600, 0));
	}
	
	protected ItemStack getArrowStack()
	{
		return new ItemStack(ItemRegistry.redstoneArrow);
	}
	
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 0)
        {
            int i = PotionRegistry.REDSTONE_NEEDLE.getLiquidColor();

            if (i > 0)
            {
                double d0 = (double)(i >> 16 & 255) / 255.0D;
                double d1 = (double)(i >> 8 & 255) / 255.0D;
                double d2 = (double)(i >> 0 & 255) / 255.0D;

                for (int j = 0; j < 20; ++j)
                {
                    this.worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, d0, d1, d2, new int[0]);
                }
            }
        }
        else
        {
            super.handleStatusUpdate(id);
        }
	}
}
