package com.mygdx.zombieland.weapon;

import com.badlogic.gdx.audio.Sound;

public abstract class AbstractPistol implements Gun {

    private final PistolType type;
    private float damage;
    private float knockbackPower;
    private float recoil;
    private final int maxAmmo;
    private final long reloadDuration;

    public AbstractPistol(PistolType type) {
        this.type = type;
        this.damage = this.type.getDamage();
        this.knockbackPower = this.type.getKnockbackPower();
        this.recoil = this.type.getRecoil();
        this.maxAmmo = this.type.getMaxAmmo();
        this.reloadDuration = this.type.getReloadDuration();
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
    public Sound getShootingSound() {
        return this.type.getShootingSound();
    }

    @Override
    public Sound getEmptySound() {
        return this.type.getEmptySound();
    }

    @Override
    public int getMaxAmmo() {
        return maxAmmo;
    }

    @Override
    public long getReloadDuration() {
        return reloadDuration;
    }

    @Override
    public Sound getReloadReleaseSound() {
        return this.type.getReloadReleaseSound();
    }
}
