package com.teamwizardry.inhumanresources.common.items;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
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
import com.teamwizardry.inhumanresources.client.gui.screen.GuiResearchTablet;
import com.teamwizardry.inhumanresources.common.capability.knowledge.KnowledgeProvider;
import com.teamwizardry.inhumanresources.common.research.BasicResearch;
import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.inhumanresources.common.utils.helper.TextHelper;
import com.teamwizardry.inhumanresources.common.utils.lib.NBTTags;
import com.teamwizardry.librarianlib.common.base.item.ItemMod;

public class ItemMobBrain extends ItemMod
{
	private ArrayList<BasicResearch> research = new ArrayList<>();
	private LinkedHashSet<BasicResearch> dupeTester = new LinkedHashSet<>();

	public ItemMobBrain(String name)
	{
		super(name);
		setCreativeTab(Util.tabEssence);
	}

	public void addResearch(BasicResearch research)
	{
		if (dupeTester.add(research)) this.research.add(research);
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		super.onCreated(stack, world, player);
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setString(NBTTags.KNOWLEDGE_TAG, research.get((int) (Math.random() * research.size())).getName());
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{
		if (TextHelper.displayShiftForDetail && !TextHelper.isShiftKeyDown())
			tooltip.add("<Shift for details>");
		if (!TextHelper.isShiftKeyDown())
			return;
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(NBTTags.KNOWLEDGE_TAG))
			tooltip.add(TextHelper.localise("essencearmory2.tooltip.knowledge") + " " + TextHelper.PURPLE + TextHelper.localise("essencearmory2.knowledge." + stack.getTagCompound().getString(NBTTags.KNOWLEDGE_TAG)));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack item, World world, EntityPlayer player, EnumHand hand)
	{
		if (player.hasCapability(KnowledgeProvider.KnowlageCapability, null) && item.hasTagCompound())
		{
			if (world.isRemote && !KnowledgeProvider.get(player).hasKnowledge(item.getTagCompound().getString(NBTTags.KNOWLEDGE_TAG)))
			{
				KnowledgeProvider.get(player).addKnowledge(player, item.getTagCompound().getString(NBTTags.KNOWLEDGE_TAG));
				player.setItemStackToSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND, null);
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
	}
}
