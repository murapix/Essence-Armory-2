package com.teamwizardry.inhumanresources.common.blocks;

import javax.annotation.Nonnull;

import com.teamwizardry.inhumanresources.common.blocks.tile.TEProbabilityBeacon;
import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.librarianlib.features.base.block.tile.BlockModContainer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockProbabilityBeacon extends BlockModContainer
{
	public static final PropertyEnum<EnumAttunement> ATTUNEMENT = PropertyEnum.create("attunement", EnumAttunement.class);
	public static final PropertyBool ACTIVE = PropertyBool.create("active");
	
	public enum EnumAttunement implements IStringSerializable
	{
		NONE,
		FORTUNE,
		LOOTING,
		PORTAL;
		
		@Override
		public String getName()
		{
			return name().toLowerCase();
		}
	}
	
	public BlockProbabilityBeacon(String name, Material material)
	{
		super(name, material, Util.enumNames(EnumAttunement.class, name));
		setDefaultState(blockState.getBaseState().withProperty(ATTUNEMENT, EnumAttunement.NONE).withProperty(ACTIVE, false));
	}
	
	@Nonnull
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, ATTUNEMENT, ACTIVE);
	}
	
	@Nonnull
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		if (meta >= 2*EnumAttunement.values().length)
			return getDefaultState();
		if (meta >= EnumAttunement.values().length)
			return getDefaultState().withProperty(ATTUNEMENT, EnumAttunement.values()[meta - EnumAttunement.values().length]).withProperty(ACTIVE, true);
		return getDefaultState().withProperty(ATTUNEMENT, EnumAttunement.values()[meta]);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(ATTUNEMENT).ordinal() + (state.getValue(ACTIVE) ? EnumAttunement.values().length : 0);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		switch (state.getValue(ATTUNEMENT))
		{
			case FORTUNE:
				return new TEProbabilityBeacon(EnumAttunement.FORTUNE);
			case LOOTING:
				return new TEProbabilityBeacon(EnumAttunement.LOOTING);
			case PORTAL:
				return new TEProbabilityBeacon(EnumAttunement.PORTAL);
			default:
				return new TEProbabilityBeacon(EnumAttunement.NONE);
		}
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos from)
	{}
}
