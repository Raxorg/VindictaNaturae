package com.epicness.vindictanaturae.logic;

import static com.epicness.vindictanaturae.constants.Box2DConstants.BOX2D_POSITION_ITERATIONS;
import static com.epicness.vindictanaturae.constants.Box2DConstants.BOX2D_TIME_STEP;
import static com.epicness.vindictanaturae.constants.Box2DConstants.BOX2D_VELOCITY_ITERATIONS;

import com.badlogic.gdx.physics.box2d.World;
import com.epicness.vindictanaturae.stuff.Plant;
import com.epicness.vindictanaturae.stuff.Stuff;

/**
 * Advances the physics of the game
 */
public class Box2DUpdater {

    private final World world;
    private final Plant plant;

    public Box2DUpdater(Stuff stuff) {
        world = stuff.getWorld();
        plant = stuff.getPlant();
    }

    public void update() {
        world.step(BOX2D_TIME_STEP, BOX2D_VELOCITY_ITERATIONS, BOX2D_POSITION_ITERATIONS);
        plant.syncVisual();
    }
}
