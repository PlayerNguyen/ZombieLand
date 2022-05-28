package com.mygdx.zombieland.runnable;

import com.badlogic.gdx.Gdx;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.projectile.Projectile;
import com.mygdx.zombieland.entity.projectile.ProjectileSource;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;
import com.mygdx.zombieland.weapon.Gun;
import com.mygdx.zombieland.weapon.Weapon;

public class ShootingRunnable implements Runnable {

    private final ProjectileSource source;
    private final World world;
    private final long shootDelay;
    private final Projectile projectile;

    public ShootingRunnable(ProjectileSource source, World world, long shootDelay) {
        this.source = source;
        this.world = world;
        this.shootDelay = shootDelay;
        this.projectile = ((Gun) source.getWeapon()).launchProjectile(this.world, this.source);
    }

    @Override
    public void run() {
        // Cannot shoot (locked)
        if (!source.isCanShoot()) return;


        // Otherwise, create bullet and runnable
        if ((this.source.getWeapon()) instanceof Gun) {
            Gun gunWeapon = (Gun) this.source.getWeapon();

            // Abort the firing action because of the ammo
            if (gunWeapon.getCurrentAmmo() == 0) {
                this.world.getTextIndicator().createText(new Location(this.source.getLocation())
                                .add((float) -this.source.getSize() / 2,(float) this.source.getSize() / 2),
                        new Vector2D(0, 12),
                        "Out of ammo", 300, 1.5f
                );
                return;
            }

            this.source.getSprite().setTexture(this.source.getShootingTexture());
            this.world.createProjectile(projectile);
            gunWeapon.setCurrentAmmo(gunWeapon.getCurrentAmmo() - 1);
        }


        long current = System.currentTimeMillis();
        this.source.setCanShoot(false);

        while (System.currentTimeMillis() < current + shootDelay) {
        }
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
