package com.FMM.block.itemBlock;

import com.FMM.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockWindmill extends ItemBlock
{
	public ItemBlockWindmill(Block block)
	{
		super(block);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			if(side == 1 && world.getBlock(x, y, z).equals(ModBlocks.windmillBase) && world.getBlockMetadata(x, y, z) == 5)
			{
                boolean notEnoughSpace = false;
                for(int x1 = -1; x1 < 1; x1++)
                {
                    for(int z1 = -1; z1 < 1; z1++)
                    {
                        for (int y1 = 0; y1 < 7; y1++)
                        {
                            if (!world.isAirBlock(x + x1, y + y1 + 1, z + z1))
                            {
                                notEnoughSpace = true;
                            }
                        }
                    }
                }
                if(!notEnoughSpace)
                {
                    int direction = calculateDirection((int)player.rotationYaw);

                    for(int i = 0 ; i < 6; i++)
                    {
                        world.setBlock(x, y + i + 1, z, ModBlocks.windmill);
                    }
                    world.setBlock(x, y+7, z, ModBlocks.windmill, direction, 3);
                }


                return true;
			}
		}
		return false;
	}

    private int calculateDirection(int rot)
    {
        if(rot >= -45 && rot < 45)
            return 1;
        if(rot >= 45 && rot < 135 || rot >= -315 && rot < -225)
            return 2;
        if(rot >= 135 && rot <= 225 || rot >= -255 && rot < -135)
            return 3;
        if(rot >= -135 && rot < -45)
            return 4;

        return 0;
    }
}
