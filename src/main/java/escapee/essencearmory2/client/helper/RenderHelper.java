package escapee.essencearmory2.client.helper;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

/**
 * Created by SirShadow on 15.8.2016.
 */
public class RenderHelper
{
    public static void bindTexture(ResourceLocation texture) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
    }

    public static int getCenteredTextOffset(FontRenderer fontRenderer, String string, int width) {
        return (width - fontRenderer.getStringWidth(string)) / 2;
    }

    @SuppressWarnings("unused")
	private static double getPulseValue() {
        return (Math.sin(System.nanoTime() / 100f) + 1) / 2;
    }
}
