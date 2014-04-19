package com.FMM.proxies;

import com.FMM.block.ModBlocks;
import com.FMM.client.models.ModelDroid;
import com.FMM.client.renderers.*;
import com.FMM.entity.EntityBomb;
import com.FMM.entity.EntityDroid;
import com.FMM.entity.EntitySpaceship;
import com.FMM.item.ModItems;
import com.FMM.reference.BlockInfo;
import com.FMM.reference.TileEntityInfo;
import com.FMM.tileEntity.TileEntityBomb;
import com.FMM.tileEntity.TileEntityWindmill;
import com.FMM.tileEntity.TileEntityWindmillBase;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ClientProxy extends CommonProxy
{
    @Override
    public void initRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntitySpaceship.class, new RenderSpaceship());

        ModelDroid droid = new ModelDroid();
        RenderingRegistry.registerEntityRenderingHandler(EntityDroid.class, new RenderDroid(droid));
        MinecraftForgeClient.registerItemRenderer(ModItems.droid, new RenderItemDroid(droid));

        RenderBlockMachine machine = new RenderBlockMachine();
        BlockInfo.machineRenderId = machine.getRenderId();
        RenderingRegistry.registerBlockHandler(machine);
        
        IModelCustom model = AdvancedModelLoader.loadModel(TileEntityInfo.BOMB_MODEL);
        RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new RenderBomb());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBomb.class, new RenderBombBlock(model));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.bomb), new RenderBombBlockItem(model));
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new RenderBlockWindmill());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmillBase.class, new RenderBlockWindmillBase());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.windmill), new RenderWindmillItem());
        
    }
}
