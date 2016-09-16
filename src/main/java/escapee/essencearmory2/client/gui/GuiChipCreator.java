package escapee.essencearmory2.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import escapee.essencearmory2.common.tile.TEChipCreator;
import escapee.essencearmory2.lib.ModInfo;
import escapee.essencearmory2.server.container.ContainerChipCreator;

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
