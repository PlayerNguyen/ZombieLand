package com.mygdx.zombieland.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.Renderable;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;

public interface Entity extends Renderable {

    /**
     * Represents a current location of the entity.
     *
     * @return a current location of the entity.
     */
    Location getLocation();

    /**
     * Represents a current direction that the sprite of entity will point towards.
     *
     * @return a current direction as vector 2D.
     */
    Vector2D getDirection();

    /**
     * Texture which represents an image (sprite) of the entity.
     *
     * @return a texture object.
     * @see Texture
     */
    Texture getTexture();

    /**
     * A drawing path for texture
     *
     * @return a sprite object
     * @see Sprite
     */
    Sprite getSprite();

    /**
     *A method to allow or disallow moving.
     * @param moveTo a new location.
     * @param speed of movement.
     * @return destination.
     */
    Location lerp(Location moveTo, float speed);

    /**
     * Changing object direction.
     *
     * @return rotation of object.
     */
    float getRotation();

    /**
     *Sets object is rotated.
     *
     * @param rotation the object with an angle.
     */
    void setRotation(float rotation);

    /**
     * Represents getting object to world.
     *
     * @see World
     * @return world.
     */
    World getWorld();

    int getSize();

    Location getCenterLocation();
}
