package it.unibo.artrat.utils.api;

import it.unibo.artrat.utils.impl.Vector2d;

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

    public static Vector2d getDirection(Directions dir) {
        return switch (dir) {
            case UP -> new Vector2d(0, -1);
            case DOWN -> new Vector2d(0, 1);
            case LEFT -> new Vector2d(-1, 0);
            case RIGHT -> new Vector2d(1, 0);
        };
    }
}
