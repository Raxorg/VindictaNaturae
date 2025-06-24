package com.epicness.vindictanaturae.utils;

import com.badlogic.gdx.Gdx;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class TestChooser extends ItemChooser<Float> {


    @Override
    public boolean trySelect(Float element) {
        float x = Gdx.input.getX();
        float y = Gdx.graphics.getHeight() - Gdx.input.getY();
        return (x-element)*(x-element)+(y-30)+(y-30)<5*5;
    }

    @Override
    public void draw(ShapeDrawer sd) {
        if (state == State.FINISHED) return;
        for (Float element: elements) {
            sd.circle(element, 30, 5);
        }
    }
}
