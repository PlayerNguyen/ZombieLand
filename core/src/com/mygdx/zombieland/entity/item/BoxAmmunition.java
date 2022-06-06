package com.mygdx.zombieland.entity.item;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.zombieland.World;
import com.mygdx.zombieland.entity.projectile.PistolProjectile;
import com.mygdx.zombieland.inventory.InventoryGun;
import com.mygdx.zombieland.location.Location;
import com.mygdx.zombieland.location.Vector2D;
import com.mygdx.zombieland.utils.MathHelper;

public class BoxAmmunition extends Box {

    private static final int ROUND_RANDOMIZE_MAX_RANGE = 6;
    private static final int SPRITE_SIZE = 20;
    private final Sprite ammoSprite;

    public BoxAmmunition(Location location, World world) {
        super(location, world);
        this.ammoSprite = new Sprite(PistolProjectile.PISTOL_TEXTURE);
    }

    @Override
    public void create() {
        super.create();
        // Set the size
        this.ammoSprite.setSize(SPRITE_SIZE, SPRITE_SIZE);
    }

    @Override
    public void render() {
        super.render();
        // Render a bullet image
        this.ammoSprite.setPosition(this.getLocation().x - ((float) SPRITE_SIZE / 2),
                this.getLocation().y - ((float) SPRITE_SIZE / 2));
        this.ammoSprite.draw(this.getWorld().getBatch());
    }

    @Override
    public void kill() {
        super.kill();

        int round = MathHelper.nextInt(1, ROUND_RANDOMIZE_MAX_RANGE);
        int receiveTotalAmmo = round * 12;

        InventoryGun currentHandItem = (InventoryGun) this.getWorld().getPlayer().getCurrentHandItem();
        currentHandItem.setTotalAmmo(currentHandItem.getTotalAmmo() + receiveTotalAmmo);

        this.getWorld().getTextIndicator().createText(
                new Location(this.getWorld().getPlayer().getLocation()).add(0, 12),
                new Vector2D(0, 12),
                String.format("+ %d", receiveTotalAmmo / 12),
                2000,
                .3F,
                Color.GREEN
        );
    }
}
