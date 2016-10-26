package escapee.essencearmory2.common.research;

import escapee.essencearmory2.common.items.ItemMobBrain;

public class BasicResearch extends ResearchBase
{
	private ItemMobBrain brain;
	
	public BasicResearch(String name, ItemMobBrain brain)
	{
		super(name);
		this.brain = brain;
		brain.addResearch(this);
	}
	
	public ItemMobBrain getBrain()
	{
		return brain;
	}
}
