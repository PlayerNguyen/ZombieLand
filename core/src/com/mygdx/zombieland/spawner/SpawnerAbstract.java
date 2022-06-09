package com.mygdx.zombieland.spawner;

import com.badlogic.gdx.Gdx;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.state.GameState;
import com.mygdx.zombieland.utils.MathHelper;

public abstract class SpawnerAbstract implements Spawner {
    private final World world;
    private final Location location;
    private final double offset;
    private long duration;
    private long lastSpawn;

    public SpawnerAbstract(World world, Location location, double offset, long duration) {
        this.world = world;
        this.location = location;
        this.offset = offset;
        this.duration = duration;
        Gdx.app.log("Spawner", "Creating a spawner " + this);
    }

    public void create() {
        this.lastSpawn = System.currentTimeMillis() + duration;
    }

    public void render() {
        if (!this.world.getGameState().equals(GameState.PLAYING)) {
            this.lastSpawn = System.currentTimeMillis();
            return;
        }
        if (System.currentTimeMillis() - this.duration >= this.lastSpawn) {
            Location spawnLocation = new Location(
                    this.location.x + (float) MathHelper.nextDouble(-offset, offset),
                    this.location.y + (float) MathHelper.nextDouble(-offset, offset)
            );

            if (this.world.getGameState().equals(GameState.PLAYING)) {
                // Spawn here
                this.onTick(spawnLocation);
                this.lastSpawn = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public abstract void onTick(Location spawnLocation);

    public void setLastSpawn(long lastSpawn) {
        this.lastSpawn = lastSpawn;
    }

    public long getLastSpawn() {
        return lastSpawn;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public Location getLocation() {
        return location;
    }

    public World getWorld() {
        return world;
    }
}
