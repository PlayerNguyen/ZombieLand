package com.mygdx.zombieland.weapon.rifle;

import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.projectile.PistolProjectile;
import com.mygdx.zombieland.entity.projectile.Projectile;
import com.mygdx.zombieland.entity.projectile.ProjectileSource;

public class Rifle extends AbstractRifle {

    public Rifle(RifleType type) {
        super(type);
    }

    @Override
    public synchronized Projectile launchProjectile(World world, ProjectileSource source) {
        return new PistolProjectile(world, source);
    }

    @Override
    public long getShootDelay() {
        return 80;
    }
}
