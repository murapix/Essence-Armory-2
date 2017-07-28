package com.teamwizardry.inhumanresources.common.blocks;

import com.teamwizardry.inhumanresources.common.blocks.tile.TEBedrockBore;
import com.teamwizardry.librarianlib.features.base.block.BlockModContainer;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBedrockBore extends BlockModContainer
{
	public BlockBedrockBore(String name, Material materialIn, String... variants)
	{
		super(name, materialIn, variants);
	}

	@Override
	public TileEntity createTileEntity(World arg0, IBlockState arg1)
	{
		return new TEBedrockBore();
	}
}
