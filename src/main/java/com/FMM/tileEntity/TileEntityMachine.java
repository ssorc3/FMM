package com.FMM.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMachine extends TileEntity implements ISidedInventory
{
	public static final String SLOT_KEY = "slot";
	public static final String ITEMS_KEY = "items";
    public static final String CUSTOM_KEY = "custom";
    public static final String HEIGHT_KEY = "height";


    public final boolean[] customSetup;
    public int heightSetting;
	
	private ItemStack[] items;
    private int anvils = -1;
    private int customAnvils = 0;

	public TileEntityMachine()
	{
		items = new ItemStack[3];
        customSetup = new boolean[49];
	}

	@Override
	public int getSizeInventory()
	{
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count)
	{
		ItemStack itemStack = getStackInSlot(i);
		if(itemStack != null)
		{
			if(itemStack.stackSize <= count)
				setInventorySlotContents(i, null);
			else
			{
				itemStack = itemStack.splitStack(count);
			}
		}
		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		ItemStack itemStack = items[i];
		setInventorySlotContents(i, null);
		return itemStack; 
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack)
	{
		items[i] = itemStack;
		if(itemStack != null && itemStack.stackSize > getInventoryStackLimit())
		{
			itemStack.stackSize = getInventoryStackLimit();
		}
		
	}

	@Override
	public String getInventoryName()
	{
		return null;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	@Override
	public void openInventory(){}

	@Override
	public void closeInventory(){}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		NBTTagList itemList = new NBTTagList();
		for(int i = 0; i < getSizeInventory(); i++)
		{
			ItemStack stack = getStackInSlot(i);
			if(stack != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte(SLOT_KEY, (byte)i);
				stack.writeToNBT(item);
				itemList.appendTag(item);
			}
		}
		tag.setTag(ITEMS_KEY, itemList);

        for(int i = 0; i < customSetup.length; i++)
            tag.setBoolean(CUSTOM_KEY + i, customSetup[i]);

        tag.setInteger(HEIGHT_KEY, heightSetting);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagList items = tag.getTagList(ITEMS_KEY, 10);
		for(int i = 0; i < items.tagCount(); i++)
		{
			NBTTagCompound item = items.getCompoundTagAt(i);
			int slot = item.getByte(SLOT_KEY);
			if(slot >= 0 && slot < getSizeInventory())
			{
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
		for(int i = 0; i < customSetup.length; i++)
        {
            setCustomAnvils(i, tag.getBoolean(CUSTOM_KEY + i));
        }

        heightSetting = tag.getInteger(HEIGHT_KEY);
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack)
	{
		return itemStack.getItem() == Item.getItemFromBlock(Blocks.anvil);
	}

    @Override
    public void markDirty()
    {
        super.markDirty();
        anvils = -1;
    }

    public void receiveInterfaceEvent(int eventId, int val)
	{
        switch(eventId)
        {
            case 0:
                switch (val)
                {
                    case 0:
                        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
                        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta ^ 1, 3);
                        break;
                    case 1:
                        int meta1 = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
                        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta1 & 3, 3); //3 = 0b0011 but no binary literals :p
                        break;
                }
                break;
            case 1:
                setCustomAnvils(val, !customSetup[val]);
                break;
            case 2:
                heightSetting = val;
                break;
        }
	}
	
	public boolean isDisabled()
	{
		int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		return (meta & 1) == 1;
	}

    public int getAnvils()
    {
        if(anvils == -1)
            calculateAnvilCount();
        return anvils;
    }

    private void calculateAnvilCount()
    {
        anvils = 0;
        for(int i = 0; i < getSizeInventory(); i++)
        {
            ItemStack stack = getStackInSlot(i);
            if(stack != null && isItemValidForSlot(i, stack))
            {
                anvils += stack.stackSize;
            }
        }
    }

    public int getCustomAnvils()
    {
        return customAnvils;
    }

    public void setCustomAnvils(int i, boolean val)
    {
        boolean oldVal = customSetup[i];
        customSetup[i] = val;
        if(oldVal && !val)
            customAnvils--;
        else if(!oldVal && val)
            customAnvils++;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return new int[]{0, 1, 2};
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack stack, int side)
    {
        return isItemValidForSlot(slotIndex, stack);
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack stack, int side)
    {
        return true;
    }
}
