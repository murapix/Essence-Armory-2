package com.teamwizardry.inhumanresources.init;

import java.util.UUID;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import com.teamwizardry.inhumanresources.common.potions.PotionIhR;

public class PotionRegistry
{
	public static Potion REDSTONE_NEEDLE;
	
	public static void init()
	{
		REDSTONE_NEEDLE = new PotionIhR("redstone_needle", true, 0xFF0000, 0).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, UUID.randomUUID().toString(), -0.2, 2);;
	}
}
