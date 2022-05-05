package com.mygdx.zombieland;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.zombieland.entity.Player;
import com.mygdx.zombieland.location.Location;

public class World implements Renderable {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public SpriteBatch batch;
    private Player player;

    public World(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void create() {
        // Load assets and materials

        // Load player and inject world into player
        this.player = new Player(this);
        this.player.create();

    }

    @Override
    public void render() {

        // Render player
        this.player.render();
    }

    @Override
    public void dispose() {
        this.player.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Location getCenterLocation(int offset) {
        return new Location(
                ((float) WINDOW_WIDTH / 2) - offset,
                ((float) WINDOW_HEIGHT / 2) - offset
        );
    }
}
