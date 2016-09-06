package escapee.essencearmory2.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import escapee.essencearmory2.common.blocks.base.TEBlockEA;
import escapee.essencearmory2.common.tile.TEChipCreator;
import escapee.essencearmory2.init.ItemRegistry;

public class BlockChipCreator extends TEBlockEA
{
	public BlockChipCreator(String name)
	{
		super(name);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TEChipCreator();
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TEChipCreator te = (TEChipCreator) world.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(world, pos, te);
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TEChipCreator)
		{
			TEChipCreator chipCreator = (TEChipCreator) te;
			ItemStack researchLog = chipCreator.getStackInSlot(TEChipCreator.RESEARCH_SLOT);
			if (researchLog != null && researchLog.getItem() == ItemRegistry.itemResearchLog)
			{
				InventoryHelper.spawnItemStack(world, player.posX, player.posY + player.height, player.posZ, researchLog);
				chipCreator.setInventorySlotContents(TEChipCreator.RESEARCH_SLOT, null);
			}
			
			if (stack != null && stack.getItem() == ItemRegistry.itemResearchLog)
			{
				chipCreator.setInventorySlotContents(TEChipCreator.RESEARCH_SLOT, stack);
				player.inventory.deleteStack(stack);
			}
		}
		return false;
	}
}
