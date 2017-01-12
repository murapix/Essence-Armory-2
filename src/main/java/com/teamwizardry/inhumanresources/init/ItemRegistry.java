package com.teamwizardry.inhumanresources.init;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import com.teamwizardry.inhumanresources.common.items.ItemBedrockSword;
import com.teamwizardry.inhumanresources.common.items.ItemComponent;
import com.teamwizardry.inhumanresources.common.items.ItemMistwroughtSword;
import com.teamwizardry.inhumanresources.common.items.ItemMobBrain;
import com.teamwizardry.inhumanresources.common.items.ItemNeedle;
import com.teamwizardry.inhumanresources.common.items.ItemRedstoneArrow;
import com.teamwizardry.inhumanresources.common.items.ItemResearchLog;
import com.teamwizardry.librarianlib.common.base.item.ItemMod;
import com.teamwizardry.librarianlib.common.base.item.ItemModArrow;
import com.teamwizardry.librarianlib.common.base.item.ItemModSword;

public class ItemRegistry
{
	public static ToolMaterial bedrock = EnumHelper.addToolMaterial("bedrock", 4, 4000, 9, 7.5F, 0);
	public static ToolMaterial mistwrought = EnumHelper.addToolMaterial("mistwrought", 4, 5000, 10, 6, 0);
	
	public static ItemMod itemResearchLog;
	
	public static ItemMod endermanBrain;
	public static ItemMod spiderBrain;
	public static ItemMod pigZombieBrain;
	public static ItemMod blazeBrain;
	public static ItemMod creeperBrain;
	public static ItemMod endermiteBrain;
	public static ItemMod guardianBrain;
	public static ItemMod silverfishBrain;
	public static ItemMod skeletonBrain;
	public static ItemMod slimeBrain;
	public static ItemMod zombieBrain;
	
	public static ItemMod component;
	public static ItemMod compressor;
	public static ItemMod needle;
	public static ItemModArrow redstoneArrow;
	
	public static ItemModSword bedrockSword;
	public static ItemModSword mistwroughtSword; 

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
		
		component = new ItemComponent("plate", "plateObsidian", "plateEndstone", "plateBedrock", "plateMistwrought");
		compressor = new ItemComponent("compressor");
		needle = new ItemNeedle("needle", "needleGold", "needleRedstone");
		redstoneArrow = new ItemRedstoneArrow("redstoneArrow");

		bedrockSword = new ItemBedrockSword("bedrockSword", bedrock);
		mistwroughtSword = new ItemMistwroughtSword("mistwroughtSword", mistwrought);
	}
}
