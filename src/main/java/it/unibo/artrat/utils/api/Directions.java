package it.unibo.artrat.utils.api;

import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Allowed directions.
 */
public enum Directions {
    /**
     * Go up.
     */
    UP,
    /**
     * Go down.
     */
    DOWN,
    /**
     * Go right.
     */
    RIGHT,
    /**
     * Go left.
     */
    LEFT;

    /**
     * Get direction from enum.
     * 
     * @param dir direction
     * @return new direction vector
     */
    public static Vector2d getDirection(final Directions dir) {
        return switch (dir) {
            case UP -> new Vector2d(0, -1);
            case DOWN -> new Vector2d(0, 1);
            case LEFT -> new Vector2d(-1, 0);
            case RIGHT -> new Vector2d(1, 0);
        };
    }
}
