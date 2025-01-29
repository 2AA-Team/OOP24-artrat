package it.unibo.artrat.model.api.world;

import java.util.Set;

import it.unibo.artrat.model.api.GameObject;

/**
 * Class that rappresents a room.
 */
public interface Room {

    /**
     * method to return the room obstacles.
     * 
     * @return all the object
     */
    Set<GameObject> getObstacles();

    /**
     * method to return the room enemies.
     * 
     * @return all the enemies
     */
    Set<GameObject> getEnemies();

    /**
     * method to return the room valuable item.
     * 
     * @return all the valuable item
     */
    Set<GameObject> getValuableItem();
}
