package it.unibo.artrat.model.api.world;

import it.unibo.artrat.model.api.GameObject;

/**
 * Class that rappresents an item that entities can interact with.
 */
public interface ValuableItem extends GameObject {
    /**
     * Steal valuable item.
     * 
     * @return true: stealed, false: not stealed
     */
    boolean stealed();

    /**
     * Get the value of the item.
     * 
     * @return value of the item
     */
    double getValue();
}
