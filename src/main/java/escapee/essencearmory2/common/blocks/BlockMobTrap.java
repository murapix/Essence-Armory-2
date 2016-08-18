package escapee.essencearmory2.common.blocks;

import escapee.essencearmory2.common.blocks.base.TEBlockEA;
import escapee.essencearmory2.common.tile.TEMobTrap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by SirShadow on 17. 08. 2016.
 */
public class BlockMobTrap extends TEBlockEA
{
    public BlockMobTrap(String name) {
        super(name);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEMobTrap();
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return true;
    }
}
