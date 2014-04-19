package com.FMM.crafting;

import com.FMM.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingHandler {
    public static void addRecipes() {
        GameRegistry.addRecipe(new ItemStack(ModItems.wand, 1), new Object[]
                {
                        "  X", " / ", "/  ", 'X', Items.golden_carrot, '/', Items.stick
                });
    }
}
