package com.mygdx.zombieland.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;
import com.mygdx.zombieland.Renderable;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.projectile.PistolProjectile;
import com.mygdx.zombieland.inventory.InventoryGun;
import com.mygdx.zombieland.inventory.InventoryItem;
import com.mygdx.zombieland.weapon.Gun;
import com.mygdx.zombieland.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class HUD implements Renderable {

    private final World world;
    public List<Sprite> bulletSprites = new ArrayList<>();

    public HUD(World world) {
        this.world = world;
    }

    @Override
    public void create() {
//        this.bulletSprite = new Sprite(PistolProjectile.PISTOL_TEXTURE);
//        this.bulletSprite.setSize(16, 16);
    }

    @Override
    public void render() {

        // Render player information
        this.world.font.setColor(Color.RED);
        this.world.font.draw(this.world.getBatch(), String.format("Health: %.0f/100", this.getWorld().getPlayer().getHealth()),
                32, 600 - 32);
        this.world.font.setColor(Color.YELLOW);

        this.indicateGunInfo();
        this.indicateHealthInfo();

    }

    private void indicateHealthInfo() {

    }

    private void indicateGunInfo() {
        for (Sprite bulletSprite : bulletSprites) {
            bulletSprite.setColor(1, 1, 1, .3f);
        }
        this.world.font.setColor(Color.YELLOW);
        this.world.font.draw(this.world.getBatch(), String.format("%s", this.getWorld().getPlayer()
                .getCurrentHandItem().getName()), 800 - (32 * 5), 64 + 18, 100, Align.center, true);

        InventoryItem currentHandItem = this.world.getPlayer().getCurrentHandItem();
        if (currentHandItem instanceof InventoryGun) {

            InventoryGun currentHandGun = (InventoryGun) currentHandItem;
            Weapon weapon = currentHandGun.getWeapon();
            Gun gunWeapon = (Gun) weapon;

            if (this.bulletSprites.size() != gunWeapon.getMaxAmmo()) {
                for (int i = 0; i < gunWeapon.getMaxAmmo(); i++) {
                    Sprite bullet = new Sprite(PistolProjectile.PISTOL_TEXTURE);
                    bullet.setSize(32, 32);
                    bullet.setPosition(800 - (32 * 6), 32 + (4 * i));

                    this.bulletSprites.add(bullet);

                }
            }

            this.world.font.draw(this.world.getBatch(),
                    String.format("x %.0f",
                            (Math.ceil((float) currentHandGun.getTotalAmmo() / 12))
                    ),
                    800 - (32 * 5),
                    64,
                    100,
                    Align.center,
                    true);

            // Draw a bullet as iconic
            for (int i = 0, spritesSize = bulletSprites.size(); i < spritesSize; i++) {

                Sprite bulletSprite = bulletSprites.get(i);
                if (i >= currentHandGun.getAmmo()) {
                    bulletSprite.setColor(0, 0, 0, .3f);
                }
                bulletSprite.draw(this.world.batch);
            }

        }
    }


    @Override
    public void dispose() {

    }

    public World getWorld() {
        return world;
    }
}
