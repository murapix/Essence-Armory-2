package escapee.essencearmory2.common.items;

import java.util.List;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import escapee.essencearmory2.common.items.base.ItemBaseEA;
import escapee.essencearmory2.lib.LibMain;

public class ItemZombieBrain extends ItemBaseEA
{
	public ItemZombieBrain(String name)
	{
		super(name);
		setHasSubtypes(true);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		super.onCreated(stack, world, player);
		stack.setTagCompound(new NBTTagCompound());
		world.getEntitiesWithinAABB(EntityZombie.class, new AxisAlignedBB(0, 0, 0, 1, 1, 1));
		stack.getTagCompound().setString("knowledge", LibMain.LibKnowledge.getRandomKnowledge(EntityZombie.class));
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{
		
	}
}
