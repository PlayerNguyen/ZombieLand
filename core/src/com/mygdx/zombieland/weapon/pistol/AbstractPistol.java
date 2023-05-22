package com.mygdx.zombieland.weapon.pistol;

import com.mygdx.zombieland.weapon.AbstractGun;

public abstract class AbstractPistol extends AbstractGun {

    private final PistolType type;

    public AbstractPistol(PistolType type) {
        super(type.getDamage(),
                type.getKnockbackPower(),
                type.getRecoil(),
                type.getMaxAmmo(),
                type.getReloadDuration(),
                type.getShootingSound(),
                type.getEmptySound(),
                type.getReloadReleaseSound());
        this.type = type;
    }

    public PistolType getType() {
        return type;
    }
}
