package com.FMM.item;

import com.FMM.reference.ItemInfo;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems {
    public static Item wand;
    public static Item card;
    public static Item droid;
    public static Item changer;

    public static void init() {
        wand = new ItemWand();
        card = new ItemCard();
        droid = new ItemDroid();
        changer = new ItemColorChanger();

        registerItems();
    }

    private static void registerItems() {
        GameRegistry.registerItem(wand, ItemInfo.WAND_NAME_UNLOCALIZED);
        GameRegistry.registerItem(card, ItemInfo.CARD_NAME_UNLOCALIZED);
        GameRegistry.registerItem(droid, ItemInfo.DROID_NAME_UNLOCALIZED);
        GameRegistry.registerItem(changer, ItemInfo.CHANGER_NAME_UNLOCALIZED);
    }
}
