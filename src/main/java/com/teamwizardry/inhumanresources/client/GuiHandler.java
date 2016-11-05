package com.teamwizardry.inhumanresources.client;

import com.teamwizardry.inhumanresources.client.gui.GuiChipCreator;
import com.teamwizardry.inhumanresources.client.gui.screen.GuiResearchTablet;
import com.teamwizardry.inhumanresources.common.tile.TEChipCreator;
import com.teamwizardry.inhumanresources.common.tile.TEMobController;
import com.teamwizardry.inhumanresources.common.utils.lib.EnumIDs;
import com.teamwizardry.inhumanresources.server.container.ContainerChipCreator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
    	BlockPos pos = new BlockPos(x, y, z);
    	TileEntity te = world.getTileEntity(pos);
        switch (EnumIDs.values()[ID])
        {
        	case GUI_RESEARCH_LIST:
        		if (te instanceof TileEntity)
        			return null;
        		break;
        	case GUI_CHIP_CREATOR:
        		if (te instanceof TEChipCreator)
        			return new ContainerChipCreator(player.inventory, (TEChipCreator) te);
        		break;
        	case GUI_MOB_CONTROLLER:
        		if (te instanceof TEMobController)
        			return null;
        		break;
        }
        throw new IllegalArgumentException("No gui with id" + ID);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
    	BlockPos pos = new BlockPos(x, y, z);
    	TileEntity te = world.getTileEntity(pos);
        switch (EnumIDs.values()[ID])
        {
        	case GUI_RESEARCH_LIST:
        		if (te instanceof TileEntity)
        			return new GuiResearchTablet();
        		break;
        	case GUI_CHIP_CREATOR:
        		if (te instanceof TEChipCreator)
        			return new GuiChipCreator((TEChipCreator) te, new ContainerChipCreator(player.inventory, (TEChipCreator) te));
        		break;
        	case GUI_MOB_CONTROLLER:
        		if (te instanceof TEMobController)
        			return null;
        		break;
        }
        throw new IllegalArgumentException("No gui with id" + ID);
    }
}
