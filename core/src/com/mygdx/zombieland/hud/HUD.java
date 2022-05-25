package com.mygdx.zombieland.hud;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.zombieland.Renderable;
import com.mygdx.zombieland.World;

public class HUD implements Renderable {

    private final World world;

    public HUD(World world) {
        this.world = world;
    }

    @Override
    public void create() {

    }

    @Override
    public void render() {
        // Render player information
        this.world.font.setColor(Color.RED);
        this.world.font.draw(this.world.getBatch(), String.format("Health: %.0f/100", this.getWorld().getPlayer().getHealth()),
                32, 600-32);
        this.world.font.setColor(Color.YELLOW);
        this.world.font.draw(this.world.getBatch(), "Amount", 32, 600-(32 * 2));

        this.world.font.setColor(Color.YELLOW);
        this.world.font.draw(this.world.getBatch(), String.format("%s", this.getWorld().getPlayer()
                .getCurrentHandItem().getName()), 32, 600-(32 * 3));
    }

    @Override
    public void dispose() {

    }

    public World getWorld() {
        return world;
    }
}
