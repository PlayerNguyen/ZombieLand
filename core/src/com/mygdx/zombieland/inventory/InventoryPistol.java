package com.mygdx.zombieland.inventory;

import com.badlogic.gdx.Input;
import com.mygdx.zombieland.weapon.Pistol;
import com.mygdx.zombieland.weapon.PistolType;

public class InventoryPistol extends AbstractInventoryGun {

    private String name;
    public InventoryPistol() {
        super(Input.Keys.NUM_1, new Pistol(PistolType.PISTOL), 6, 12);
        this.name = "Pistol";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
