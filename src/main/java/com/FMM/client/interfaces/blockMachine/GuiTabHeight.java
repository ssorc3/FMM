package com.FMM.client.interfaces.blockMachine;

import com.FMM.FMM;
import com.FMM.client.interfaces.GuiRectangle;
import com.FMM.client.interfaces.GuiTab;
import com.FMM.network.InterfacePacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTabHeight extends GuiTab
{
    private static final GuiRectangle bar = new GuiRectangle(50, 100, 91, 6);
    private static final GuiRectangle slider = new GuiRectangle(75, 97, 6, 11);

    private int tempSetting = 25;
    private boolean isMoving;

    public GuiTabHeight(int id)
    {
        super("Height Setting", id);
    }

    @Override
    public void drawBackground(GuiMachine gui, int x, int y)
    {
        bar.draw(gui, 0, 250);
        updateSliderPosition(gui);
        slider.draw(gui, 0, 239);
    }

    @Override
    public void drawForeground(GuiMachine gui, int x, int y)
    {
        gui.getFontRenderer().drawString("Height: " + getCurrentHeight(gui), 50, 88, GuiMachine.TEXT_GRAY);
    }

    @Override
    public void mouseClick(GuiMachine gui, int x, int y, int button)
    {
        updateSliderPosition(gui);
        if(bar.isMouseIn(gui, x, y))
        {
            isMoving = true;
            tempSetting = gui.machine.heightSetting;
        }
    }

    @Override
    public void moveMouseClick(GuiMachine gui, int x, int y, int button, long timeSinceClick)
    {
        if(isMoving)
        {
            tempSetting = x - gui.getLeft() - 50;
            if(tempSetting < 0)
                tempSetting = 0;
            if(tempSetting > 85)
                tempSetting = 85;
        }
    }

    @Override
    public void mouseReleased(GuiMachine gui, int x, int y, int button)
    {
        if(isMoving)
        {
            FMM.packetPipeline.sendToServer(new InterfacePacket(2, tempSetting));
            gui.machine.heightSetting = tempSetting;
            isMoving = false;
        }
    }

    private void updateSliderPosition(GuiMachine gui)
    {
        slider.setX(50 + (getCurrentHeight(gui)));
    }

    private int getCurrentHeight(GuiMachine gui)
    {
        return isMoving ? tempSetting : gui.machine.heightSetting;
    }
}
