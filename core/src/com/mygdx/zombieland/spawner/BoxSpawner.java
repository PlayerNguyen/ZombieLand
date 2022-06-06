package com.mygdx.zombieland.spawner;

import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.item.Box;
import com.mygdx.zombieland.entity.item.BoxAmmunition;
import com.mygdx.zombieland.location.Location;

public class BoxSpawner extends SpawnerAbstract {

    public BoxSpawner(World world, Location location, double offset, long duration) {
        super(world, location, offset, duration);
    }

    @Override
    public void onTick(Location spawnLocation) {
        Box box = new BoxAmmunition(new Location(spawnLocation), this.getWorld());
        this.getWorld().createEntity(box);
    }
}
