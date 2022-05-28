package com.mygdx.zombieland.entity;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;

public abstract class DamageableAbstract
        implements Damageable, Entity {

    @Override
    public void damage(DamageSource source, float amount) {
        this.setHealth(this.getHealth() - amount);

        // Knock back
        Vector2D displacement = new Vector2D(source.getDirection())
                .scalar(source.getKnockbackPower()); // Scalar is power
        Location destination = new Location(
                this.getLocation().x,
                this.getLocation().y
        ).add(displacement);

        this.lerp(destination, 12f);
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

        Location indicatorTextLocation = new Location(this.getCenterLocation())
                .add((float) this.getSize() / 2F, this.getSize());
        this.getWorld().getTextIndicator().createText(indicatorTextLocation,
                new Vector2D(0, 16F),
                String.format("%.0f", amount),
                1000,
                .5F
        );
    }

    @Override
    public void kill() {
        this.getWorld().removeEntity(this);
    }

    @Override
    public Location getCenterLocation() {
        return new Location(this.getLocation().x - ((float) this.getSize() / 2)
                , this.getLocation().y - ((float) this.getSize() / 2));
    }
}
