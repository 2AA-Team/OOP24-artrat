package it.unibo.artrat.model.api.world.floorgeneration;

import java.util.Set;

import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.utils.impl.Point;

/**
 * interface that describes squared room generation class.
 */
public interface RoomGeneration {
    /**
     * return a set of game object that describes one room.
     * 
     * @param size  the side of the room.
     * @param start the absolute position of the up-righter object
     * @return set of gameobject
     */
    Set<GameObject> generateRoom(int size, Point start);
}
