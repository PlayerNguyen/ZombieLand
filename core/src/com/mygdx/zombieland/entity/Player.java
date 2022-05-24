package com.mygdx.zombieland.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;
import com.mygdx.zombieland.runnable.ShootingRunnable;
import com.mygdx.zombieland.utils.VisualizeHelper;

public class Player implements ProjectableEntity, LivingEntity {

    private static final Texture TEXTURE_SHOOTING = new Texture(Gdx.files.internal("shooting.png"));
    private static final Texture TEXTURE_IDLING = new Texture(Gdx.files.internal("idle.png"));
    private static final long SHOOT_DELAY_IN_MILLIS = 320;

    public static final int PLAYER_SIZE = 64;

    private final Location location;
    private final Vector2D direction;
    private final Texture texture;
    private final World world;
    private Sprite sprite;
    private boolean canShoot;

    private float health = 100;
    private float rotation = 0;
    private final BitmapFont fontDrawer = new BitmapFont();

    public Player(World world) {
        this.world = world;
        this.location = new Location(0, 0);
        this.direction = new Vector2D(0, 0);
        this.texture = TEXTURE_IDLING;
        this.canShoot = true;
    }

    @Override
    public void create() {

        this.sprite = new Sprite(texture);
        this.sprite.setSize(PLAYER_SIZE, PLAYER_SIZE);

        this.sprite.setOrigin(this.sprite.getWidth() / 2, this.sprite.getHeight() / 2);
        this.location.set(this.world.getCenterLocation(32));

        this.rotateFollowsCursor();
    }

    @Override
    public void render() {

        this.rotateFollowsCursor();

        // Shoot function
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)
                || Gdx.input.isButtonPressed(Input.Buttons.RIGHT)
        ) {
            shoot();
        }

        // Draw UI
        this.updateUI();
        this.
                // Draw/Render the player
                        sprite.setX(this.getCenterLocation().x);
        sprite.setY(this.getCenterLocation().y);
        sprite.setRotation(this.rotation);
        sprite.draw(this.world.getBatch());
    }

    private void updateUI() {
        // Top-left
        this.fontDrawer.setColor(Color.VIOLET);
        this.fontDrawer.draw(this.getWorld().getBatch(), "Ammo: ", 32, 600 - 32);
        this.fontDrawer.draw(this.getWorld().getBatch(), "Health: ", 32, 600 - (32 * 2));
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

    /**
     * Rotate the player direction using mouse movement.
     * <p>
     * Method setRotation from radiant to degrees.
     * The direction is set by radRotation.
     */
    private void rotateFollowsCursor() {
        float radRotation = (float) ((float)
                Math.atan2(-(this.location.y - Gdx.input.getY()),// Minus because y-down
                        this.location.x - Gdx.input.getX()) + (Math.PI)
        );
        this.setRotation((float) Math.toDegrees(radRotation));

        // Set the direction by using basic geometry
        this.direction.x = Math.cos(radRotation);
        this.direction.y = Math.sin(radRotation);
    }

    /**
     * Create and run threads along with interface Runnable, for Multithreading.
     * Feature shooting with ShootingRunnable.
     *
     * @see Runnable
     * @see ShootingRunnable
     * @see Thread
     */
    public void shoot() {
        new Thread(new ShootingRunnable(this,
                this.world,
                SHOOT_DELAY_IN_MILLIS)
        ).start();
    }

    @Override
    public float getAngle() {
        return this.sprite.getRotation();
    }

    @Override
    public Texture getShootingTexture() {
        return TEXTURE_SHOOTING;
    }

    @Override
    public boolean isCanShoot() {
        return this.canShoot;
    }

    @Override
    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public Location lerp(Location moveTo, float speed) {
        throw new UnsupportedOperationException("Player cannot be moved");
    }

    @Override
    public void damage(DamageSource source, float amount) {
        this.health -= amount;
    }

    @Override
    public float getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(float health) {
        this.health = health;
    }

    @Override
    public void kill() {
        // TODO: end game
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
    public World getWorld() {
        return world;
    }

    @Override
    public int getSize() {
        return PLAYER_SIZE;
    }

    @Override
    public Location getCenterLocation() {
        return new Location(this.getLocation().x, this.getLocation().y);
    }
}
