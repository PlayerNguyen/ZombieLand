package com.mygdx.zombieland.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.Damageable;
import com.mygdx.zombieland.entity.Entity;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;
import com.mygdx.zombieland.state.GameState;
import com.mygdx.zombieland.utils.VisualizeHelper;

public class Zombie extends EnemyAbstract {

    public static final int ZOMBIE_SIZE = 64;
//    public static final float ZOMBIE_MOVEMENT_SPEED = 30f; // Each type has different speed
    public static final long ZOMBIE_HIT_DURATION = 2000;

    private final World world;
    private final Entity target;
    private final ZombieType type;

    private Location destination;
    private float fraction = 1;
    private float speed; // Zombie movement speed

    private long lastHit = 0;

    public Zombie(World world, Location startLocation, Entity target, ZombieType type) {
        super(startLocation, new Vector2D(), null, null, type.getHealth());

        this.world = world;
        this.destination = new Location(this.getLocation());
        this.target = target;
        this.type = type;

        // Set texture later
        Texture texture = this.getType().getTexture();
        this.setSprite(new Sprite(texture));
        this.setTexture(texture);

        // Set zombie speed
        this.speed = this.type.getSpeed();

        // Set angle
        this.rotateToTarget();
        // Set direction to the target
        this.getDirection().x = Math.sin(this.getRotation());
        this.getDirection().y = -Math.cos(this.getRotation());
    }

    @Override
    public void create() {
//        this.getSprite().setTexture(ZOMBIE_TEXTURE);
        this.getSprite().setSize(ZOMBIE_SIZE, ZOMBIE_SIZE);
        this.getSprite().setOrigin((float) ZOMBIE_SIZE / 2, (float) ZOMBIE_SIZE / 2);

        this.updateMove();
    }

    @Override
    public void render() {

        if (this.world.getGameState().equals(GameState.PLAYING)) {
            this.updateMove();

            // Update lerp
            if (fraction < 1) {
                fraction += Gdx.graphics.getDeltaTime() * speed ;
                this.getLocation().x += (this.destination.x - this.getLocation().x) * fraction;
                this.getLocation().y += (this.destination.y - this.getLocation().y) * fraction;
            }

            this.getLocation().add(
                    this.getDirection().x * Gdx.graphics.getDeltaTime() * speed * (this.getWorld().isDebug() ? 5 : 1),
                    this.getDirection().y * Gdx.graphics.getDeltaTime() * speed * (this.getWorld().isDebug() ? 5 : 1)
            );
        }

        // Export (render) image
        this.getSprite().setRotation(this.getRotation());
        this.getSprite().setPosition(this.getLocation().x - 32, this.getLocation().y - 32);
        this.getSprite().draw(world.getBatch());

        // Debug
        if (this.getWorld().isDebug()) {
            VisualizeHelper.simulateBox(this.getWorld(), this);
            VisualizeHelper.simulateDirection(this.getWorld(), this);
        }
    }

    private void rotateToTarget() {
        // arc tan(y / x)
        Location temp = this.target.getLocation();
        Location cur = this.getLocation();

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

//        if (this.target.getLocation().distance(this.getLocation()) <= (float) this.target.getSize() / 2) {
//
//        } else {
//            this.getDirection().x = 0;
//            this.getDirection().y = 0;
//
//
//        }

        // Set direction to the target
        this.getDirection().x = Math.sin(this.getRotation());
        this.getDirection().y = -Math.cos(this.getRotation());


        // Update speed
        this.setSpeed(this.getType().getSpeed());
//        this.speed = this.target.getLocation().distance(this.getLocation()) <= (float) this.target.getSize() / 2
//                ? 0
//                : this.getType().getSpeed();

        // Hit player when get close
        if (this.target.getLocation().distance(this.getLocation()) <= (float) this.target.getSize() / 2) {
            if (System.currentTimeMillis() - ZOMBIE_HIT_DURATION >= lastHit || lastHit == 0) {
                this.attack();
                ((Damageable) this.target).damage(this, this.getType().getDamage());
                this.lastHit = System.currentTimeMillis();
            }
        }
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public int getSize() {
        return ZOMBIE_SIZE;
    }

    private void attack() {
        // Set attack texture
        this.getSprite().setTexture(this.getType().getAttackTexture());
        this.world.getScheduler().runTaskAfter(new Runnable() {
            @Override
            public void run() {
                getSprite().setTexture(getType().getTexture());
            }
        }, 200);
    }

    public ZombieType getType() {
        return type;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }
}
