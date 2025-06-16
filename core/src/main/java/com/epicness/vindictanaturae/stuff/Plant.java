package com.epicness.vindictanaturae.stuff;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.math.MathUtils.degRad;
import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;
import static com.epicness.vindictanaturae.constants.Box2DConstants.BOX2D_TO_GAME_SCALE;
import static com.epicness.vindictanaturae.constants.Box2DConstants.GAME_TO_BOX2D_SCALE;
import static com.epicness.vindictanaturae.constants.GameConstants.TENDRIL_HEIGHT;
import static com.epicness.vindictanaturae.constants.GameConstants.TENDRIL_WIDTH;
import static com.epicness.vindictanaturae.utils.ShapeBuilder.buildCircle;
import static com.epicness.vindictanaturae.utils.ShapeBuilder.buildRectangle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.epicness.vindictanaturae.utils.BodyPlus;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Plant {

    private final BodyPlus body, tendril;
    public float x, y, radius, angle;
    public Rectangle tendrilRect;

    public Plant(World world) {
        radius = 30f;
        x = 600f;
        y = radius + 600f;

        Shape bodyShape = buildCircle(radius * GAME_TO_BOX2D_SCALE);
        body = new BodyPlus(world, DynamicBody, bodyShape);
        body.setPosition(x * GAME_TO_BOX2D_SCALE, y * GAME_TO_BOX2D_SCALE);

        Shape tendrilShape = buildRectangle(TENDRIL_WIDTH * GAME_TO_BOX2D_SCALE, TENDRIL_HEIGHT * GAME_TO_BOX2D_SCALE);
        tendril = new BodyPlus(world, DynamicBody, tendrilShape);
        tendril.setPosition(x * GAME_TO_BOX2D_SCALE, y * GAME_TO_BOX2D_SCALE);

        tendrilRect = new Rectangle(x, y, TENDRIL_WIDTH, TENDRIL_HEIGHT);
    }

    public void draw(ShapeDrawer shapeDrawer) {
        shapeDrawer.filledCircle(x, y, radius, Color.FOREST);
        shapeDrawer.filledRectangle(tendrilRect, angle * degRad, BLUE, RED);
    }

    public void syncVisual() {
        x = body.getX() * BOX2D_TO_GAME_SCALE;
        y = body.getY() * BOX2D_TO_GAME_SCALE;
        tendrilRect.setPosition(tendril.getX() * BOX2D_TO_GAME_SCALE, tendril.getY() * BOX2D_TO_GAME_SCALE);
    }
}
