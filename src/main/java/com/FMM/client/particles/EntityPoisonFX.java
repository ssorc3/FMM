package com.FMM.client.particles;

import com.FMM.block.BlockPoison;
import com.FMM.block.ModBlocks;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class EntityPoisonFX extends EntityFX
{
    public EntityPoisonFX(World world, double x, double y, double z, double velX, double velY, double velZ)
    {
        super(world, x, y, z, velX, velY, velZ);
        setParticleIcon(((BlockPoison)ModBlocks.poison).particleIconNC);

        particleRed = rand.nextFloat() * 0.5f;
        particleGreen = (rand.nextFloat() * 0.5f) + 0.5f;
        particleBlue = rand.nextFloat() * 0.5f;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        particleScale =  (1 - (float)particleAge / particleMaxAge) * 2;
    }

    @Override
    public int getFXLayer()
    {
        return 1;
    }
}
