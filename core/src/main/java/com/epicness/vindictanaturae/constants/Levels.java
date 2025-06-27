package com.epicness.vindictanaturae.constants;

import com.epicness.vindictanaturae.stuff.Level;
import com.epicness.vindictanaturae.stuff.PlatformDef;

/**
 * This class only stores levels
 */
public class Levels {

    public static final Level FIRST_LEVEL;

    static {
        PlatformDef[] firstLevelPlatforms = new PlatformDef[] {
            new PlatformDef(0f, 10f, 1200f)
        };
        FIRST_LEVEL = new Level(firstLevelPlatforms);
    }
}
