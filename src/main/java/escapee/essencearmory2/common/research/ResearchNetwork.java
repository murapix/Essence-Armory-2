package escapee.essencearmory2.common.research;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ResearchNetwork
{
	private static Multimap<ResearchBase, ResearchBase> requirementTree;
	private static Multimap<ResearchBase, ResearchBase> reverseTree;
	private static Map<String, ResearchBase> researchRegistry;
	
	public static BasicResearch ENDERMAN_AGGRESSION;
	public static BasicResearch ENDERMAN_TELEPORTATION;
	public static BasicResearch ENDERMAN_MOVEMENT;
	public static BasicResearch ENDERMAN_BLOCK_PICKING;
	public static BasicResearch ENDERMAN_COMPLETE;
	
	public static BasicResearch SPIDER_AGGRESSION;
	public static BasicResearch SPIDER_MOVEMENT;
	public static BasicResearch SPIDER_CLIMBING;
	public static BasicResearch SPIDER_WEBBING;
	public static BasicResearch SPIDER_COMPLETE;

	public static BasicResearch PIG_ZOMBIE_AGGRESSION;
	public static BasicResearch PIG_ZOMBIE_MOVEMENT;
	public static BasicResearch PIG_ZOMBIE_HORDE;
	public static BasicResearch PIG_ZOMBIE_ITEM_USAGE;
	public static BasicResearch PIG_ZOMBIE_COMPLETE;	

	public static BasicResearch BLAZE_AGGRESSION;
	public static BasicResearch BLAZE_FLIGHT;
	public static BasicResearch BLAZE_FIREBALLS;
	public static BasicResearch BLAZE_AIM;
	public static BasicResearch BLAZE_COMPLETE;
	
	public static BasicResearch CREEPER_AGGRESSION;
	public static BasicResearch CREEPER_EXPLOSION;
	public static BasicResearch CREEPER_MOVEMENT;
	public static BasicResearch CREEPER_FLEEING;
	public static BasicResearch CREEPER_COMPLETE;
	
	public static BasicResearch ENDERMITE_AGGRESSION;
	public static BasicResearch ENDERMITE_MOVEMENT;
	public static BasicResearch ENDERMITE_COMPLETE;
	
	public static BasicResearch GUARDIAN_AGGRESSION;
	public static BasicResearch GUARDIAN_MOVEMENT;
	public static BasicResearch GUARDIAN_FLEEING;
	public static BasicResearch GUARDIAN_LASER;
	public static BasicResearch GUARDIAN_COMPLETE;
	
	public static BasicResearch SILVERFISH_AGGRESSION;
	public static BasicResearch SILVERFISH_MOVEMENT;
	public static BasicResearch SILVERFISH_SWARMING;
	public static BasicResearch SILVERFISH_DIGGING;
	public static BasicResearch SILVERFISH_COMPLETE;
	
	public static BasicResearch SKELETON_AGGRESSION;
	public static BasicResearch SKELETON_AIM;
	public static BasicResearch SKELETON_MOVEMENT;
	public static BasicResearch SKELETON_ITEM_USAGE;
	public static BasicResearch SKELETON_COMPLETE;
	
	public static BasicResearch SLIME_AGGRESSION;
	public static BasicResearch SLIME_JUMPING;
	public static BasicResearch SLIME_SPLITTING;
	public static BasicResearch SLIME_COMPLETE;
	
	public static BasicResearch ZOMBIE_AGGRESSION;
	public static BasicResearch ZOMBIE_WALKING;
	public static BasicResearch ZOMBIE_JUMPING;
	public static BasicResearch ZOMBIE_TARGETING;
	public static BasicResearch ZOMBIE_COMPLETE;
	
	public static void init()
	{
		requirementTree = HashMultimap.create();
		reverseTree = HashMultimap.create();
		researchRegistry = new HashMap<>();
		
		addResearch(ENDERMAN_AGGRESSION);
		addResearch(ENDERMAN_TELEPORTATION);
		addResearch(ENDERMAN_MOVEMENT);
		addResearch(ENDERMAN_BLOCK_PICKING);
		addResearch(ENDERMAN_COMPLETE, ENDERMAN_AGGRESSION, ENDERMAN_TELEPORTATION, ENDERMAN_MOVEMENT, ENDERMAN_BLOCK_PICKING);
		
		addResearch(SPIDER_AGGRESSION);
		addResearch(SPIDER_MOVEMENT);
		addResearch(SPIDER_CLIMBING);
		addResearch(SPIDER_WEBBING);
		addResearch(SPIDER_COMPLETE, SPIDER_AGGRESSION, SPIDER_MOVEMENT, SPIDER_CLIMBING, SPIDER_WEBBING);

		addResearch(PIG_ZOMBIE_AGGRESSION);
		addResearch(PIG_ZOMBIE_MOVEMENT);
		addResearch(PIG_ZOMBIE_HORDE);
		addResearch(PIG_ZOMBIE_ITEM_USAGE);
		addResearch(PIG_ZOMBIE_COMPLETE, PIG_ZOMBIE_AGGRESSION, PIG_ZOMBIE_MOVEMENT, PIG_ZOMBIE_HORDE, PIG_ZOMBIE_ITEM_USAGE);	

		addResearch(BLAZE_AGGRESSION);
		addResearch(BLAZE_FLIGHT);
		addResearch(BLAZE_FIREBALLS);
		addResearch(BLAZE_AIM);
		addResearch(BLAZE_COMPLETE, BLAZE_AGGRESSION, BLAZE_FLIGHT, BLAZE_FIREBALLS, BLAZE_AIM);
		
		addResearch(CREEPER_AGGRESSION);
		addResearch(CREEPER_EXPLOSION);
		addResearch(CREEPER_MOVEMENT);
		addResearch(CREEPER_FLEEING);
		addResearch(CREEPER_COMPLETE, CREEPER_AGGRESSION, CREEPER_EXPLOSION, CREEPER_MOVEMENT, CREEPER_FLEEING);
		
		addResearch(ENDERMITE_AGGRESSION);
		addResearch(ENDERMITE_MOVEMENT);
		addResearch(ENDERMITE_COMPLETE, ENDERMITE_AGGRESSION, ENDERMITE_MOVEMENT);
		
		addResearch(GUARDIAN_AGGRESSION);
		addResearch(GUARDIAN_MOVEMENT);
		addResearch(GUARDIAN_FLEEING);
		addResearch(GUARDIAN_LASER);
		addResearch(GUARDIAN_COMPLETE, GUARDIAN_AGGRESSION, GUARDIAN_MOVEMENT, GUARDIAN_FLEEING, GUARDIAN_LASER);
		
		addResearch(SILVERFISH_AGGRESSION);
		addResearch(SILVERFISH_MOVEMENT);
		addResearch(SILVERFISH_SWARMING);
		addResearch(SILVERFISH_DIGGING);
		addResearch(SILVERFISH_COMPLETE, SILVERFISH_AGGRESSION, SILVERFISH_MOVEMENT, SILVERFISH_SWARMING, SILVERFISH_DIGGING);
		
		addResearch(SKELETON_AGGRESSION);
		addResearch(SKELETON_AIM);
		addResearch(SKELETON_MOVEMENT);
		addResearch(SKELETON_ITEM_USAGE);
		addResearch(SKELETON_COMPLETE, SKELETON_AGGRESSION, SKELETON_AIM, SKELETON_MOVEMENT, SKELETON_ITEM_USAGE);
		
		addResearch(SLIME_AGGRESSION);
		addResearch(SLIME_JUMPING);
		addResearch(SLIME_SPLITTING);
		addResearch(SLIME_COMPLETE, SLIME_AGGRESSION, SLIME_JUMPING, SLIME_SPLITTING);
		
		addResearch(ZOMBIE_AGGRESSION);
		addResearch(ZOMBIE_WALKING);
		addResearch(ZOMBIE_JUMPING);
		addResearch(ZOMBIE_TARGETING);
		addResearch(ZOMBIE_COMPLETE, ZOMBIE_AGGRESSION, ZOMBIE_WALKING, ZOMBIE_JUMPING, ZOMBIE_TARGETING);
	}
	
	public static void addResearch(ResearchBase research, ResearchBase... prerequisites)
	{
		researchRegistry.put(research.getName(), research);
		for (ResearchBase r : prerequisites)
		{
			requirementTree.put(research, r);
			reverseTree.put(r, research);
		}
	}
	
	public static void removeResearch(ResearchBase research)
	{
		researchRegistry.remove(research.getName());
		for (ResearchBase r : reverseTree.removeAll(research))
			requirementTree.remove(r, research);
	}
	
	public static ResearchBase getResearch(String name)
	{
		return researchRegistry.get(name);
	}
	
	public static Collection<ResearchBase> getPrerequisites(String name)
	{
		return requirementTree.get(getResearch(name));
	}
}
