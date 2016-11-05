package com.teamwizardry.inhumanresources.common.entity.tasks.interactions.inventory;

import com.teamwizardry.inhumanresources.common.entity.mobs.MobBase;
import com.teamwizardry.inhumanresources.common.entity.tasks.interactions.IInteraction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public interface InteractionInventory extends IInteraction
{
	public ItemStack[] interact(MobBase mob);
	
	public BlockPos getPos();
}
