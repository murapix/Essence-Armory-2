package com.teamwizardry.inhumanresources.common.blocks.base;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public abstract class TEBlockEA extends BlockBaseEA implements ITileEntityProvider
{
	public TEBlockEA(String name)
	{
		super(name);
	}
	
	public TEBlockEA(String name, Material material)
	{
		super(name, material);
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
}
