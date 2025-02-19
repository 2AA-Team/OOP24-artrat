package it.unibo.artrat.utils.api;

import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Allowed directions.
 * 
 * @author Samuele Trapani
 */
public enum Directions {
    /**
     * Go up.
     */
    UP(new Vector2d(0, -1)),
    /**
     * Go down.
     */
    DOWN(new Vector2d(0, 1)),
    /**
     * Go right.
     */
    RIGHT(new Vector2d(1, 0)),
    /**
     * Go left.
     */
    LEFT(new Vector2d(-1, 0));

    private final Vector2d vector;

    /**
     * Directions constructor.
     * 
     * @param vector
     */
    Directions(final Vector2d vector) {
        this.vector = vector;
    }

    /**
     * Vector of the current direction.
     * 
     * @return vector's direction
     */
    public Vector2d vector() {
        return new Vector2d(this.vector.x(), this.vector.y());
    }

}
