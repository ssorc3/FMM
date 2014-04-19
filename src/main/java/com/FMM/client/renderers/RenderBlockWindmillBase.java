package com.FMM.client.renderers;

import com.FMM.block.ModBlocks;
import com.FMM.reference.TileEntityInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;

public class RenderBlockWindmillBase extends TileEntitySpecialRenderer
{
    private final ResourceLocation CONNECTED = new ResourceLocation(TileEntityInfo.RESOURCE_LOCATION, TileEntityInfo.WINDMILLBASE_CONNECTED);

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f)
	{
        int textureWidth = 32;
        int textureHeight = 32;

		glPushMatrix();
		glDisable(GL_LIGHTING);
		glTranslated(x, y, z);
		bindTexture(CONNECTED);
		
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();

        if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 1)
		{
            //top
			tessellator.addVertexWithUV(0.5f, 0.3f, 0.5f, 1f/textureWidth*(32), 1f/textureHeight*(32));
			tessellator.addVertexWithUV(0.5f, 0.3f, 0, 1f/textureWidth*(32), 1f/textureHeight*(24));
			tessellator.addVertexWithUV(0, 0.3f, 0, 1f/textureWidth*(24), 1f/textureHeight*(24));
			tessellator.addVertexWithUV(0, 0.3f, 0.5f, 1f/textureWidth*(24), 1f/textureHeight*(32));

            //bottom
            tessellator.addVertexWithUV(0.5, 0, 0.5, 1f/textureWidth*(32), 1f/textureHeight*(32));
            tessellator.addVertexWithUV(0, 0, 0.5, 1f/textureWidth*(32), 1f/textureHeight*(24));
            tessellator.addVertexWithUV(0, 0, 0, 1f/textureWidth*(24), 1f/textureHeight*(24));
            tessellator.addVertexWithUV(0.5, 0, 0, 1f/textureWidth*(24), 1f/textureHeight*(32));

            //-z
            tessellator.addVertexWithUV(0.5, 0, 0.5, 1f/textureWidth*(32), 1f/textureHeight*(32));
            tessellator.addVertexWithUV(0.5, 0.3, 0.5, 1f/textureWidth*(32), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0, 0.3, 0.5, 1f/textureWidth*(24), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0, 0, 0.5, 1f/textureWidth*(24), 1f/textureHeight*(32));

            //-x
            tessellator.addVertexWithUV(0.5, 0, 0, 1f/textureWidth*(8), 1f/textureHeight*(32));
            tessellator.addVertexWithUV(0.5, 0.3, 0, 1f/textureWidth*(8), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.5, 0.3, 0.5, 1f/textureWidth*(0), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.5, 0, 0.5, 1f/textureWidth*(0), 1f/textureHeight*(32));
		}
		else if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 2)
		{
            //top
			tessellator.addVertexWithUV(0.5f, 0.3f, 1, 1f/textureWidth*(32), 1f/textureHeight*(24));
			tessellator.addVertexWithUV(0.5f, 0.3f, 0, 1f/textureWidth*(32), 1f/textureHeight*(8));
			tessellator.addVertexWithUV(0, 0.3f, 0, 1f/textureWidth*(24), 1f/textureHeight*(8));
			tessellator.addVertexWithUV(0, 0.3f, 1, 1f/textureWidth*(24), 1f/textureHeight*(24));

            //bottom
            tessellator.addVertexWithUV(0.5, 0, 0, 1f/textureWidth*(32), 1f/textureHeight*(24));
            tessellator.addVertexWithUV(0.5, 0, 1, 1f/textureWidth*(32), 1f/textureHeight*(8));
            tessellator.addVertexWithUV(0, 0, 1, 1f/textureWidth*(24), 1f/textureHeight*(8));
            tessellator.addVertexWithUV(0, 0, 0, 1f/textureWidth*(24), 1f/textureHeight*(24));

            //-x
            tessellator.addVertexWithUV(0.5, 0, 0, 1f/textureWidth*(24), 1f/textureHeight*(32));
            tessellator.addVertexWithUV(0.5, 0.3, 0, 1f/textureWidth*(24), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.5, 0.3, 1, 1f/textureWidth*(8), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.5, 0, 1, 1f/textureWidth*(8), 1f/textureHeight*(32));
		}
        else if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 3)
        {
            tessellator.addVertexWithUV(0.5f, 0.3f, 1, 1f/textureWidth*(32), 1f/textureHeight*(8));
            tessellator.addVertexWithUV(0.5f, 0.3f, 0.5f, 1f/textureWidth*(32), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0, 0.3f, 0.5f, 1f/textureWidth*(24), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0, 0.3f, 1, 1f/textureWidth*(24), 1f/textureHeight*(8));
        }
        else if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 4)
        {
            tessellator.addVertexWithUV(1, 0.3f, 0.5f, 1f/textureWidth*(24), 1f/textureHeight*(32));
            tessellator.addVertexWithUV(1, 0.3f, 0, 1f/textureWidth*(24), 1f/textureHeight*(24));
            tessellator.addVertexWithUV(0, 0.3f, 0, 1f/textureWidth*(8), 1f/textureHeight*(24));
            tessellator.addVertexWithUV(0, 0.3f, 0.5f, 1f/textureWidth*(8), 1f/textureHeight*(32));
        }
        else if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 5 && te.getWorldObj().getBlock(te.xCoord, te.yCoord+1, te.zCoord).equals(ModBlocks.windmill))
        {
            tessellator.addVertexWithUV(1, 0.3f, 1, 1f/textureWidth*(24), 1f/textureHeight*(24));
            tessellator.addVertexWithUV(1, 0.3f, 0, 1f/textureWidth*(24), 1f/textureHeight*(8));
            tessellator.addVertexWithUV(0, 0.3f, 0, 1f/textureWidth*(8), 1f/textureHeight*(8));
            tessellator.addVertexWithUV(0, 0.3f, 1, 1f/textureWidth*(8), 1f/textureHeight*(24));

            tessellator.draw();

            bindTexture(RenderBlockWindmill.TEXTURE);

            tessellator.startDrawingQuads();

            //bottom
            tessellator.addVertexWithUV(0.25, 0.3001f, 0.25, 1f/textureWidth*(7), 1f/textureHeight*(1));
            tessellator.addVertexWithUV(0.25, 0.3001f, 0.75, 1f/textureWidth*(7), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.75, 0.3001f, 0.75, 1f/textureWidth*(1), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.75, 0.3001f, 0.25, 1f/textureWidth*(1), 1f/textureHeight*(1));

            //top
            tessellator.addVertexWithUV(0.25, 1.0125, 0.25, 1f/textureWidth*(7), 1f/textureHeight*(1));
            tessellator.addVertexWithUV(0.25, 1.0125, 0.75, 1f/textureWidth*(7), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.75, 1.0125, 0.75, 1f/textureWidth*(1), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.75, 1.0125, 0.25, 1f/textureWidth*(1), 1f/textureHeight*(1));

            //front
            tessellator.addVertexWithUV(0.25, 0.3001f, 0.25, 1f/textureWidth*(8), 1f/textureHeight*(1));
            tessellator.addVertexWithUV(0.25, 1, 0.25, 1f/textureWidth*(8), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.75, 1, 0.25, 1f/textureWidth*(0), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.75, 0.3001f, 0.25, 1f/textureWidth*(0), 1f/textureHeight*(1));

            tessellator.addVertexWithUV(0.75, 0.3001f, 0.25, 1f/textureWidth*(8), 1f/textureHeight*(1));
            tessellator.addVertexWithUV(0.75, 1, 0.25, 1f/textureWidth*(8), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.75, 1, 0.75, 1f/textureWidth*(0), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.75, 0.3001f, 0.75, 1f/textureWidth*(0), 1f/textureHeight*(1));

            tessellator.addVertexWithUV(0.75, 0.3001f, 0.75, 1f/textureWidth*(8), 1f/textureHeight*(1));
            tessellator.addVertexWithUV(0.75, 1, 0.75, 1f/textureWidth*(8), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.25, 1, 0.75, 1f/textureWidth*(0), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.25, 0.3001f, 0.75, 1f/textureWidth*(0), 1f/textureHeight*(1));

            tessellator.addVertexWithUV(0.25, 0.3001f, 0.75, 1f/textureWidth*(8), 1f/textureHeight*(1));
            tessellator.addVertexWithUV(0.25, 1, 0.75, 1f/textureWidth*(8), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.25, 1, 0.25, 1f/textureWidth*(0), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.25, 0.3001f, 0.25, 1f/textureWidth*(0), 1f/textureHeight*(1));
        }
        else if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 5)
        {
            tessellator.addVertexWithUV(1, 0.3f, 1, 1f/textureWidth*(24), 1f/textureHeight*(24));
            tessellator.addVertexWithUV(1, 0.3f, 0, 1f/textureWidth*(24), 1f/textureHeight*(8));
            tessellator.addVertexWithUV(0, 0.3f, 0, 1f/textureWidth*(8), 1f/textureHeight*(8));
            tessellator.addVertexWithUV(0, 0.3f, 1, 1f/textureWidth*(8), 1f/textureHeight*(24));
        }
        else if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 6)
        {
            tessellator.addVertexWithUV(1, 0.3f, 1, 1f/textureWidth*(24), 1f/textureHeight*(8));
            tessellator.addVertexWithUV(1, 0.3f, 0.5f, 1f/textureWidth*(24), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0, 0.3f, 0.5f, 1f/textureWidth*(8), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0, 0.3f, 1, 1f/textureWidth*(8), 1f/textureHeight*(8));
        }
        else if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 7)
        {
            tessellator.addVertexWithUV(1, 0.3f, 0.5f, 1f/textureWidth*(8), 1f/textureHeight*(32));
            tessellator.addVertexWithUV(1, 0.3f, 0, 1f/textureWidth*(8), 1f/textureHeight*(24));
            tessellator.addVertexWithUV(0.5f, 0.3f, 0, 1f/textureWidth*(0), 1f/textureHeight*(24));
            tessellator.addVertexWithUV(0.5f, 0.3f, 0.5f, 1f/textureWidth*(0), 1f/textureHeight*(32));
        }
        else if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 8)
        {
            tessellator.addVertexWithUV(1, 0.3f, 1, 1f/textureWidth*(8), 1f/textureHeight*(24));
            tessellator.addVertexWithUV(1, 0.3f, 0, 1f/textureWidth*(8), 1f/textureHeight*(8));
            tessellator.addVertexWithUV(0.5f, 0.3f, 0, 1f/textureWidth*(0), 1f/textureHeight*(8));
            tessellator.addVertexWithUV(0.5f, 0.3f, 1, 1f/textureWidth*(0), 1f/textureHeight*(24));
        }
        else if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 9)
        {
            tessellator.addVertexWithUV(1, 0.3f, 1, 1f/textureWidth*(8), 1f/textureHeight*(8));
            tessellator.addVertexWithUV(1, 0.3f, 0.5f, 1f/textureWidth*(8), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.5f, 0.3f, 0.5f, 1f/textureWidth*(0), 1f/textureHeight*(0));
            tessellator.addVertexWithUV(0.5f, 0.3f, 1, 1f/textureWidth*(0), 1f/textureHeight*(8));
        }
        else if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 0)
        {
            tessellator.addVertexWithUV(1, 0.3f, 1, 1, 1);
            tessellator.addVertexWithUV(1, 0.3f, 0, 1, 0);
            tessellator.addVertexWithUV(0, 0.3f, 0, 0, 0);
            tessellator.addVertexWithUV(0, 0.3f, 1, 0, 1);

            tessellator.addVertexWithUV(0, 0, 1, 1, 1);
            tessellator.addVertexWithUV(0, 0, 0, 1, 0);
            tessellator.addVertexWithUV(1, 0, 0, 0, 0);
            tessellator.addVertexWithUV(1, 0, 1, 0, 1);

            tessellator.addVertexWithUV(1, 0, 1, 1, 1);
            tessellator.addVertexWithUV(1, 0.3f, 1, 1, 0);
            tessellator.addVertexWithUV(0, 0.3f, 1, 0, 0);
            tessellator.addVertexWithUV(0, 0, 1, 0, 1);

            tessellator.addVertexWithUV(0, 0, 1, 1, 1);
            tessellator.addVertexWithUV(0, 0.3f, 1, 1, 0);
            tessellator.addVertexWithUV(0, 0.3f, 0, 0, 0);
            tessellator.addVertexWithUV(0, 0, 0, 0, 1);

            tessellator.addVertexWithUV(0, 0, 0, 1, 1);
            tessellator.addVertexWithUV(0, 0.3f, 0, 1, 0);
            tessellator.addVertexWithUV(1, 0.3f, 0, 0, 0);
            tessellator.addVertexWithUV(1, 0, 0, 0, 1);

            tessellator.addVertexWithUV(1, 0, 0, 1, 1);
            tessellator.addVertexWithUV(1, 0.3f, 0, 1, 0);
            tessellator.addVertexWithUV(1, 0.3f, 1, 0, 0);
            tessellator.addVertexWithUV(1, 0, 1, 0, 1);
        }
		
		tessellator.draw();
		glEnable(GL_LIGHTING);
		glPopMatrix();
	}
	
	

}
