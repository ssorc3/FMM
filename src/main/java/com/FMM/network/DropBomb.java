package com.FMM.network;

import com.FMM.entity.EntitySpaceship;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class DropBomb extends AbstractPacket {
    private EntitySpaceship spaceship;
    private int entityId;

    public DropBomb() {
    }

    public DropBomb(EntitySpaceship spaceship) {
        this.spaceship = spaceship;
        entityId = spaceship.getEntityId();
    }

    @Override
    public void encodeInto(ChannelHandlerContext chc, ByteBuf buffer) {
        buffer.writeInt(entityId);
    }

    @Override
    public void decodeInto(ChannelHandlerContext chc, ByteBuf buffer) {
        entityId = buffer.readInt();
    }

    @Override
    public void handleClientSide(EntityPlayer entityPlayer) {

    }

    @Override
    public void handleServerSide(EntityPlayer entityPlayer) {
        Entity entity = entityPlayer.worldObj.getEntityByID(entityId);
        if (entity instanceof EntitySpaceship) {
            spaceship = (EntitySpaceship) entity;
            spaceship.doDrop();
        }
    }
}
