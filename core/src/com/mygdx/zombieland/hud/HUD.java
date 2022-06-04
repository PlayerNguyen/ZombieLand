package com.mygdx.zombieland.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.mygdx.zombieland.Renderable;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.inventory.InventoryGun;
import com.mygdx.zombieland.inventory.InventoryItem;
import com.mygdx.zombieland.weapon.Gun;
import com.mygdx.zombieland.weapon.Weapon;

public class HUD implements Renderable {

    private final World world;

    public HUD(World world) {
        this.world = world;
    }

    @Override
    public void create() {

    }

    @Override
    public void render() {
        // Render player information
        this.world.font.setColor(Color.RED);
        this.world.font.draw(this.world.getBatch(), String.format("Health: %.0f/100", this.getWorld().getPlayer().getHealth()),
                32, 600 - 32);
        this.world.font.setColor(Color.YELLOW);
        this.world.font.draw(this.world.getBatch(), "Amount", 32, 600 - (32 * 2));

        this.world.font.setColor(Color.YELLOW);
        this.world.font.draw(this.world.getBatch(), String.format("%s", this.getWorld().getPlayer()
                .getCurrentHandItem().getName()), 800 - (32 * 6), 64, 100, Align.center, true);

        InventoryItem currentHandItem = this.world.getPlayer().getCurrentHandItem();
        if (currentHandItem instanceof InventoryGun) {

            InventoryGun currentHandGun = (InventoryGun) currentHandItem;
            Weapon weapon = currentHandGun.getWeapon();
            Gun gunWeapon = (Gun) weapon;

            this.world.font.draw(this.world.getBatch(),
                    String.format("%s / %s", currentHandGun.getAmmo(),
                            (currentHandGun.getTotalAmmo())
                    ),
                    800 - (32 * 6),
                    32,
                    100,
                    Align.center,
                    true);
        }
    }

    @Override
    public void dispose() {

    }

    public World getWorld() {
        return world;
    }
}
