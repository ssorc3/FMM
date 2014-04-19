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
import com.FMM.tileEntity.TileEntityWindmillBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWindmillBase extends BlockContainer
{
	public BlockWindmillBase()
	{
		super(Material.rock);
		setCreativeTab(FMM.tabFMM);
		setBlockName(BlockInfo.WINDMILLBASE_NAME_UNLOCALIZED);
		setBlockBounds(0, 0, 0, 1, 0.3f, 1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		blockIcon = ir.registerIcon(BlockInfo.TEXTURE_LOCATION + BlockInfo.WINDMILLBASE_TEXTURE);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		updateMultiBlock(world, x, y, z);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		updateMultiBlock(world, x, y, z);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if(meta == 1)
            setBlockBounds(0, 0, 0, 0.5f, 0.3f, 0.5f);
        else if(meta == 2)
            setBlockBounds(0, 0, 0, 0.5f, 0.3f, 1);
        else if(meta == 3)
            setBlockBounds(0, 0, 0.5f, 0.5f, 0.3f, 1);
        else if(meta == 4)
            setBlockBounds(0, 0, 0, 1, 0.3f, 0.5f);
        else if(meta == 5)
            setBlockBounds(0, 0, 0, 1, 0.3f, 1);
        else if(meta == 6)
            setBlockBounds(0, 0, 0.5f, 1, 0.3f, 1);
        else if(meta == 7)
            setBlockBounds(0.5f, 0, 0, 1, 0.3f, 0.5f);
        else if(meta == 8)
            setBlockBounds(0.5f, 0, 0, 1, 0.3f, 1);
        else if(meta == 9)
            setBlockBounds(0.5f, 0, 0.5f, 1, 0.3f, 1);
        else if(meta == 0)
            setBlockBounds(0, 0, 0, 1, 0.3f, 1);
    }

    public void updateMultiBlock(World world, int x, int y, int z)
	{
		isMultiblock(world, x, y, z);
	}

	public boolean isMultiblock(World world, int x1, int y1, int z1)
	{
		boolean mStructure = false;
		boolean currentCheckStructure = true;

		for (int x2 = 0; x2 < 3; x2++)
		{
			for (int z2 = 0; z2 < 3; z2++)
			{
				if (!mStructure)
				{
					currentCheckStructure = true;

					for (int x3 = 0; x3 < 3; x3++)
					{
						for (int z3 = 0; z3 < 3; z3++)
						{
							if (currentCheckStructure && !world.getBlock(x1 + x2 - x3, y1, z1 + z2 - z3).equals(ModBlocks.windmillBase))
							{
								currentCheckStructure = false;
							}
						}
					}

					if (currentCheckStructure)
					{
						for (int x3 = 0; x3 < 3; x3++)
						{
							for (int z3 = 0; z3 < 3; z3++)
							{
								world.setBlockMetadataWithNotify(x1 + x2 - x3, y1, z1 + z2 - z3, x3 * 3 + z3 + 1, 3);
							}
						}
					}
				}
				mStructure = currentCheckStructure;
			}
		}
		if (mStructure)
			return true;

		if (world.getBlockMetadata(x1, y1, z1) > 0)
			world.setBlockMetadataWithNotify(x1, y1, z1, 0, 3);

		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityWindmillBase();
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityWindmillBase();
	}
}
