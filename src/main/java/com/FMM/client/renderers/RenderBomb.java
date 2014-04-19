package com.FMM.client.renderers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.FMM.client.models.ModelBomb;
import static com.FMM.reference.EntityInfo.*;

public class RenderBomb extends Render
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_LOCATION, BOMB_TEXTURE);

	private ModelBomb model;
	
	public RenderBomb()
	{
		this.model = new ModelBomb();
		shadowSize = 0.5f;
	}
	
	@Override
	public void doRender(Entity bomb, double x, double y, double z, float yaw, float partialTickTime)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glScalef(-1, -1, 1);
		bindEntityTexture(bomb);
		model.render(bomb, 0, 0, 0, 0, 0, 0.0625f);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1)
	{
		return TEXTURE;
	}

}
