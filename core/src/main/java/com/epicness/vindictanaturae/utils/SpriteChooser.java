package com.epicness.vindictanaturae.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class SpriteChooser extends ItemChooser<SpriteChooser.SpriteElement> {

    public static class SpriteElement {
        public TextureRegion region;
        // position on screen
        public float posX;
        public float posY;
        // width/height on screen
        public float width;
        public float height;
    }

    Vector2 v2 = new Vector2();
    
    @Override
    public boolean trySelect(SpriteChooser.SpriteElement element) {
        float x = Gdx.input.getX();
        float y = Gdx.graphics.getHeight() - Gdx.input.getY();
        if (Gdx.input.isButtonJustPressed(0)) {
            return x > element.posX && x < element.posX + element.width && y > element.posY && y < element.posY + element.height;
        }
        return false;
    }

    @Override
    public void draw(ShapeDrawer sd) {
        ;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (state == State.FINISHED) return;
        
        for (SpriteChooser.SpriteElement element: elements) {
            v2.set(0, 0);
            affine.idt();

            ///MODIFY THOSE TO CHANGE ANIMATION EFFECT!
            affine.preTranslate(-element.width/2, -element.height/2);
            affine.preScale(1-animParam*20, 1 - animParam);
            affine.preRotateRad(animParam*3);
            affine.preTranslate(element.posX+element.width/2, element.posY+element.height/2);


            affine.applyTo(v2);
            batch.draw(element.region, element.width, element.height, affine);
        }
    }
}
