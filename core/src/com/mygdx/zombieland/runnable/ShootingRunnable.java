package com.mygdx.zombieland.runnable;

import com.badlogic.gdx.Gdx;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.ProjectableEntity;
import com.mygdx.zombieland.entity.BulletProjectile;

public class ShootingRunnable implements Runnable {

    private final ProjectableEntity source;
    private final World world;
    private final long shootDelay;
    private final BulletProjectile projectile;

    public ShootingRunnable(ProjectableEntity source, World world, long shootDelay) {
        this.source = source;
        this.world = world;
        this.shootDelay = shootDelay;
        this.projectile = new BulletProjectile(this.source, this.world);
    }

    @Override
    public void run() {
        // Cannot shoot (locked)
        if (!source.isCanShoot()) return;

        // Otherwise, create bullet and runnable
        long current = System.currentTimeMillis();
        this.source.getSprite().setTexture(this.source.getShootingTexture());
        this.source.setCanShoot(false);
        this.world.createProjectile(projectile);

        while (System.currentTimeMillis() < current + shootDelay) {}
        Gdx.app.postRunnable(this.onPostRunnable());
    }

    private Runnable onPostRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                source.getSprite().setTexture(source.getTexture());
                source.setCanShoot(true);
            }
        };
    }
}
