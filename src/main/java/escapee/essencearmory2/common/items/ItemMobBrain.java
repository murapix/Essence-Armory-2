package escapee.essencearmory2.common.items;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import escapee.essencearmory2.client.gui.screen.GuiResearchTablet;
import escapee.essencearmory2.common.capability.knowledge.KnowledgeProvider;
import escapee.essencearmory2.common.items.base.ItemBaseEA;
import escapee.essencearmory2.common.utils.helper.TextHelper;
import escapee.essencearmory2.lib.MobKnowledge;
import escapee.essencearmory2.lib.NBTTags;

public class ItemMobBrain extends ItemBaseEA
{
	private Class<? extends IMob> mobClass;
	
	public ItemMobBrain(Class<? extends IMob> cls, String name)
	{
		super(name);
		this.mobClass = cls;
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		super.onCreated(stack, world, player);
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setString(NBTTags.KNOWLEDGE_TAG, MobKnowledge.getRandomKnowledge(mobClass));
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{
		if (TextHelper.displayShiftForDetail && !TextHelper.isShiftKeyDown())
			tooltip.add("<Shift for details>");
		if (!TextHelper.isShiftKeyDown())
			return;
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(NBTTags.KNOWLEDGE_TAG))
			tooltip.add(TextHelper.localise("essencearmory2.tooltip.knowledge")+ " " + TextHelper.PURPLE + TextHelper.localise("essencearmory2.knowledge." + stack.getTagCompound().getString(NBTTags.KNOWLEDGE_TAG)));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack item, World world, EntityPlayer player, EnumHand hand)
	{
		if (player.hasCapability(KnowledgeProvider.KnowlageCapability, null) && item.hasTagCompound())
		{
			if (world.isRemote && !KnowledgeProvider.get(player).hasKnowledge(item.getTagCompound().getString(NBTTags.KNOWLEDGE_TAG)))
			{
				KnowledgeProvider.get(player).addKnowledge(player, item.getTagCompound().getString(NBTTags.KNOWLEDGE_TAG));
				player.setItemStackToSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.MAINHAND: EntityEquipmentSlot.OFFHAND, null);
				return new ActionResult<>(EnumActionResult.SUCCESS, item);
			}
		}
		return new ActionResult<>(EnumActionResult.FAIL, item);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack item, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			Minecraft.getMinecraft().displayGuiScreen(new GuiResearchTablet());
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int slot, boolean isSelected)
	{
		if (!item.hasTagCompound())
			item.setTagCompound(new NBTTagCompound());
		if (!item.getTagCompound().hasKey(NBTTags.KNOWLEDGE_TAG))
			item.getTagCompound().setString(NBTTags.KNOWLEDGE_TAG, MobKnowledge.getRandomKnowledge(mobClass));
	}
}
