package com.FMM.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.FMM.FMM;
import com.FMM.reference.BlockInfo;
import com.FMM.tileEntity.TileEntityWindmill;

public class BlockWindmill extends BlockContainer
{
	public BlockWindmill()
	{
		super(Material.rock);
		setCreativeTab(FMM.tabFMM);
		setBlockName(BlockInfo.WINDMILL_NAME_UNLOCALIZED);
	}

    @Override
    public void registerBlockIcons(IIconRegister ir)
    {
        blockIcon = ir.registerIcon(BlockInfo.TEXTURE_LOCATION + BlockInfo.WINDMILLBASE_TEXTURE);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        if(world.getBlock(x, y+1, z).equals(ModBlocks.windmill))
            world.setBlockToAir(x, y+1, z);
        if(world.getBlock(x, y-1, z).equals(ModBlocks.windmill))
            world.setBlockToAir(x, y - 1, z);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        if(world.getBlockMetadata(x, y, z) == 0)
            setBlockBounds(0.25f, 0, 0.25f, 0.75f, 1, 0.75f);
        else
            setBlockBounds(0, 0, 0, 1, 1, 1);
    }

    @Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int id)
	{
		return new TileEntityWindmill();
	}
}
