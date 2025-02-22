package it.unibo.artrat.model.api.world;

import java.util.Set;

import it.unibo.artrat.model.api.Collectable;
import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.characters.Enemy;

/**
 * interface that describes the room.
 */
public interface Room {

    /**
     * getter for the walls structure of the room.
     * 
     * @return set of gameobject
     */
    Set<GameObject> getStructure();

    /**
     * getter for all enemies of the room.
     * 
     * @return set of gameobject
     */
    Set<Enemy> getEnemies();

    /**
     * getter for all valuable objects of the room.
     * 
     * @return set of gameobject
     */
    Set<Collectable> getValues();

}
