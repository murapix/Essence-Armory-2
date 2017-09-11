package com.teamwizardry.inhumanresources.init;

import com.teamwizardry.inhumanresources.common.blocks.BlockBedrockBore;
import com.teamwizardry.inhumanresources.common.blocks.BlockDuplicator;
import com.teamwizardry.inhumanresources.common.blocks.BlockProbabilityBeacon;
import com.teamwizardry.librarianlib.features.base.block.BlockModContainer;
import com.teamwizardry.librarianlib.features.base.block.BlockModVariant;

import net.minecraft.block.material.Material;

public class BlockRegistry
{
	public static BlockModContainer blockBedrockBore;
	public static BlockModContainer blockDuplicator;
	public static BlockModVariant blockProbabilityBeacon;
	
	public static void init()
	{
		blockBedrockBore = new BlockBedrockBore("block_bedrock_bore", Material.ROCK);
		blockDuplicator = new BlockDuplicator("block_duplicator", Material.IRON);
		blockProbabilityBeacon = new BlockProbabilityBeacon("block_probability_beacon", Material.IRON);
	}
}
