package com.teamwizardry.inhumanresources.init;

import static com.teamwizardry.inhumanresources.init.ResearchRegistry.*;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.teamwizardry.inhumanresources.common.crafting.ResearchCraftingRecipe;

public class RecipeRegistry
{
	public static void registerRecipes()
	{
		@SuppressWarnings("unused")
		ItemStack obsidianPlate = new ItemStack(ItemRegistry.component, 1, 0);
		ItemStack endstonePlate = new ItemStack(ItemRegistry.component, 1, 1);
		ItemStack bedrockPlate = new ItemStack(ItemRegistry.component, 1, 2);
		ItemStack mistwroughtPlate = new ItemStack(ItemRegistry.component, 1, 3);
		
		GameRegistry.addRecipe(new ResearchCraftingRecipe(BEDROCK_FORGING, ItemRegistry.bedrockSword, 
				"    A",
				"   AA",
				"  AA ",
				"CBB  ",
				"BC   ",
				'A', bedrockPlate, 'B', endstonePlate, 'C', Items.DIAMOND));
		
		GameRegistry.addRecipe(new ResearchCraftingRecipe(MIST_FORGING, ItemRegistry.mistwroughtSword,
				"    A",
				"  BAA",
				" BAA ",
				"DCD  ",
				"ED   ",
				'A', mistwroughtPlate, 'B', bedrockPlate, 'C', ItemRegistry.compressor, 'D', Items.IRON_INGOT, 'E', Items.DIAMOND));
	}
}
