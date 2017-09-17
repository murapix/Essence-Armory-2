package com.teamwizardry.inhumanresources.init;

import java.util.LinkedList;
import java.util.List;

import com.teamwizardry.inhumanresources.common.potions.PotionCritBonus;
import com.teamwizardry.inhumanresources.common.potions.PotionMaxCrit;
import com.teamwizardry.inhumanresources.common.potions.PotionRedstoneNeedle;
import com.teamwizardry.inhumanresources.common.potions.PotionStun;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionRegistry
{
	public static List<Potion> potions = new LinkedList<>();
	
	public static Potion REDSTONE_NEEDLE;
	public static Potion STUN;
	public static Potion CRIT_BONUS;
	public static Potion MAX_CRIT;
	
	public static void init()
	{
		int index = 0;
		REDSTONE_NEEDLE = new PotionRedstoneNeedle("redstone_needle", true, 0xFF0000, index++);
		STUN = new PotionStun("stun", true, 0x000000, index++);
		CRIT_BONUS = new PotionCritBonus("crit_bonus", false, 0x7F7F7F, index++);
		MAX_CRIT = new PotionMaxCrit("max_crit", false, 0xFF7F7F, index++);
	}
	
	@SubscribeEvent
	public void registerPotions(RegistryEvent.Register<Potion> event)
	{
		potions.forEach(potion -> event.getRegistry().register(potion));
	}

}
