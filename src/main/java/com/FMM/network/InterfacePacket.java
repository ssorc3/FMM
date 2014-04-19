package com.FMM.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import com.FMM.client.interfaces.blockMachine.ContainerMachine;
import com.FMM.tileEntity.TileEntityMachine;

public class InterfacePacket extends AbstractPacket
{
	private int type;
    private int val;

	public InterfacePacket(int type, int val)
	{
		this.type = type;
        this.val = val;
	}

	@Override
	public void encodeInto(ChannelHandlerContext chc, ByteBuf buffer)
	{
		buffer.writeInt(type);
        buffer.writeInt(val);
	}

	@Override
	public void decodeInto(ChannelHandlerContext chc, ByteBuf buffer)
	{
		type = buffer.readInt();
        val = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer entityPlayer)
	{

	}

	@Override
	public void handleServerSide(EntityPlayer entityPlayer)
	{
		Container container = entityPlayer.openContainer;
		if(container != null && container instanceof ContainerMachine)
		{
			TileEntityMachine machine = ((ContainerMachine)container).getMachine();
			machine.receiveInterfaceEvent(type, val);
		}
	}

	public InterfacePacket()
	{
	}
}
