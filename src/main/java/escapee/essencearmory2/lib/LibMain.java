package escapee.essencearmory2.lib;

import net.minecraft.util.ResourceLocation;

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
        public static final String[] validKnowledge = new String[]{"zombieThinking"};
    }

    public static class LibUtils
    {
        public static String customTileName(String name)
        {
            return ModInfo.MOD_ID + ":tile" + name;
        }
    }
}
