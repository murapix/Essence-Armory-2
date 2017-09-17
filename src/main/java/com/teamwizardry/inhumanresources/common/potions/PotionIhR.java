package com.teamwizardry.inhumanresources.common.potions;

import org.lwjgl.opengl.GL11;

import com.teamwizardry.inhumanresources.InhumanResources;
import com.teamwizardry.inhumanresources.init.PotionRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionIhR extends Potion
{
	public static ResourceLocation texture = new ResourceLocation(InhumanResources.MOD_ID + ":textures/misc/potions.png");
	private final int iconIndex;

	public PotionIhR(String name, boolean badEffect, int color, int iconIndex)
	{
		super(badEffect, color);
		setRegistryName(new ResourceLocation(InhumanResources.MOD_ID, name));
		setPotionName(InhumanResources.MOD_ID + ".potion." + name);
		this.iconIndex = iconIndex;
		PotionRegistry.potions.add(this);
	}
	
	public boolean hasEffect(EntityLivingBase entity)
	{
		return hasEffect(entity, this);
	}

	public boolean hasEffect(EntityLivingBase entity, Potion potion)
	{
		return entity.getActivePotionEffect(potion) != null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
	{
		render(x + 6, y + 7, 1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha)
	{
		render(x + 3, y + 3, alpha);
	}
	
	@SideOnly(Side.CLIENT)
	private void render(int x, int y, float alpha)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffBuild = tessellator.getBuffer();
		buffBuild.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		GlStateManager.color(1, 1, 1, alpha);
		
		int textureX = iconIndex % 8 * 18;
		int textureY = 198 + iconIndex / 8 * 18;
		
		buffBuild.pos(x, y + 18, 0).tex(textureX * 0.00390625, (textureY + 18) * 0.00390625).endVertex();
		buffBuild.pos(x + 18, y + 18, 0).tex((textureX + 18) * 0.00390625, (textureY + 18) * 0.00390625).endVertex();
		buffBuild.pos(x + 18, y, 0).tex((textureX + 18) * 0.00390625, textureY * 0.00390625).endVertex();
		buffBuild.pos(x, y, 0).tex(textureX * 0.00390625, textureY * 0.00390625).endVertex();

		tessellator.draw();
	}
}
