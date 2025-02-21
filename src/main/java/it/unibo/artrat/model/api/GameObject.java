package it.unibo.artrat.model.api;

import it.unibo.artrat.utils.api.BoundingBox;
import it.unibo.artrat.utils.impl.Point;

/**
 * GameObject represents the base class for any physical element in the game.
 */
public interface GameObject {

    /**
     * updates the object data.
     * 
     * @param delta delta time
     */
    void update(long delta);

    /**
     * Get current position.
     * 
     * @return current position
     */
    Point getPosition();

    /**
     * Set Object Position.
     * 
     * @param p new position
     */
    void setPosition(Point p);

    /**
     * Collision check with other bounding box.
     * 
     * @param box
     * @return true if colliding, false otherwise
     */
    BoundingBox getBoundingBox();

    /**
     * clone method.
     * 
     * @return a clone
     */
    GameObject clone();

}
