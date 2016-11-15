package com.teamwizardry.inhumanresources.common.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import com.teamwizardry.inhumanresources.common.entity.mobs.MobSlime;

public class LayerSlimeGel implements LayerRenderer<MobSlime>
{
	private final RenderSlime slimeRenderer;
    private final ModelBase slimeModel = new ModelSlime(0);

    public LayerSlimeGel(RenderSlime slimeRendererIn)
    {
        this.slimeRenderer = slimeRendererIn;
    }

    @Override
    public void doRenderLayer(MobSlime entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (!entitylivingbaseIn.isInvisible())
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.slimeModel.setModelAttributes(this.slimeRenderer.getMainModel());
            this.slimeModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return true;
    }
}
