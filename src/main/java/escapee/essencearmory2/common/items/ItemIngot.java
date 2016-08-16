package escapee.essencearmory2.common.items;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import escapee.essencearmory2.common.items.base.ItemBaseEA;
import escapee.essencearmory2.common.utils.helper.TextHelper;

/**
 * Created by SirShadow on 15.8.2016.
 */
public class ItemIngot extends ItemBaseEA
{
    public ItemIngot(String name) {
        super(name);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (TextHelper.displayShiftForDetail && !TextHelper.isShiftKeyDown())
        {
            tooltip.add("<Shift for details>");
        }
        if (!TextHelper.isShiftKeyDown()) {
            return;
        }
        tooltip.add(TextHelper.debugText("test!!"));
        tooltip.add(TextHelper.specialText("This is a very special text!",TextHelper.PURPLE));
        tooltip.add(TextHelper.intText(1002));
        tooltip.add(TextHelper.floatText(0.24324234212314141f));
    }
}
