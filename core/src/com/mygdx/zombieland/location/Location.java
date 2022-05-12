package com.mygdx.zombieland.location;

public class Location {

    public float x;
    public float y;

    public Location(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Location(Location location) {
        this.x = location.x;
        this.y = location.y;
    }

    public Location() {
        this.x = 0F;
        this.y = 0F;
    }

    /**
     * Calculates a distance from one-point to others.
     * Using Euclidean geography distance.
     *
     * @param l a next location to calculate a distance
     * @return a distance as double to resolve
     */
    public double distance(Location l) {
        return Math.sqrt(Math.pow(this.x - l.x, 2) + Math.pow(this.y - l.y, 2));
    }

    /**
     * Checks if the current location is collided to a parameter location by checking a
     * distance from root location to destination location with an offset value. If the subtraction of distance
     * location and offset is lower than zero. It means, the location is collided other location.
     *
     * @param location a next location to make a comparison
     * @param offset   an offset to compare
     * @return true if the subtraction of distance of next location and offset is lower than zero, false otherwise
     */
    public boolean isCollided(Location location, double offset) {
        return distance(location) - offset < 0;
    }

    /**
     * Sets new location.
     *
     * @param x a x coordinate
     * @param y a y coordinate
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets new location by location.
     *
     * @param location a location to set x, y coordinates.
     */
    public void set(Location location) {
        this.x = location.x;
        this.y = location.y;
    }

    public void add(Location location) {
        this.x += location.x;
        this.y += location.y;
    }

    public Location add(double amount) {
        this.x += amount;
        this.y += amount;
        return this;
    }

    public Location add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Location add(Vector2D vector) {
        this.x += vector.x;
        this.y += vector.y;
        return this;
    }

    public Location scalar(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
