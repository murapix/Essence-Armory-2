package com.teamwizardry.inhumanresources.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.teamwizardry.inhumanresources.common.tile.TEBrainExtractor;
import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.librarianlib.common.base.block.BlockModContainer;

public class BlockBrainExtractor extends BlockModContainer
{
	public BlockBrainExtractor(String name)
	{
		super(name, Material.IRON);
		setCreativeTab(Util.tabEssence);
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TEBrainExtractor();
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return true;
	}
}
