package com.mygdx.zombieland.entity;

public interface DamageSource extends Entity {

    /**
     * Requirement to knock-back entity.
     *
     * @return knockbackPower.
     */
    float getKnockbackPower();

    /**
     * Sets entity is knock-backed when meet value.
     *
     * @param value of power to knock-back object.
     */
    void setKnockbackPower(float value);
}
