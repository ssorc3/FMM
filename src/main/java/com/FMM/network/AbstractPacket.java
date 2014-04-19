package com.FMM.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractPacket {
    public abstract void encodeInto(ChannelHandlerContext chc, ByteBuf buffer);

    public abstract void decodeInto(ChannelHandlerContext chc, ByteBuf buffer);

    public abstract void handleClientSide(EntityPlayer entityPlayer);

    public abstract void handleServerSide(EntityPlayer entityPlayer);
}
