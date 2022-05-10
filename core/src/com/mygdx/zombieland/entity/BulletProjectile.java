package com.mygdx.zombieland.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;
import com.mygdx.zombieland.utils.MathHelper;

public class BulletProjectile implements Projectile {

    private final Texture texture;
    private final Sprite sprite;
    private final Location location;
    private final Vector2D direction;
    private final ProjectableEntity source;
    private final World world;

    private boolean hit;
    private float damage;

    private final Location sourceLocation;

    private static final float BULLET_VELOCITY = 20f;
    // Max distances that bullet can fly
    private static final double BULLET_MAXIMUM_DISTANCE = 1999;
    // Bullet eccentricity
    private static final double BULLET_ECCENTRICITY = .1f;

    public BulletProjectile(ProjectableEntity source, World world) {
        this.source = source;
        this.world = world;
        this.direction = new Vector2D(source.getDirection());
        this.location = new Location(source.getLocation());
        this.texture = new Texture(Gdx.files.internal("projectile.png"));
        this.sprite = new Sprite(texture);
        this.sourceLocation = source.getLocation();

        this.direction.x += MathHelper.nextDouble(-BULLET_ECCENTRICITY, BULLET_ECCENTRICITY);
        this.direction.y += MathHelper.nextDouble(-BULLET_ECCENTRICITY, BULLET_ECCENTRICITY);
    }

    @Override
    public void create() {
        this.sprite.setSize(32, 32);
        this.sprite.setOrigin(32, 32);
        this.sprite.rotate(source.getAngle());

        Gdx.app.log("Projectile", "Generated projectile @" + System.identityHashCode(this));
    }

    @Override
    public void render() {
        // Remove the projectile if is out of distance
        if (this.sourceLocation.distance(this.location) > BULLET_MAXIMUM_DISTANCE) {
            if (world.removeProjectile(this)) {
                Gdx.app.log("Projectile", "Removed projectile @" + System.identityHashCode(this));
            }
            return;
        }

        // Check collision
//        if (this.location) {
//
//        }

        // Render projectile location
        this.sprite.setPosition(this.location.x, this.location.y);

        // Update bullet location from direction
        this.location.x = (float) (this.location.x + (this.direction.x * BULLET_VELOCITY));
        this.location.y = (float) (this.location.y + (this.direction.y * BULLET_VELOCITY));

        // From gun, not from hand =))
        if (this.sourceLocation.distance(this.location) > 80) {
            this.sprite.draw(this.world.getBatch());
        }

    }

    @Override
    public void dispose() {
        this.direction.set(0, 0);
        this.texture.dispose();
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public Vector2D getDirection() {
        return this.direction;
    }

    @Override
    public Texture getTexture() {
        return this.texture;
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public float getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    public boolean isHit() {
        return hit;
    }

    @Override
    public void setIsHit(boolean isHit) {
        this.hit = isHit;
    }
}
