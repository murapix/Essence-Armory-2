package com.teamwizardry.inhumanresources.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.teamwizardry.inhumanresources.common.tile.TEMobTrap;
import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.librarianlib.common.base.block.BlockModContainer;

/**
 * Created by SirShadow on 17. 08. 2016.
 */
public class BlockMobTrap extends BlockModContainer
{
    public BlockMobTrap(String name) {
        super(name, Material.IRON);
        setCreativeTab(Util.tabEssence);
    }

    @Override
    public TileEntity createTileEntity(World worldIn, IBlockState state) {
        return new TEMobTrap();
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return true;
    }
}
