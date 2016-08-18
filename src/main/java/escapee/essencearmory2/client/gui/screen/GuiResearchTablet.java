package escapee.essencearmory2.client.gui.screen;

import escapee.essencearmory2.common.capability.knowlage.KnowledgeProvider;
import escapee.essencearmory2.common.utils.helper.TextHelper;
import escapee.essencearmory2.lib.LibMain;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.w3c.dom.Text;

/**
 * Created by SirShadow on 18. 08. 2016.
 */
public class GuiResearchTablet extends GuiScreen
{
    private int WIDTH,HEIGHT,guiXStart;


    public GuiResearchTablet()
    {
        WIDTH = 150;
        HEIGHT = 200;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        guiXStart = (width - WIDTH) / 2;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawBackground();
        drawForeground();
        super.drawScreen(mouseX,mouseY,partialTicks);
    }

    public void drawBackground()
    {
        mc.renderEngine.bindTexture(LibMain.ModResourceLocations.SIMPLE_GUI);
        GL11.glPushMatrix();
        GL11.glColor4f(0,0,0,10f);
        GL11.glPopMatrix();
        drawModalRectWithCustomSizedTexture(guiXStart - 15, 20, 0, 0, 180,230, 180,230);
    }

    public void drawForeground()
    {
        EntityPlayer player = mc.getMinecraft().thePlayer;
        fontRendererObj.drawSplitString("Player knowledge",guiXStart + 25,28,100,0xFFFFF);
        if(KnowledgeProvider.get(player).hasKnowledge("zombieThinking"))
        {
            fontRendererObj.drawString(TextHelper.localise("essencearmory2.knowledge.zombieThinking"),guiXStart - 10,70,0xFFFFF);
        }
    }
}
