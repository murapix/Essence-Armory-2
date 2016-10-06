package escapee.essencearmory2.common.entity.tasks;

import java.util.Queue;
import escapee.essencearmory2.common.entity.tasks.interactions.IInteraction;

public class Task
{
	private Queue<IInteraction> tasks;
	
	public Task(Queue<IInteraction> tasks)
	{
		if (tasks == null || tasks.size() == 0)
			throw new IllegalArgumentException("Task list must not be empty");
		this.tasks = tasks;
	}
	
	public IInteraction cycleFirstTask()
	{
		IInteraction inter = tasks.remove();
		inter.setShouldCycle(false);
		tasks.add(inter);
		return inter;
	}
	
	public IInteraction getNextTask()
	{
		return tasks.peek();
	}
	
	public boolean shouldCycle()
	{
		return getNextTask().shouldCycle();
	}
	
	public void setShouldCycle(boolean shouldCycle)
	{
		tasks.peek().setShouldCycle(shouldCycle);
	}
}
