package com.mygdx.zombieland.entity.projectile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;
import com.mygdx.zombieland.utils.VisualizeHelper;

public abstract class AbstractProjectile implements Projectile {

    private final World world;
    private ProjectileSource source;

    private final Vector2D direction;
    private final Location location;
    private final Texture texture;
    private final Sprite sprite;
    private final int textureSize;

    private float rotation;
    private float damage;
    private boolean hit;
    private float knockbackPower;



    public AbstractProjectile(World world, ProjectileSource source, Texture texture, int textureSize) {
        this.world = world;
        this.source = source;

        this.direction = new Vector2D(source.getDirection());
        this.location = new Location(source.getLocation());
        this.texture = texture;
        this.sprite = new Sprite(texture);
        Location sourceLocation = source.getLocation();

        this.rotation = source.getAngle();

        Location currentLocation = new Location(this.getLocation());
        this.damage = this.source.getWeapon().getDamage();
        this.textureSize = textureSize;
        this.knockbackPower = this.source.getWeapon().getKnockbackPower();
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
    public float getRotation() {
        return this.rotation;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public Location getCenterLocation() {
        return new Location(this.getLocation().x - ((float) this.getSize() / 2)
                , this.getLocation().y - ((float) this.getSize() / 2));
    }

    @Override
    public ProjectileSource getProjectileSource() {
        return this.source;
    }

    public void setProjectileSource(ProjectileSource source) {
        this.source = source;
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
    public void setHit(boolean hit) {
        this.hit = hit;
    }

    @Override
    public int getSize() {
        return textureSize;
    }

    @Override
    public void render() {
        // For debug, draw box and line
        if (this.getWorld().isDebug()) {
            VisualizeHelper.simulateDirection(this.getWorld(), this, Color.WHITE);
            VisualizeHelper.simulateBox(this.getWorld(), this);
        }
    }

    @Override
    public float getKnockbackPower() {
        return knockbackPower;
    }

    @Override
    public void setKnockbackPower(float knockbackPower) {
        this.knockbackPower = knockbackPower;
    }
}
