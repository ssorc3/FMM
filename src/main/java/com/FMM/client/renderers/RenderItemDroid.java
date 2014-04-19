package com.FMM.client.renderers;

import com.FMM.client.models.ModelDroid;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderItemDroid implements IItemRenderer
{
    private ModelDroid model;

    public RenderItemDroid(ModelDroid model)
    {
        this.model = model;
    }

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
        GL11.glPushMatrix();
        GL11.glScalef(-1, -1, 1);
        switch(type)
        {
            case INVENTORY:
                GL11.glTranslatef(0, 0.12f, 0);
                break;
            case EQUIPPED:
                GL11.glTranslatef(-0.8f, 0.2f, 0.7f);
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glTranslatef(0, -0.7f, 0.7f);
                break;
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(RenderDroid.TEXTURE);
        model.render(0, 0, (float)Math.PI, -6, 0xFFFFFF,0.0625f);
        GL11.glPopMatrix();
    }
}
