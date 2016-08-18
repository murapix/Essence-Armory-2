package escapee.essencearmory2.common.items;

import java.util.List;

import escapee.essencearmory2.client.gui.screen.GuiResearchTablet;
import escapee.essencearmory2.common.capability.knowlage.KnowledgeProvider;
import escapee.essencearmory2.lib.LibMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import escapee.essencearmory2.common.items.base.ItemBaseEA;
import escapee.essencearmory2.common.utils.helper.TextHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by SirShadow on 15.8.2016.
 *  SirShadows testing ingot! Do not judge!
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
        if (!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setString("knowledge", LibMain.LibKnowledge.validKnowledge[stack.getItemDamage()]);
        tooltip.add(TextHelper.localise("essencearmory2.tooltip.knowledge")+ " " + TextHelper.PURPLE + TextHelper.localise("essencearmory2.knowledge." + stack.getTagCompound().getString("knowledge")));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if(playerIn.hasCapability(KnowledgeProvider.KnowlageCapability,null) && itemStackIn.hasTagCompound())
        {
            if(worldIn.isRemote && !KnowledgeProvider.get(playerIn).hasKnowledge(itemStackIn.getTagCompound().getString("knowledge")))
            {
                KnowledgeProvider.get(playerIn).addKnowledge(playerIn,itemStackIn.getTagCompound().getString("knowledge"));
                playerIn.setItemStackToSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND,null);
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,itemStackIn);
            }
            if(!worldIn.isRemote)
            {

            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL,itemStackIn);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiResearchTablet());
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected){
        if (!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setString("knowledge", LibMain.LibKnowledge.validKnowledge[(itemRand.nextInt(LibMain.LibKnowledge.validKnowledge.length))]);
        }
        else {
            stack.getTagCompound().setString("knowledge", LibMain.LibKnowledge.validKnowledge[stack.getItemDamage()]);
        }
    }
}
