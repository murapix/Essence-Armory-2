package com.teamwizardry.inhumanresources.client.gui.base;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import com.teamwizardry.inhumanresources.client.helper.RenderHelper;

/**
 * Created by SirShadow on 15.8.2016.
 */
@SideOnly(Side.CLIENT)
public class GuiBase extends GuiContainer
{
    @SideOnly(Side.CLIENT)
    ResourceLocation location;
    String title;
    @SuppressWarnings("unused")
	private boolean drawTitle;

    public GuiBase(String title,Container container,ResourceLocation location)
    {
        super(container);
        this.title = title;
        this.location = location;
    }

    public String getTitle()
    {
        return title;
    }

    public GuiBase setTitle(String title)
    {
        this.title = title;
        return this;
    }

    public GuiBase setDrawTitle(boolean setDrawTitle)
    {
        this.drawTitle = setDrawTitle;
        return this;
    }

    public int getScreenWidth() {
        return width;
    }

    public int getScreenHeight() {
        return height;
    }

    public int getGuiPositionX() {
        return guiLeft;
    }

    public GuiBase setGuiPositionX(int positionX) {
        this.guiLeft = positionX;
        return this;
    }

    public int getGuiPositionY() {
        return guiTop;
    }

    public GuiBase setGuiPositionY(int positionY) {
        this.guiTop = positionY;
        return this;
    }

    public int getGuiWidth() {
        return xSize;
    }

    public GuiBase setGuiWidth(int width) {
        this.xSize = width;
        return this;
    }

    public int getGuiHeight() {
        return ySize;
    }

    public GuiBase setGuiHeight(int height) {
        this.ySize = height;
        return this;
    }

    public FontRenderer getFontRenderer() {
        return fontRendererObj;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (location != null) {
            RenderHelper.bindTexture(location);
            int xStart = (getScreenWidth() - getGuiWidth()) / 2;
            int yStart = (getScreenHeight() - getGuiHeight()) / 2;
            this.drawTexturedModalRect(xStart, yStart, 0, 0, getGuiWidth(), getGuiHeight());
        }
    }
}
