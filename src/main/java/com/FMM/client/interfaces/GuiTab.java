package com.FMM.client.interfaces;

import com.FMM.client.interfaces.blockMachine.GuiMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class GuiTab extends GuiRectangle
{
    private String name;

    public GuiTab(String name, int id)
    {
        super(8, 80 + id * 16, 20, 16);
        this.name = name;
    }

    public abstract void drawBackground(GuiMachine gui, int x, int y);
    public abstract void drawForeground(GuiMachine gui, int x, int y);
    public void mouseClick(GuiMachine gui, int x, int y, int button){}
    public void moveMouseClick(GuiMachine gui, int x, int y, int button, long timeSinceClick){}
    public void mouseReleased(GuiMachine gui, int x, int y, int button){}

    public String getName()
    {
        return name;
    }
}
