package com.FMM.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static final String ITEMS = "Items";

    public static void init(File file) {
        Configuration config = new Configuration(file);
        config.load();


        config.save();
    }
}
