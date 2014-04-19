package com.FMM.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.FMM.reference.TileEntityInfo;

public class RenderBombBlock extends TileEntitySpecialRenderer
{
	public static final ResourceLocation TEXTURE = new ResourceLocation(TileEntityInfo.RESOURCE_LOCATION, TileEntityInfo.BOMB_TEXTURE);
	private static final ResourceLocation TEXTURE_IDLE = new ResourceLocation(TileEntityInfo.RESOURCE_LOCATION, TileEntityInfo.BOMB_TEXTURE_IDLE);
	
	private IModelCustom model;
	
	public RenderBombBlock(IModelCustom model)
	{
		this.model = model;
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTickTime)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5f, (float)y, (float)z + 0.5f);
		GL11.glScalef(0.5f, 0.5f, 0.5f);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(te.getBlockMetadata() == 0 ? TEXTURE : TEXTURE_IDLE);
		model.renderAll();
		
		GL11.glPopMatrix();
	}

}
