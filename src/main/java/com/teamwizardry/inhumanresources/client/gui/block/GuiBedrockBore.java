package com.teamwizardry.inhumanresources.client.gui.block;

import com.teamwizardry.inhumanresources.client.gui.container.ContainerBedrockBore;
import com.teamwizardry.inhumanresources.common.blocks.tile.TEBedrockBore;
import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;
import com.teamwizardry.librarianlib.features.container.builtin.BaseWrappers.InventoryWrapperPlayer;
import com.teamwizardry.librarianlib.features.container.internal.SlotBase;
import com.teamwizardry.librarianlib.features.gui.components.ComponentSprite;
import com.teamwizardry.librarianlib.features.guicontainer.ComponentSlot;
import com.teamwizardry.librarianlib.features.guicontainer.GuiContainerBase;
import com.teamwizardry.librarianlib.features.guicontainer.builtin.BaseLayouts;
import com.teamwizardry.librarianlib.features.guicontainer.builtin.BaseLayouts.PlayerLayout;
import com.teamwizardry.librarianlib.features.math.Vec2d;
import com.teamwizardry.librarianlib.features.sprite.Sprite;
import com.teamwizardry.librarianlib.features.sprite.Texture;

import net.minecraft.util.ResourceLocation;

public class GuiBedrockBore extends GuiContainerBase
{
	public static Texture spriteSheet = new Texture(new ResourceLocation(ModInfo.MOD_ID, "textures/gui/bedrock_bore.png"));
	public static Sprite texture = spriteSheet.getSprite("background", 176, 166);
	public static Sprite progress = spriteSheet.getSprite("progress_bar", 29, 12);
	
	public GuiBedrockBore(ContainerBedrockBore container)
	{
		super(container, texture.getWidth(), texture.getHeight());
		ComponentSprite background = new ComponentSprite(texture, 0, 0);
		getMainComponents().add(background);
		InventoryWrapperPlayer playerInv = container.playerInv;
		PlayerLayout inventory = BaseLayouts.INSTANCE.player(playerInv);
		inventory.getMain().getChildren().forEach(slot -> slot.setSize(new Vec2d(18, 18)));
		inventory.getHotbar().getChildren().forEach(slot -> slot.setSize(new Vec2d(18, 18)));
		inventory.getRoot().setPos(new Vec2d(7, 83));
		ComponentSlot fuel = new ComponentSlot(new SlotBase(container.blockInv.getInventory(), TEBedrockBore.FUEL_SLOT), 55, 34);
		fuel.setSize(new Vec2d(18, 18));
		ComponentSlot out = new ComponentSlot(new SlotBase(container.blockInv.getInventory(), TEBedrockBore.OUTPUT_SLOT), 111, 30);
		out.setSize(new Vec2d(26, 26));
		ComponentSprite progressBar = new ComponentSprite(progress, 77, 37);
		
		double progressPercent = ((double) container.bore.burnTime) / TEBedrockBore.FUEL_TIME;
		Vec2d progressSize = new Vec2d(progress.getWidth() * progressPercent, progress.getHeight());
		progressBar.setSize(progressSize);
		
		background.add(inventory.getRoot(), fuel, out, progressBar);
	}
}
