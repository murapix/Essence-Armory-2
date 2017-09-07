package com.teamwizardry.inhumanresources.init;

import com.teamwizardry.inhumanresources.common.items.ItemComponent;
import com.teamwizardry.inhumanresources.common.items.ItemMistwroughtAxe;
import com.teamwizardry.inhumanresources.common.items.ItemMistwroughtSword;
import com.teamwizardry.inhumanresources.common.items.ItemNeedle;
import com.teamwizardry.inhumanresources.common.items.ItemRedstoneArrow;
import com.teamwizardry.librarianlib.features.base.item.ItemMod;
import com.teamwizardry.librarianlib.features.base.item.ItemModArrow;
import com.teamwizardry.librarianlib.features.base.item.ItemModSword;
import com.teamwizardry.librarianlib.features.base.item.ItemModTool;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

public class ItemRegistry
{
	public static ToolMaterial bedrock = EnumHelper.addToolMaterial("bedrock", 4, 4000, 9, 7.5F, 0);
	public static ToolMaterial mistwrought = EnumHelper.addToolMaterial("mistwrought", 4, 5000, 10, 6, 0);

	public static ItemMod voidParticle;
	public static ItemMod obsidianHammer;
	public static ItemMod spike;
	
	public static ItemMod component;
	public static ItemMod compressor;
	public static ItemMod needle;
	public static ItemModArrow redstoneArrow;

	public static ItemModSword mistwroughtSword;
	public static ItemModSword mistwroughtClaw;
	public static ItemModTool mistwroughtAxe;

	public static void init()
	{
		voidParticle = new ItemComponent("voidMatter", "voidParticle", "voidClump");
		obsidianHammer = new ItemComponent("obsidianHammer");
		spike = new ItemComponent("spike", "ironSpike", "diamondSpike");
		
		component = new ItemComponent("plate", "plateObsidian", "plateEndstone", "plateBedrock", "plateMistwrought");
		compressor = new ItemComponent("compressor");
		needle = new ItemNeedle("needle", "needleGold", "needleRedstone");
		redstoneArrow = new ItemRedstoneArrow("redstoneArrow");

		mistwroughtSword = new ItemMistwroughtSword("mistwroughtSword", mistwrought);
		mistwroughtAxe = new ItemMistwroughtAxe("mistwroughtAxe", mistwrought);
		MinecraftForge.EVENT_BUS.register(mistwroughtSword);
	}
}
