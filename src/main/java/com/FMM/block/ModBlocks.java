package com.FMM.block;

import net.minecraft.block.Block;

import com.FMM.block.itemBlock.ItemBlockMachine;
import com.FMM.block.itemBlock.ItemBlockWindmill;
import com.FMM.reference.BlockInfo;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static Block machine;
    public static Block bomb;
    public static Block poison;
    public static Block color;
    public static Block windmill;
    public static Block windmillBase;

    public static void init() {
        machine = new BlockMachine();
        bomb = new BlockBomb();
        poison = new BlockPoison();
        color = new BlockColor();
        windmill = new BlockWindmill();
        windmillBase = new BlockWindmillBase();

        registerBlocks();
    }

    private static void registerBlocks() {
        GameRegistry.registerBlock(machine, ItemBlockMachine.class, BlockInfo.MACHINE_NAME_UNLOCALIZED);
        GameRegistry.registerBlock(bomb, BlockInfo.BOMB_NAME_UNLOCALIZED);
        GameRegistry.registerBlock(poison, BlockInfo.POISON_NAME_UNLOCALIZED);
        GameRegistry.registerBlock(color, BlockInfo.COLOR_NAME_UNLOCALIZED);
        GameRegistry.registerBlock(windmill, ItemBlockWindmill.class,BlockInfo.WINDMILL_NAME_UNLOCALIZED);
        GameRegistry.registerBlock(windmillBase, BlockInfo.WINDMILLBASE_NAME_UNLOCALIZED);
    }
}
