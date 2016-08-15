package escapee.essencearmory2.lib;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class LibMain
{
    public class ModInfo
    {
        public static final String
        mod_id =  "essencearmory2",
        mod_name = "Essence Armory2",
        mod_version = "0.1",
        client_proxy_class = "escapee.essencearmory2.proxy.ClientProxy",
        server_proxy_class = "escapee.essencearmory2.proxy.ServerProxy",
        dependencies = "",
        gui_factory = "escapee.essencearmory2.client.gui.GuiFactory";
    }

    public enum EnumIDs
    {
    }

    public static class LibUtils
    {
        public static String customTileName(String name)
        {
            return ModInfo.mod_id + ":tile" + name;
        }
    }
}
