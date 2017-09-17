package com.teamwizardry.inhumanresources.common.blocks;

import java.util.List;

import com.teamwizardry.inhumanresources.InhumanResources;
import com.teamwizardry.inhumanresources.common.blocks.tile.TEBedrockBore;
import com.teamwizardry.librarianlib.features.base.block.tile.BlockModContainer;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBedrockBore extends BlockModContainer
{
	public BlockBedrockBore(String name, Material materialIn, String... variants)
	{
		super(name, materialIn, variants);
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TEBedrockBore();
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> items, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		super.getDrops(items, world, pos, state, fortune);
		
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TEBedrockBore)
			for (ItemStack item : ((TEBedrockBore) te).items)
				if (item != ItemStack.EMPTY)
					items.add(item);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float x, float y, float z)
	{
		player.openGui(InhumanResources.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
}
