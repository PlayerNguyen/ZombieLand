package com.mygdx.zombieland.inventory;

import com.mygdx.zombieland.weapon.Weapon;

public interface InventoryItem {

    int getSlotKey();

    Weapon getWeapon();

    String getName();

    void setName(String name);

}
