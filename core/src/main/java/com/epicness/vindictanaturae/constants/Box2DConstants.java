package com.epicness.vindictanaturae.constants;

import com.badlogic.gdx.math.Vector2;

public class Box2DConstants {

    public static final Vector2 WORLD_GRAVITY = new Vector2(0f, -1f);
    public static final float BOX2D_TIME_STEP = 0.016f;
    public static final int BOX2D_VELOCITY_ITERATIONS = 6;
    public static final int BOX2D_POSITION_ITERATIONS = 2;
    public static final float BOX2D_TO_GAME_SCALE = 80f;
    public static final float GAME_TO_BOX2D_SCALE = 1f / BOX2D_TO_GAME_SCALE;
}
