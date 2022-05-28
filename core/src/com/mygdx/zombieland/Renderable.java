package com.mygdx.zombieland;

public interface Renderable {

    /**
     * Method to define how the object is created.
     */
    void create();

    /**
     * Method to define how the object is rendered.
     */
    void render();

    /**
     * Method to dispose the object.
     */
    void dispose();

}
