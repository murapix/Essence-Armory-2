package escapee.essencearmory2.common.items;

import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.UsernameCache;
import escapee.essencearmory2.common.items.base.ItemBaseEA;
import escapee.essencearmory2.common.utils.helper.TextHelper;
import escapee.essencearmory2.lib.NBTTags;

public class ItemResearchLog extends ItemBaseEA
{
	public ItemResearchLog(String name)
	{
		super(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
	{
		if (world.isRemote && Minecraft.getMinecraft().currentScreen != null)
			return new ActionResult<>(EnumActionResult.FAIL, stack);
		player.setActiveHand(hand);
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey(NBTTags.PLAYER_ID_TAG))
		{
			stack.getTagCompound().setUniqueId(NBTTags.PLAYER_ID_TAG, player.getUniqueID());
			if (world.isRemote) player.addChatMessage(new TextComponentString("Bound to " + TextHelper.BRIGHT_BLUE + player.getName()));
		}
		return new ActionResult<>(EnumActionResult.PASS, stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{
		if (!stack.hasTagCompound()) return;
		if (!stack.getTagCompound().hasUniqueId(NBTTags.PLAYER_ID_TAG)) return;
		UUID playerID = stack.getTagCompound().getUniqueId(NBTTags.PLAYER_ID_TAG);
		String username = UsernameCache.getLastKnownUsername(playerID);
		if (username == null) stack.getTagCompound().removeTag(NBTTags.PLAYER_ID_TAG);
		else tooltip.add("Bound Player: " + TextHelper.BRIGHT_BLUE + username);
	}
	
	public static EntityPlayer getBoundPlayer(ItemStack stack)
	{
		if (!stack.hasTagCompound()) return null;
		if (!stack.getTagCompound().hasKey(NBTTags.PLAYER_ID_TAG)) return null;
		UUID playerID = stack.getTagCompound().getUniqueId(NBTTags.PLAYER_ID_TAG);
		String username = UsernameCache.getLastKnownUsername(playerID);
		if (username == null) return null;
		
		return null;
	}
}
