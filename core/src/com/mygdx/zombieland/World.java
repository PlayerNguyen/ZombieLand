package com.mygdx.zombieland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.zombieland.entity.Box;
import com.mygdx.zombieland.entity.Entity;
import com.mygdx.zombieland.entity.Player;
import com.mygdx.zombieland.entity.Projectile;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;
import com.mygdx.zombieland.scheduler.Scheduler;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class World implements Renderable {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public SpriteBatch batch;
    private Player player;
    private final Set<Entity> projectiles = new CopyOnWriteArraySet<>();
    private final Set<Entity> entities = new CopyOnWriteArraySet<>();
    private final Scheduler scheduler;

    public World(SpriteBatch batch) {
        this.batch = batch;
        this.scheduler = new Scheduler();
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

        createEntity(new Box(new Location(0, 0), this));

        // Load projectiles
        for (Entity projectile : this.projectiles) {
            projectile.create();
        }
    }

    @Override
    public void render() {

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
}
