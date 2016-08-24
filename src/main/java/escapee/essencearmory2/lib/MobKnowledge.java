package escapee.essencearmory2.lib;

import io.netty.util.internal.ThreadLocalRandom;
import java.util.HashMap;
import java.util.List;
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
import net.minecraft.entity.monster.IMob;
import scala.actors.threadpool.Arrays;

public class MobKnowledge
{
	public static final HashMap<Class<? extends IMob>, List<String>> validKnowledge = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	private static final List<Class<? extends IMob>> validEntities = Arrays.asList(new Class[]{
		EntityEnderman.class,
		EntitySpider.class,
		EntityPigZombie.class,
		EntityBlaze.class,
		EntityCreeper.class,
		EntityEndermite.class,
		EntityGuardian.class,
		EntitySilverfish.class,
		EntitySkeleton.class,
		EntitySlime.class,
		EntityZombie.class
	});
	
	@SuppressWarnings("unchecked")
	public static void init()
	{
		validKnowledge.put(EntityEnderman.class, Arrays.asList(new String[]
				{"EndermanAggression",
				"EndermanTeleportation",
				"EndermanMovement",
				"EndermanBlockPicking"}
		));
		validKnowledge.put(EntitySpider.class, Arrays.asList(new String[]
				{"SpiderAggression",
				"SpiderMovement",
				"SpiderClimbing",
				"SpiderWebbing"}
		));
		validKnowledge.put(EntityPigZombie.class, Arrays.asList(new String[]
				{"PigZombieAggression",
				"PigZombieMovement",
				"PigZombieHorde",
				"PigZombieItemUsage"}
		));
		validKnowledge.put(EntityBlaze.class, Arrays.asList(new String[]
				{"BlazeAggression",
				"BlazeFlight",
				"BlazeFireballs",
				"BlazeAim"}
		));
		validKnowledge.put(EntityCreeper.class, Arrays.asList(new String[]
				{"CreeperAggression",
				"CreeperExplosion",
				"CreeperMovement",
				"CreeperFleeing"}
		));
		validKnowledge.put(EntityEndermite.class, Arrays.asList(new String[]
				{"EndermiteAggression",
				"EndermiteMovement"}
		));
		validKnowledge.put(EntityGuardian.class, Arrays.asList(new String[]
				{"GuardianAggression",
				"GuardianMovement",
				"GuardianFleeing",
				"GuardianLaser"}
		));
		validKnowledge.put(EntitySilverfish.class, Arrays.asList(new String[]
				{"SilverfishAggresion",
				"SilverfishMovement",
				"SilverfishSwarming",
				"SilverfishDigging"}
		));
		validKnowledge.put(EntitySkeleton.class, Arrays.asList(new String[]
				{"SkeletonAggression",
				"SkeletonAim",
				"SkeletonMovement",
				"SkeletonItemUsage"}
		));
		validKnowledge.put(EntitySlime.class, Arrays.asList(new String[]
				{"SlimeAggression",
				"SlimeJumping",
				"SlimeSplitting"}
		));
		validKnowledge.put(EntityZombie.class, Arrays.asList(new String[]
				{"ZombieAggression",
				"ZombieWalking",
				"ZombieJumping",
				"ZombieTargeting"}
		));
	}
	
	public static String getKnowledge(Class<? extends IMob> entityClass, int index)
	{
		List<String> knowledge = validKnowledge.get(entityClass);
		if (knowledge == null) return "";
		if (index < 0 || index >= knowledge.size()) return "";
		return knowledge.get(index);
	}
	
	public static String getRandomKnowledge(Class<? extends IMob> entityClass)
	{
		List<String> knowledge = validKnowledge.get(entityClass);
		if (knowledge == null) return "";
		return knowledge.get(ThreadLocalRandom.current().nextInt(knowledge.size()));
	}
	
	public static boolean isValidEntityClass(Class<? extends IMob> entityClass)
	{
		return validEntities.contains(entityClass);
	}
}
