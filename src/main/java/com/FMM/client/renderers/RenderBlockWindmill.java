package com.FMM.client.renderers;

import com.FMM.reference.TileEntityInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;

public class RenderBlockWindmill extends TileEntitySpecialRenderer
{
	public static final ResourceLocation TEXTURE = new ResourceLocation(TileEntityInfo.RESOURCE_LOCATION, TileEntityInfo.WINDMILL_TEXTURE);

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f)
	{
        int meta = te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);

		glPushMatrix();
		glTranslated(x+0.5, y+0.5, z+0.5);
        glRotatef(-(meta - 1) * 90, 0, 1, 0);
        glTranslated(-0.5, -0.5, -0.5);
        glDisable(GL_LIGHTING);
		
		bindTexture(TEXTURE);
		
		Tessellator tessellator = Tessellator.instance;

        tessellator.startDrawingQuads();

        if(meta == 0)
        {
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
        }
        tessellator.draw();
		
		glColor4f(1, 1, 1, 1);
        glEnable(GL_LIGHTING);
		glPopMatrix();
	}

}
