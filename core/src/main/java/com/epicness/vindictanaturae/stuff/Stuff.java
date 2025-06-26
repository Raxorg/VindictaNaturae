package com.epicness.vindictanaturae.stuff;

import static com.epicness.vindictanaturae.constants.Box2DConstants.WORLD_GRAVITY;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SnapshotArray;
import com.epicness.vindictanaturae.utils.SpriteChooser;

/**
 * Stores all the game objects
 */
public class Stuff {

    private final World world; // Box2d
    private final Plant plant;
    private final SnapshotArray<Platform> platforms;
    private final SpriteChooser tc;

    public Stuff() {
        world = new World(WORLD_GRAVITY, false);
        plant = new Plant(world);
        platforms = new SnapshotArray<>();
        tc = new SpriteChooser();
    }

    public World getWorld() {
        return world;
    }

    public Plant getPlant() {
        return plant;
    }

    public SnapshotArray<Platform> getPlatforms() {
        return platforms;
    }

    public SpriteChooser getTC() {
        return tc;
    }
}
