package com.FMM.creativeTab;

import com.FMM.item.ModItems;
import com.FMM.reference.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabFMM extends CreativeTabs {
    public TabFMM(int tabId) {
        super(tabId, Strings.MODID);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return ModItems.wand;
    }
}
