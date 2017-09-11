package com.teamwizardry.inhumanresources.common.blocks;

import java.util.stream.Stream;

import com.teamwizardry.librarianlib.features.base.block.BlockModVariant;

import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class BlockProbabilityBeacon extends BlockModVariant
{
	public enum EnumAttunement implements IStringSerializable
	{
		NONE,
		FORTUNE,
		LOOTING,
		PORTAL;
		
		public static String[] names()
		{
			return Stream.of(EnumAttunement.values()).map(EnumAttunement::name).toArray(String[]::new);
		}

		@Override
		public String getName()
		{
			return name().toLowerCase();
		}
	}
	
	public BlockProbabilityBeacon(String name, Material material)
	{
		super(name, material, EnumAttunement.names());
	}
}
