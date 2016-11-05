package com.teamwizardry.inhumanresources.common.blocks;

import com.teamwizardry.inhumanresources.common.blocks.base.TEBlockEA;
import com.teamwizardry.inhumanresources.common.tile.TEBrainExtractor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBrainExtractor extends TEBlockEA
{
	public BlockBrainExtractor(String name)
	{
		super(name, Material.IRON);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TEBrainExtractor();
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return true;
	}
}
