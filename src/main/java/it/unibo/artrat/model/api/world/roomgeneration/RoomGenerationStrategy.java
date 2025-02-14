package it.unibo.artrat.model.api.world.roomgeneration;

import java.util.Set;
import it.unibo.artrat.model.api.AbstractGameObject;

/**
 * strategy for a single room generation.
 */
public interface RoomGenerationStrategy {

    /**
     * generate the room.
     * 
     * @param size size of the room side
     * @return the set of game object representing the room
     */
    Set<AbstractGameObject> generateRoomSet(int size);
}
