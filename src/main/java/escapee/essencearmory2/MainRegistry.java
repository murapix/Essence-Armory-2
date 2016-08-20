package escapee.essencearmory2;

import java.util.ArrayList;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import escapee.essencearmory2.common.blocks.BlockBrainExtractor;
import escapee.essencearmory2.common.blocks.BlockMobTrap;
import escapee.essencearmory2.common.blocks.base.BlockBaseEA;
import escapee.essencearmory2.common.blocks.base.TEBlockEA;
import escapee.essencearmory2.common.items.ItemIngot;
import escapee.essencearmory2.common.items.ItemMobBrain;
import escapee.essencearmory2.common.items.base.ItemBaseEA;
import escapee.essencearmory2.common.tile.TEBrainExtractor;
import escapee.essencearmory2.common.tile.TEMobTrap;
import escapee.essencearmory2.common.utils.helper.TextHelper;
import escapee.essencearmory2.lib.LibMain;

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

		public static final ArrayList<ItemBaseEA> ITEMS = new ArrayList<ItemBaseEA>();


		public static ItemBaseEA itemInfusedIngot = new ItemIngot("ingotInfused");
		public static ItemBaseEA endermanBrain = new ItemMobBrain(EntityEnderman.class, "endermanBrain");
		public static ItemBaseEA spiderBrain = new ItemMobBrain(EntitySpider.class, "spiderBrain");
		public static ItemBaseEA pigZombieBrain = new ItemMobBrain(EntityPigZombie.class, "pigZombieBrain");
		public static ItemBaseEA blazeBrain = new ItemMobBrain(EntityBlaze.class, "blazeBrain");
		public static ItemBaseEA creeperBrain = new ItemMobBrain(EntityCreeper.class, "creeperBrain");
		public static ItemBaseEA endermiteBrain = new ItemMobBrain(EntityEndermite.class, "endermiteBrain");
		public static ItemBaseEA guardianBrain = new ItemMobBrain(EntityGuardian.class, "guardianBrain");
		public static ItemBaseEA silverfishBrain = new ItemMobBrain(EntitySilverfish.class, "silverfishBrain");
		public static ItemBaseEA skeletonBrain = new ItemMobBrain(EntitySkeleton.class, "skeletonBrain");
		public static ItemBaseEA slimeBrain = new ItemMobBrain(EntitySlime.class, "slimeBrain");
		public static ItemBaseEA zombieBrain = new ItemMobBrain(EntityZombie.class, "zombieBrain");

		private static void registerItems()
		{
			for (ItemBaseEA itemBaseEA : ITEMS)
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

		public static final ArrayList<BlockBaseEA> BLOCKS = new ArrayList<BlockBaseEA>();

		public static TEBlockEA blockMobTrap = new BlockMobTrap("blockMobTrap");
		public static TEBlockEA blockBrainExtractor = new BlockBrainExtractor("blockBrainExtractor");

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
			// example:
			// GameRegistry.registerTileEntityWithAlternatives(TileEntityExample.class,
			// LibMain.LibUtils.customTileName("example"));
			GameRegistry.registerTileEntityWithAlternatives(TEMobTrap.class, LibMain.LibUtils.customTileName("mobtrap"));
			GameRegistry.registerTileEntityWithAlternatives(TEBrainExtractor.class, LibMain.LibUtils.customTileName("brainextractor"));
		}
	}

	public static class recipeRegistry
	{
		public static void registerRecipes()
		{

		}
	}
}
