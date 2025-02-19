package it.unibo.artrat.model.api;

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
     * reloads the object view.
     */
    void redraw();

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

}
