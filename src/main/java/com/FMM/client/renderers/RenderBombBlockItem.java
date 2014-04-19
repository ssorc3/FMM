package com.FMM.client.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.IModelCustom;

public class RenderBombBlockItem implements IItemRenderer
{
	private IModelCustom model;
	
	public RenderBombBlockItem(IModelCustom model)
	{
		this.model = model;
	}

	@Override
	public boolean handleRenderType(ItemStack arg0, ItemRenderType arg1)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack arg1, Object... arg2)
	{
		GL11.glPushMatrix();
		switch(type)
		{
			case EQUIPPED:
				GL11.glTranslatef(0.6f, 0, 0.3f);
				GL11.glRotatef(200, 0, 1, 0);
				break;
			case EQUIPPED_FIRST_PERSON:
				GL11.glTranslatef(0, 0.2f, 0.5f);
				GL11.glRotatef(90, 0, 1, 0);
				break;
			case INVENTORY:
				GL11.glTranslatef(0, -0.5f, 0);
				GL11.glRotatef(180, 0, 1, 0);
				break;
		}
		GL11.glScalef(0.5f, 0.5f, 0.5f);
		Minecraft.getMinecraft().renderEngine.bindTexture(RenderBombBlock.TEXTURE);
		model.renderAll();
		GL11.glPopMatrix();
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType arg0, ItemStack arg1, ItemRendererHelper arg2)
	{
		return true;
	}
	
}
