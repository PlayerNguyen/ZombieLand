package com.mygdx.zombieland.spawner;

import com.badlogic.gdx.Gdx;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.Entity;
import com.mygdx.zombieland.entity.enemy.Zombie;
import com.mygdx.zombieland.entity.enemy.ZombieType;
import com.mygdx.zombieland.location.Location;

public class ZombieSpawner extends SpawnerAbstract {
    public ZombieSpawner(World world, Location location, double offset, long duration) {
        super(world, location, offset, duration);
    }

    @Override
    public void onTick(Location spawnLocation) {
        ZombieType type = ZombieType.values()[(int) (Math.random() * (ZombieType.values().length))];
        // Spawn here
        Entity zombie = this.getWorld().createEntity(
                new Zombie(
                        this.getWorld(),
                        spawnLocation,
                        this.getWorld().getPlayer(),
                        type
                )
        );

        Gdx.app.log("Spawner", "Spawning a zombie " + zombie + " at " + spawnLocation);
    }


}
