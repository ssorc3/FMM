package com.FMM.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import static org.lwjgl.opengl.GL11.*;

public class RenderWindmillItem implements IItemRenderer
{

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        glPushMatrix();
        glScaled(0.5, 0.5, 0.5);
        switch(type)
        {
            case EQUIPPED_FIRST_PERSON:
                glTranslated(0, 0.5, 0);
                break;
            case EQUIPPED:
                glTranslated(0, 0, 0.2);
                break;
            case INVENTORY:
                glScaled(1.5, 1.5, 1.5);
            default:
        }
        Minecraft.getMinecraft().renderEngine.bindTexture(RenderBlockWindmill.TEXTURE);
        Tessellator tessellator = Tessellator.instance;

        tessellator.startDrawingQuads();
        //front
        tessellator.addVertexWithUV(0.25, 0, 0.25, 1f/32*8, 1f/32*1);
        tessellator.addVertexWithUV(0.25, 1.0125, 0.25, 1f/32*8, 1f/32*0);
        tessellator.addVertexWithUV(0.75, 1.0125, 0.25, 1f/32*0, 1f/32*0);
        tessellator.addVertexWithUV(0.75, 0, 0.25, 1f/32*0, 1f/32*1);

        //left
        tessellator.addVertexWithUV(0.75, 0, 0.25, 1f/32*8, 1f/32*1);
        tessellator.addVertexWithUV(0.75, 1.0125, 0.25, 1f/32*8, 1f/32*0);
        tessellator.addVertexWithUV(0.75, 1.0125, 0.75, 1f/32*0, 1f/32*0);
        tessellator.addVertexWithUV(0.75, 0, 0.75, 1f/32*0, 1f/32*1);

        //back
        tessellator.addVertexWithUV(0.75, 0, 0.75, 1f/32*8, 1f/32*1);
        tessellator.addVertexWithUV(0.75, 1.0125, 0.75, 1f/32*8, 1f/32*0);
        tessellator.addVertexWithUV(0.25, 1.0125, 0.75, 1f/32*0, 1f/32*0);
        tessellator.addVertexWithUV(0.25, 0, 0.75, 1f/32*0, 1f/32*1);

        //right
        tessellator.addVertexWithUV(0.25, 0, 0.75, 1f/32*8, 1f/32*1);
        tessellator.addVertexWithUV(0.25, 1.0125, 0.75, 1f/32*8, 1f/32*0);
        tessellator.addVertexWithUV(0.25, 1.0125, 0.25, 1f/32*0, 1f/32*0);
        tessellator.addVertexWithUV(0.25, 0, 0.25, 1f/32*0, 1f/32*1);

        //top
        tessellator.addVertexWithUV(0.25, 1.0125, 0.25, 1f/32*7, 1f/32*1);
        tessellator.addVertexWithUV(0.25, 1.0125, 0.75, 1f/32*7, 1f/32*0);
        tessellator.addVertexWithUV(0.75, 1.0125, 0.75, 1f/32*1, 1f/32*0);
        tessellator.addVertexWithUV(0.75, 1.0125, 0.25, 1f/32*1, 1f/32*1);

        //bottom
        tessellator.addVertexWithUV(0.25, 0, 0.25, 1f/32*7, 1f/32*1);
        tessellator.addVertexWithUV(0.25, 0, 0.75, 1f/32*7, 1f/32*0);
        tessellator.addVertexWithUV(0.75, 0, 0.75, 1f/32*1, 1f/32*0);
        tessellator.addVertexWithUV(0.75, 0, 0.25, 1f/32*1, 1f/32*1);

        tessellator.draw();
        glPopMatrix();
    }
}
