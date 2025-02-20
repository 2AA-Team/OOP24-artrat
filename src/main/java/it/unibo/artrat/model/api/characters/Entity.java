package it.unibo.artrat.model.api.characters;

import java.util.Set;

import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Interface that rappresents every "alive" object.
 * 
 * @author Samuele Trapani
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
    Set<Vector2d> getSpeed();

    /**
     * Set method for speed vector.
     * 
     * @param v speed vector.
     */
    void setSpeed(Vector2d v);

    /**
     * Add direction to current movements.
     * 
     * @param v
     */
    void addDirection(Vector2d v);

    void removeDirection(final Vector2d v);

    /**
     * Directions sum.
     * 
     * @return current speed
     */
    Vector2d calculateSpeed();
}
