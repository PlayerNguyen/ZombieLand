package com.mygdx.zombieland.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;

public class Box extends ItemAbstract {

    private Location destination;
    private float velocity = 0;


    private static final Texture BOX_TEXTURE = new Texture(Gdx.files.internal("box.png"));

    public Box(Location location, Vector2D direction, World world) {
        super(location, direction, world, BOX_TEXTURE, new Sprite(BOX_TEXTURE));
        this.destination = location;
    }

    public Box(Location location, World world) {
        super(location, new Vector2D(0, 0), world, BOX_TEXTURE, new Sprite(BOX_TEXTURE));
        this.destination = location;
    }

    @Override
    public void create() {
        this.getSprite().setSize(32, 32);
        this.getSprite().setOrigin(16, 16);
    }

    @Override
    public void render() {

        if (this.getLocation().x != this.destination.x) {
            this.getLocation().x += (this.destination.x - this.getLocation().x) * velocity;
        }

        if (this.getLocation().y != this.destination.y) {
            this.getLocation().y += (this.destination.y - this.getLocation().y) * velocity;
        }

        this.getLocation().add(
                this.getDirection().x,
                this.getDirection().y
        );

        this.getSprite().setPosition(this.getLocation().x, this.getLocation().y);
        this.getSprite().draw(this.getWorld().getBatch());

    }

    @Override
    public void dispose() {
        this.getTexture().dispose();
    }

    public Location lerp(Location b, float t) {
        this.destination = new Location(b.x, b.y);
        this.velocity = t;
        return this.destination;
    }
}
