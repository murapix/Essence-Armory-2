package com.teamwizardry.inhumanresources.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.teamwizardry.inhumanresources.InhumanResources;
import com.teamwizardry.inhumanresources.common.tile.TEChipCreator;
import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.librarianlib.common.base.block.BlockModContainer;

public class BlockChipCreator extends BlockModContainer
{
	public BlockChipCreator(String name)
	{
		super(name, Material.IRON);
		setCreativeTab(Util.tabEssence);
	}

	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state)
	{
		return new TEChipCreator();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote) return true;
		TileEntity te = world.getTileEntity(pos);
		if (!(te instanceof TEChipCreator)) return false;
		player.openGui(InhumanResources.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
}
