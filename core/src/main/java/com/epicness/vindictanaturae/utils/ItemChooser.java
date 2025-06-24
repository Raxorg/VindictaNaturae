package com.epicness.vindictanaturae.utils;

import space.earlygrey.shapedrawer.ShapeDrawer;

public abstract class ItemChooser<T> {
    
    protected enum State {FADE_IN, PRESENTING, FADE_OUT, FINISHED}

    protected T elements[];
    private T selected;
    protected State state;
    private float time;
    private float SHOWTIME = 3.f; // 3 seconds

    @SafeVarargs
    final public void init(T ... elements) {
        this.elements = elements;
        selected = null;
        state = State.FADE_IN;
        time = 0;
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
            }
        }
        return false;
    }

    final private boolean fadingIn(float dt) {
        time = Math.min(SHOWTIME, dt + time);
        if (time == SHOWTIME) {
            state = State.PRESENTING;
        }
        return false;
    }

    final private boolean fadingOut(float dt) {
        time = Math.max(0,  time - dt);
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
    public abstract boolean trySelect(T element);

}
