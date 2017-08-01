package com.teamwizardry.inhumanresources.client.gui.container;

import com.teamwizardry.inhumanresources.common.blocks.tile.TEBedrockBore;
import com.teamwizardry.librarianlib.features.container.ContainerBase;
import com.teamwizardry.librarianlib.features.container.InventoryWrapper;
import com.teamwizardry.librarianlib.features.container.builtin.BaseWrappers;
import com.teamwizardry.librarianlib.features.container.builtin.BaseWrappers.InventoryWrapperPlayer;

import net.minecraft.entity.player.EntityPlayer;

public class ContainerBedrockBore extends ContainerBase
{
	public InventoryWrapperPlayer playerInv;
	public InventoryWrapper blockInv;
	public TEBedrockBore bore;
	
	public ContainerBedrockBore(EntityPlayer player, TEBedrockBore bore)
	{
		super(player);
		this.bore = bore;
		playerInv = BaseWrappers.INSTANCE.player(player);
		blockInv = BaseWrappers.INSTANCE.stacks(bore.inventory);
	}
}
