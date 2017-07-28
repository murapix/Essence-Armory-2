package com.teamwizardry.inhumanresources.init;

import net.minecraft.item.ItemStack;

public class RecipeRegistry
{
	public static void registerRecipes()
	{
		ItemStack obsidianPlate = new ItemStack(ItemRegistry.component, 1, 0);
		ItemStack endstonePlate = new ItemStack(ItemRegistry.component, 1, 1);
		ItemStack bedrockPlate = new ItemStack(ItemRegistry.component, 1, 2);
		ItemStack mistwroughtPlate = new ItemStack(ItemRegistry.component, 1, 3);
	}
}
