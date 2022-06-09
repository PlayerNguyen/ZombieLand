package com.mygdx.zombieland.entity;

import com.mygdx.zombieland.inventory.InventoryItem;

public interface InventoryHolder {

    InventoryItem getCurrentHandItem();

    void setCurrentHandItem(InventoryItem item);

}
