package com.FMM.block;

import com.FMM.FMM;
import com.FMM.client.particles.Particles;
import com.FMM.reference.BlockInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockPoison extends Block
{
    @SideOnly(Side.CLIENT)
    public IIcon particleIcon;

    @SideOnly(Side.CLIENT)
    public IIcon particleIconNC;

    public BlockPoison()
    {
        super(Material.rock);
        setCreativeTab(FMM.tabFMM);
        setHardness(2.0f);
        setStepSound(Block.soundTypeStone);
        setBlockName(BlockInfo.POISON_NAME_UNLOCALIZED);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister ir)
    {
        blockIcon = ir.registerIcon(BlockInfo.POISON_TEXTURE);
        particleIcon = ir.registerIcon(BlockInfo.POISON_PARTICLE_TEXTURE);
        particleIconNC = ir.registerIcon(BlockInfo.POISON_PARTICLE_TEXTURE_NC);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        double particleX = x + 0.5;
        double particleY = y + 0.5;
        double particleZ = z + 0.5;
        double velX = -0.5 + rand.nextDouble();
        double velY = -0.5 + rand.nextDouble();
        double velZ = -0.5 + rand.nextDouble();
        Particles.POISON.spawn(world, particleX, particleY, particleZ, velX, velY, velZ);
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        if(!world.isRemote)
        {
            player.addPotionEffect(new PotionEffect(Potion.poison.getId(), 40, 1));
        }
    }
}
