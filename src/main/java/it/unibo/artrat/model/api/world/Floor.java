package it.unibo.artrat.model.api.world;

import java.io.IOException;
import java.util.Set;

import it.unibo.artrat.model.api.Collectable;
import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.utils.impl.Point;

/**
 * interface for the floor.
 * 
 * @author Matteo Tonelli
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
    Set<Collectable> getCollectables();

    /**
     * getter for all walls.
     * 
     * @return a set of AbstractGameObject
     */
    Set<GameObject> getWalls();

    /**
     * getter for all enemies.
     * 
     * @return a set of AbstractGameObject
     */
    Set<Enemy> getEnemies();

    /**
     * get the player startPosition.
     * 
     * @return a point representing the position
     */
    Point getStartPosition();

    /**
     * get the floor exit position.
     * 
     * @return an abstract game object representing the position
     */
    Set<GameObject> getExit();

    /**
     * get the max floor size.
     * 
     * @return a double representing the max floor size.
     */
    double getMaxFloorSize();

    /**
     * get the minj floor size.
     * 
     * @return a double representing the min floor size.
     */
    double getMinFloorSize();

    /**
     * get the max room size.
     * 
     * @return a double representing the max room size.
     */
    double getMaxRoomSize();

    /**
     * get the min room size.
     * 
     * @return a double representing the min room size.
     */
    double getMinRoomSize();

    /**
     * Return a copy of the called floor.
     * 
     * @return a copy of the called floor.
     */
    Floor copyFloor();

    /**
     * setter for all enemies.
     * 
     * @param enemies set of enemies
     */
    void setEnemies(Set<Enemy> enemies);

    /**
     * setter for all collectables.
     * 
     * @param passedCollectables set of collectables
     */
    void setCollectables(Set<Collectable> passedCollectables);
}
