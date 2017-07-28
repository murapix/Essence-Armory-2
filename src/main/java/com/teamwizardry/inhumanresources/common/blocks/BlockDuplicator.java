package com.teamwizardry.inhumanresources.common.blocks;

import com.teamwizardry.inhumanresources.common.blocks.tile.TEDuplicator;
import com.teamwizardry.librarianlib.features.base.block.BlockModContainer;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDuplicator extends BlockModContainer
{
	public BlockDuplicator(String name, Material material, String... variants)
	{
		super(name, material, variants);
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TEDuplicator();
	}
}
