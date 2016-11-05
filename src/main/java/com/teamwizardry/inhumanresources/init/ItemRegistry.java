package com.teamwizardry.inhumanresources.init;

import com.teamwizardry.inhumanresources.common.items.ItemMobBrain;
import com.teamwizardry.inhumanresources.common.items.ItemResearchLog;
import com.teamwizardry.inhumanresources.common.items.base.ItemBaseEA;

public class ItemRegistry
{
	public static ItemBaseEA itemResearchLog;
	
	public static ItemBaseEA endermanBrain;
	public static ItemBaseEA spiderBrain;
	public static ItemBaseEA pigZombieBrain;
	public static ItemBaseEA blazeBrain;
	public static ItemBaseEA creeperBrain;
	public static ItemBaseEA endermiteBrain;
	public static ItemBaseEA guardianBrain;
	public static ItemBaseEA silverfishBrain;
	public static ItemBaseEA skeletonBrain;
	public static ItemBaseEA slimeBrain;
	public static ItemBaseEA zombieBrain;

	public static void init()
	{
		itemResearchLog = new ItemResearchLog("researchLog");
		endermanBrain = new ItemMobBrain("endermanBrain");
		spiderBrain = new ItemMobBrain("spiderBrain");
		pigZombieBrain = new ItemMobBrain("pigZombieBrain");
		blazeBrain = new ItemMobBrain("blazeBrain");
		creeperBrain = new ItemMobBrain("creeperBrain");
		endermiteBrain = new ItemMobBrain("endermiteBrain");
		guardianBrain = new ItemMobBrain("guardianBrain");
		silverfishBrain = new ItemMobBrain("silverfishBrain");
		skeletonBrain = new ItemMobBrain("skeletonBrain");
		slimeBrain = new ItemMobBrain("slimeBrain");
		zombieBrain = new ItemMobBrain("zombieBrain");

	}
}
