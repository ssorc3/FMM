package com.FMM.client.interfaces.blockMachine;

import com.FMM.FMM;
import com.FMM.block.ModBlocks;
import com.FMM.client.interfaces.GuiRectangle;
import com.FMM.client.interfaces.GuiTab;
import com.FMM.network.InterfacePacket;
import com.FMM.reference.GuiInfo;
import com.FMM.tileEntity.TileEntityMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiMachine extends GuiContainer
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(GuiInfo.RESOURCE_LOCATION, GuiInfo.MACHINE_TEXTURE);
    public static final GuiRectangle[] rectangles;
	public static final int TEXT_GRAY = 0x404040;
    public static final int TEXT_RED = 0xD30000;
    private static final String ENABLE_TEXT = "Enable";
    private static final String DISABLE_TEXT = "Disable";

    private int meta;
    private int type;
	
	public TileEntityMachine machine;
    private final GuiTab[] tabs;
    private GuiTab activeTab;

    static
    {
        rectangles = new GuiRectangle[49];
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                rectangles[i * 7 + j] = new GuiRectangle(57 + i * 9, 70 + j * 9, 8, 8);
    }

	public GuiMachine(InventoryPlayer invPlayer, TileEntityMachine machine)
	{
		super(new ContainerMachine(invPlayer, machine));
		this.machine = machine;
        meta = machine.getWorldObj().getBlockMetadata(machine.xCoord, machine.yCoord, machine.zCoord);
        type = meta >> 2;
		xSize = 176;
		ySize = 218;

        tabs = new GuiTab[]
        {
            new GuiTabCustom(0),
            new GuiTabHeight(1),
            new GuiTabPreview(2)
        };
        activeTab = tabs[0];
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
        TextureManager renderEngine = Minecraft.getMinecraft().renderEngine;

		GL11.glColor4f(1, 1, 1, 1);
		renderEngine.bindTexture(TEXTURE);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if(type > 0)
            drawTexturedModalRect(guiLeft + 16, guiTop + 42, (type - 1) * 20, ySize, 20, 20);

        int height = (int) ((machine.getAnvils() / 192f) * 27);
        if(height > 0)
            drawTexturedModalRect(guiLeft + 157, guiTop + 40 + 27 - height, xSize, 27 - height, 7, height);

        for(GuiRectangle tab : tabs)
        {
            int srcY = 43;

            if(tab == activeTab)
                srcY += 32;
            else if(tab.isMouseIn(this, x, y))
                srcY += 16;

            tab.draw(this, xSize, srcY);
        }

        activeTab.drawBackground(this, x, y);

        renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        drawTexturedModelRectFromIcon(guiLeft + 63, guiTop + 17, ModBlocks.machine.getIcon(1, meta),16, 16);
	}

    @Override
    protected void mouseClicked(int x, int y, int button)
    {
        super.mouseClicked(x, y, button);

        activeTab.mouseClick(this, x, y, button);

        for(GuiTab tab : tabs)
        {
            if(activeTab != tab)
            {
                if(tab.isMouseIn(this, x, y))
                {
                    activeTab = tab;
                    break;
                }
            }
        }
    }

    @Override
    protected void mouseClickMove(int x, int y, int button, long timeSinceClicked)
    {
        super.mouseClickMove(x, y, button, timeSinceClicked);
        activeTab.moveMouseClick(this, x, y, button, timeSinceClicked);
    }

    @Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		fontRendererObj.drawString("Anvil Machine", 8, 6, TEXT_GRAY);

        int anvilsNeeded;
        boolean invalid;
        int meta = machine.getWorldObj().getBlockMetadata(machine.xCoord, machine.yCoord, machine.zCoord);
        int type = meta >> 2;
        switch(type)
        {
            case 1:
                anvilsNeeded = 12;
                break;
            case 2:
                anvilsNeeded = machine.getCustomAnvils();
                break;
            case 3:
                anvilsNeeded = 8;
                break;
            default:
                anvilsNeeded = 0;
        }
        invalid = machine.getAnvils() < anvilsNeeded;
        int color = invalid ? TEXT_RED : TEXT_GRAY;
        if(anvilsNeeded != 0)
            fontRendererObj.drawSplitString("Anvils needed: " + anvilsNeeded, 45, 44, 100, color);

        activeTab.drawForeground(this, x, y);

        for(GuiTab tab : tabs)
        {
            tab.drawString(this, x, y, tab.getName());
        }
	}

	@Override
    @SuppressWarnings("unchecked")
	public void initGui()
	{
		super.initGui();
		buttonList.clear();
		
		buttonList.add(new GuiButton(0, guiLeft + 80, guiTop + 14, 48, 20, machine.isDisabled() ? ENABLE_TEXT : DISABLE_TEXT));

        GuiButton clear = new GuiButton(1, guiLeft + 130, guiTop + 14, 40, 20, "Clear");
        clear.enabled = (machine.getBlockMetadata() >> 2) > 0;
        buttonList.add(clear);
    }
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
        FMM.packetPipeline.sendToServer(new InterfacePacket(0, button.id));
		switch(button.id)
		{
			case 0:
                button.displayString = button.displayString.equals(DISABLE_TEXT) ? ENABLE_TEXT : DISABLE_TEXT;
				break;
            case 1:
                button.enabled = false;
                break;
		}
	}

    @Override
    protected void mouseMovedOrUp(int x, int y, int button)
    {
        super.mouseMovedOrUp(x, y, button);

        activeTab.mouseReleased(this, x, y, button);
    }

    public int getLeft()
    {
        return guiLeft;
    }

    public int getTop()
    {
        return guiTop;
    }

    public void drawHoverString(List list, int x, int y)
    {
        drawHoveringText(list, x, y, fontRendererObj);
    }

    public FontRenderer getFontRenderer()
    {
        return fontRendererObj;
    }
}
