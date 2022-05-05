package com.mygdx.zombieland.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;

public class Player implements Entity {

    private final Location location;
    private final Vector2D direction;
    private final Texture texture;
    private final World world;
    private Sprite sprite;

    public Player(World world) {
        this.world = world;
        this.location = new Location(0, 0);
        this.direction = new Vector2D(0, 0);

        this.texture = new Texture(Gdx.files.internal("badlogic.jpg"));
    }

    @Override
    public void create() {
        // TODO: Init player here
        this.sprite = new Sprite(texture);
        this.sprite.setSize(64, 64);
        this.sprite.setOrigin(this.sprite.getWidth() / 2, this.sprite.getHeight() / 2);
        this.location.set(this.world.getCenterLocation(32));
        this.rotateFollowsCursor();
    }

    @Override
    public void render() {
        // TODO: finish update movement here by set location and direction

        // Draw/Render the player
        sprite.setX(this.location.x);
        sprite.setY(this.location.y);
        sprite.draw(this.world.getBatch());
        this.rotateFollowsCursor();

    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public Vector2D getDirection() {
        return direction;
    }

    private void rotateFollowsCursor() {
        float radRotation = (float) ((float)
                Math.atan2(-(this.location.y - Gdx.input.getY()),// Minus because y-down
                        this.location.x - Gdx.input.getX()) + (Math.PI)
        );
        sprite.setRotation((float) Math.toDegrees(radRotation));
    }
}
