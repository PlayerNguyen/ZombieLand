package com.mygdx.zombieland.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.entity.DamageSource;
import com.mygdx.zombieland.entity.DamageableAbstract;
import com.mygdx.zombieland.entity.LivingEntity;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;

public abstract class EnemyAbstract extends DamageableAbstract implements LivingEntity, DamageSource {

    private float knockbackPower = 0;
    private float health;
    private final Location location;
    private final Vector2D direction;
    private Sprite sprite;
    private Texture texture;

    private float rotation;

    public EnemyAbstract(Location location, Vector2D direction, Sprite sprite, Texture texture, float health) {
        this.location = location;
        this.direction = direction;
        this.sprite = sprite;
        this.texture = texture;
        this.health = health;
        this.rotation = 0;
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
    public Location getLocation() {
        return location;
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
    public Vector2D getDirection() {
        return direction;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
