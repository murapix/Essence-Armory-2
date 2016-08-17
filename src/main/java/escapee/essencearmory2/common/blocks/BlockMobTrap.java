package escapee.essencearmory2.common.blocks;

import escapee.essencearmory2.common.blocks.base.BlockBaseEA;
import escapee.essencearmory2.common.tile.TEMobTrap;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by SirShadow on 17. 08. 2016.
 */
public class BlockMobTrap extends BlockBaseEA implements ITileEntityProvider
{
    public BlockMobTrap(String name) {
        super(name);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEMobTrap();
    }
}
