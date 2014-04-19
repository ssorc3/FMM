package com.FMM.block;

import com.FMM.FMM;
import com.FMM.reference.BlockInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockColor extends Block
{
    @SideOnly(Side.CLIENT)
    public IIcon[] frontIcons;

    @SideOnly(Side.CLIENT)
    public IIcon[] sideIcons;

    public BlockColor()
    {
        super(Material.rock);
        setCreativeTab(FMM.tabFMM);
        setHardness(1);
        setStepSound(Block.soundTypeStone);
        setBlockName(BlockInfo.COLOR_NAME_UNLOCALIZED);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister ir)
    {
        frontIcons = new IIcon[BlockInfo.COLOR_TEXTURE_COLORS.length];
        sideIcons = new IIcon[BlockInfo.COLOR_TEXTURE_COLORS.length];

        for(int i = 0; i < frontIcons.length; i++)
        {
            frontIcons[i] = ir.registerIcon(BlockInfo.COLOR_TEXTURE + BlockInfo.COLOR_TEXTURE_COLORS[i] + BlockInfo.COLOR_TEXTURE_FRONT);
        }

        for(int i = 0; i < sideIcons.length; i++)
        {
            sideIcons[i] = ir.registerIcon(BlockInfo.COLOR_TEXTURE + BlockInfo.COLOR_TEXTURE_COLORS[i] + BlockInfo.COLOR_TEXTURE_SIDE);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        int color = meta & 3;
        if(side == 0 || side == 1)
            return sideIcons[color];
        else
        {
            side -= 2;
            int frontSide = (meta & 12) >> 2;
            return frontSide == side ? frontIcons[color] : sideIcons[color];
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(side >= 2)
        {
            if(!world.isRemote)
            {
                int meta = world.getBlockMetadata(x, y, z);

                meta &= ~12;
                meta |= (side - 2) << 2;

                world.setBlockMetadataWithNotify(x, y, z, meta, 3);
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if(!world.isRemote && world.isBlockIndirectlyGettingPowered(x, y, z))
        {
            int meta = world.getBlockMetadata(x, y, z);
            int side =  (meta & 12) >> 2;
            int targetX = x;
            int targetZ = z;
            switch(side)
            {
                case 0:
                    targetZ--;
                    break;
                case 1:
                    targetZ++;
                    break;
                case 2:
                    targetX--;
                    break;
                case 3:
                    targetX++;
                    break;
            }
            int woolMeta = 0;
            int color = meta & 3;
            switch(color)
            {
                case 0:
                    woolMeta = 0;
                    break;
                case 1:
                    woolMeta = 14;
                    break;
                case 2:
                    woolMeta = 13;
                    break;
                case 3:
                    woolMeta = 11;
                    break;
            }
                world.setBlock(targetX, y, targetZ, Blocks.wool, woolMeta, 3);
        }
    }
}
