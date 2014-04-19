package com.FMM.block.itemBlock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockMachine extends ItemBlock {
    public ItemBlockMachine(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }
}
