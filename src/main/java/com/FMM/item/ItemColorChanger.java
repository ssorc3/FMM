package com.FMM.item;

import com.FMM.FMM;
import com.FMM.block.ModBlocks;
import com.FMM.reference.Colors;
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
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemColorChanger extends Item
{
    private IIcon[] icons;

    public ItemColorChanger()
    {
        setUnlocalizedName(ItemInfo.CHANGER_NAME_UNLOCALIZED);
        setHasSubtypes(true);
        setMaxStackSize(1);
        setCreativeTab(FMM.tabFMM);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir)
    {
        icons = new IIcon[ItemInfo.CHANGER_COLORS.length];
        for(int i = 0; i < icons.length; i++)
        {
            icons[i] = ir.registerIcon(ItemInfo.CHANGER_ICON + (ItemInfo.CHANGER_COLORS[i]).toLowerCase());
        }
    }

    @Override
    public IIcon getIconFromDamage(int damage)
    {
        return icons[getActiveColor(damage)];
    }

    public int getUsesForColor(int damage, int color)
    {
        return (damage >> (color * 3 + 2)) & 7;
        //return (damage & 0b111 << (color * 3 + 2)) >> (color * 3 + 2);
    }

    public int getActiveColor(int damage)
    {
        return damage & 3;
    }

    @SuppressWarnings("unchecked")
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean debug)
    {
        list.add("Active Color: " + ItemInfo.CHANGER_COLOR_TAGS[getActiveColor(itemStack.getItemDamage())] + ItemInfo.CHANGER_COLORS[getActiveColor(itemStack.getItemDamage())]);
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
        {
            for (int i = 0; i < ItemInfo.CHANGER_COLORS.length; i++)
            {
                int uses = getUsesForColor(itemStack.getItemDamage(), i);
                if (uses > 0)
                    list.add(ItemInfo.CHANGER_COLOR_TAGS[i] + ItemInfo.CHANGER_COLORS[i] + Colors.GRAY + ": " + uses + (uses == 1 ? " use" : " uses"));
            }
        }
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            if(player.isSneaking())
            {
                int damage = stack.getItemDamage();
                int nextColor = (damage += 1) & 3;
                while(getUsesForColor(damage, nextColor) == 0)
                {
                    nextColor = (damage += 1) & 3;
                }
                damage &= ~3;
                damage |= nextColor;
                stack.setItemDamage(damage);
                return true;
            }
            else if(world.getBlock(x, y, z) == ModBlocks.color)
            {
                int meta = world.getBlockMetadata(x, y, z);
                int itemColor = getActiveColor(stack.getItemDamage());

                if(getUsesForColor(stack.getItemDamage(), itemColor) > 0 && (meta & 3) != itemColor)
                {
                    meta &= ~3;
                    meta |= itemColor;

                    world.setBlockMetadataWithNotify(x, y, z, meta, 3);
                    stack.setItemDamage(reduceActiveUses(stack.getItemDamage()));

                    if(getUsesForColor(stack.getItemDamage(), getActiveColor(stack.getItemDamage())) == 0)
                    {
                        int damage = stack.getItemDamage();
                        int nextColor = (damage += 1) & 3;
                        while(getUsesForColor(damage, nextColor) == 0)
                        {
                            nextColor = (damage += 1) & 3;
                        }
                        damage &= ~3;
                        damage |= nextColor;
                        stack.setItemDamage(damage);
                    }

                    if((stack.getItemDamage() & 16380) == 0)
                    {
                        stack.stackSize--;
                    }

                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(ModItems.changer, 1, 16380));
    }

    public int reduceActiveUses(int damage)
    {
        int activeColor = getActiveColor(damage);
        int uses = getUsesForColor(damage, activeColor) - 1;

        damage &= ~(7 << (2 + 3 * (activeColor)));
        damage |= uses << (2 + 3 * (activeColor));

        return damage;
    }
}
