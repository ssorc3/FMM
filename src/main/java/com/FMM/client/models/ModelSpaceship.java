package com.FMM.client.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class ModelSpaceship extends ModelBase {
    private ArrayList<ModelRenderer> parts = new ArrayList<ModelRenderer>();

    public ModelSpaceship() {
        textureWidth = 128;
        textureHeight = 64;

        ModelRenderer bot = new ModelRenderer(this, 0, 0);
        parts.add(bot);

        bot.addBox(-14, -8, -2,
                28, 16, 4,
                0.0F);

        bot.setRotationPoint(0, 4, 0);

        bot.rotateAngleX = (float) Math.PI / 2F;


        ModelRenderer side1 = new ModelRenderer(this, 0, 20);
        parts.add(side1);

        side1.addBox(-14, -1.5F, -2,
                28, 3, 4,
                0.0F);

        side1.setRotationPoint(0, 0, -9);

        side1.rotateAngleX = (float) Math.PI / 2F;

        ModelRenderer side2 = new ModelRenderer(this, 0, 20);
        parts.add(side2);

        side2.addBox(-14, -1.5F, -2,
                28, 3, 4,
                0.0F);

        side2.setRotationPoint(0, 0, 9);

        side2.rotateAngleX = (float) Math.PI / 2F;

        ModelRenderer back = new ModelRenderer(this, 0, 27);
        parts.add(back);

        back.addBox(-10.5F, -1.5F, -2,
                21, 3, 4,
                0.0F);

        back.setRotationPoint(15.5F, 0, 0);

        back.rotateAngleX = (float) Math.PI / 2F;
        back.rotateAngleY = (float) Math.PI / 2F;


        ModelRenderer wing1 = new ModelRenderer(this, 0, 34);
        parts.add(wing1);

        wing1.addBox(-13, -0.5F, -4,
                26, 1, 8,
                0.0F);

        wing1.setRotationPoint(0, -1, -14);


        ModelRenderer wing2 = new ModelRenderer(this, 0, 34);
        parts.add(wing2);

        wing2.addBox(-13, -0.5F, -4,
                26, 1, 8,
                0.0F);

        wing2.setRotationPoint(0, -1, 14);


        ModelRenderer front = new ModelRenderer(this, 0, 43);
        parts.add(front);

        front.addBox(-7.5F, -2.5F, -3,
                15, 5, 6,
                0.0F);

        front.setRotationPoint(-16, 1, 0);

        front.rotateAngleX = (float) Math.PI / 2F;
        front.rotateAngleY = (float) Math.PI / 2F;


        ModelRenderer window = new ModelRenderer(this, 0, 54);
        parts.add(window);

        window.addBox(-7.5F, -0.5F, -2,
                15, 1, 4,
                0.0F);

        window.setRotationPoint(-14.5F, -4, 0);

        window.rotateAngleX = (float) Math.PI / 2F;
        window.rotateAngleY = (float) Math.PI / 2F;

    }


    public void render(Entity entity, float val1, float val2, float val3, float val4, float val5, float mult) {
        for (ModelRenderer part : parts) {
            part.render(mult);
        }
    }
}
