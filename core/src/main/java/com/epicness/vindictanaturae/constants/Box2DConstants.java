package com.epicness.vindictanaturae.constants;

import com.badlogic.gdx.math.Vector2;

public class Box2DConstants {

    public static final Vector2 WORLD_GRAVITY = new Vector2(0f, -1f);
    public static final float BOX2D_TIME_STEP = 0.016f;
    public static final int BOX2D_VELOCITY_ITERATIONS = 6;
    public static final int BOX2D_POSITION_ITERATIONS = 2;
    public static final float BOX2D_TO_GAME_SCALE = 80f;
    public static final float GAME_TO_BOX2D_SCALE = 1f / BOX2D_TO_GAME_SCALE;

    public static final float TENDRIL_MOTOR_MAX_TORQUE = 2f;
    public static final float TENDRIL_ANGULAR_SPEED_FACTOR = 5f;

    public static final short CATEGORY_PLAYER = 0x0001;     // Your plant base
    public static final short CATEGORY_TENDRIL = 0x0002;    // Your tendril
    public static final short CATEGORY_WORLD = 0x0004;      // Static level geometry
    public static final short CATEGORY_COLLECTIBLE = 0x0008;// Example for other things

    // What the PLAYER can collide with
    public static final short MASK_PLAYER = CATEGORY_WORLD | CATEGORY_COLLECTIBLE;
    // What the TENDRIL can collide with
    public static final short MASK_TENDRIL = CATEGORY_WORLD | CATEGORY_COLLECTIBLE; // Tendril might also hit collectibles or trigger things
    // What the WORLD can collide with
    public static final short MASK_WORLD = CATEGORY_PLAYER | CATEGORY_TENDRIL | CATEGORY_COLLECTIBLE; // World collides with almost everything
}
