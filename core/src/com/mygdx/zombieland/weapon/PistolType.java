package com.mygdx.zombieland.weapon;

public enum PistolType {

    PISTOL(7F, 6F, 12, 0.05F);

    private final float damage;
    private final float knockbackPower;
    private final int maxAmmo;
    private final float recoil;

    PistolType(float damage, float knockbackPower, int maxAmmo, float recoil) {
        this.damage = damage;
        this.knockbackPower = knockbackPower;
        this.maxAmmo = maxAmmo;
        this.recoil = recoil;
    }

    public float getDamage() {
        return damage;
    }

    public float getKnockbackPower() {
        return knockbackPower;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public float getRecoil() {
        return recoil;
    }
}
