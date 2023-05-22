package com.mygdx.zombieland.weapon.rifle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum RifleType {
    RIFLE(
            15F,
                    6F,
                    30,
                    0.2F,
            Gdx.audio.newSound(Gdx.files.internal("audio/weapon/pistol_1.wav")),
            Gdx.audio.newSound(Gdx.files.internal("audio/weapon/pistol_no_ammo.mp3")),
            Gdx.audio.newSound(Gdx.files.internal("audio/weapon/pistol_release.wav")),
            1500
            );

    private final float damage;
    private final float knockbackPower;
    private final int maxAmmo;
    private final float recoil;
    private final Sound shootingSound;
    private final Sound emptySound;
    private final Sound reloadReleaseSound;
    private final long reloadDuration;


    RifleType(float damage,
               float knockbackPower,
               int maxAmmo,
               float recoil,
               Sound shootingSound,
               Sound emptySound,
               Sound reloadReleaseSound) {
        this.damage = damage;
        this.knockbackPower = knockbackPower;
        this.maxAmmo = maxAmmo;
        this.recoil = recoil;
        this.shootingSound = shootingSound;
        this.emptySound = emptySound;
        this.reloadReleaseSound = reloadReleaseSound;
        this.reloadDuration = 0;
    }

    RifleType(float damage,
               float knockbackPower,
               int maxAmmo,
               float recoil,
               Sound shootingSound,
               Sound emptySound,
               Sound reloadReleaseSound,
               long reloadDuration) {
        this.damage = damage;
        this.knockbackPower = knockbackPower;
        this.maxAmmo = maxAmmo;
        this.recoil = recoil;
        this.shootingSound = shootingSound;
        this.emptySound = emptySound;
        this.reloadReleaseSound = reloadReleaseSound;
        this.reloadDuration = reloadDuration;
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

    public Sound getEmptySound() {
        return emptySound;
    }

    public long getReloadDuration() {
        return reloadDuration;
    }

    public Sound getReloadReleaseSound() {
        return reloadReleaseSound;
    }
}
