package escapee.essencearmory2.init;

import escapee.essencearmory2.common.utils.helper.TextHelper;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class MainRegistry
{

	public static void register()
	{
		/**
		 * Items
		 */
		ItemRegistry.registerItems();
		ItemRegistry.itemRenderRegistry();

		/**
		 * Blocks and tile entities
		 */
		BlockRegistry.registerBlocks();
		BlockRegistry.blockRenderRegistry();
		BlockRegistry.tileEntityRegisty();

		/**
		 * Other
		 */

		TextHelper.addColorsAndComponents();

		RecipeRegistry.registerRecipes();
	}
}
