package com.mygdx.zombieland.hud;

import com.badlogic.gdx.Gdx;
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

    public static final Texture HEALTH_FULL_TEXTURE = new Texture(Gdx.files.internal("health.png"));
    public static final Texture HEALTH_HALF_TEXTURE = new Texture(Gdx.files.internal("halfhealth.png"));
    public static final Texture NO_HEALTH_TEXTURE = new Texture(Gdx.files.internal("nohealth.png"));

    private final World world;
    public List<Sprite> bulletSprites = new ArrayList<>();
    public List<Sprite> healthSprites = new ArrayList<>();

    public HUD(World world) {
        this.world = world;
    }

    @Override
    public void create() {
    }

    @Override
    public void render() {

        // Render gun information
        this.indicateGunInfo();

        // Render player health information
        this.indicateHealthInfo();

    }

    private void indicateHealthInfo() {
        float health = this.getWorld().getPlayer().getHealth();
        if (this.healthSprites.size() != 5) {
            for (int i = 0; i < 5; i++) {
                Sprite sprite = new Sprite(HEALTH_FULL_TEXTURE);

                sprite.setSize(32, 31);
                sprite.setPosition(720 - (32 * i), 8);


                this.healthSprites.add(sprite);
            }
        }



        // Draw healths sprite
        for (int i = 0; i < healthSprites.size(); i++) {
            Sprite healthSprite = healthSprites.get(i);
            healthSprite.setTexture(HEALTH_FULL_TEXTURE);

            healthSprite.setColor(1, 1, 1, this.getWorld().getGameSetting().getHudVisibleLevel());
            float filledHeartIndex = (health * (5F / 100));


            if (i > filledHeartIndex) {
                healthSprite.setTexture(NO_HEALTH_TEXTURE);
            }

            healthSprite.draw(this.world.batch);
        }
    }

    private void indicateGunInfo() {

        this.world.font.setColor(201F/255, 198F/255, 38F/255, this.world.getGameSetting()
                .getHudVisibleLevel());

        for (Sprite bulletSprite : bulletSprites) {
            bulletSprite.setColor(1, 1, 1, this.world.getGameSetting()
                    .getHudVisibleLevel());
        }
//        this.world.font.setColor(Color.YELLOW);
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
                    bulletSprite.setColor(0, 0, 0, this.world.getGameSetting()
                            .getHudVisibleLevel());
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
