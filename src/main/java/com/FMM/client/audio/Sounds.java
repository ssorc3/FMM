package com.FMM.client.audio;


import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public enum Sounds
{
    WAND("wand"),
    BOMB_DROP("bombfall"),
    BOMB_CLICK("emptyclick"),
    BEEP("beep");

    private final String SOUND_LOCATION = "fmm:";

    public String filename;

    Sounds(String filename)
    {
        this.filename = SOUND_LOCATION + filename;
    }

    public void playAtEntity(Entity entity, float volume, float pitch)
    {
        entity.worldObj.playSoundAtEntity(entity, filename, volume, pitch);
    }

    public void play(World world, double x, double y, double z, float volume, float pitch)
    {
        world.playSoundEffect(x, y, z, filename, volume, pitch);
    }
}
