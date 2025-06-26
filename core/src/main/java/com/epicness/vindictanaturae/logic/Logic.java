package com.epicness.vindictanaturae.logic;

import static com.epicness.vindictanaturae.constants.Levels.FIRST_LEVEL;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.epicness.vindictanaturae.stuff.Stuff;
import com.epicness.vindictanaturae.utils.SpriteChooser;

public class Logic {

    private final Box2DUpdater box2DUpdater;
    private final LevelLoader levelLoader;
    private final SpriteChooser tc;

    public Logic(Stuff stuff) {
        box2DUpdater = new Box2DUpdater(stuff);
        levelLoader = new LevelLoader(stuff);
        tc = stuff.getTC();
    }

    public void init() {
        levelLoader.loadLevel(FIRST_LEVEL);
        tc.init();

        /// TESTING!
        Texture t = new Texture("image3.png");
        SpriteChooser.SpriteElement elem = new SpriteChooser.SpriteElement();
        elem.region = new TextureRegion(t, 0, 0, 16, 16);
        elem.posX = 300f;
        elem.posY = 100f;
        elem.width = 32;
        elem.height = 32;

        tc.add(elem);

        elem = new SpriteChooser.SpriteElement();
        elem.region = new TextureRegion(t, 0, 48, 16, 16);
        elem.posX = 300f;
        elem.posY = 200f;
        elem.width = 32;
        elem.height = 32;

        tc.add(elem);

        elem = new SpriteChooser.SpriteElement();
        elem.region = new TextureRegion(t, 0, 80, 16, 16);
        elem.posX = 300f;
        elem.posY = 300f;
        elem.width = 32;
        elem.height = 32;

        tc.add(elem);
    }

    public void update(float delta) {
        box2DUpdater.update();
        tc.process(delta);
    }
}
