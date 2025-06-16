package com.epicness.vindictanaturae.logic;

import static com.epicness.vindictanaturae.constants.Levels.FIRST_LEVEL;

import com.epicness.vindictanaturae.stuff.Stuff;

public class Logic {

    private final Box2DUpdater box2DUpdater;
    private final LevelLoader levelLoader;

    public Logic(Stuff stuff) {
        box2DUpdater = new Box2DUpdater(stuff);
        levelLoader = new LevelLoader(stuff);
    }

    public void init() {
        levelLoader.loadLevel(FIRST_LEVEL);
    }

    public void update(float delta) {
        box2DUpdater.update();
    }
}
