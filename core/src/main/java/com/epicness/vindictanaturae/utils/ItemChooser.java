package com.epicness.vindictanaturae.utils;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Affine2;

import space.earlygrey.shapedrawer.ShapeDrawer;

public abstract class ItemChooser<T> {
    
    protected enum State {FADE_IN, PRESENTING, FADE_OUT, FINISHED}
    protected Affine2 affine;
    protected ArrayList<T> elements = new ArrayList<>();
    private T selected;
    protected State state;
    private float time;
    private final float SHOWTIME = 1.f; // 1 second
    public float animParam;

    final public void init() {
        affine = new Affine2();
        affine.idt();
        elements.clear();
        selected = null;
        state = State.FADE_IN;
        time = 0;
        animParam = 0;
    }

    final public void add(T elem) {
        elements.add(elem);
    }

    final public boolean process(float dt) {
        if (state == State.FADE_IN) {
            return fadingIn(dt);
        } else if (state == State.PRESENTING) {
            return processInternal();
        } else if (state == State.FADE_OUT){
            return fadingOut(dt);
        } else 
            return false;
    }

    final private boolean processInternal() {
        for (T element: elements) {
            if (trySelect(element)) {
                state = State.FADE_OUT;
                selected = element;
                return false;
            }
        }
        return false;
    }

    final private boolean fadingIn(float dt) {
        time = Math.min(SHOWTIME, dt + time);
        animParam = 1 - time/SHOWTIME;
        if (time == SHOWTIME) {
            state = State.PRESENTING;
        }
        return false;
    }

    final private boolean fadingOut(float dt) {
        time = Math.max(0,  time - dt);
        animParam = 1 - time/SHOWTIME;
        if (time == 0) {
            state = State.FINISHED;
            return true;
        }
        return false;
    }

    public final T getSelected() {
        return selected;
    }

    public abstract void draw(ShapeDrawer sd);
    public abstract void draw(SpriteBatch batch);
    public abstract boolean trySelect(T element);

}
