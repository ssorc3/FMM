package com.FMM.entity;

import com.FMM.FMM;
import com.FMM.reference.EntityInfo;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities {
    public static void init() {
        EntityRegistry.registerModEntity(EntitySpaceship.class, EntityInfo.SPACESHIP_NAME, EntityInfo.SPACESHIP_ID, FMM.instance, 80, 3, true);
        EntityRegistry.registerModEntity(EntityBomb.class, EntityInfo.BOMB_NAME, EntityInfo.BOMB_ID, FMM.instance, 80, 3, false);
        EntityRegistry.registerModEntity(EntityDroid.class, EntityInfo.DROID_NAME, EntityInfo.DROID_ID, FMM.instance, 80, 3, true);
    }
}
