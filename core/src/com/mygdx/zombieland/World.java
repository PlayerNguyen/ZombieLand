package com.mygdx.zombieland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.mygdx.zombieland.effects.TextIndicator;
import com.mygdx.zombieland.entity.*;
import com.mygdx.zombieland.hud.HUD;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;
import com.mygdx.zombieland.runnable.Spawner;
import com.mygdx.zombieland.scheduler.Scheduler;
import com.mygdx.zombieland.state.GameState;
import com.mygdx.zombieland.utils.MathHelper;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class World implements Renderable {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final String BACKGROUND_TEXTURE = "background.png";

    public SpriteBatch batch;
    public BitmapFont font;
    private Player player;
    private final OrthographicCamera camera;
    private final ShapeRenderer shapeRenderer;
    private Texture background;
    private GameState gameState;
    private boolean debug;
    private long lastDebugSet;

    private final Set<Entity> projectiles = new CopyOnWriteArraySet<>();
    private final Set<Entity> entities = new CopyOnWriteArraySet<>();
    private final Set<Spawner> spawners = new CopyOnWriteArraySet<>();
    private final Scheduler scheduler;
    private final TextIndicator textIndicator;
    private final HUD hud;

    public World(SpriteBatch batch) {
        this.batch = batch;
        this.scheduler = new Scheduler();

        this.font = new BitmapFont(Gdx.files.internal("fonts/default.fnt"),
                new TextureRegion(new Texture(Gdx.files.internal("fonts/default.png"))));
        this.textIndicator = new TextIndicator(this);
        this.camera = new OrthographicCamera(800, 600);
        this.shapeRenderer = new ShapeRenderer();
        this.gameState = GameState.PLAYING;
        this.hud = new HUD(this);
        this.debug = true;
    }

    @Override
    public void create() {
        // Load assets and materials
        this.loadBackground();

        // Load player and inject world into player
        this.player = new Player(this);
        this.player.create();

        // Load entities
        for (Entity entity : entities) {
            entity.create();
        }


        // Load projectiles
        for (Entity projectile : this.projectiles) {
            projectile.create();
        }

        // Load spawners
        this.spawners.add(new Spawner(this,
                new Location(-30, 300), 50f, 5000));
        this.spawners.add(new Spawner(this,
                new Location(400, 630), 50f, 5000));
        this.spawners.add(new Spawner(this,
                new Location(830, 300), 50f, 5000));
        this.spawners.add(new Spawner(this,
                new Location(400, -30), 50f, 5000));
        for (Spawner spawner : this.spawners) {
            spawner.create();
        }
    }

    @Override
    public void render() {
        // Update camera
        this.camera.update();

        this.batch.setProjectionMatrix(this.camera.combined);
        this.camera.position.x = (float) 800 / 2;
        this.camera.position.y = (float) 600 / 2;

        // Update background
        this.updateBackground();

        // Debug shortcut
        if (Gdx.input.isKeyPressed(Input.Keys.F3)) {
            if (System.currentTimeMillis() - 300 >= lastDebugSet) {
                this.getTextIndicator().createText(new Location(this.getPlayer().getLocation()).add(-64, 64),
                        new Vector2D(0, 3F),
                        String.format("Debug is %s", (!isDebug() ? "on" : "off")),
                        1000,
                        .003F, (!isDebug() ? Color.GREEN : Color.RED));
                this.setDebug(!this.isDebug());
                lastDebugSet = System.currentTimeMillis();
            }
        }

        // Render HUD
        switch (this.gameState) {
            case STARTING: {
                Gdx.app.log("Game status", "Starting status");
//                font.getData().setScale(0.6f);
//                this.font.draw(this.batch, "Press any key to start", 350, 400, 200, Align.center, true);
                break;
            }
            case PAUSING: {
                break;
            }
            case PLAYING: {

                // Render player
                this.player.render();

                // Render all projectiles
                for (Entity projectile : this.projectiles) {
                    projectile.render();
                }

                // Render all entities
                for (Entity entity : entities) {
                    entity.render();
                }

                // Text indicator render
                this.getTextIndicator().render();

                // Spawner
                for (Spawner spawner : spawners) {
                    spawner.update();
                }

                this.hud.render();
                break;
            }
            case ENDING: {
                break;
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public void dispose() {
        this.player.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Location getCenterLocation(int offset) {
        return new Location(
                ((float) WINDOW_WIDTH / 2) - offset,
                ((float) WINDOW_HEIGHT / 2) - offset
        );
    }

    public Projectile createProjectile(Projectile projectile) {
        this.projectiles.add(projectile);
        projectile.create();
        return projectile;
    }

    public boolean removeProjectile(Projectile projectile) {
        projectile.dispose();
        return this.projectiles.remove(projectile);
    }

    public boolean removeEntity(Entity entity) {
//        entity.dispose();
        return this.entities.remove(entity);
    }

    public Entity createEntity(Entity entity) {
        this.entities.add(entity);
        entity.create();
        return entity;
    }

    public Set<Entity> getProjectiles() {
        return projectiles;
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public Player getPlayer() {
        return player;
    }

    public BitmapFont getFont() {
        return font;
    }

    public TextIndicator getTextIndicator() {
        return textIndicator;
    }

    public void loadBackground() {
        this.background = new Texture(Gdx.files.internal(BACKGROUND_TEXTURE));
    }

    public void updateBackground() {
        this.batch.draw(this.background, 0, 0);
    }

    public void playingRender() {

    }

    public void pausingRender() {

    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
