package it.unibo.artrat.model.api.world.roomgeneration;

import java.util.Set;
import java.util.function.BiFunction;

import it.unibo.artrat.model.impl.AbstractGameObject;

/**
 * strategy to describe the logic of placing some objects in a room.
 */
public interface ObjectInsertionStrategy<O> {

    /**
     * return a set of new objects.
     * 
     * @param baseRoom  the room in which to place the objects
     * @param roomSize  size of the room
     * @param obj       type of the object to add
     * @param addNumber number of the objects to add
     * @return set of the objects to add
     */
    Set<O> insertMultipleObject(Set<AbstractGameObject> baseRoom, int roomSize, int addNumber,
            BiFunction<Integer, Integer, O> factored);

    /**
     * create a new copy instance of the strategy.
     * 
     * @return new instance of the strategy
     */
    ObjectInsertionStrategy<O> cloneStrategy();
}
