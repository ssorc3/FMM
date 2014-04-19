package com.FMM.reference;

public class BlockInfo {
    public static final String TEXTURE_LOCATION = "fmm:";

    public static final String MACHINE_NAME_UNLOCALIZED = "fmm_anvil_machine";
    public static final String MACHINE_TOP = TEXTURE_LOCATION + "machine_top";
    public static final String[] MACHINE_SIDES = {TEXTURE_LOCATION + "machine_side", TEXTURE_LOCATION + "machine_side_box", TEXTURE_LOCATION + "machine_side_custom", TEXTURE_LOCATION + "machine_side_cross"};
    public static final String MACHINE_BOT = TEXTURE_LOCATION + "machine_bottom";
    public static final String MACHINE_DISABLED = TEXTURE_LOCATION + "machine_disabled";
    public static int machineRenderId;

    public static final String BOMB_NAME_UNLOCALIZED = "fmm_bomb";
    public static final String BOMB_TEXTURE = TEXTURE_LOCATION + "bomb";
    public static final String BOMB_TEXTURE_IDLE = TEXTURE_LOCATION + "bomb_idle";

    public static final String POISON_NAME_UNLOCALIZED = "fmm_poison";
    public static final String POISON_TEXTURE = TEXTURE_LOCATION + "poison_block";
    public static final String POISON_PARTICLE_TEXTURE = TEXTURE_LOCATION + "poison";
    public static final String POISON_PARTICLE_TEXTURE_NC = TEXTURE_LOCATION + "poison_nocolor";

    public static final String COLOR_NAME_UNLOCALIZED = "fmm_color";
    public static final String COLOR_TEXTURE = TEXTURE_LOCATION + "color";
    public static final String[] COLOR_TEXTURE_COLORS = {"_white", "_red", "_green", "_blue"};
    public static final String COLOR_TEXTURE_FRONT = "_front";
    public static final String COLOR_TEXTURE_SIDE = "_side";
    
    public static final String WINDMILL_NAME_UNLOCALIZED = "fmm_windmill";

	public static final String WINDMILLBASE_NAME_UNLOCALIZED = "fmm_windmillBase";
	public static final String WINDMILLBASE_TEXTURE = "windmillBase";
}
