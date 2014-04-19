package com.FMM.client.interfaces.blockMachine;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotAnvil extends Slot
{

	public SlotAnvil(IInventory inv, int id, int x, int y)
	{
		super(inv, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		return itemStack.getItem() == Item.getItemFromBlock(Blocks.anvil);
	}

}
