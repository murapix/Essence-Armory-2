package com.teamwizardry.inhumanresources.network;

import com.teamwizardry.inhumanresources.common.utils.IUpgradable;
import com.teamwizardry.librarianlib.features.autoregister.PacketRegister;
import com.teamwizardry.librarianlib.features.network.PacketBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

@PacketRegister(Side.SERVER)
public class PacketUseActive extends PacketBase
{
	@Override
	public void handle(MessageContext context)
	{
		EntityPlayer player = context.getServerHandler().player;
		ItemStack mainhand = player.getHeldItemMainhand();
		ItemStack offhand = player.getHeldItemOffhand();
		if (mainhand == null && offhand == null)
			return;
		if (offhand.getItem() instanceof IUpgradable)
		{
			if (!((IUpgradable) offhand.getItem()).runActive(player, mainhand, offhand, false))
				((IUpgradable) mainhand.getItem()).runActive(player, mainhand, offhand, true);
		}
		else if (mainhand.getItem() instanceof IUpgradable)
			((IUpgradable) mainhand.getItem()).runActive(player, mainhand, offhand, true);
	}
}
