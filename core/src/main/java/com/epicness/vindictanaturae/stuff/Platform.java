package com.epicness.vindictanaturae.stuff;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;
import static com.epicness.vindictanaturae.constants.Box2DConstants.CATEGORY_WORLD;
import static com.epicness.vindictanaturae.constants.Box2DConstants.GAME_TO_BOX2D_SCALE;
import static com.epicness.vindictanaturae.constants.Box2DConstants.MASK_WORLD;
import static com.epicness.vindictanaturae.utils.ShapeBuilder.buildLine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.epicness.vindictanaturae.utils.BodyPlus;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Platform {

    public float x, y, length;

    public Platform(float x, float y, float length, World world) {
        Shape tendrilShape = buildLine(x, y, x + length, y, GAME_TO_BOX2D_SCALE);
        BodyPlus body = new BodyPlus(world, StaticBody, tendrilShape);
        body.setPosition(x * GAME_TO_BOX2D_SCALE, y * GAME_TO_BOX2D_SCALE);
        body.setFilter(CATEGORY_WORLD, MASK_WORLD);
    }

    public void draw(ShapeDrawer shapeDrawer) {
        shapeDrawer.line(x, y, x + length, y, Color.FOREST);
    }
}
