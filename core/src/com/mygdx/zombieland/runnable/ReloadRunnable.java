package com.mygdx.zombieland.runnable;

import com.mygdx.zombieland.World;
import com.mygdx.zombieland.inventory.InventoryGun;
import com.mygdx.zombieland.inventory.InventoryItem;
import com.mygdx.zombieland.weapon.Gun;
import com.mygdx.zombieland.weapon.Weapon;


public class ReloadRunnable implements Runnable {

    private final World world;

    public ReloadRunnable(World world) {
        this.world = world;
    }

    @Override
    public void run() {
        InventoryItem currentHandItem = world.getPlayer().getCurrentHandItem();
        Weapon weapon = currentHandItem.getWeapon();

        this.world.getPlayer().setCanShoot(false);
        this.world.getScheduler().runTaskAfter(filledReload(), ((Gun)weapon).getReloadDuration());
    }

    private Runnable filledReload() {
        return new Runnable() {
            @Override
            public void run() {
                InventoryItem currentHandItem = world.getPlayer().getCurrentHandItem();
                Weapon weapon = currentHandItem.getWeapon();
                if (weapon instanceof Gun) {
                    // Set amount of inventory gun
                    ((InventoryGun) currentHandItem).setAmmo(((Gun) weapon).getMaxAmmo());
                    ((InventoryGun) currentHandItem).setTotalAmmo(((InventoryGun) currentHandItem).getTotalAmmo()
                            - ((Gun) weapon).getMaxAmmo());

                    // Play sound
                    ((Gun) weapon).getReloadReleaseSound()
                            .play(world.getGameSetting().getVfxSoundLevel(), 1f, 0);

                    // Unlock trigger
                    world.getPlayer().setCanShoot(true);
                    world.getPlayer().setReloading(false);
                }
            }
        };
    }
}
