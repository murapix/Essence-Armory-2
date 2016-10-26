package escapee.essencearmory2.init;

import java.util.ArrayList;
import net.minecraftforge.fml.common.registry.GameRegistry;
import escapee.essencearmory2.common.items.ItemMobBrain;
import escapee.essencearmory2.common.items.ItemResearchLog;
import escapee.essencearmory2.common.items.base.ItemBaseEA;

public class ItemRegistry
{
	public static final ArrayList<ItemBaseEA> ITEMS = new ArrayList<ItemBaseEA>();

	public static ItemBaseEA itemResearchLog = new ItemResearchLog("researchLog");
	
	public static ItemBaseEA endermanBrain = new ItemMobBrain("endermanBrain");
	public static ItemBaseEA spiderBrain = new ItemMobBrain("spiderBrain");
	public static ItemBaseEA pigZombieBrain = new ItemMobBrain("pigZombieBrain");
	public static ItemBaseEA blazeBrain = new ItemMobBrain("blazeBrain");
	public static ItemBaseEA creeperBrain = new ItemMobBrain("creeperBrain");
	public static ItemBaseEA endermiteBrain = new ItemMobBrain("endermiteBrain");
	public static ItemBaseEA guardianBrain = new ItemMobBrain("guardianBrain");
	public static ItemBaseEA silverfishBrain = new ItemMobBrain("silverfishBrain");
	public static ItemBaseEA skeletonBrain = new ItemMobBrain("skeletonBrain");
	public static ItemBaseEA slimeBrain = new ItemMobBrain("slimeBrain");
	public static ItemBaseEA zombieBrain = new ItemMobBrain("zombieBrain");

	public static void registerItems()
	{
		for (ItemBaseEA itemBaseEA : ITEMS)
		{
			GameRegistry.register(itemBaseEA);
		}
	}

	public static void itemRenderRegistry()
	{
		ITEMS.forEach(ItemBaseEA::initModelsAndVariants);
	}
}
