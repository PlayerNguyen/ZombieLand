package com.mygdx.zombieland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.zombieland.effects.TextIndicator;
import com.mygdx.zombieland.entity.*;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;
import com.mygdx.zombieland.runnable.Spawner;
import com.mygdx.zombieland.scheduler.Scheduler;
import com.mygdx.zombieland.utils.MathHelper;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class World implements Renderable {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public SpriteBatch batch;
    public BitmapFont font;
    private Player player;
    private final OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private final Set<Entity> projectiles = new CopyOnWriteArraySet<>();
    private final Set<Entity> entities = new CopyOnWriteArraySet<>();
    private final Scheduler scheduler;
    private final TextIndicator textIndicator;

    public World(SpriteBatch batch) {
        this.batch = batch;
        this.scheduler = new Scheduler();

        this.font = new BitmapFont();
        this.textIndicator = new TextIndicator(this);
        this.camera = new OrthographicCamera(800, 600);
        this.shapeRenderer = new ShapeRenderer();
//        this.spawner = new Spawner(this);
    }

    @Override
    public void create() {
        // Load assets and materials

        // Load player and inject world into player
        this.player = new Player(this);
        this.player.create();

        // Load entities
        for (Entity entity : entities) {
            entity.create();
        }
//
//        for (int i = 0; i < 20; i++) {
//            createEntity(new Box(new Location((float) MathHelper.nextDouble(10, 800), (float) MathHelper.nextDouble(10, 600)),
//                    this));
//        }
//
//        for (int i = 0; i < 100; i++) {
//            createEntity(new Zombie(this, new Location((float) MathHelper.nextDouble(10, 800), (float) MathHelper.nextDouble(10, 600)), this.player));
//        }

        // Load projectiles
        for (Entity projectile : this.projectiles) {
            projectile.create();
        }

        // Load spawners

    }

    @Override
    public void render() {
        // Update camera
        this.camera.update();

        this.batch.setProjectionMatrix(this.camera.combined);
        this.camera.position.x = (float) 800 / 2;
        this.camera.position.y = (float) 600 / 2;

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


//        shapeRenderer.setProjectionMatrix(this.camera.combined);
//
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(0, 0, 0, 1);
//        for (int i = 0; i <800; i+=32) {
//            for (int j = 0; j < 600; j+=32) {
//                shapeRenderer.rect(i, j, 32, 32);
//            }
//        }
//        shapeRenderer.end();
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
}
