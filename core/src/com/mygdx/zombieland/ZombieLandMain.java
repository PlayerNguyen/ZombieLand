package com.mygdx.zombieland;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class ZombieLandMain extends ApplicationAdapter {
    public static final String gameVersion = "1.0.0 beta 1";
    private static final long STARTUP_BANNER_DURATION = 3000;

    private SpriteBatch batch;
    private World world;
    private final long startMillis;
    private Texture phourTeamBannerTexture;

    public ZombieLandMain() {
        this.startMillis = System.currentTimeMillis();
    }

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.world = new World(batch);
        this.world.create();

        this.phourTeamBannerTexture = new Texture(Gdx.files.internal("phour_team.PNG"));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.96078431372f, 0.96078431372f, 0.96078431372f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (startMillis + STARTUP_BANNER_DURATION > System.currentTimeMillis()) {
            this.batch.begin();
            this.getBatch().draw(this.phourTeamBannerTexture, 0, 0);
            this.batch.end();
        } else {

            this.batch.begin();
            this.world.render(); // Render a whole world
            this.batch.end();
        }
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public void dispose() {
        batch.dispose();
        this.world.dispose();
    }
}
