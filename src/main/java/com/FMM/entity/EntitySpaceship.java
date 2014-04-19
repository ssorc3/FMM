package com.FMM.entity;

import org.lwjgl.input.Keyboard;

import com.FMM.FMM;
import com.FMM.client.audio.Sounds;
import com.FMM.network.DropBomb;
import com.FMM.reference.EntityInfo;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class EntitySpaceship extends Entity implements IEntityAdditionalSpawnData {

    private boolean charged;
    private boolean lastPressedState;

    public EntitySpaceship(World world) {
        super(world);
        setSize(1.5f, 0.6f);
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return boundingBox;
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity entity) {
        if (entity != riddenByEntity)
            return entity.boundingBox;
        else
            return null;
    }


    @Override
    public boolean canBeCollidedWith() {
        return !isDead;
    }

    @Override
    public boolean interactFirst(EntityPlayer player) {
        if (!worldObj.isRemote && riddenByEntity == null) {
            player.mountEntity(this);
        }
        return true;
    }

    @Override
    public double getMountedYOffset() {
        return -0.15;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!worldObj.isRemote) {
            if (riddenByEntity != null)
                motionY = 0.2f;
            else if (worldObj.isAirBlock((int) posX, (int) posY - 1, (int) posZ))
                motionY = -0.2f;
            else
                motionY = 0;
        } else {
            sendInformation();
        }

        setPosition(posX + motionX, posY + motionY, posZ + motionZ);
    }

    @Override
    public void writeSpawnData(ByteBuf data) {
        data.writeBoolean(charged);
    }

    @Override
    public void readSpawnData(ByteBuf data) {
        charged = data.readBoolean();
    }

    @Override
    protected void entityInit() {
        dataWatcher.addObject(EntityInfo.AMMO_ID, (byte)10);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        tag.setBoolean(EntityInfo.CHARGED_KEY, charged);
        tag.setByte(EntityInfo.AMMO_KEY, (byte) getAmmo());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        charged = tag.getBoolean(EntityInfo.CHARGED_KEY);
        setAmmo(tag.getByte(EntityInfo.AMMO_KEY));
    }

    private void sendInformation() {
        Minecraft minecraft = Minecraft.getMinecraft();
        boolean state = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
        if (state && !lastPressedState && charged && riddenByEntity == minecraft.thePlayer) {
            if(getAmmo() == 0){
            	System.out.println("No ammo");
                minecraft.thePlayer.addChatMessage(new ChatComponentText("You have no ammo"));
                Sounds.BOMB_CLICK.play(worldObj, posX, posY, posZ, 1, 1);
            }else{
            	System.out.println("Packet Sent");
                FMM.packetPipeline.sendToServer(new DropBomb(this));
            }
        }
        lastPressedState = state;
    }

    public void doDrop() {
        System.out.println("doDrop()");
        if (getAmmo() > 0) {
            EntityBomb bomb = new EntityBomb(worldObj);
            bomb.posX = posX;
            bomb.posY = posY;
            bomb.posZ = posZ;
            worldObj.spawnEntityInWorld(bomb);
            setAmmo(getAmmo() - 1);
            Sounds.BOMB_DROP.playAtEntity(this, 1, 1);
        }
    }

    public int getAmmo() {
        return dataWatcher.getWatchableObjectByte(EntityInfo.AMMO_ID);
    }

    public void setAmmo(int ammo) {
        dataWatcher.updateObject(EntityInfo.AMMO_ID, (byte)ammo);
    }

    public boolean isCharged() {
        return charged;
    }

    public void setCharged(boolean charged) {
        this.charged = charged;
    }

}
