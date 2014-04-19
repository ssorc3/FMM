package com.FMM.block;

import com.FMM.FMM;
import com.FMM.reference.BlockInfo;
import com.FMM.tileEntity.TileEntityBomb;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockBomb extends BlockContainer {
    @SideOnly(Side.CLIENT)
    private IIcon idleIcon;

    public BlockBomb() {
        super(Material.tnt);
        setCreativeTab(FMM.tabFMM);
        setHardness(2f);
        setStepSound(Block.soundTypeMetal);
        setBlockName(BlockInfo.BOMB_NAME_UNLOCALIZED);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        blockIcon = ir.registerIcon(BlockInfo.BOMB_TEXTURE);
        idleIcon = ir.registerIcon(BlockInfo.BOMB_TEXTURE_IDLE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityBomb bomb = (TileEntityBomb) world.getTileEntity(x, y, z);
        return bomb.isIdle() ? idleIcon : blockIcon;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune) {
        return null;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityBomb();
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
    	return false;
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
    
}
