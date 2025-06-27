package com.epicness.vindictanaturae.logic;

import static com.epicness.vindictanaturae.constants.Box2DConstants.BOX2D_TO_GAME_SCALE;
import static com.epicness.vindictanaturae.constants.Box2DConstants.TENDRIL_ANGULAR_SPEED_FACTOR;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.epicness.vindictanaturae.stuff.Stuff;
import com.epicness.vindictanaturae.utils.BodyPlus;

public class TendrilHandler {

    private final BodyPlus plantBody, tendril;
    private final RevoluteJoint joint;

    public TendrilHandler(Stuff stuff) {
        plantBody = stuff.getPlant().getPlantBody();
        tendril = stuff.getPlant().getTendrilBody();
        joint = stuff.getPlant().getJoint();
    }

    public void update() {
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        float targetAngleRad = MathUtils.atan2(
            mouseY - plantBody.getY() * BOX2D_TO_GAME_SCALE,
            mouseX - plantBody.getX() * BOX2D_TO_GAME_SCALE
        );

        // The hammer's angle is relative to the world, but the joint angle is relative
        // to bodyA's angle. If cauldronBody can rotate, this gets more complex.
        // For GOI, cauldron doesn't freely rotate much, so world angle is a good approx.
        // Body.getAngle() is the body's rotation. Joint.getJointAngle() is relative angle.
        float currentHammerAngleRad = tendril.getAngle(); // Hammer's angle in world space

        // We want to drive the hammer to `targetAngleRad`.
        // The motor speed is how fast the joint tries to achieve the relative angle.
        // A simple proportional controller for motor speed:
        float angleDifference = targetAngleRad - currentHammerAngleRad;

        // Normalize angle difference to be between -PI and PI
        while (angleDifference <= -MathUtils.PI) angleDifference += MathUtils.PI2;
        while (angleDifference > MathUtils.PI) angleDifference -= MathUtils.PI2;

        // Set motor speed to close this gap.
        // The motor works on the relative angle between the two bodies.
        // If cauldron is bodyA, and it's not rotating much, hammerBody.getAngle() is a good proxy
        // for what we want to control.
        // The motor speed will attempt to make bodyB rotate relative to bodyA.
        // If cauldronBody.getAngle() is C_angle and hammerBody.getAngle() is H_angle,
        // then joint.getJointAngle() is roughly H_angle - C_angle.
        // We want to make H_angle = targetAngleRad.
        // So, targetJointAngle = targetAngleRad - C_angle.
        // motorSpeed = K * (targetJointAngle - currentJointAngle)

        float cauldronAngle = plantBody.getAngle();
        float currentJointAngle = joint.getJointAngle(); // This is B.angle - A.angle
        float targetJointAngle = targetAngleRad - cauldronAngle;

        // Normalize targetJointAngle relative to currentJointAngle for shortest path
        float jointAngleError = targetJointAngle - currentJointAngle;
        while (jointAngleError <= -MathUtils.PI) jointAngleError += MathUtils.PI2;
        while (jointAngleError > MathUtils.PI) jointAngleError -= MathUtils.PI2;

        float desiredAngularVelocity = jointAngleError * TENDRIL_ANGULAR_SPEED_FACTOR;
        // Clamp speed if necessary, though torque limit often handles this
        // desiredAngularVelocity = MathUtils.clamp(desiredAngularVelocity, -MAX_HAMMER_ROT_SPEED, MAX_HAMMER_ROT_SPEED);

        joint.setMotorSpeed(desiredAngularVelocity * 5f);
    }
}
