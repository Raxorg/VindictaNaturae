package com.epicness.vindictanaturae.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Box2d Body wrapper for convenience
 */
public class BodyPlus {

    protected Body body;
    private final World world;

    public BodyPlus(World world, BodyDef.BodyType type, Shape... shapes) {
        this.world = world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        for (int i = 0; i < shapes.length; i++) {
            fixtureDef.shape = shapes[i];
            body.createFixture(fixtureDef);
            shapes[i].dispose();
        }
    }

    public float getX() {
        return body.getPosition().x;
    }

    public void translateX(float amount) {
        body.setTransform(body.getPosition().add(amount, 0f), body.getAngle());
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void translateY(float amount) {
        body.setTransform(body.getPosition().add(0f, amount), body.getAngle());
    }

    public void setPosition(float x, float y) {
        translateX(x - getX());
        translateY(y - getY());
    }

    public void destroy() {
        world.destroyBody(body);
    }
}
