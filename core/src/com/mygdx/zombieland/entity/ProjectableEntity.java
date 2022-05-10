package com.mygdx.zombieland.entity;

import com.badlogic.gdx.graphics.Texture;

public interface ProjectableEntity extends Entity {

    float getAngle();

    Texture getShootingTexture();

    boolean isCanShoot();

    void setCanShoot(boolean canShoot);
}
