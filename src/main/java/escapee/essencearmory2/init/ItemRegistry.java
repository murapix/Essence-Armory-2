package escapee.essencearmory2.init;

import java.util.ArrayList;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.fml.common.registry.GameRegistry;
import escapee.essencearmory2.common.items.ItemIngot;
import escapee.essencearmory2.common.items.ItemMobBrain;
import escapee.essencearmory2.common.items.base.ItemBaseEA;

public class ItemRegistry
{
	public static final ArrayList<ItemBaseEA> ITEMS = new ArrayList<ItemBaseEA>();

	public static ItemBaseEA itemInfusedIngot = new ItemIngot("ingotInfused");
	public static ItemBaseEA endermanBrain = new ItemMobBrain(EntityEnderman.class, "endermanBrain");
	public static ItemBaseEA spiderBrain = new ItemMobBrain(EntitySpider.class, "spiderBrain");
	public static ItemBaseEA pigZombieBrain = new ItemMobBrain(EntityPigZombie.class, "pigZombieBrain");
	public static ItemBaseEA blazeBrain = new ItemMobBrain(EntityBlaze.class, "blazeBrain");
	public static ItemBaseEA creeperBrain = new ItemMobBrain(EntityCreeper.class, "creeperBrain");
	public static ItemBaseEA endermiteBrain = new ItemMobBrain(EntityEndermite.class, "endermiteBrain");
	public static ItemBaseEA guardianBrain = new ItemMobBrain(EntityGuardian.class, "guardianBrain");
	public static ItemBaseEA silverfishBrain = new ItemMobBrain(EntitySilverfish.class, "silverfishBrain");
	public static ItemBaseEA skeletonBrain = new ItemMobBrain(EntitySkeleton.class, "skeletonBrain");
	public static ItemBaseEA slimeBrain = new ItemMobBrain(EntitySlime.class, "slimeBrain");
	public static ItemBaseEA zombieBrain = new ItemMobBrain(EntityZombie.class, "zombieBrain");

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
