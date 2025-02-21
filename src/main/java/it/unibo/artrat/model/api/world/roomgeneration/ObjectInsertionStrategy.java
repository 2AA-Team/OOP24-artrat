package it.unibo.artrat.model.api.world.roomgeneration;

import java.util.Set;
import java.util.function.BiFunction;

import it.unibo.artrat.model.api.GameObject;

/**
 * strategy to describe the logic of placing some objects in a room.
 * 
 * @param <O> object type
 */
public interface ObjectInsertionStrategy<O> {

    /**
     * method to insert multiple object inside a room.
     * 
     * @param baseRoom  structure of the room
     * @param roomSize  size of the room
     * @param addNumber number of object to add
     * @param factored  function that create the object to add
     * @return set of object
     */
    Set<O> insertMultipleObject(Set<? extends GameObject> baseRoom, int roomSize, int addNumber,
            BiFunction<Integer, Integer, O> factored);

    /**
     * create a new copy instance of the strategy.
     * 
     * @return new instance of the strategy
     */
    ObjectInsertionStrategy<O> cloneStrategy();
}
