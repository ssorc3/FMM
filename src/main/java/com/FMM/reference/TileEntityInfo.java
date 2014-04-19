package com.FMM.reference;

import net.minecraft.util.ResourceLocation;

public class TileEntityInfo {
	public static final String RESOURCE_LOCATION = "fmm";
	
    public static final String BOMB_KEY = "bomb_te";
    public static final String TIMER_KEY = "timer";
    public static final String LEVEL_KEY = "level";
    public static final String BOMB_MODEL_LOCATION = "models/BlockBomb.obj";
    public static final String BOMB_TEXTURE = "textures/models/blockbomb.png";
    public static final String BOMB_TEXTURE_IDLE = "textures/models/bombidle.png";
    public static final ResourceLocation BOMB_MODEL = new ResourceLocation(RESOURCE_LOCATION, BOMB_MODEL_LOCATION);
    
    public static final String MACHINE_KEY = "machine_te";

	public static final String WINDMILL_KEY = "windmill_te";
	public static final String WINDMILL_TEXTURE = "textures/models/windmill.png";
    public static final String WINDMILLBASE_NORMAL = "textures/blocks/windmillBase.png";
	public static final String WINDMILLBASE_CONNECTED = "textures/models/windmillBase.png";

	public static final String WINDMILLBASE_KEY = "windmillBase_te";
}
