package com.mygdx.zombieland.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;

public abstract class ItemAbstract implements Item {

    private final Location location;
    private final Vector2D direction;
    private final Texture texture;
    private final Sprite sprite;
    private final World world;


    public ItemAbstract(Location location, Vector2D direction, World world, Texture texture, Sprite sprite) {
        this.location = location;
        this.direction = direction;
        this.world = world;
        this.texture = texture;
        this.sprite = sprite;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public Vector2D getDirection() {
        return direction;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }
}
