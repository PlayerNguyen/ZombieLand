package com.mygdx.zombieland.runnable;

import com.badlogic.gdx.Gdx;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.Entity;
import com.mygdx.zombieland.entity.Zombie;
import com.mygdx.zombieland.entity.ZombieType;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.utils.MathHelper;

/**
 * Spawner is a delay-task to spawn(create) a new zombie
 */
public class Spawner {

    private final World world;
    private final Location location;
    private final double offset;
    private long duration;
    private long lastSpawn;
    private long currentSpawn;

    public Spawner(World world, Location location, double offset, long duration) {
        this.world = world;
        this.location = location;
        this.offset = offset;
        this.duration = duration;
        Gdx.app.log("Spawner", "Creating a spawner " + this);
    }

    public void create() {
        this.lastSpawn = System.currentTimeMillis() + duration;
    }

    public void update() {
        if (System.currentTimeMillis() - this.duration >= this.lastSpawn) {
            Location spawnLocation = new Location(this.location.x + (float) MathHelper.nextDouble(-offset, offset),
                    this.location.y + (float) MathHelper.nextDouble(-offset, offset));
            ZombieType type = ZombieType.values()[(int) (Math.random() * ZombieType.values().length)];
            // Spawn here
            Entity zombie = this.world.createEntity(
                    new Zombie(
                            this.world,
                            spawnLocation,
                            this.world.getPlayer(),
                            type
                    )
            );

            Gdx.app.log("Spawner", "Spawning a zombie " + zombie + " at " + spawnLocation);
            this.lastSpawn = System.currentTimeMillis();
        }
    }

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

}
