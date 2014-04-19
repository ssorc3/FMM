package com.FMM.tileEntity;

import com.FMM.reference.TileEntityInfo;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityBomb.class, TileEntityInfo.BOMB_KEY);
        GameRegistry.registerTileEntity(TileEntityMachine.class, TileEntityInfo.MACHINE_KEY);
        GameRegistry.registerTileEntity(TileEntityWindmill.class, TileEntityInfo.WINDMILL_KEY);
        GameRegistry.registerTileEntity(TileEntityWindmillBase.class, TileEntityInfo.WINDMILLBASE_KEY);
    }
}
