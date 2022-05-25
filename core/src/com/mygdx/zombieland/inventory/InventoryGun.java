package com.mygdx.zombieland.inventory;

public interface InventoryGun extends InventoryItem {

    int getTotalAmmo();

    void setTotalAmmo(int totalAmmo);

    int getAmmo();

    void setAmmo(int ammo);


}
