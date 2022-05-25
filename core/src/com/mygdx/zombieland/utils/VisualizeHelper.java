package com.mygdx.zombieland.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.Entity;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;

public class VisualizeHelper {

    public static void simulateLine(World world, Location location, Location location2, Color color) {
        world.getBatch().end();

        world.getShapeRenderer().begin(ShapeRenderer.ShapeType.Line);
        world.getShapeRenderer().setColor(color);

        world.getShapeRenderer().line(location.x, location.y, location2.x, location2.y);
        world.getShapeRenderer().end();

        world.getBatch().begin();
    }

    public static void simulateLine(World world, Location location, Location location2) {
        simulateLine(world, location, location2, Color.BLACK);
    }

    public static void simulateDirection(World world, Entity entity, Color color) {
        Location offsetLocation = new Location(
                entity.getLocation()
        ).add(new Vector2D(entity.getDirection()).scalar(100));
        simulateLine(world, entity.getLocation(), offsetLocation, color);
    }

    public static void simulateDirection(World world, Entity entity) {
        simulateDirection(world, entity, Color.RED);
    }

    public static void simulateBox(World world, Location originLocation, float size, Color color) {
        world.getBatch().end();

        world.getShapeRenderer().begin(ShapeRenderer.ShapeType.Line);
        world.getShapeRenderer().setColor(color);

        world.getShapeRenderer().rect(originLocation.x, originLocation.y, size, size);
        world.getShapeRenderer().end();

        world.getBatch().begin();
    }

    public static void simulateBox(World world, Location originLocation, float size) {
        world.getBatch().end();

        world.getShapeRenderer().begin(ShapeRenderer.ShapeType.Line);
        world.getShapeRenderer().setColor(Color.BLACK);

        world.getShapeRenderer().rect(originLocation.x, originLocation.y, size, size);
        world.getShapeRenderer().end();

        world.getBatch().begin();
    }

    public static void simulateBox(World world, Entity entity) {
        world.getBatch().end();
        // Draw center location
        world.getShapeRenderer().begin(ShapeRenderer.ShapeType.Line);
        world.getShapeRenderer().setColor(Color.BLACK);

        world.getShapeRenderer().rect(entity.getCenterLocation().x,
                entity.getCenterLocation().y,
                entity.getSize(),
                entity.getSize());

        // Draw a red (location) location
        world.getShapeRenderer().setColor(Color.RED);

        world.getShapeRenderer().rect(entity.getLocation().x,
                entity.getLocation().y,
                entity.getSize(),
                entity.getSize());
        world.getShapeRenderer().end();

        world.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
        world.getShapeRenderer().circle(entity.getLocation().x,
                entity.getLocation().y,
                8);
        world.getShapeRenderer().end();

        world.getBatch().begin();
    }

    public static void simulateCircle (World world, Location location, float radius) {
        world.getBatch().end();
        world.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
        world.getShapeRenderer().circle(location.x,
                location.y,
                radius);
        world.getShapeRenderer().end();

        world.getBatch().begin();
    }
}
