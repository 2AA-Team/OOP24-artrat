package it.unibo.artrat.model.api.characters;

import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Interface that rappresents every "alive" object.
 */
public interface Entity extends GameObject {

    /**
     * Movement handling.
     */
    void move();

    /**
     * Usefull method for objects interaction.
     */
    void interact();

    /**
     * Get method for speed vector.
     * 
     * @return current speed
     */
    Vector2d getSpeed();

    /**
     * Set method for speed vector.
     * 
     * @param v speed vector.
     */
    void setSpeed(Vector2d v);
}
