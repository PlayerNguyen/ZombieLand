package com.mygdx.zombieland.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.zombieland.Renderable;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TextIndicator implements Effect, Renderable {

    public World world;
    public List<TextItem> items = new CopyOnWriteArrayList<>();

    public static class TextItem {
        private final Location location;
        private final Location destination;
        private final long created;
        private final long duration;
        private final String text;
        private float fraction = 0;
        private float speed = 3F;
        private final Color color;

        public TextItem(Location location, Vector2D offset, long duration, String text, float speed, Color color) {
            this.location = location;
            this.destination = new Location(this.location).add(offset);
            this.duration = duration;
            this.created = System.currentTimeMillis();
            this.text = text;
            this.speed = speed;
            this.color = color;
        }

        public Location getLocation() {
            return location;
        }

        public long getDuration() {
            return duration;
        }

        public String getText() {
            return text;
        }

        public long getCreated() {
            return created;
        }

        public float getSpeed() {
            return speed;
        }

        public Color getColor() {
            return color;
        }
    }

    public List<TextItem> getItems() {
        return items;
    }

    public void createText(Location location, Vector2D offset, String text, long duration, float speed, Color color) {
        this.items.add(new TextItem(location, offset, duration, text, speed, color));
    }
    public void createText(Location location, Vector2D offset, String text, long duration, float speed) {
        this.items.add(new TextItem(location, offset, duration, text, speed, Color.RED));
    }

    public TextIndicator(World world) {
        this.world = world;
    }

    @Override
    public void create() {

    }

    @Override
    public void render() {
        for (TextItem textItem : items) {
            if (System.currentTimeMillis() < (textItem.created + textItem.duration)) {
                if (textItem.fraction < 1) {
                    textItem.fraction += Gdx.graphics.getDeltaTime() * textItem.speed;
                    textItem.location.x += (textItem.destination.x - textItem.location.x) * textItem.fraction;
                    textItem.location.y += (textItem.destination.y - textItem.location.y) * textItem.fraction;
                }
                this.world.getFont().setColor((textItem.color == null)
                        ? new Color(1, .1f,0,.6f)
                        : textItem.color);

                this.world.getFont().draw(this.world.getBatch(),
                        textItem.text,
                        textItem.location.x,
                        textItem.location.y
                );
            } else {
                items.remove(textItem);
            }
        }
    }

    @Override
    public void dispose() {
        this.world.getFont().dispose();
    }


}
