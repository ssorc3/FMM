package com.FMM.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class ModelDroid extends ModelBase
{
    private ArrayList<ModelRenderer> parts;
    private ArrayList<ModelRenderer> panels;
    private ArrayList<ModelRenderer> outerPanels;
    private ModelRenderer core;
    private ModelRenderer pillars;

    public ModelDroid()
    {
        parts = new ArrayList<ModelRenderer>();
        panels = new ArrayList<ModelRenderer>();
        outerPanels = new ArrayList<ModelRenderer>();
        textureWidth = 64;
        textureHeight = 64;

        ModelRenderer main = new ModelRenderer(this, 0, 0);
        main.addBox(-5, -5, -5, 10, 10, 10);
        main.setRotationPoint(0f, 0f, 0f);
        parts.add(main);

        pillars = new ModelRenderer(this, 0, 0);
        for(int x = -1; x <= 1; x += 2)
            for(int z = -1; z <= 1; z += 2)
            {
                pillars.addBox(-1 + x * 3.995f, -1, -1 + z * 3.995F, 2, 8, 2);
            }
        pillars.rotateAngleY = (float)Math.PI;
        parts.add(pillars);

        ModelRenderer top = new ModelRenderer(this, 0, 20);
        top.addBox(-5, -2, -5, 10, 4, 10);
        top.setRotationPoint(0, -3, 0);
        pillars.addChild(top);

        for (float r = 0; r <= Math.PI * 2; r += Math.PI / 2)
        {
            ModelRenderer side = new ModelRenderer(this, 0, 34);
            side.addBox(-4, -2.5F, 5, 8, 5, 1);
            side.setRotationPoint(0, 0, 0);
            side.rotateAngleY = r;
            parts.add(side);

            ModelRenderer panel = new ModelRenderer(this, 18, 34);
            panel.addBox(-4f, -0.5f, -0.5f, 8, 5, 1);
            panel.setRotationPoint(0, -2f, 6.5f);
            side.addChild(panel);
            panels.add(panel);

            ModelRenderer panelOuter = new ModelRenderer(this, 18, 34);
            panelOuter.addBox(-4, 0, -1, 8, 5, 1);
            panelOuter.setRotationPoint(0, 4.5f, 0.5f);
            panel.addChild(panelOuter);
            outerPanels.add(panelOuter);
        }

        core = new ModelRenderer(this, 30, 0);
        core.addBox(-3, -1, -3, 6, 2, 6);
        core.setRotationPoint(0, -6, 0);


        //z * z gap, x * z, x * z (height = z)
        //z * y, x * y, z * y, x * y (height = y)
        //top, bottom
        //left, front, right, back
    }

    public void render(float coreRotation, float panelRotation, float outerPanelRotation, float topPosition, int color, float scale)
    {
        core.rotateAngleY = coreRotation;

        for (ModelRenderer panel : panels) {
            panel.rotateAngleX = panelRotation;
        }

        for (ModelRenderer panelOuter : outerPanels) {
            panelOuter.rotateAngleX = outerPanelRotation;
        }

        pillars.rotationPointY = topPosition;

        for(ModelRenderer part : parts)
        {
            part.render(scale);
        }

        float r = (color >> 16 & 0xFF) / 255;
        float g = (color >> 8 & 0xFF) / 255;
        float b = (color & 0xFF) / 255;

        GL11.glColor4f(r, g, b, 1);
        core.render(scale);
        GL11.glColor4f(1, 1, 1, 1);
    }
}
