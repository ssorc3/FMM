package com.FMM.client.interfaces.blockMachine;

import com.FMM.tileEntity.TileEntityMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMachine extends Container
{
	private TileEntityMachine machine;
    private int oldHeight;
    private int oldCompact1;
    private int oldCompact2;


	public ContainerMachine(InventoryPlayer invPlayer, TileEntityMachine machine)
	{
		this.machine = machine;

		//hotbar
		for(int x = 0; x < 9; x++)
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 194));

		//player inventory
		for(int y = 0; y < 3; y++)
			for(int x = 0; x < 9; x++)
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 136 + 18 * y));

		//machine inventory
		for(int x = 0; x < 3; x++)
		{
			addSlotToContainer(new SlotAnvil(machine, x, 8 + 18 * x, 17));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return machine.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i)
	{
        Slot slot = getSlot(i);
        if(slot != null && slot.getHasStack())
        {
            ItemStack stack = slot.getStack();
            ItemStack result = stack.copy();

            if(i >= 36)
            {
                if(!mergeItemStack(stack, 0, 36, false))
                    return null;
            }
            else if(!mergeItemStack(stack, 36, 36 + machine.getSizeInventory(), false))
            {
                return null;
            }

            if(stack.stackSize == 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();

            slot.onPickupFromSlot(player, stack);

            return result;
        }
		return null;
	}

    @Override
    public void addCraftingToCrafters(ICrafting player)
    {
        super.addCraftingToCrafters(player);

        int compact1 = 0;
        int compact2 = 0;

        for(int i = 0; i < 32; i++)
        {
            compact1 |= (machine.customSetup[i] ? 1 : 0) << i;
        }
        for(int i = 32; i < machine.customSetup.length; i++)
        {
            compact2 |= (machine.customSetup[i] ? 1 : 0) << i;
        }

        player.sendProgressBarUpdate(this, 0, compact1);
        player.sendProgressBarUpdate(this, 1, compact2);
        player.sendProgressBarUpdate(this, 2, machine.heightSetting);


//        for(int i = 0; i < machine.customSetup.length; i++)
//        {
//            player.sendProgressBarUpdate(this, i, machine.customSetup[i] ? 1 : 0);
//        }
//        player.sendProgressBarUpdate(this, 49, machine.heightSetting);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        if(id == 0)
        {
            for(int i = 0; i < 32; i++)
            {
                machine.setCustomAnvils(i, ((data >> i) & 1) != 0);
            }
        }
        else if(id == 1)
        {
            for(int i = 32; i < machine.customSetup.length; i++)
            {
                machine.setCustomAnvils(i, ((data >> i) & 1) != 0);
            }
        }
        else if(id == 2)
        {
            machine.heightSetting = data;
        }
//        switch(id)
//        {
//            case 0:
//                for(int i = 0; i < 32; i++)
//                {
//                    machine.setCustomAnvils(i, ((data >> i) & 0b1) != 0);
//                }
//                break;
//            case 1:
//                for(int i = 32; i < machine.customSetup.length; i++)
//                {
//                    machine.setCustomAnvils(i, ((data >> i) & 0b1) != 0);
//                }
//                break;
//            case 2:
//                machine.heightSetting = data;
//                break;
//        }
//        if(id < machine.customSetup.length)
//            machine.setCustomAnvils(id, data == 1);
//        else if(id == 50)
//            machine.heightSetting = data;
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        int compact1 = 0;
        int compact2 = 0;

        for(int i = 0; i < 32; i++)
        {
                compact1 |= (machine.customSetup[i] ? 1 : 0) << i;
        }

        for(int i = 32; i < machine.customSetup.length; i++)
        {
                compact2 |= (machine.customSetup[i] ? 1 : 0) << i;
        }

        for(Object player : crafters)
        {
            if (machine.heightSetting != oldHeight)
            {
                ((ICrafting) player).sendProgressBarUpdate(this, 2, machine.heightSetting);
            }

            if(oldCompact1 != compact1)
                ((ICrafting) player).sendProgressBarUpdate(this, 0, compact1);
            if(oldCompact2 != compact2)
            ((ICrafting) player).sendProgressBarUpdate(this, 1, compact2);
        }


//        for(Object player : crafters)
//        {
//            for (int i = 0; i < machine.customSetup.length; i++)
//                if (machine.customSetup[i] != oldData[i])
//                {
//                    ((ICrafting) player).sendProgressBarUpdate(this, i, machine.customSetup[i] ? 1 : 0);
//                }
//            if(machine.heightSetting != oldHeight)
//            {
//                ((ICrafting)player).sendProgressBarUpdate(this, 49, machine.heightSetting);
//            }
//        }
        oldCompact1 = compact1;
        oldCompact2 = compact2;
        oldHeight = machine.heightSetting;
    }

    public TileEntityMachine getMachine()
	{
		return machine;
	}

    //Copied from Container to check if item is valid for slot
    @Override
    protected boolean mergeItemStack(ItemStack stack, int maxSlot, int minSlot, boolean par4)
    {
        for(int i = maxSlot; i < minSlot; i++)
            if(machine.isItemValidForSlot(i, stack))
                break;
            else if(i == minSlot -1)
                return false;
        boolean flag1 = false;
        int k = maxSlot;

        if (par4)
        {
            k = minSlot - 1;
        }

        Slot slot;
        ItemStack itemstack1;

        if (stack.isStackable())
        {
            while (stack.stackSize > 0 && (!par4 && k < minSlot || par4 && k >= maxSlot))
            {
                slot = (Slot)this.inventorySlots.get(k);
                itemstack1 = slot.getStack();

                if (itemstack1 != null && itemstack1.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getItemDamage() == itemstack1.getItemDamage()) && ItemStack.areItemStackTagsEqual(stack, itemstack1))
                {
                    int l = itemstack1.stackSize + stack.stackSize;

                    if (l <= stack.getMaxStackSize())
                    {
                        stack.stackSize = 0;
                        itemstack1.stackSize = l;
                        slot.onSlotChanged();
                        flag1 = true;
                    }
                    else if (itemstack1.stackSize < stack.getMaxStackSize())
                    {
                        stack.stackSize -= stack.getMaxStackSize() - itemstack1.stackSize;
                        itemstack1.stackSize = stack.getMaxStackSize();
                        slot.onSlotChanged();
                        flag1 = true;
                    }
                }

                if (par4)
                {
                    --k;
                }
                else
                {
                    ++k;
                }
            }
        }

        if (stack.stackSize > 0)
        {
            if (par4)
            {
                k = minSlot - 1;
            }
            else
            {
                k = maxSlot;
            }

            while (!par4 && k < minSlot || par4 && k >= maxSlot)
            {
                slot = (Slot)this.inventorySlots.get(k);
                itemstack1 = slot.getStack();

                if (itemstack1 == null)
                {
                    slot.putStack(stack.copy());
                    slot.onSlotChanged();
                    stack.stackSize = 0;
                    flag1 = true;
                    break;
                }

                if (par4)
                {
                    --k;
                }
                else
                {
                    ++k;
                }
            }
        }

        return flag1;
    }
}
