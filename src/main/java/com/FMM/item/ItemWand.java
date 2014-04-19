package com.FMM.item;

import com.FMM.FMM;
import com.FMM.client.audio.Sounds;
import com.FMM.entity.EntitySpaceship;
import com.FMM.reference.ItemInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class ItemWand extends Item {
    @SideOnly(Side.CLIENT)
    private IIcon chargedIcon;

    public ItemWand() {
        setCreativeTab(FMM.tabFMM);
        setMaxStackSize(1);
        setUnlocalizedName(ItemInfo.WAND_NAME_UNLOCALIZED);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer entityPlayer, EntityLivingBase target) {
        if (!target.worldObj.isRemote) {
            target.motionY = 2;

            if (isCharged(itemstack.getItemDamage())) {
                itemstack.setItemDamage(0);
                target.motionX = (target.posX - entityPlayer.posX) * 2;
                target.motionZ = (target.posZ - entityPlayer.posZ) * 2;
                Sounds.WAND.playAtEntity(target, 1, 3);
            } else {
                itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                Sounds.WAND.playAtEntity(target, 1, 1);
            }
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir) {
        itemIcon = ir.registerIcon(ItemInfo.WAND_ICON);
        chargedIcon = ir.registerIcon(ItemInfo.WAND_ICON_CHARGED);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int damage) {
        return isCharged(damage) ? chargedIcon : itemIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List info, boolean useExtraInformation) {
        info.add("Used " + itemStack.getItemDamage() + (itemStack.getItemDamage() == 1 ? "time" : " times"));
        if (isCharged(itemStack.getItemDamage()))
            info.add("§4Charged");
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote && player.isSneaking()) {
            EntitySpaceship spaceship = new EntitySpaceship(world);
            spaceship.posX = x + hitX;
            spaceship.posY = y + 1.5f;
            spaceship.posZ = z + hitZ;
            if (isCharged(itemStack.getItemDamage())) {
                itemStack.setItemDamage(0);
                spaceship.setCharged(true);
            } else
                itemStack.setItemDamage(itemStack.getItemDamage() + 1);
            world.spawnEntityInWorld(spaceship);
            return true;
        }
        return false;
    }

    private boolean isCharged(int damage) {
        return damage >= 5;
    }
}
