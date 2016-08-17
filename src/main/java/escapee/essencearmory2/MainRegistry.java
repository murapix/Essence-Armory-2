package escapee.essencearmory2;

import escapee.essencearmory2.common.blocks.BlockMobTrap;
import escapee.essencearmory2.common.blocks.base.BlockBaseEA;
import escapee.essencearmory2.common.items.base.ItemBaseEA;
import escapee.essencearmory2.common.tile.TEMobTrap;
import escapee.essencearmory2.common.utils.helper.TextHelper;
import escapee.essencearmory2.lib.LibMain;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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

        public static ItemBaseEA itemInfusedIngot = new ItemBaseEA("ingotInfused");

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

        public static BlockBaseEA blockMobTrap = new BlockMobTrap("blockMobTrap");

        private static void registerBlocks()
        {
            for (BlockBaseEA blockBaseEA : BLOCKS)
            {
                GameRegistry.register(blockBaseEA);
                GameRegistry.register(new ItemBlock(blockBaseEA).setRegistryName(blockBaseEA.getRegistryName()));
            }
        }

        private static void blockRenderRegistry()
        {
            BLOCKS.forEach(BlockBaseEA::initModelsAndVariants);
        }

        private static void tileEntityRegisty()
        {
            //example: GameRegistry.registerTileEntityWithAlternatives(TileEntityExample.class, LibMain.LibUtils.customTileName("example"));
            GameRegistry.registerTileEntityWithAlternatives(TEMobTrap.class, LibMain.LibUtils.customTileName("mobtrap"));
        }
    }

    public static class recipeRegistry
    {
        public static void registerRecipes()
        {

        }
    }
}
