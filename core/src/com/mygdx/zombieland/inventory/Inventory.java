package com.mygdx.zombieland.inventory;

import com.mygdx.zombieland.World;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Inventory {

    private final List<InventoryItem> items = new CopyOnWriteArrayList<>();
    private final World world;

    public Inventory(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public void clearItems() {
        this.items.clear();
    }
}
