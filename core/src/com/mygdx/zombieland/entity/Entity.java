package com.mygdx.zombieland.entity;

import com.mygdx.zombieland.location.Location;

public interface Entity {

    /**
     * Represents a current location of the entity.
     *
     * @return a current location of the entity.
     */
    Location getLocation();

}
