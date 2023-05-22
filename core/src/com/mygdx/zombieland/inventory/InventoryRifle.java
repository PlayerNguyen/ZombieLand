package com.mygdx.zombieland.inventory;

import com.badlogic.gdx.Input;
import com.mygdx.zombieland.weapon.Weapon;
import com.mygdx.zombieland.weapon.rifle.Rifle;
import com.mygdx.zombieland.weapon.rifle.RifleType;

public class InventoryRifle extends AbstractInventoryGun {
    private final String name;
    public InventoryRifle() {
        super(Input.Keys.NUM_2, new Rifle(RifleType.RIFLE), 30 * 5, 30);
        this.name = "Rifle";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {

    }
}
