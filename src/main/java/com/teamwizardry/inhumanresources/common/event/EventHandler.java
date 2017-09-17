package com.teamwizardry.inhumanresources.common.event;

import java.util.LinkedList;
import java.util.List;

import com.teamwizardry.inhumanresources.InhumanResources;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobBase;
import com.teamwizardry.inhumanresources.common.items.ItemMagnet;
import com.teamwizardry.inhumanresources.common.items.ItemMagnet.EnumMagnetType;
import com.teamwizardry.inhumanresources.common.utils.IUpgradable;
import com.teamwizardry.inhumanresources.init.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventHandler
{
	public static EventHandler INSTANCE = new EventHandler();
	
	@SubscribeEvent
	public void onEntityRightClicked(EntityInteract event)
	{
		if (event.getTarget() instanceof MobBase)
		{
			MobBase mob = (MobBase) event.getTarget();
			if (mob.getOwnerId() == null) mob.setOwner(event.getEntityPlayer());
		}
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onTextureStitch(TextureStitchEvent event)
	{
		event.getMap().registerSprite(new ResourceLocation(InhumanResources.MOD_ID, "particles/blur"));
	}
	
	@SubscribeEvent
	public void onEntityLooted(LootingLevelEvent event)
	{
		EntityLivingBase target = event.getEntityLiving();
		BlockPos pos = target.getPosition();
		int lootingBonus = 0;
		for (int x = -16; x <= 16; x++)
			for (int z = -16; z <= 16; z++)
				for (int y = -8; y <= 8; y++)
					if (target.world.getBlockState(pos.add(x, y, z)).getBlock() == Blocks.BEACON)
					{
						Block block = target.world.getBlockState(pos.add(x, y, z).down()).getBlock();
						if (block == Blocks.IRON_BLOCK)
							lootingBonus = Math.max(lootingBonus, 1);
						else if (block == Blocks.GOLD_BLOCK)
							lootingBonus = Math.max(lootingBonus, 2);
						else if (block == Blocks.DIAMOND_BLOCK)
							lootingBonus = Math.max(lootingBonus, 3);
						else if (block == Blocks.EMERALD_BLOCK)
							lootingBonus = Math.max(lootingBonus, 4);
					}
		event.setLootingLevel(event.getLootingLevel() + lootingBonus);
	}
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onBlockBreak(HarvestDropsEvent event)
	{
		if (!event.isSilkTouching())
		{
			BlockPos pos = event.getPos();
			int fortuneBonus = 0;
			for (int x = -16; x <= 16; x++)
				for (int z = -16; z <= 16; z++)
					for (int y = -8; y <= 8; y++)
						if (event.getWorld().getBlockState(pos.add(x, y, z)).getBlock() == Blocks.BEACON)
						{
							Block block = event.getWorld().getBlockState(pos.add(x, y, z).down()).getBlock();
							if (block == Blocks.IRON_BLOCK)
								fortuneBonus = Math.max(fortuneBonus, 1);
							else if (block == Blocks.GOLD_BLOCK)
								fortuneBonus = Math.max(fortuneBonus, 2);
							else if (block == Blocks.DIAMOND_BLOCK)
								fortuneBonus = Math.max(fortuneBonus, 3);
							else if (block == Blocks.EMERALD_BLOCK)
								fortuneBonus = Math.max(fortuneBonus, 4);
						}
			List<ItemStack> drops = event.getDrops();
			drops.clear();
			drops.addAll(event.getState().getBlock().getDrops(event.getWorld(), event.getPos(), event.getState(), event.getFortuneLevel() + fortuneBonus));
		}
	}
	
//	@SideOnly(Side.CLIENT)
//	@SubscribeEvent(receiveCanceled=true)
//	public void onKeyPress(KeyInputEvent event)
//	{
//		if (ClientProxy.mistwroughtActive.isPressed())
//		{
//			EntityPlayer player = Minecraft.getMinecraft().player;
//			ItemStack mainhand = player.getHeldItemMainhand();
//			ItemStack offhand = player.getHeldItemOffhand();
//			if (mainhand == null && offhand == null)
//				return;
//			if (offhand.getItem() instanceof IUpgradable)
//			{
//				if (((IUpgradable) offhand.getItem()).runActive(player, mainhand, offhand, false))
//					PacketHandler.NETWORK.sendToServer(new PacketUseActive());
//				else if (((IUpgradable) mainhand.getItem()).runActive(player, mainhand, offhand, true))
//					PacketHandler.NETWORK.sendToServer(new PacketUseActive());
//			}
//			else if (mainhand.getItem() instanceof IUpgradable)
//				if (((IUpgradable) mainhand.getItem()).runActive(player, mainhand, offhand, true))
//					PacketHandler.NETWORK.sendToServer(new PacketUseActive());
//		}
//	}
	
	@SubscribeEvent
	public void onEntityAttacked(LivingHurtEvent event)
	{
		Entity sourceEntity = event.getSource().getTrueSource();
		if (sourceEntity instanceof EntityLivingBase)
		{
			EntityLivingBase target = event.getEntityLiving();
			if (target == null)
				return;
			EntityLivingBase attacker = (EntityLivingBase) sourceEntity;
			ItemStack weapon = attacker.getHeldItemMainhand();
			ItemStack offhand = attacker.getHeldItemOffhand();
			if (offhand != null && offhand.getItem() instanceof IUpgradable)
				((IUpgradable) weapon.getItem()).onAttackEntity(event, attacker, target, weapon, offhand);
			if (weapon != null && weapon.getItem() instanceof IUpgradable)
				((IUpgradable) weapon.getItem()).onAttackEntity(event, attacker, target, weapon, offhand);
		}
	}
	
	@SubscribeEvent
	public void magnetEntityDrops(LivingDropsEvent event)
	{
		Entity sourceEntity = event.getSource().getTrueSource();
		if (sourceEntity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) sourceEntity;
			for (ItemStack item : player.inventory.mainInventory)
			{
				if (item.getItem() == ItemRegistry.magnet)
				{
					if (item.getItemDamage() == EnumMagnetType.ALL.ordinal())
					{
						List<EntityItem> drops = new LinkedList<>();
						for (EntityItem drop : event.getDrops())
							drops.add(drop);
						event.getDrops().clear();
						ItemMagnet.magnetItems(player.world, player, NonNullList.from(ItemStack.EMPTY, drops.stream().map(EntityItem::getItem).toArray(ItemStack[]::new)));
						return;
					}
					if (item.getItemDamage() == EnumMagnetType.MELEE.ordinal())
					{
						if (event.getSource().getImmediateSource().equals(player))
						{
							List<EntityItem> drops = new LinkedList<>();
							for (EntityItem drop : event.getDrops())
								drops.add(drop);
							event.getDrops().clear();
							ItemMagnet.magnetItems(player.world, player, NonNullList.from(ItemStack.EMPTY, drops.stream().map(EntityItem::getItem).toArray(ItemStack[]::new)));
							return;
						}
					}
					if (item.getItemDamage() == EnumMagnetType.RANGED.ordinal())
					{
						if (!event.getSource().getImmediateSource().equals(player))
						{
							List<EntityItem> drops = new LinkedList<>();
							for (EntityItem drop : event.getDrops())
								drops.add(drop);
							event.getDrops().clear();
							ItemMagnet.magnetItems(player.world, player, NonNullList.from(ItemStack.EMPTY, drops.stream().map(EntityItem::getItem).toArray(ItemStack[]::new)));
							return;
						}
					}
				}
			}
			for (ItemStack item : player.inventory.offHandInventory)
			{
				if (item.getItem() == ItemRegistry.magnet)
				{
					if (item.getItemDamage() == EnumMagnetType.ALL.ordinal())
					{
						List<EntityItem> drops = new LinkedList<>();
						for (EntityItem drop : event.getDrops())
							drops.add(drop);
						event.getDrops().clear();
						ItemMagnet.magnetItems(player.world, player, NonNullList.from(ItemStack.EMPTY, drops.stream().map(EntityItem::getItem).toArray(ItemStack[]::new)));
						return;
					}
					if (item.getItemDamage() == EnumMagnetType.MELEE.ordinal())
					{
						if (event.getSource().getImmediateSource().equals(player))
						{
							List<EntityItem> drops = new LinkedList<>();
							for (EntityItem drop : event.getDrops())
								drops.add(drop);
							event.getDrops().clear();
							ItemMagnet.magnetItems(player.world, player, NonNullList.from(ItemStack.EMPTY, drops.stream().map(EntityItem::getItem).toArray(ItemStack[]::new)));
							return;
						}
					}
					if (item.getItemDamage() == EnumMagnetType.RANGED.ordinal())
					{
						if (!event.getSource().getImmediateSource().equals(player))
						{
							List<EntityItem> drops = new LinkedList<>();
							for (EntityItem drop : event.getDrops())
								drops.add(drop);
							event.getDrops().clear();
							ItemMagnet.magnetItems(player.world, player, NonNullList.from(ItemStack.EMPTY, drops.stream().map(EntityItem::getItem).toArray(ItemStack[]::new)));
							return;
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void magnetBlockDrops(HarvestDropsEvent event)
	{
		EntityPlayer player = event.getHarvester();
		if (player == null)
			return;
		if (player.inventory == null)
			return;
		for (ItemStack item : player.inventory.mainInventory)
		{
			if (item.getItem() == ItemRegistry.magnet && (item.getItemDamage() == EnumMagnetType.MINE.ordinal() || item.getItemDamage() == EnumMagnetType.ALL.ordinal()))
			{
				List<ItemStack> drops = new LinkedList<>();
				for (ItemStack drop : event.getDrops())
					drops.add(drop);
				event.getDrops().clear();
				ItemMagnet.magnetItems(event.getWorld(), player, drops);
				return;
			}
		}
		for (ItemStack item : player.inventory.offHandInventory)
		{
			if (item.getItem() == ItemRegistry.magnet && (item.getItemDamage() == EnumMagnetType.MINE.ordinal() || item.getItemDamage() == EnumMagnetType.ALL.ordinal()))
			{
				List<ItemStack> drops = new LinkedList<>();
				for (ItemStack drop : event.getDrops())
					drops.add(drop);
				event.getDrops().clear();
				ItemMagnet.magnetItems(event.getWorld(), player, drops);
				return;
			}
		}
	}
}
