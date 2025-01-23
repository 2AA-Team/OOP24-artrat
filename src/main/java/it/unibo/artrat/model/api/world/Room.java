package it.unibo.artrat.model.api.world;

import java.util.Set;

import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.utils.impl.Pair;
import it.unibo.artrat.utils.impl.Point;

/**
 * Class that rappresents a room.
 */
public interface Room {

    /**
     * Add objects in the room.
     * 
     * @param pos
     * @param tile
     * @throws IllegalStateException if you try to add the same type of object in
     *                               the same spot
     */
    void addObject(Point pos, GameObject tile);

    /**
     * method to return the room objects.
     * 
     * @return all the object (without repetion)
     */
    Set<Pair<GameObject, Point>> getObjects();
}
