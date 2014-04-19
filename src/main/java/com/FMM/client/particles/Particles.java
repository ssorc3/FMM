package com.FMM.client.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public enum Particles
{
    POISON(EntityPoisonFX.class);

    Class<? extends EntityFX> clazz;

    Particles(Class<? extends EntityFX> clazz)
    {
        this.clazz = clazz;
    }

    public void spawn(World world, double x, double y, double z, double velX, double velY, double velZ)
    {
        Minecraft mc = Minecraft.getMinecraft();
        if(mc != null && mc.renderViewEntity != null && mc.effectRenderer != null)
        {
            int particleSetting = mc.gameSettings.particleSetting;
            if(particleSetting == 2 || particleSetting == 1)
                return;

            double distanceX = mc.renderViewEntity.posX - x;
            double distanceY = mc.renderViewEntity.posY - y;
            double distanceZ = mc.renderViewEntity.posZ - z;

            double maxDistance = 16;
            if(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ > maxDistance * maxDistance)
                return;

            try {
                EntityFX particleEffect = clazz.getConstructor(new Class[]{World.class, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE}).newInstance(world, x, y, z, velX, velY, velZ);
                if(particleEffect != null)
                mc.effectRenderer.addEffect(particleEffect);
            }catch(Exception e)
            {
                e.printStackTrace();
            }

//            EntityFX particleEffect;
//            switch(this)
//            {
//                case POISON:
//                    particleEffect = new EntityPoisonFX(world, x, y, z, velX, velY, velZ);
//                    mc.effectRenderer.addEffect(particleEffect);
//                    break;
//            }
        }
    }
}
