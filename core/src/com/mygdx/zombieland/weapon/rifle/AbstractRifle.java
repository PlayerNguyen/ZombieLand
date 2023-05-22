package com.mygdx.zombieland.weapon.rifle;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.zombieland.weapon.AbstractGun;

public abstract class AbstractRifle extends AbstractGun {

    public AbstractRifle(RifleType type) {
        super(type.getDamage(),
                type.getKnockbackPower(),
                type.getRecoil(),
                type.getMaxAmmo(),
                type.getReloadDuration(),
                type.getShootingSound(),
                type.getEmptySound(),
                type.getReloadReleaseSound()
        );
    }
}
