package com.teamwizardry.inhumanresources.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import com.teamwizardry.inhumanresources.common.blocks.BlockBrainExtractor;
import com.teamwizardry.inhumanresources.common.blocks.BlockChipCreator;
import com.teamwizardry.inhumanresources.common.blocks.BlockMobController;
import com.teamwizardry.inhumanresources.common.blocks.BlockMobTrap;
import com.teamwizardry.inhumanresources.common.tile.TEBrainExtractor;
import com.teamwizardry.inhumanresources.common.tile.TEChipCreator;
import com.teamwizardry.inhumanresources.common.tile.TEMobController;
import com.teamwizardry.inhumanresources.common.tile.TEMobTrap;
import com.teamwizardry.inhumanresources.common.utils.lib.LibUtils;
import com.teamwizardry.librarianlib.common.base.block.BlockModContainer;

public class BlockRegistry
{
	public static BlockModContainer blockMobTrap;
	public static BlockModContainer blockBrainExtractor;
	public static BlockModContainer blockChipCreator;
	public static BlockModContainer blockMobController;
	
	public static void init()
	{
		blockMobTrap = new BlockMobTrap("blockMobTrap");
		blockBrainExtractor = new BlockBrainExtractor("blockBrainExtractor");
		blockChipCreator = new BlockChipCreator("blockChipCreator");
		blockMobController = new BlockMobController("blockMobController");
	}
	
	public static void tileEntityRegisty()
	{
		GameRegistry.registerTileEntity(TEMobTrap.class, LibUtils.customTileName("mobtrap"));
		GameRegistry.registerTileEntity(TEBrainExtractor.class, LibUtils.customTileName("brainextractor"));
		GameRegistry.registerTileEntity(TEChipCreator.class, LibUtils.customTileName("chipcreator"));
		GameRegistry.registerTileEntity(TEMobController.class, LibUtils.customTileName("mobController"));
	}
}
