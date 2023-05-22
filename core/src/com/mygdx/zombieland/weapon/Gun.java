package com.mygdx.zombieland.weapon;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.projectile.Projectile;
import com.mygdx.zombieland.entity.projectile.ProjectileSource;

public interface Gun extends Weapon {

    float getRecoil();

    void setRecoil(float recoil);

    Projectile launchProjectile(World world, ProjectileSource source);

    int getMaxAmmo();

    Sound getShootingSound();

    Sound getEmptySound();

    Sound getReloadReleaseSound();

    long getReloadDuration();

    long getShootDelay();

}
