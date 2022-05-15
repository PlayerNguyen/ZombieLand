package com.mygdx.zombieland.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;

public class Box extends ItemAbstract {

    private static final Texture BOX_TEXTURE = new Texture(Gdx.files.internal("box.png"));
    public static final int BOX_SIZE = 32;

    private BitmapFont font = new BitmapFont();
    private Location destination;
    private float fraction = 1;
    private float velocity;

    public Box(Location location, Vector2D direction, World world) {
        super(location, direction, world, BOX_TEXTURE, new Sprite(BOX_TEXTURE), 10);
        this.destination = location;
    }

    public Box(Location location, World world) {
        super(location, new Vector2D(0, 0), world, BOX_TEXTURE, new Sprite(BOX_TEXTURE), 10);
        this.destination = location;
    }

    @Override
    public void create() {
        this.getSprite().setSize(BOX_SIZE, BOX_SIZE);
        this.getSprite().setOrigin(BOX_SIZE / 2, BOX_SIZE / 2);
    }

    @Override
    public void render() {
        super.render();

        this.font.setColor(Color.GRAY);
        this.font.draw(this.getWorld().getBatch(),
                String.format("%.0f %.0f", this.getLocation().x, this.getLocation().y),
                this.getLocation().x + 16,
                this.getLocation().y + 16);

        if (fraction < 1) {
            fraction += Gdx.graphics.getDeltaTime() * velocity;
            this.getLocation().x += (this.destination.x - this.getLocation().x) * fraction;
            this.getLocation().y += (this.destination.y - this.getLocation().y) * fraction;
        }

        this.getLocation().add(
                this.getDirection().x,
                this.getDirection().y
        );

        this.getSprite().setPosition(this.getLocation().x - 16, this.getLocation().y - 16);
//        this.getSprite().draw(this.getWorld().getBatch());

    }

    @Override
    public void dispose() {
        this.getTexture().dispose();
    }

    public Location lerp(Location b, float t) {
        this.destination = new Location(b.x, b.y);
        this.fraction = 0;
        this.velocity = t;
        return this.destination;
    }

    @Override
    public int getSize() {
        return BOX_SIZE;
    }

}
