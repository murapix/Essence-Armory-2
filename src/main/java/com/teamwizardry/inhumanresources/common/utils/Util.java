package com.teamwizardry.inhumanresources.common.utils;

import java.util.UUID;

import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;
import com.teamwizardry.inhumanresources.init.ItemRegistry;
import com.teamwizardry.inhumanresources.init.PotionRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Util
{
	public static CreativeTabs tabEssence = new CreativeTabs(ModInfo.MOD_ID)
	{
		@Override
		public String getTabLabel()
		{
			return ModInfo.MOD_ID;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(ItemRegistry.voidParticle);
		}
	};

	public static EntityPlayer getPlayerFromUUID(UUID uuid)
	{
		if (uuid == null) return null;
		
		Entity entity = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityFromUuid(uuid);
		if (entity instanceof EntityPlayer) return (EntityPlayer) entity;
		
		return null;
	}
	
	public static boolean isCritting(EntityLivingBase entity)
	{
		if (entity.isPotionActive(PotionRegistry.MAX_CRIT)) return true;
		if (entity.fallDistance <= 0) return false;
		if (entity.onGround) return false;
		if (entity.isOnLadder()) return false;
		if (entity.isInWater()) return false;
		if (entity.isPotionActive(MobEffects.BLINDNESS)) return false;
		return !entity.isRiding();
	}
}
