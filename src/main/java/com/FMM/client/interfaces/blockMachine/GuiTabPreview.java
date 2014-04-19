package com.FMM.client.interfaces.blockMachine;

import com.FMM.client.interfaces.GuiTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiTabPreview extends GuiTab
{
    public final Entity anvil;

    private float yaw;
    private float roll;
    private boolean rollDown;

    public GuiTabPreview(int id)
    {
        super("3D Anvil for some reason...", id);

        anvil = new EntityFallingBlock(Minecraft.getMinecraft().theWorld, 0, 0, 0, Blocks.anvil);
    }

    @Override
    public void drawBackground(GuiMachine gui, int x, int y)
    {
        GL11.glPushMatrix();

        GL11.glTranslatef(gui.getLeft() + 90, gui.getTop() + 100, 100);
        GL11.glScalef(-30, 30, 30);
        GL11.glRotatef(180, 0, 0, 1);
        GL11.glRotatef(roll, 1, 0, 0);
        GL11.glRotatef(yaw, 0, 1, 0);


        RenderHelper.enableStandardItemLighting();
        RenderManager.instance.renderEntityWithPosYaw(anvil, 0, 0, 0, 0, 0);
        RenderHelper.disableStandardItemLighting();


        GL11.glPopMatrix();

        yaw += 0.5;
        if(rollDown)
        {
            roll -= 0.05f;
            if(roll < -5)
            {
                rollDown = false;
                roll = -5;
            }
        }
        else
        {
            roll += 0.05f;
            if(roll > 25)
            {
                rollDown = true;
                roll = 25;
            }
        }

    }

    @Override
    public void drawForeground(GuiMachine gui, int x, int y)
    {

    }
}
