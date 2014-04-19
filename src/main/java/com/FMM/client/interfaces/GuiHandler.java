package com.FMM.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.FMM.FMM;
import com.FMM.client.interfaces.blockMachine.ContainerMachine;
import com.FMM.client.interfaces.blockMachine.GuiMachine;
import com.FMM.tileEntity.TileEntityMachine;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler
{
	public static final int MACHINE = 0;
	
	public GuiHandler()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(FMM.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
			case MACHINE:
				TileEntity te = world.getTileEntity(x, y, z);
				if(te != null && te instanceof TileEntityMachine)
					return new ContainerMachine(player.inventory, (TileEntityMachine)te);
				break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
			case MACHINE:
				TileEntity te = world.getTileEntity(x, y, z);
				if(te != null && te instanceof TileEntityMachine)
					return new GuiMachine(player.inventory, (TileEntityMachine)te);
				break;
		}
		return null;
	}

}
