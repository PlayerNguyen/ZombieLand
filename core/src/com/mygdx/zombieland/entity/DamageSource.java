package com.mygdx.zombieland.entity;

public interface DamageSource extends Entity {

    float getKnockbackPower();

    void setKnockbackPower(float value);
}
