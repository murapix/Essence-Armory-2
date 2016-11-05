package com.teamwizardry.inhumanresources.common.blocks;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.teamwizardry.inhumanresources.common.blocks.base.TEBlockEA;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobBase;
import com.teamwizardry.inhumanresources.common.entity.tasks.Task;
import com.teamwizardry.inhumanresources.common.entity.tasks.interactions.IInteraction;
import com.teamwizardry.inhumanresources.common.entity.tasks.interactions.inventory.EnumBehavior;
import com.teamwizardry.inhumanresources.common.entity.tasks.interactions.inventory.InteractionInventoryPull;
import com.teamwizardry.inhumanresources.common.entity.tasks.interactions.inventory.InteractionInventoryPush;
import com.teamwizardry.inhumanresources.common.tile.TEMobController;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMobController extends TEBlockEA
{
	public BlockMobController(String name)
	{
		super(name);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TEMobController();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		TileEntity te = world.getTileEntity(pos);
		if (!(te instanceof TEMobController)) return false;
		TEMobController controller = (TEMobController) te;
		AxisAlignedBB axis = new AxisAlignedBB(controller.getPos().getX()-32, 0, controller.getPos().getZ()-32, controller.getPos().getX()+32, controller.getWorld().getHeight(), controller.getPos().getZ()+32);
		List<MobBase> entities = controller.getWorld().getEntitiesWithinAABB(MobBase.class, axis);
		Queue<IInteraction> queue = new LinkedList<>();
		queue.add(new InteractionInventoryPull(controller.getPos().add(2, 0, 0), false, EnumBehavior.ANY_AMOUNT));
		queue.add(new InteractionInventoryPush(controller.getPos().add(-2, 0, 0), false, EnumBehavior.ANY_AMOUNT));
		Task task = new Task(queue);
		
		for (MobBase mob : entities)
		{
			controller.addMob(mob);
			controller.addTask(task);
		}
		return true;
//		if (world.isRemote) return true; 
//		TileEntity te = world.getTileEntity(pos);
//		if (!(te instanceof TEMobController)) return false;
//		// TODO: Add GUI classes and ids
//		player.openGui(EssenceArmory.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
//		return true;
	}
}
