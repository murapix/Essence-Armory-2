package com.teamwizardry.inhumanresources.init;

import com.teamwizardry.inhumanresources.common.blocks.BlockBrainExtractor;
import com.teamwizardry.inhumanresources.common.blocks.BlockChipCreator;
import com.teamwizardry.inhumanresources.common.blocks.BlockMobController;
import com.teamwizardry.inhumanresources.common.blocks.BlockMobTrap;
import com.teamwizardry.inhumanresources.common.blocks.base.TEBlockEA;
import com.teamwizardry.inhumanresources.common.tile.TEBrainExtractor;
import com.teamwizardry.inhumanresources.common.tile.TEChipCreator;
import com.teamwizardry.inhumanresources.common.tile.TEMobController;
import com.teamwizardry.inhumanresources.common.tile.TEMobTrap;
import com.teamwizardry.inhumanresources.common.utils.lib.LibUtils;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistry
{
	public static TEBlockEA blockMobTrap;
	public static TEBlockEA blockBrainExtractor;
	public static TEBlockEA blockChipCreator;
	public static TEBlockEA blockMobController;
	
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
