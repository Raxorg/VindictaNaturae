package com.epicness.vindictanaturae.logic;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SnapshotArray;
import com.epicness.vindictanaturae.stuff.Level;
import com.epicness.vindictanaturae.stuff.Platform;
import com.epicness.vindictanaturae.stuff.PlatformDef;
import com.epicness.vindictanaturae.stuff.Stuff;

public class LevelLoader {

    private final World world;
    private final SnapshotArray<Platform> platforms;

    public LevelLoader(Stuff stuff) {
        world = stuff.getWorld();
        platforms = stuff.getPlatforms();
    }

    public void loadLevel(Level level) {
        PlatformDef def;
        for (int i = 0; i < level.platformDefs.length; i++) {
            def = level.platformDefs[i];
            Platform platform = new Platform(def.x, def.y, def.length, world);
            platforms.add(platform);
        }
    }
}
