package escapee.essencearmory2.common.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.tileentity.TileEntity;
import escapee.essencearmory2.common.entity.mobs.MobBase;
import escapee.essencearmory2.common.entity.tasks.Task;

public class TEMobController extends TileEntity
{
	private HashMap<MobBase, Task> mobTasks = new HashMap<>();
	private List<Task> unboundTasks = new ArrayList<>();
	private List<MobBase> unboundMobs = new ArrayList<>();
	
	public boolean addMob(MobBase mob)
	{
		mob.setController(this);
		Iterator<Task> iter = unboundTasks.iterator();
		while (iter.hasNext())
		{
			Task t = iter.next();
			if (t.getNextTask().isMobValid(mob))
			{
				mobTasks.put(mob, t);
				iter.remove();
				return true;
			}
		}
		unboundMobs.add(mob);
		return false;
	}
	
	public boolean addTask(Task t)
	{
		Iterator<MobBase> iter = unboundMobs.iterator();
		while (iter.hasNext())
		{
			MobBase mob = iter.next();
			if (t.getNextTask().isMobValid(mob))
			{
				mobTasks.put(mob, t);
				iter.remove();
				return true;
			}
		}
		unboundTasks.add(t);
		return false;
	}

	public Task getTask(MobBase mob)
	{
		return mobTasks.get(mob);
	}
}
