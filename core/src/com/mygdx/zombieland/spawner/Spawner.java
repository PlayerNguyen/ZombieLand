package com.mygdx.zombieland.spawner;

import com.badlogic.gdx.Gdx;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.Entity;
import com.mygdx.zombieland.entity.enemy.Zombie;
import com.mygdx.zombieland.entity.enemy.ZombieType;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.utils.MathHelper;

/**
 * Spawner is a delay-task to spawn(create) a new entities
 */
public interface Spawner {

    void create();

    void update();


}
