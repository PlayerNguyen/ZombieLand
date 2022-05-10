package com.mygdx.zombieland.entity;

public interface Damageable {

    void damage(DamageSource source, float amount);

    float getHealth();

    void setHealth(float health);

}
