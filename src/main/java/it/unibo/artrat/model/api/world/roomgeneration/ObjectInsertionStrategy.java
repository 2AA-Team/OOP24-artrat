package it.unibo.artrat.model.api.world.roomgeneration;

import java.util.Set;
import it.unibo.artrat.model.impl.AbstractGameObject;
import it.unibo.artrat.model.impl.world.RoomSymbols;

/**
 * strategy to describe the logic of placing some objects in a room.
 */
public interface ObjectInsertionStrategy {

    /**
     * return a set of new objects.
     * 
     * @param baseRoom  the room in which to place the objects
     * @param roomSize  size of the room
     * @param obj       type of the object to add
     * @param addNumber number of the objects to add
     * @return set of the objects to add
     */
    Set<AbstractGameObject> insertMultipleObject(Set<AbstractGameObject> baseRoom, int roomSize, RoomSymbols obj,
            int addNumber);

    /**
     * create a new copy instance of the strategy.
     * 
     * @return new instance of the strategy
     */
    ObjectInsertionStrategy cloneStrategy();
}
