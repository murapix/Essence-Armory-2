package com.teamwizardry.inhumanresources.client.gui;

import com.teamwizardry.inhumanresources.common.tile.TEChipCreator;
import com.teamwizardry.inhumanresources.common.utils.lib.ModInfo;
import com.teamwizardry.inhumanresources.server.container.ContainerChipCreator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiChipCreator extends GuiContainer
{
	public static final int WIDTH = 180;
	public static final int HEIGHT = 152;
	
	private static final ResourceLocation background = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/chip_creator.png");
	
	public GuiChipCreator(TEChipCreator te, ContainerChipCreator container)
	{
		super(container);
		this.xSize = WIDTH;
		this.ySize = HEIGHT;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
