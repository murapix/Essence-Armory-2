package escapee.essencearmory2.common.items.base;

import net.minecraft.client.renderer.ItemMeshDefinition;

/**
 * Created by SirShadow on 14.8.2016.
 */
public interface IItemVariantHolder<t extends ItemBaseEA>
{
    t getItem();

    String[] getVariants();

    ItemMeshDefinition getCustomMeshDefinition();
}
