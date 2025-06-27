package com.epicness.vindictanaturae.stuff;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;
import static com.epicness.vindictanaturae.constants.Box2DConstants.BOX2D_TO_GAME_SCALE;
import static com.epicness.vindictanaturae.constants.Box2DConstants.CATEGORY_PLAYER;
import static com.epicness.vindictanaturae.constants.Box2DConstants.CATEGORY_TENDRIL;
import static com.epicness.vindictanaturae.constants.Box2DConstants.GAME_TO_BOX2D_SCALE;
import static com.epicness.vindictanaturae.constants.Box2DConstants.MASK_PLAYER;
import static com.epicness.vindictanaturae.constants.Box2DConstants.MASK_TENDRIL;
import static com.epicness.vindictanaturae.constants.Box2DConstants.TENDRIL_MOTOR_MAX_TORQUE;
import static com.epicness.vindictanaturae.constants.GameConstants.TENDRIL_THICKNESS;
import static com.epicness.vindictanaturae.constants.GameConstants.TENDRIL_LENGTH;
import static com.epicness.vindictanaturae.utils.ShapeBuilder.buildCircle;
import static com.epicness.vindictanaturae.utils.ShapeBuilder.buildRectangle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.epicness.vindictanaturae.utils.BodyPlus;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Plant {

    private final BodyPlus plantBody, tendrilBody;
    private final RevoluteJoint joint;
    public float x, y, radius;
    private float tendrilAngle;
    public Rectangle tendrilRect;

    public Plant(World world) {
        radius = 30f;
        x = 600f;
        y = radius + 500f;

        Shape bodyShape = buildCircle(radius * GAME_TO_BOX2D_SCALE);
        plantBody = new BodyPlus(world, DynamicBody, bodyShape);
        plantBody.setPosition(x * GAME_TO_BOX2D_SCALE, y * GAME_TO_BOX2D_SCALE);
        plantBody.setFilter(CATEGORY_PLAYER, MASK_PLAYER);
        plantBody.setFixedRotation(true);

        Shape tendrilShape = buildRectangle(TENDRIL_LENGTH * GAME_TO_BOX2D_SCALE, TENDRIL_THICKNESS * GAME_TO_BOX2D_SCALE);
        tendrilBody = new BodyPlus(world, DynamicBody, tendrilShape);
        tendrilBody.setPosition(x * GAME_TO_BOX2D_SCALE, y * GAME_TO_BOX2D_SCALE - TENDRIL_THICKNESS * 0.5f * GAME_TO_BOX2D_SCALE);
        tendrilBody.setFilter(CATEGORY_TENDRIL, MASK_TENDRIL);

        tendrilRect = new Rectangle(x, y, TENDRIL_LENGTH, TENDRIL_THICKNESS);

        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.initialize(plantBody.getBody(), tendrilBody.getBody(), new Vector2(x * GAME_TO_BOX2D_SCALE, y * GAME_TO_BOX2D_SCALE));
        jointDef.collideConnected = false;
        jointDef.enableMotor = true;
        jointDef.maxMotorTorque = TENDRIL_MOTOR_MAX_TORQUE;

        joint = (RevoluteJoint) world.createJoint(jointDef);
    }

    public void draw(ShapeDrawer shapeDrawer) {
        shapeDrawer.filledCircle(x, y, radius, Color.FOREST);
        shapeDrawer.filledRectangle(tendrilRect, tendrilAngle, BLUE, RED);
    }

    public void syncVisual() {
        x = plantBody.getX() * BOX2D_TO_GAME_SCALE;
        y = plantBody.getY() * BOX2D_TO_GAME_SCALE;
        tendrilAngle = tendrilBody.getAngle();
        Vector2 tendrilCenter = new Vector2(tendrilBody.getWorldCenter()).sub(tendrilBody.getLocalCenter());
        tendrilRect.setPosition(tendrilCenter.x * BOX2D_TO_GAME_SCALE, tendrilCenter.y * BOX2D_TO_GAME_SCALE);
    }

    public BodyPlus getPlantBody() {
        return plantBody;
    }

    public BodyPlus getTendrilBody() {
        return tendrilBody;
    }

    public RevoluteJoint getJoint() {
        return joint;
    }
}
