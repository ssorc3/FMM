package com.FMM.client.renderers;

import com.FMM.client.models.ModelSpaceship;
import com.FMM.entity.EntitySpaceship;
import com.FMM.reference.EntityInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSpaceship extends Render {
    private static final ResourceLocation texture = new ResourceLocation(EntityInfo.TEXTURE_LOCATION, EntityInfo.SPACESHIP_TEXTURE);
    private static final ResourceLocation chargedTexture = new ResourceLocation(EntityInfo.TEXTURE_LOCATION, EntityInfo.SPACESHIP_TEXTURE_CHARGED);

    protected ModelSpaceship model;

    public RenderSpaceship() {
        shadowSize = 0.5F;
        model = new ModelSpaceship();
    }

    public void renderSpaceship(EntitySpaceship spaceship, double x, double y, double z, float yaw, float partialTickTime) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);


        bindEntityTexture(spaceship);

        model.render(spaceship, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
    }


    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
        this.renderSpaceship((EntitySpaceship) entity, x, y, z, yaw, partialTickTime);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return ((EntitySpaceship) entity).isCharged() ? chargedTexture : texture;
    }
}
