package com.FMM.tileEntity;

import com.FMM.block.ModBlocks;
import com.FMM.client.audio.Sounds;
import com.FMM.reference.TileEntityInfo;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBomb extends TileEntity {
    private static final int SPREAD_TIME = 5;
    private static final int SPREAD_LEVEL = 3;

    private int timer;
    private int level;

    public TileEntityBomb() {
        timer = SPREAD_TIME;
        level = 0;
    }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            if (timer == 0 && level < SPREAD_LEVEL) {
                spread(xCoord + 1, yCoord, zCoord);
                spread(xCoord - 1, yCoord, zCoord);
                spread(xCoord, yCoord, zCoord + 1);
                spread(xCoord, yCoord, zCoord - 1);
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
                Sounds.BEEP.play(worldObj, xCoord, yCoord, zCoord, 1, 1);
            } else if (timer == SPREAD_TIME * (level - SPREAD_LEVEL)) {
                worldObj.createExplosion(null, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 4, true);
            }
            timer--;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setShort(TileEntityInfo.TIMER_KEY, (short) timer);
        tag.setByte(TileEntityInfo.LEVEL_KEY, (byte) level);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        timer = tag.getShort(TileEntityInfo.TIMER_KEY);
        level = tag.getShort(TileEntityInfo.LEVEL_KEY);
    }

    private void spread(int x, int y, int z) {
        if (worldObj.isAirBlock(x, y, z)) {
            worldObj.setBlock(x, y, z, ModBlocks.bomb);

            TileEntity te = worldObj.getTileEntity(x, y, z);
            if (te != null && te instanceof TileEntityBomb) {
                TileEntityBomb bomb = (TileEntityBomb) te;
                bomb.level = level + 1;
            }
        }
    }

    public boolean isIdle() {
        return worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1;
    }
}
