package com.mygdx.zombieland.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;

public class Zombie extends EnemyAbstract {

    private static final Texture ZOMBIE_TEXTURE = new Texture(Gdx.files.internal("idle.png"));

    private final World world;
    private final Entity target;
    private Location destination;
    private float fraction = 1;
    private float speed = 12f; // Zombie movement speed

    public Zombie(World world, Location startLocation, Entity target) {
        super(startLocation, new Vector2D(), new Sprite(ZOMBIE_TEXTURE), ZOMBIE_TEXTURE, 20F);
        this.world = world;
        this.destination = new Location(this.getLocation());
        this.target = target;
    }

    @Override
    public void create() {
        this.getSprite().setTexture(ZOMBIE_TEXTURE);
        this.getSprite().setSize(64, 64);
        this.getSprite().setOrigin(32, 32);

        this.updateMove();
    }

    @Override
    public void render() {

        this.updateMove();

        System.out.println(fraction);
        // Update lerp
        if (fraction < 1) {
            fraction += Gdx.graphics.getDeltaTime() * speed;
            this.getLocation().x += (this.destination.x - this.getLocation().x) * fraction;
            this.getLocation().y += (this.destination.y - this.getLocation().y) * fraction;
        }

        this.getLocation().add(
                this.getDirection().x * Gdx.graphics.getDeltaTime() * speed,
                this.getDirection().y * Gdx.graphics.getDeltaTime() * speed
        );

        // Export (render) image
        this.getSprite().setRotation(this.getRotation());
        this.getSprite().setPosition(this.getLocation().x, this.getLocation().y);
        this.getSprite().draw(world.getBatch());

    }

    private void rotateToTarget() {
        // arc tan(y / x)
        Location temp = this.target.getLocation();
        Location cur = this.getLocation();
//        Gdx.app.log("Location", " Player current location: " + temp.x + " " + temp.y);
        float atan2 = (float) Math.atan2(temp.y - cur.y, temp.x - cur.x);
        this.setRotation((float) Math.toDegrees(atan2));
    }

    @Override
    public void dispose() {
        this.getTexture().dispose();
    }


    @Override
    public Location lerp(Location moveTo, float speed) {
        this.fraction = 0;
        this.destination = new Location(moveTo.x, moveTo.y);
        // This velocity for lerp,
        return this.destination;

    }

    private void updateMove() {
        // Update rotation to target
        this.rotateToTarget();
        // Set direction to the target
        this.getDirection().x = Math.sin(this.getRotation());
        this.getDirection().y = -Math.cos(this.getRotation());
    }

    @Override
    public World getWorld() {
        return world;
    }
}
