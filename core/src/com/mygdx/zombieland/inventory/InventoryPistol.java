package com.mygdx.zombieland.inventory;

import com.badlogic.gdx.Input;
import com.mygdx.zombieland.weapon.pistol.Pistol;
import com.mygdx.zombieland.weapon.pistol.PistolType;

public class InventoryPistol extends AbstractInventoryGun {

    private String name;
    public InventoryPistol() {
        super(Input.Keys.NUM_1, new Pistol(PistolType.PISTOL), 12*10, 12);
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
