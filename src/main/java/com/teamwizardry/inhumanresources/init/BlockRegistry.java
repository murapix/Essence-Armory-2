package com.teamwizardry.inhumanresources.init;

import com.teamwizardry.inhumanresources.common.blocks.BlockBedrockBore;
import com.teamwizardry.inhumanresources.common.blocks.BlockDuplicator;
import com.teamwizardry.inhumanresources.common.blocks.BlockProbabilityBeacon;
import com.teamwizardry.inhumanresources.common.blocks.BlockProbabilityUpgrade;
import com.teamwizardry.librarianlib.features.base.block.BlockMod;
import com.teamwizardry.librarianlib.features.base.block.tile.BlockModContainer;

import net.minecraft.block.material.Material;

public class BlockRegistry
{
	public static BlockModContainer blockBedrockBore;
	public static BlockModContainer blockDuplicator;
	public static BlockModContainer blockProbabilityBeacon;
	
	public static BlockMod blockProbabilityUpgrade;
	
	public static BlockMod blockObsidianPlated;
	
	public static void init()
	{
		blockBedrockBore = new BlockBedrockBore("block_bedrock_bore", Material.ROCK);
		blockDuplicator = new BlockDuplicator("block_duplicator", Material.IRON);
		blockProbabilityBeacon = new BlockProbabilityBeacon("block_probability_beacon", Material.IRON);
		blockProbabilityUpgrade = new BlockProbabilityUpgrade("block_probability_upgrade", Material.ROCK);
		
		blockObsidianPlated = new BlockMod("block_obsidian_plated", Material.ROCK);
	}
}
