package com.FMM.client.models;

import java.util.ArrayList;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBomb extends ModelBase
{
	private ArrayList<ModelRenderer> parts = new ArrayList<ModelRenderer>();
	
	public ModelBomb()
	{
		parts = new ArrayList<ModelRenderer>();
		textureWidth = 64;
		textureHeight = 64;
		
		ModelRenderer tip = new ModelRenderer(this, 0, 0);
		tip.addBox(-2, -1, -2, 4, 2, 4);
		tip.setRotationPoint(0, 0, 0);
		parts.add(tip);
		
		ModelRenderer body = new ModelRenderer(this, 0, 6);
		body.addBox(-2.5f, -3, -2.5f, 5, 6, 5);
		body.setRotationPoint(0, -4, 0);
		parts.add(body);
		
		for(int i = 0; i < 4; i++)
		{
			ModelRenderer helper = new ModelRenderer(this);
			ModelRenderer leg = new ModelRenderer(this, 16, 0);
			leg.addBox(5, -1.5f, 1.5f, 4, 3, 1);
			helper.setRotationPoint(0, -1, 0);
			helper.addChild(leg);
			setRotation(helper, 0, (float)(Math.PI / 2) * i, 0);
			setRotation(leg, (float)Math.PI / 2, 0, -(float)Math.PI / 4);
			parts.add(helper);
			
		}
	}

	public void render(Entity entity, float v1, float v2, float v3, float v4, float v5, float scale)
	{
		for(ModelRenderer part : parts)
		{
			part.render(scale);
		}
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
}
