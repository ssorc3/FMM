package com.FMM.entity;

import com.FMM.reference.EntityInfo;
import com.FMM.reference.ItemInfo;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDroid extends Entity
{
    private double startY;
    private double targetY;
    private float coreRotation;
    private float panelRotation;
    private float outerPanelRotation = (float)Math.PI;
    private float topPosition;
    private int color;

    public EntityDroid(World world)
    {
        super(world);
        color = world.rand.nextInt(0x1000000);//0xFFFFFF + 1
        setSize(1, 1);
    }

    public EntityDroid(World world, double x, double y, double z)
    {
        this(world);
        posX = x;
        startY = posY = y;
        posZ = z;
    }

    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float f)
    {
        if(!worldObj.isRemote)
        {
            setDead();
        }
        return true;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

    @Override
    protected void entityInit()
    {
        dataWatcher.addObject(EntityInfo.LIGHT_ID, (byte)0);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag)
    {
        tag.setShort(ItemInfo.STARTY_KEY, (short)startY);
        tag.setShort(ItemInfo.TARGETY_KEY, (short)targetY);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag)
    {
        startY = tag.getShort(ItemInfo.STARTY_KEY);
        targetY = tag.getShort(ItemInfo.TARGETY_KEY);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!worldObj.isRemote) {
            if (targetY == 0 || Math.abs(posY - targetY) < 0.25) {
                targetY = startY + worldObj.rand.nextDouble() * 7;
            }

            if (posY < targetY)
                motionY = 0.03;
            else
                motionY = -0.03;
            boolean light = worldObj.getBlockLightValue((int) posX, (int) posY, (int) posZ) == 15 && worldObj.canBlockSeeTheSky((int) posX - 1, (int) posY, (int) posZ - 1);
            dataWatcher.updateObject(EntityInfo.LIGHT_ID, light ? (byte)1 : (byte)0);
        }
        else
        {
            coreRotation += 0.05;
            topPosition += 0.02;
            if(dataWatcher.getWatchableObjectByte(EntityInfo.LIGHT_ID) != 0)
            {
                float openInnerRotation = (float)Math.PI / 2;
                panelRotation = Math.min(openInnerRotation, panelRotation + 0.04f);
                if(panelRotation == openInnerRotation)
                {
                    outerPanelRotation = Math.max(0, outerPanelRotation - 0.08f);
                }
            }
            else
            {
                float closedOuterRotation = (float)Math.PI;
                outerPanelRotation = Math.min(closedOuterRotation, outerPanelRotation + 0.08f);
                if(outerPanelRotation == closedOuterRotation)
                    panelRotation = Math.max(0, panelRotation - 0.04f);
            }
        }
        setPosition(posX + motionX, posY + motionY, posZ + motionZ);

    }



    public float getCoreRotation()
    {
        return coreRotation;
    }

    public float getPanelRotation()
    {
        return panelRotation;
    }

    public float getOuterPanelRotation()
    {
        return outerPanelRotation;
    }

    public float getTopPosition() { return -6 - Math.abs((float)Math.sin(topPosition)) * 6; }

    public int getColor()
    {
        return color;
    }
}
