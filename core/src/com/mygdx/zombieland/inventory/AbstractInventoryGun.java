package com.mygdx.zombieland.inventory;

import com.mygdx.zombieland.weapon.Weapon;

public abstract class AbstractInventoryGun implements InventoryGun {

    private final int slotKey;
    private final Weapon weapon;
    private int totalAmmo;
    private int ammo;

    public AbstractInventoryGun(int slotKey, Weapon weapon, int totalAmmo, int ammo) {
        this.slotKey = slotKey;
        this.weapon = weapon;
        this.totalAmmo = totalAmmo;
        this.ammo = ammo;
    }

    @Override
    public int getSlotKey() {
        return slotKey;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public int getTotalAmmo() {
        return totalAmmo;
    }

    @Override
    public void setTotalAmmo(int totalAmmo) {
        this.totalAmmo = totalAmmo;
    }

    @Override
    public int getAmmo() {
        return ammo;
    }

    @Override
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    @Override
    public String toString() {
        return "AbstractInventoryGun{" +
                "slotKey=" + slotKey +
                ", weapon=" + weapon +
                ", totalAmmo=" + totalAmmo +
                ", ammo=" + ammo +
                '}';
    }
}
