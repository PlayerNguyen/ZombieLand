package com.mygdx.zombieland.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum PistolType {

    PISTOL(7F, 6F, 12, 0.05F,
            Gdx.audio.newSound(Gdx.files.internal("audio/weapon/pistol_1.wav")));

    private final float damage;
    private final float knockbackPower;
    private final int maxAmmo;
    private final float recoil;
    private final Sound shootingSound;


    PistolType(float damage, float knockbackPower, int maxAmmo, float recoil, Sound shootingSound) {
        this.damage = damage;
        this.knockbackPower = knockbackPower;
        this.maxAmmo = maxAmmo;
        this.recoil = recoil;
        this.shootingSound = shootingSound;
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

    public Sound getShootingSound() {
        return shootingSound;
    }
}
