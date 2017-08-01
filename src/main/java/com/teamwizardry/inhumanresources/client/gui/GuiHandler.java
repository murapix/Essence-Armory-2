package com.teamwizardry.inhumanresources.client.gui;

import com.teamwizardry.inhumanresources.client.gui.block.GuiBedrockBore;
import com.teamwizardry.inhumanresources.client.gui.container.ContainerBedrockBore;
import com.teamwizardry.inhumanresources.common.blocks.tile.TEBedrockBore;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case 0:
				TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
				if (te instanceof TEBedrockBore)
					return new ContainerBedrockBore(player, (TEBedrockBore) te).impl;
				break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case 0:
				TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
				if (te instanceof TEBedrockBore)
					return new GuiBedrockBore(new ContainerBedrockBore(player, (TEBedrockBore) te));
				break;
		}
		return null;
	}
}
