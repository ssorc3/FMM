package com.FMM.client.renderers;

import com.FMM.client.models.ModelDroid;
import com.FMM.entity.EntityDroid;
import com.FMM.reference.EntityInfo;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderDroid extends Render
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(EntityInfo.TEXTURE_LOCATION, EntityInfo.DROID_TEXTURE);

    private ModelDroid model;

    public RenderDroid(ModelDroid model)
    {
        this.model = model;
        shadowSize = 0.5f;
    }

    public void renderDroid(EntityDroid droid, double x, double y, double z, float yaw, float partialTickTime)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glScalef(-1f, -1f, 1f);
        bindEntityTexture(droid);
        model.render(droid.getCoreRotation(),droid.getPanelRotation(), droid.getOuterPanelRotation(), droid.getTopPosition(), droid.getColor(), 0.0625f);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TEXTURE;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime)
    {
        renderDroid((EntityDroid)entity, x, y, z, yaw, partialTickTime);
    }
}
