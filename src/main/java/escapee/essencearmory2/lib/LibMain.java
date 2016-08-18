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
import net.minecraft.util.ResourceLocation;
import scala.actors.threadpool.Arrays;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class LibMain
{
    public class ModInfo
    {
        public static final String
        MOD_ID =  "essencearmory2",
        MOD_NAME = "Essence Armory 2",
        MOD_VERSION = "0.1",
        CLIENT_PROXY = "escapee.essencearmory2.proxy.ClientProxy",
        SERVER_PROXY = "escapee.essencearmory2.proxy.ServerProxy",
        DEPENDENCIES = "",
        GUI_FACTORY = "escapee.essencearmory2.client.gui.GuiFactory";
    }
    public class NBTTags
    {
        public static final String
        KNOWLEDGE_TAG = "knowledge";
    }

    public static class ModResourceLocations
    {
        public static final ResourceLocation
        KNOWLEDGE_CAPABILITY = new ResourceLocation(ModInfo.MOD_ID + ":knowledgeCapability"),
        SIMPLE_GUI = new ResourceLocation(ModInfo.MOD_ID,"textures/gui/simpleGui.png");
    }
    public enum EnumIDs
    {
    }

    public static class LibKnowledge
    {
    	public static final HashMap<Class<? extends IMob>, List<String>> validKnowledge = new HashMap<>();
    	
    	@SuppressWarnings("unchecked")
		public static void init()
    	{
    		validKnowledge.put(EntityEnderman.class, Arrays.asList(new String[]{""}));
    		validKnowledge.put(EntitySpider.class, Arrays.asList(new String[]{""}));
    		validKnowledge.put(EntityPigZombie.class, Arrays.asList(new String[]{""}));
    		validKnowledge.put(EntityBlaze.class, Arrays.asList(new String[]{""}));
    		validKnowledge.put(EntityCreeper.class, Arrays.asList(new String[]{""}));
    		validKnowledge.put(EntityEndermite.class, Arrays.asList(new String[]{""}));
    		validKnowledge.put(EntityGuardian.class, Arrays.asList(new String[]{""}));
    		validKnowledge.put(EntitySilverfish.class, Arrays.asList(new String[]{""}));
    		validKnowledge.put(EntitySkeleton.class, Arrays.asList(new String[]{""}));
    		validKnowledge.put(EntitySlime.class, Arrays.asList(new String[]{""}));
    		validKnowledge.put(EntityZombie.class, Arrays.asList(new String[]{"zombieThinking"}));
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
    }

    public static class LibUtils
    {
        public static String customTileName(String name)
        {
            return ModInfo.MOD_ID + ":tile" + name;
        }
    }
}
