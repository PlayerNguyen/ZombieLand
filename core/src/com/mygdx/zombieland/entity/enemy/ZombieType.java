package com.mygdx.zombieland.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum ZombieType {

    ZOMBIE(2.3F, 5F, 23.5999F, "zombie1"),
    DOCTOR_ZOMBIE(3.5F, 3F, 30F, "zombie2"),
    MUTANT_ZOMBIE(7.2F, 1F, 50F, "zombie3"),
    STARVE_ZOMBIE(2.0F, 7F, 15F, "zombie4");

    private final float damage;
    private final float speed;
    private final float health;
    private final String spriteName;
    private final Texture texture;
    private final Texture attackTexture;

    ZombieType(float damage, float speed, float health, String spriteName) {
        this.damage = damage;
        this.health = health;
        this.speed = speed;
        this.spriteName = spriteName;
        this.texture = new Texture(Gdx.files.internal(this.spriteName + ".png"));
        this.attackTexture = new Texture(Gdx.files.internal(this.spriteName + "_attack.png"));
    }

    public String getSpriteName() {
        return spriteName;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getHealth() {
        return health;
    }

    public float getDamage() {
        return damage;
    }

    public float getSpeed() {
        return speed;
    }

    public Texture getAttackTexture() {
        return attackTexture;
    }
}
