package escapee.essencearmory2.lib;

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

    public enum EnumIDs
    {
    }

    public static class LibUtils
    {
        public static String customTileName(String name)
        {
            return ModInfo.MOD_ID + ":tile" + name;
        }
    }
}
