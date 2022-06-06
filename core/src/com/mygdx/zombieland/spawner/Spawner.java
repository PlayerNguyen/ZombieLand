package com.mygdx.zombieland.spawner;

import com.mygdx.zombieland.location.Location;

/**
 * Spawner is a delay-task to spawn(create) a new entities
 */
public interface Spawner {

    void create();

    void update();

    long getLastSpawn();

    void setLastSpawn(long lastSpawnDuration);

    void setDuration(long duration);

    long getDuration();

    void onTick(Location spawnLocation);

}
