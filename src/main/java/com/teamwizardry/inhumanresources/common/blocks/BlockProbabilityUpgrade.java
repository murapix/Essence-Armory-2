package com.teamwizardry.inhumanresources.common.blocks;

import javax.annotation.Nonnull;

import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.librarianlib.features.base.block.BlockMod;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

public class BlockProbabilityUpgrade extends BlockMod
{
	public static final PropertyEnum<EnumUpgrade> UPGRADE = PropertyEnum.create("attunement", EnumUpgrade.class);
	
	public enum EnumUpgrade implements IStringSerializable
	{
		NONE,
		RANGE,
		LEVEL;
		
		@Override
		public String getName()
		{
			return name().toLowerCase();
		}
	}
	
	public BlockProbabilityUpgrade(String name, Material material)
	{
		super(name, material, Util.enumNames(EnumUpgrade.class, name));
		setDefaultState(blockState.getBaseState().withProperty(UPGRADE, EnumUpgrade.NONE));
	}
	
	@Nonnull
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, UPGRADE);
	}
	
	@Nonnull
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		if (meta >= EnumUpgrade.values().length)
			return getDefaultState();
		return getDefaultState().withProperty(UPGRADE, EnumUpgrade.values()[meta]);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(UPGRADE).ordinal();
	}
}
