package escapee.essencearmory2.client;

import escapee.essencearmory2.lib.LibMain;
import net.minecraft.entity.player.EntityPlayer;
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
        switch (LibMain.EnumIDs.values()[ID])
        {

        }
        throw new IllegalArgumentException("No gui with id" + ID);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (LibMain.EnumIDs.values()[ID])
        {

        }
        throw new IllegalArgumentException("No gui with id" + ID);
    }
}
