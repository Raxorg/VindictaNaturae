package com.epicness.vindictanaturae;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.epicness.vindictanaturae.logic.Logic;
import com.epicness.vindictanaturae.stuff.Stuff;

public class VindictaNaturaeGame extends Game {

    private Logic logic;
    private Renderer renderer;

    @Override
    public void create() {
        Stuff stuff = new Stuff();      // Holds all the game objects
        logic = new Logic(stuff);       // Handles/Updates the logic of the game
        renderer = new Renderer(stuff); // Renders the game
        logic.init();
    }

    @Override
    public void render() {
        logic.update(Gdx.graphics.getDeltaTime());
        renderer.render();
    }
}
