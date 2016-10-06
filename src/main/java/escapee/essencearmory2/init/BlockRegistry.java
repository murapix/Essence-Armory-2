package escapee.essencearmory2.init;

import java.util.ArrayList;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import escapee.essencearmory2.common.blocks.BlockBrainExtractor;
import escapee.essencearmory2.common.blocks.BlockChipCreator;
import escapee.essencearmory2.common.blocks.BlockMobController;
import escapee.essencearmory2.common.blocks.BlockMobTrap;
import escapee.essencearmory2.common.blocks.base.BlockBaseEA;
import escapee.essencearmory2.common.blocks.base.TEBlockEA;
import escapee.essencearmory2.common.tile.TEBrainExtractor;
import escapee.essencearmory2.common.tile.TEChipCreator;
import escapee.essencearmory2.common.tile.TEMobController;
import escapee.essencearmory2.common.tile.TEMobTrap;
import escapee.essencearmory2.lib.LibUtils;

public class BlockRegistry
{
	public static final ArrayList<BlockBaseEA> BLOCKS = new ArrayList<BlockBaseEA>();

	public static TEBlockEA blockMobTrap = new BlockMobTrap("blockMobTrap");
	public static TEBlockEA blockBrainExtractor = new BlockBrainExtractor("blockBrainExtractor");
	public static TEBlockEA blockChipCreator = new BlockChipCreator("blockChipCreator");
	public static TEBlockEA blockMobController = new BlockMobController("blockMobController");

	public static void registerBlocks()
	{
		for (BlockBaseEA blockBaseEA : BLOCKS)
		{
			GameRegistry.register(blockBaseEA);
			GameRegistry.register(new ItemBlock(blockBaseEA).setRegistryName(blockBaseEA.getRegistryName()));
		}
	}

	public static void blockRenderRegistry()
	{
		BLOCKS.forEach(BlockBaseEA::initModelsAndVariants);
	}

	public static void tileEntityRegisty()
	{
		// example:
		// GameRegistry.registerTileEntityWithAlternatives(TileEntityExample.class,
		// LibUtils.customTileName("example"));
		GameRegistry.registerTileEntityWithAlternatives(TEMobTrap.class, LibUtils.customTileName("mobtrap"));
		GameRegistry.registerTileEntityWithAlternatives(TEBrainExtractor.class, LibUtils.customTileName("brainextractor"));
		GameRegistry.registerTileEntityWithAlternatives(TEChipCreator.class, LibUtils.customTileName("chipcreator"));
		GameRegistry.registerTileEntityWithAlternatives(TEMobController.class, LibUtils.customTileName("mobController"));
	}
}
