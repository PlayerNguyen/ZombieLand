package com.mygdx.zombieland.weapon;

public abstract class AbstractPistol implements Gun {

    private final PistolType type;
    private float damage;
    private float knockbackPower;
    private float recoil;
    private int maxAmmo;
    private int currentAmmo;

    public AbstractPistol(PistolType type) {
        this.type = type;
        this.damage = this.type.getDamage();
        this.knockbackPower = this.type.getKnockbackPower();
        this.recoil = this.type.getRecoil();
        this.maxAmmo = this.type.getMaxAmmo();
        this.currentAmmo = this.type.getMaxAmmo();
    }

    public PistolType getType() {
        return type;
    }

    @Override
    public float getDamage() {
        return damage;
    }

    @Override
    public float getKnockbackPower() {
        return knockbackPower;
    }

    @Override
    public float getRecoil() {
        return recoil;
    }

    @Override
    public int getMaxAmmo() {
        return maxAmmo;
    }

    @Override
    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    public void setKnockbackPower(float knockbackPower) {
        this.knockbackPower = knockbackPower;
    }

    @Override
    public void setRecoil(float recoil) {
        this.recoil = recoil;
    }

    @Override
    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    @Override
    public void setCurrentAmmo(int currentAmmo) {
        this.currentAmmo = currentAmmo;
    }

    @Override
    public int getCurrentAmmo() {
        return currentAmmo;
    }
}
