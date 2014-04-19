package com.FMM.item;

import com.FMM.FMM;
import com.FMM.block.ModBlocks;
import com.FMM.reference.ItemInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class ItemCard extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public ItemCard() {
        setCreativeTab(FMM.tabFMM);
        setHasSubtypes(true);
        setUnlocalizedName(ItemInfo.CARD_NAME_UNLOCALIZED);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return ItemInfo.CARD_NAME_UNLOCALIZED + itemStack.getItemDamage();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        icons = new IIcon[ItemInfo.CARD_ICONS.length];
        for (int i = 0; i < icons.length; i++)
            icons[i] = ir.registerIcon(ItemInfo.CARD_ICONS[i]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return icons[damage];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < ItemInfo.CARD_ICONS.length; i++) {
            ItemStack itemStack = new ItemStack(ModItems.card, 1, i);
            list.add(itemStack);
        }
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote && world.getBlock(x, y, z) == ModBlocks.machine) {
            int meta = world.getBlockMetadata(x, y, z);
            int type = itemStack.getItemDamage() + 1;
            int lsb = meta & 3; //3 = 0b0011 but no binary literals allowed! :p
            world.setBlockMetadataWithNotify(x, y, z, (type << 2) | lsb, 3);
            if (!player.capabilities.isCreativeMode)
                itemStack.stackSize--;

            return true;
        } else
            return false;
    }
}
