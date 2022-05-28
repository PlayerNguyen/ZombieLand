package com.mygdx.zombieland.entity.projectile;

import com.mygdx.zombieland.entity.DamageSource;
import com.mygdx.zombieland.entity.Entity;

public interface Projectile extends Entity, DamageSource {

    ProjectileSource getProjectileSource();

    void setProjectileSource(ProjectileSource source);

    /**
     * The level of damage when hits other entity
     *
     * @return the level of damage as float
     */
    float getDamage();

    /**
     * Sets the level of damage.
     *
     * @param damage damage level to set.
     */
    void setDamage(float damage);

    /**
     * Checks whether the current projectile is hit to entity.
     *
     * @return true if is collided, false otherwise.
     */
    boolean isHit();

    /**
     * Sets the projectile is hit.
     *
     * @param isHit the value to set
     */
    void setHit(boolean isHit);

    float getProjectileRange();

    void setProjectileRange(float projectileRange);
}
