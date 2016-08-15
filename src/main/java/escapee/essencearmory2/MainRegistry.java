package escapee.essencearmory2;

import escapee.essencearmory2.common.blocks.base.BlockBaseEA;
import escapee.essencearmory2.common.items.ItemIngot;
import escapee.essencearmory2.common.items.base.ItemBaseEA;
import escapee.essencearmory2.common.utils.helper.TextHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

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
        itemRegistry.registerItems();
        itemRegistry.itemRenderRegistry();


        /**
         * Blocks and tile entities
         */
        blockRegistry.registerBlocks();
        blockRegistry.blockRenderRegistry();
        blockRegistry.tileEntityRegisty();

        /**
         * Other
         */

        TextHelper.addColorsAndComponents();

        recipeRegistry.registerRecipes();
    }

    public static class itemRegistry
    {

        public static final ArrayList<ItemBaseEA>ITEMS = new ArrayList<ItemBaseEA>();

        public static ItemBaseEA itemInfusedIngot = new ItemIngot("ingotInfused");

        private static void registerItems()
        {
            for(ItemBaseEA itemBaseEA : ITEMS)
            {
                GameRegistry.register(itemBaseEA);
            }
        }

        private static void itemRenderRegistry()
        {
            ITEMS.forEach(ItemBaseEA::initModelsAndVariants);
        }
    }

    public static class blockRegistry
    {

        public static final ArrayList<BlockBaseEA>BLOCKS = new ArrayList<BlockBaseEA>();

        private static void registerBlocks()
        {

        }

        private static void blockRenderRegistry()
        {
            BLOCKS.forEach(BlockBaseEA::initModelsAndVariants);
        }

        private static void tileEntityRegisty()
        {
            //example: GameRegistry.registerTileEntityWithAlternatives(TileEntityExample.class, LibMain.LibUtils.customTileName("example"));
        }
    }

    public static class recipeRegistry
    {
        public static void registerRecipes()
        {

        }
    }
}
