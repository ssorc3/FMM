package com.FMM.entity;

import com.FMM.block.ModBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBomb extends Entity {
    public EntityBomb(World world) {
        super(world);
        motionY = -0.6;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!worldObj.isRemote) {
            if (worldObj.isAirBlock((int) posX, (int) posY, (int) posZ) && !worldObj.isAirBlock((int) posX, (int) posY - 1, (int) posZ)) {

                worldObj.setBlock((int) posX, (int) posY, (int) posZ, ModBlocks.bomb);
                setDead();
            }
        }

        setPosition(posX + motionX, posY + motionY, posZ + motionZ);
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
    }
}
