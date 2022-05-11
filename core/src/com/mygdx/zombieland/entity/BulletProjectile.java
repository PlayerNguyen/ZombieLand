package com.mygdx.zombieland.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;
import com.mygdx.zombieland.utils.MathHelper;

public class BulletProjectile implements Projectile, DamageSource {

    private static final float BULLET_VELOCITY = 40f;
    // Max distances that bullet can fly
    private static final double BULLET_MAXIMUM_DISTANCE = 1999;
    // Bullet eccentricity
    private static final double BULLET_ECCENTRICITY = .01f;
    private static final float BULLET_DAMAGE = 10;
    private static final float BULLET_DEFAULT_KNOCKBACK = 10;

    private final Texture texture;
    private final Sprite sprite;
    private final Location location;
    private final Vector2D direction;
    private final ProjectableEntity source;
    private final World world;

    private boolean hit;
    private float damage;
    private float knockbackPower;
    private float rotation;

    private final Location sourceLocation;

    public BulletProjectile(ProjectableEntity source, World world) {
        this.source = source;
        this.world = world;
        this.direction = new Vector2D(source.getDirection());
        this.location = new Location(source.getLocation());
        this.texture = new Texture(Gdx.files.internal("projectile.png"));
        this.sprite = new Sprite(texture);
        this.sourceLocation = source.getLocation();

        this.knockbackPower = BULLET_DEFAULT_KNOCKBACK;
        this.direction.x += MathHelper.nextDouble(-BULLET_ECCENTRICITY, BULLET_ECCENTRICITY);
        this.direction.y += MathHelper.nextDouble(-BULLET_ECCENTRICITY, BULLET_ECCENTRICITY);

        this.rotation = source.getAngle();
    }

    @Override
    public void create() {
        this.sprite.setSize(32, 32);
        this.sprite.setOrigin(32, 32);
        this.setRotation(source.getAngle());

        Gdx.app.log("Projectile", "Generated projectile @" + System.identityHashCode(this));
    }

    @Override
    public void render() {
        // Remove the projectile if is out of distance
        if (this.sourceLocation.distance(this.location) > BULLET_MAXIMUM_DISTANCE || isHit() ) {
            if (world.removeProjectile(this)) {
                Gdx.app.log("Projectile", "Removed projectile @" + System.identityHashCode(this));
            }
            return;
        }

        // Check collision
        for (final Entity entity : world.getEntities()) {
            Location entityLocation = new Location(entity.getLocation());
            if (entityLocation.add(-32).distance(this.location) <= 32) {

                Gdx.app.log("Triggered", "Hit to entity ...");
                // Set triggered
                setIsHit(true);
                // Damage things
                if (entity instanceof Damageable) {
                    ((Damageable) entity).damage(this, BULLET_DAMAGE);
                }
            }
        }

        this.sprite.setRotation(this.rotation);

        // Render projectile location
        this.sprite.setPosition(this.location.x, this.location.y);

        // Update bullet location from direction
        this.location.x = (float) (this.location.x + (this.direction.x * BULLET_VELOCITY));
        this.location.y = (float) (this.location.y + (this.direction.y * BULLET_VELOCITY));

        // From gun, not from aim
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
    public Location lerp(Location moveTo, float speed) {
        throw new UnsupportedOperationException("Projectile cannot be lerped");
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

    @Override
    public float getKnockbackPower() {
        return knockbackPower;
    }

    @Override
    public void setKnockbackPower(float knockbackPower) {
        this.knockbackPower = knockbackPower;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public World getWorld() {
        return world;
    }
}
