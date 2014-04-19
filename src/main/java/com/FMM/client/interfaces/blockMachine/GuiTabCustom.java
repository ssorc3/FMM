package com.FMM.client.interfaces.blockMachine;

import com.FMM.FMM;
import com.FMM.client.interfaces.GuiRectangle;
import com.FMM.client.interfaces.GuiTab;
import com.FMM.network.InterfacePacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import static com.FMM.reference.Colors.*;

@SideOnly(Side.CLIENT)
public class GuiTabCustom extends GuiTab
{
    private boolean currentMode;


    public GuiTabCustom(int id)
    {
        super("Custom Layout", id);
    }

    @Override
    public void drawBackground(GuiMachine gui, int x, int y)
    {
        int meta = gui.machine.getWorldObj().getBlockMetadata(gui.machine.xCoord, gui.machine.yCoord, gui.machine.zCoord);
        int type = meta >> 2;
        if(type == 2)
        {
            for(int i = 0; i < GuiMachine.rectangles.length; i++)
            {
                GuiRectangle rect = GuiMachine.rectangles[i];

                if(!rect.isMouseIn(gui, x, y))
                    GL11.glColor4f(0.5f, 0.5f, 0.5f, 1);
                rect.draw(gui, 176, 27);
                GL11.glColor4f(1, 1, 1, 1);
                if(gui.machine.customSetup[i])
                    rect.draw(gui, 176, 35);
            }

        }
    }

    @Override
    public void drawForeground(GuiMachine gui, int x, int y)
    {
        int meta = gui.machine.getWorldObj().getBlockMetadata(gui.machine.xCoord, gui.machine.yCoord, gui.machine.zCoord);
        int type = meta >> 2;
        if(type == 2)
        {
            for(int i = 0; i < GuiMachine.rectangles.length; i++)
            {
                GuiRectangle rect = GuiMachine.rectangles[i];

                String text = gui.machine.customSetup[i] ? GREEN + "Active" : RED + "Inactive";
                text += "\n" + YELLOW + "Click To Change";
                rect.drawString(gui, x, y, text);
            }
        }
    }

    @Override
    public void mouseClick(GuiMachine gui, int x, int y, int button)
    {
        for(int i = 0; i < GuiMachine.rectangles.length; i++)
        {
            if(GuiMachine.rectangles[i].isMouseIn(gui, x, y))
            {
                FMM.packetPipeline.sendToServer(new InterfacePacket(1, i));
                currentMode = gui.machine.customSetup[i];
                break;
            }
        }
    }

    @Override
    public void moveMouseClick(GuiMachine gui, int x, int y, int button, long timeSinceClick)
    {

        for(int i = 0; i < GuiMachine.rectangles.length; i++)
        {
            GuiRectangle rect = GuiMachine.rectangles[i];
            if(gui.machine.customSetup[i] == currentMode && rect.isMouseIn(gui, x, y))
            {
                FMM.packetPipeline.sendToServer(new InterfacePacket(1, i));
                gui.machine.setCustomAnvils(i, !currentMode);
            }
        }
    }
}
