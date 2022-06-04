package com.mygdx.zombieland.weapon;

import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.projectile.PistolProjectile;
import com.mygdx.zombieland.entity.projectile.Projectile;
import com.mygdx.zombieland.entity.projectile.ProjectileSource;

public class Pistol extends AbstractPistol {

    public Pistol(PistolType type) {
        super(type);
    }

    @Override
    public synchronized Projectile launchProjectile(World world, ProjectileSource source) {
        return new PistolProjectile(world, source);
    }
}
