package com.FMM.item;

import com.FMM.FMM;
import com.FMM.entity.EntityDroid;
import com.FMM.reference.ItemInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDroid extends Item
{
    public ItemDroid()
    {
        setCreativeTab(FMM.tabFMM);
        setUnlocalizedName(ItemInfo.DROID_NAME_UNLOCALIZED);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir)
    {
        itemIcon = ir.registerIcon(ItemInfo.DROID_ICON);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote)
        {
            world.spawnEntityInWorld(new EntityDroid(world, x + 0.5, y + 1.5, z + 0.5));
            if(!player.capabilities.isCreativeMode)
                stack.stackSize--;
            return true;
        }else
            return false;
    }
}
