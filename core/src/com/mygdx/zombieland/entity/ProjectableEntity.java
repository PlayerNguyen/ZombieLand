package com.mygdx.zombieland.entity;

import com.badlogic.gdx.graphics.Texture;

public interface ProjectableEntity extends Entity {
    /**
     * Represent the change of direction.
     *
     * @return angle.
     */
    float getAngle();

    /**
     * Represent the line of shooting texture.
     *
     * @see Texture
     * @return shootingTexture.
     */
    Texture getShootingTexture();

    /**
     * Checks whether the current entity can shoot.
     *
     * @return canShoot.
     */
    boolean isCanShoot();

    /**
     * Sets the entity to shoot.
     *
     * @param canShoot the value to set.
     */
    void setCanShoot(boolean canShoot);
}
