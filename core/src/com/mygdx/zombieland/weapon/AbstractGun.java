package com.mygdx.zombieland.weapon;

import com.badlogic.gdx.audio.Sound;

public abstract class AbstractGun implements Gun{
    private float damage;
    private float knockbackPower;
    private float recoil;
    private final int maxAmmo;
    private final long reloadDuration;

    private final Sound shootingSound;
    private final Sound emptySound;
    private final Sound releaseReloadSound;

    public AbstractGun(float damage,
                       float knockbackPower,
                       float recoil,
                       int maxAmmo,
                       long reloadDuration,
                       Sound shootingSound,
                       Sound emptySound,
                       Sound releaseReloadSound) {
        this.damage = damage;
        this.knockbackPower = knockbackPower;
        this.recoil = recoil;
        this.maxAmmo = maxAmmo;
        this.reloadDuration = reloadDuration;
        this.shootingSound = shootingSound;
        this.emptySound = emptySound;
        this.releaseReloadSound = releaseReloadSound;
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
        return this.shootingSound;
    }

    @Override
    public Sound getEmptySound() {
        return this.emptySound;
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
        return this.releaseReloadSound;
    }
}
