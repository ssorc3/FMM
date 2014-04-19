package com.FMM.world;

import com.FMM.block.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GenerationHandler implements IWorldGenerator
{
    private WorldGenerator poisonGen;

    public GenerationHandler()
    {
        GameRegistry.registerWorldGenerator(this, 6);
        poisonGen = new WorldGenMinable(ModBlocks.poison, 16);
    }

    public void generateStandardOre(Random rand, int chunkX, int chunkZ, World world, int iterations, WorldGenerator gen, int lowestY, int highestY)
    {
        for(int i = 0; i < iterations; i++)
        {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = rand.nextInt(highestY - lowestY) + lowestY;
            int z = chunkZ * 16 + rand.nextInt(16);
            gen.generate(world, rand, x, y, z);
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        this.generateStandardOre(random, chunkX, chunkZ, world, 20, poisonGen, 0, 128);
    }
}
