package com.mygdx.zombieland.entity.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.DamageableAbstract;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;

public abstract class ItemAbstract extends DamageableAbstract implements Item {

    private final Location location;
    private final Vector2D direction;
    private final Texture texture;
    private final Sprite sprite;
    private final World world;

    private float health;
    private float rotation;

    public ItemAbstract(Location location, Vector2D direction, World world, Texture texture, Sprite sprite, float health) {
        this.location = location;
        this.direction = direction;
        this.world = world;
        this.texture = texture;
        this.sprite = sprite;
        this.health = health;
        this.rotation = 0;
    }

    @Override
    public void render() {
        this.sprite.rotate(this.rotation);
        this.sprite.draw(world.getBatch());
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

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public void setHealth(float health) {
        this.health = health;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public Location getCenterLocation() {
        return new Location(this.getLocation().x - ((float) this.getSize() / 2)
                , this.getLocation().y - ((float) this.getSize() / 2));
    }
}
