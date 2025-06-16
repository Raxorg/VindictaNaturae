package com.epicness.vindictanaturae;

import static com.epicness.vindictanaturae.constants.Box2DConstants.BOX2D_TO_GAME_SCALE;
import static com.epicness.vindictanaturae.constants.GameConstants.LIBGDX_MATTE;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.vindictanaturae.stuff.Stuff;

import space.earlygrey.shapedrawer.ShapeDrawer;

/**
 * Handles rendering agents like batches and rendering order
 */
public class Renderer {

    private final SpriteBatch spriteBatch;
    private final ShapeDrawer shapeDrawer;
    private final Stuff stuff;

    // Box2d
    private final Box2DDebugRenderer debugRenderer;
    private final Matrix4 box2DMatrix;

    public Renderer(Stuff stuff) {
        spriteBatch = new SpriteBatch();
        shapeDrawer = new ShapeDrawer(spriteBatch);
        shapeDrawer.setTextureRegion(new TextureRegion(new Texture("pixel.png")));
        this.stuff = stuff;

        debugRenderer = new Box2DDebugRenderer();
        box2DMatrix = new Matrix4();
    }

    public void render() {
        ScreenUtils.clear(LIBGDX_MATTE);

        spriteBatch.begin();
        stuff.getPlant().draw(shapeDrawer);
        for (int i = 0; i < stuff.getPlatforms().size; i++) {
            stuff.getPlatforms().get(i).draw(shapeDrawer);
        }
        spriteBatch.end();

        renderDebug();
    }

    // Shows what's going on with Box2d
    private void renderDebug() {
        debugRenderer.render(stuff.getWorld(), box2DMatrix.set(spriteBatch.getProjectionMatrix()).scl(BOX2D_TO_GAME_SCALE));
    }
}
