package com.FMM;

import net.minecraft.creativetab.CreativeTabs;

import com.FMM.block.ModBlocks;
import com.FMM.client.interfaces.GuiHandler;
import com.FMM.config.ConfigHandler;
import com.FMM.crafting.CraftingHandler;
import com.FMM.creativeTab.TabFMM;
import com.FMM.entity.ModEntities;
import com.FMM.item.ModItems;
import com.FMM.network.PacketPipeline;
import com.FMM.proxies.CommonProxy;
import com.FMM.reference.Strings;
import com.FMM.tileEntity.ModTileEntities;
import com.FMM.world.GenerationHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Strings.MODID, name = Strings.NAME, version = Strings.VERSION)
public class FMM {
    public static final PacketPipeline packetPipeline = new PacketPipeline();

    public static final CreativeTabs tabFMM = new TabFMM(CreativeTabs.getNextID());

    @Mod.Instance(Strings.MODID)
    public static FMM instance;

    @SidedProxy(clientSide = Strings.CLIENT_PROXY, serverSide = Strings.COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        ModItems.init();
        ModBlocks.init();
        ModTileEntities.init();
        ModEntities.init();
        proxy.initRenderers();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        CraftingHandler.addRecipes();
        packetPipeline.initialise();
        new GenerationHandler();
        new GuiHandler();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        packetPipeline.postInitialise();
    }
}
