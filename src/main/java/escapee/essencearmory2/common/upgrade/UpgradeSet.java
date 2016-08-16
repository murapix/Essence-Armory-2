package escapee.essencearmory2.common.upgrade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by SirShadow on 17.8.2016.
 */
public class UpgradeSet extends Upgrade
{
    public UpgradeSet(String name, int level, Upgrade... upgrades)
    {
        super(name, level, upgrades);
    }

    public UpgradeSet(String name, Upgrade... upgrades)
    {
        this(name, 0, upgrades);
    }

    public UpgradeSet(String name, int level, String... upgrades)
    {
        super(name, level, upgrades);
    }

    public UpgradeSet(String name, String... upgrades)
    {
        this(name, 0, upgrades);
    }

    public UpgradeSet(String name, int level)
    {
        this(name, level, new String[]{});
    }

    public UpgradeSet(String name)
    {
        this(name, 0);
    }

    /**
     * @param player Which player is the upgrade count being counted for
     * @param level The required level of the upgrade being checked
     * @param upgrade The upgrade being searched for
     * @return The number of separate equipped items with the upgrade over the given level
     */
    public static int getUpgradeCount(EntityPlayer player, int level, Upgrade upgrade)
    {
        int count = 0;
        for (int i = 0; i < 4; i++)
        {
            ItemStack item = player.inventory.getStackInSlot(i + 1);
            if (getUpgradeLevel(item, upgrade) >= level) count++;
        }
        /*for (int i = 0; i < 4; i++)
        {
            ItemStack item = PlayerHandler.getPlayerBaubles(player).getStackInSlot(i);
            if (getUpgradeLevel(item, upgrade) >= level) count++;
        }*/
        return count;
    }

}
