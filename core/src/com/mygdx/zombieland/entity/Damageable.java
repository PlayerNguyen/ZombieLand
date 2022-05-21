package com.mygdx.zombieland.entity;

public interface Damageable {

    /**
     * Check information of the damage.
     *
     * @param source of damage.
     * @see DamageSource
     *
     * @param amount of health loses from damage.
     */
    void damage(DamageSource source, float amount);

    /**
     * Value of health to keep entity alive.
     *
     * @return health.
     */
    float getHealth();

    /**
     * Set object has health.
     *
     * @param health of object.
     */
    void setHealth(float health);

    /**
     * Get killed when health down to 0
     */
    void kill();

}
