package com.FMM.reference;

public class ItemInfo {
    private static final String TEXTURE_LOCATION = "fmm:";

    //ItemWand
    public static final String WAND_NAME_UNLOCALIZED = "fmm_wand";
    public static final String WAND_ICON = TEXTURE_LOCATION + "wand";
    public static final String WAND_ICON_CHARGED = TEXTURE_LOCATION + "wand_charged";
    //End ItemWand

    public static final String CARD_NAME_UNLOCALIZED = "fmm_symbol_card";
    public static final String[] CARD_ICONS = {TEXTURE_LOCATION + "card_box", TEXTURE_LOCATION + "card_custom", TEXTURE_LOCATION + "card_cross"};

    public static final String DROID_NAME_UNLOCALIZED = "fmm_droid";
    public static final String DROID_ICON = TEXTURE_LOCATION + "droidItem";
    public static final String STARTY_KEY = "startY";
    public static final String TARGETY_KEY = "targetY";

    public static final String CHANGER_NAME_UNLOCALIZED = "fmm_colorChanger";
    public static final String CHANGER_ICON = TEXTURE_LOCATION + "charger_";
    public static final String[] CHANGER_COLORS = {"White", "Red", "Green", "Blue"};
    public static final String[] CHANGER_COLOR_TAGS = {Colors.WHITE, Colors.RED, Colors.GREEN, Colors.BLUE};
    
    public static final String WINDMILL_NAME_UNLOCALIZED = "windmill";
}
