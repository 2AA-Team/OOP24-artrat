package it.unibo.artrat.model.api;

/**
 * interface to describe collectable item in the map.
 */
public interface Collectable extends GameObject {

    /**
     * getter for the price of the item.
     * 
     * @return the price as a double
     */
    double getPrice();
}
