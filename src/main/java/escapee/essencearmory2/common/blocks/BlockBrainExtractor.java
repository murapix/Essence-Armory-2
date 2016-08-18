package escapee.essencearmory2.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import escapee.essencearmory2.common.blocks.base.TEBlockEA;
import escapee.essencearmory2.common.tile.TEBrainExtractor;

public class BlockBrainExtractor extends TEBlockEA
{
	public BlockBrainExtractor(String name)
	{
		super(name, Material.IRON);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TEBrainExtractor();
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return true;
	}
}
