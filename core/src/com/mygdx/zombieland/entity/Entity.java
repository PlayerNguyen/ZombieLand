package com.mygdx.zombieland.entity;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.zombieland.Renderable;
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
     * @return a texture object.
     * @see Texture
     */
    Texture getTexture();

}
