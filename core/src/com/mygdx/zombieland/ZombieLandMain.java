package com.mygdx.zombieland;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class ZombieLandMain extends ApplicationAdapter {

    SpriteBatch batch;
    World world;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.world = new World(batch);
        this.world.create();
    }

    @Override
    public void render() {
        ScreenUtils.clear(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        this.world.render(); // Render a whole world
        this.batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        this.world.dispose();
    }
}
