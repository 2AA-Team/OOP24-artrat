package it.unibo.artrat.model.api.world;

import java.io.IOException;
import java.util.Set;
import it.unibo.artrat.model.api.AbstractGameObject;

/**
 * interface for the floor.
 */
public interface Floor {

    /**
     * generate a pseudo-random floor as a room matrix.
     * 
     * @throws IOException if room generation file doesn't exist
     */
    void generateFloorSet() throws IOException;

    /**
     * getter for all valuable item.
     * 
     * @return a set of AbstractGameObject
     */
    Set<AbstractGameObject> getValues();

    /**
     * getter for all walls.
     * 
     * @return a set of AbstractGameObject
     */
    Set<AbstractGameObject> getWalls();

    /**
     * getter for all enemies.
     * 
     * @return a set of AbstractGameObject
     */
    Set<AbstractGameObject> getEnemies();
}
