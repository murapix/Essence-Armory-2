package com.teamwizardry.inhumanresources.init;

import net.minecraft.potion.Potion;
import com.teamwizardry.inhumanresources.common.potions.PotionRedstoneNeedle;

public class PotionRegistry
{
	public static Potion REDSTONE_NEEDLE;
	
	public static void init()
	{
		REDSTONE_NEEDLE = new PotionRedstoneNeedle("redstone_needle", true, 0xFF0000, 0);
	}
}
