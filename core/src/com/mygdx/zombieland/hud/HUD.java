package com.mygdx.zombieland.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.zombieland.Renderable;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.projectile.PistolProjectile;
import com.mygdx.zombieland.inventory.InventoryGun;
import com.mygdx.zombieland.inventory.InventoryItem;
import com.mygdx.zombieland.state.GameState;
import com.mygdx.zombieland.weapon.Gun;
import com.mygdx.zombieland.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class HUD implements Renderable {

    public static final Texture HEALTH_FULL_TEXTURE = new Texture(Gdx.files.internal("health.png"));
    public static final Texture HEALTH_HALF_TEXTURE = new Texture(Gdx.files.internal("halfhealth.png"));
    public static final Texture NO_HEALTH_TEXTURE = new Texture(Gdx.files.internal("nohealth.png"));

    private final World world;
    public List<Sprite> bulletSprites = new ArrayList<>();
    public List<Sprite> healthSprites = new ArrayList<>();

    private Stage stage;
    private Table table;

    public HUD(World world) {
        this.world = world;
    }

    @Override
    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.setColor(1, 1, 1, .2f);
        stage.addActor(table);


        table.setDebug(true); // This is optional, but enables debug lines for tables.

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
//        style.up = new TextureRegionDrawable(upRegion);
//        style.down = new TextureRegionDrawable(downRegion);
        style.font = this.getWorld().font;
        style.fontColor = Color.RED;

        stage.addActor(new TextButton("hi", style));
        // Add widgets to the table here.
    }

    @Override
    public void render() {

        // Render gun information
        this.indicateGunInfo();

        // Render player health information
        this.indicateHealthInfo();

        // Render pausing menu
        this.renderPausingMenu();

        // Render death menu
        this.renderDeathUI();
    }

    private void renderDeathUI() {
        if (this.getWorld().getGameState().equals(GameState.ENDING)) {
            this.getWorld().getFont().setColor(Color.RED);
//            this.getWorld().getFont().getData().setScale(1.25f);
            this.getWorld().getFont().draw(this.getWorld().getBatch(),
                    "Game Over",
                    this.getWorld().getPlayer().getLocation().x - 48,
                    this.getWorld().getPlayer().getLocation().y + 120
            );
        }
    }

    private void renderPausingMenu() {
//        new Actor()
        if (this.getWorld().getGameState().equals(GameState.PAUSING)) {
//            this.world.batch.end();
//
//            // TODO Draw a transparent black wrapper to wrap all menu things
//
//            // TODO Draw a pause panel which contains vfx sound, transparent UI, ...
//
//            this.world.batch.begin();
//
//            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//            stage.act(Gdx.graphics.getDeltaTime());
//            stage.draw();
            this.getWorld().getFont().setColor(Color.WHITE);
            this.getWorld().getFont().draw(this.getWorld().getBatch(),
                    "Paused",
                    this.getWorld().getPlayer().getLocation().x - 30,
                    this.getWorld().getPlayer().getLocation().y + 60
            );
        }


    }

    private void indicateHealthInfo() {
        float health = this.getWorld().getPlayer().getHealth();
        if (this.healthSprites.size() != 5) {
            for (int i = 0; i < 5; i++) {
                Sprite sprite = new Sprite(HEALTH_FULL_TEXTURE);

                sprite.setSize(32, 31);
                sprite.setPosition(720 - (32 * i), 8);


                this.healthSprites.add(sprite);
            }
        }


        // Draw healths sprite
        for (int i = 0; i < healthSprites.size(); i++) {
            Sprite healthSprite = healthSprites.get(i);
            healthSprite.setTexture(HEALTH_FULL_TEXTURE);

            healthSprite.setColor(1, 1, 1, this.getWorld().getGameSetting().getHudVisibleLevel());
            float filledHeartIndex = (health * (5F / 100));


            if (i > filledHeartIndex) {
                healthSprite.setTexture(NO_HEALTH_TEXTURE);
            }

            healthSprite.draw(this.world.batch);
        }
    }

    private void indicateGunInfo() {

        this.world.font.setColor(201F / 255, 198F / 255, 38F / 255, this.world.getGameSetting()
                .getHudVisibleLevel());

        for (Sprite bulletSprite : bulletSprites) {
            bulletSprite.setColor(1, 1, 1, this.world.getGameSetting()
                    .getHudVisibleLevel());
        }
//        this.world.font.setColor(Color.YELLOW);
        this.world.font.draw(this.world.getBatch(), String.format("%s", this.getWorld().getPlayer()
                .getCurrentHandItem().getName()), 800 - (32 * 5), 64 + 18, 100, Align.center, true);

        InventoryItem currentHandItem = this.world.getPlayer().getCurrentHandItem();
        if (currentHandItem instanceof InventoryGun) {

            InventoryGun currentHandGun = (InventoryGun) currentHandItem;
            Weapon weapon = currentHandGun.getWeapon();
            Gun gunWeapon = (Gun) weapon;

            if (this.bulletSprites.size() != gunWeapon.getMaxAmmo()) {
                for (int i = 0; i < gunWeapon.getMaxAmmo(); i++) {
                    Sprite bullet = new Sprite(PistolProjectile.PISTOL_TEXTURE);
                    bullet.setSize(32, 32);
                    bullet.setPosition(800 - (32 * 6), 32 + (4 * i));

                    this.bulletSprites.add(bullet);

                }
            }

            this.world.font.draw(this.world.getBatch(),
                    String.format("x %.0f",
                            (Math.ceil((float) currentHandGun.getTotalAmmo() / 12))
                    ),
                    800 - (32 * 5),
                    64,
                    100,
                    Align.center,
                    true);

            // Draw a bullet as iconic
            for (int i = 0, spritesSize = bulletSprites.size(); i < spritesSize; i++) {

                Sprite bulletSprite = bulletSprites.get(i);
                if (i >= currentHandGun.getAmmo()) {
                    bulletSprite.setColor(0, 0, 0, this.world.getGameSetting()
                            .getHudVisibleLevel());
                }
                bulletSprite.draw(this.world.batch);
            }

        }
    }


    @Override
    public void dispose() {

    }

    public World getWorld() {
        return world;
    }
}
