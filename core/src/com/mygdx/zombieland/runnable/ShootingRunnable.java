package com.mygdx.zombieland.runnable;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.Player;
import com.mygdx.zombieland.entity.projectile.Projectile;
import com.mygdx.zombieland.entity.projectile.ProjectileSource;
import com.mygdx.zombieland.inventory.InventoryGun;
import com.mygdx.zombieland.inventory.InventoryItem;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;
import com.mygdx.zombieland.weapon.Gun;

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

        // Shooter is a player
        if (this.source instanceof Player) {
            Player player = (Player) this.source;
            InventoryItem currentHandItem = player.getCurrentHandItem();

            // If player is using gun
            if (currentHandItem instanceof InventoryGun) {
                InventoryGun currentHandGun = (com.mygdx.zombieland.inventory.InventoryGun) currentHandItem;

                // If no more ammo in a mag
                if (currentHandGun.getAmmo() == 0) {

                    // Play no ammo sound
                    ((Gun) currentHandGun.getWeapon()).getEmptySound().play(
                            this.world.getGameSetting().getVfxSoundLevel(),
                            1,
                            0
                    );
                    this.source.setCanShoot(false);
//                    this.world.getScheduler().runTaskAfter(this.onPostRunnable(), shootDelay);

                    // Reload
                    if (currentHandGun.getTotalAmmo() > 0) {
                        if (!((Player) this.source).isReloading()) {
                            player.reload();
                            this.world.getTextIndicator().createText(new Location(this.source.getLocation())
                                            .add((float) -this.source.getSize() / 2, (float) this.source.getSize() / 2),
                                    new Vector2D(0, 12),
                                    "Reloading", (((Gun) currentHandGun.getWeapon()).getReloadDuration()), 1.5f,
                                    Color.GREEN
                            );
                        }
                    } else {
                        this.world.getTextIndicator().createText(new Location(this.source.getLocation())
                                        .add((float) -this.source.getSize() / 2, (float) this.source.getSize() / 2),
                                new Vector2D(0, 12),
                                "Out of ammo", 300, 1.5f
                        );
                    }


                    return;

                }

                // Create a projectile
                this.source.getSprite().setTexture(this.source.getShootingTexture());
                this.world.createProjectile(projectile);
                ((Gun) currentHandGun.getWeapon()).getShootingSound()
                        .play(
                                this.world.getGameSetting().getVfxSoundLevel(),
                                1,
                                0
                        );
                // Decrease an ammo
                currentHandGun.setAmmo(currentHandGun.getAmmo() - 1);
            }
        }

        this.source.setCanShoot(false);
        this.world.getScheduler().runTaskAfter(this.onPostRunnable(), shootDelay);
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
