package com.mygdx.zombieland.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.projectile.ProjectileSource;
import com.mygdx.zombieland.inventory.Inventory;
import com.mygdx.zombieland.inventory.InventoryItem;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;
import com.mygdx.zombieland.runnable.ShootingRunnable;
import com.mygdx.zombieland.utils.VisualizeHelper;
import com.mygdx.zombieland.weapon.Gun;
import com.mygdx.zombieland.weapon.Pistol;
import com.mygdx.zombieland.weapon.PistolType;
import com.mygdx.zombieland.weapon.Weapon;

public class Player extends DamageableAbstract
        implements ProjectileSource, LivingEntity, InventoryHolder {

    private static final Texture TEXTURE_SHOOTING = new Texture(Gdx.files.internal("shooting.png"));
    private static final Texture TEXTURE_IDLING = new Texture(Gdx.files.internal("idle.png"));
    private static final long SHOOT_DELAY_IN_MILLIS = 320;

    public static final int PLAYER_SIZE = 96;

    private final Location location;
    private final Vector2D direction;
    private final Texture texture;
    private final World world;

    private Sprite sprite;
    private boolean canShoot;
    private float health = 100;
    private float rotation = 0;
    private InventoryItem currentHandItem;

    public Player(World world) {
        this.world = world;
        this.location = new Location(0, 0);
        this.direction = new Vector2D(0, 0);
        this.texture = TEXTURE_IDLING;
        this.canShoot = true;
//        this.weapon = new Pistol(PistolType.PISTOL);
        this.currentHandItem = world.getInventory().getItems().get(0);
    }

    @Override
    public void create() {

        this.sprite = new Sprite(texture);
        this.sprite.setSize(PLAYER_SIZE, PLAYER_SIZE);

        this.sprite.setOrigin((float) this.getSize() / 2, (float) this.getSize() / 2);
        this.location.set(this.world.getCenterLocation(0));

        this.rotateFollowsCursor();
    }

    @Override
    public void render() {
        // Make the player rotate follows cursor
        this.rotateFollowsCursor();

        // Shoot function
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)
                || Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            this.shoot();
        }

        // Switch item
        this.handleSwitch();

        // Draw/Render the player
        this.sprite.setX(this.getCenterLocation().x);
        this.sprite.setY(this.getCenterLocation().y);

        sprite.setRotation(this.rotation);
        sprite.draw(this.world.getBatch());

        if (this.world.isDebug()) {
            VisualizeHelper.simulateBox(this.getWorld(), this);
            VisualizeHelper.simulateDirection(this.getWorld(), this);
        }
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
        this.setHealth(this.getHealth() - amount);

        this.getSprite().setColor(Color.RED);
        this.getWorld().getScheduler().runTaskAfter(new Runnable() {
            @Override
            public void run() {
                // Reset color
                getSprite().setColor(Color.WHITE);

                // Remove if entity is out of health
                if (getHealth() <= 0) kill();
            }
        }, 300);

        Location indicatorTextLocation = new Location(this.getLocation());
        this.getWorld().getTextIndicator().createText(indicatorTextLocation,
                new Vector2D(0, 16F),
                String.format("%.0f", amount),
                1000,
                .3F
        );
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
        Gdx.app.log("Player", "Killing the player and trigger end the game");
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
//        return new Location(this.getLocation().x, this.getLocation().y);
        return super.getCenterLocation();
    }

    @Override
    public Weapon getWeapon() {
        return this.getCurrentHandItem().getWeapon();
    }

    @Override
    public void setWeapon(Weapon weapon) {
//        this.weapon = weapon;
    }

    @Override
    public InventoryItem getCurrentHandItem() {
        return this.currentHandItem;
    }

    @Override
    public void setCurrentHandItem(InventoryItem currentHandItem) {
        this.currentHandItem = currentHandItem;
    }

    private void handleSwitch () {
        for (InventoryItem item : this.getWorld().getInventory().getItems()) {
            if (Gdx.input.isKeyPressed(item.getSlotKey())) {
                Gdx.app.log("WeaponSwitcher", "Swap into item " + item.getName());
                this.setCurrentHandItem(item);
            }
        }
    }

    public void reload() {
        throw new UnsupportedOperationException("");
    }
}
