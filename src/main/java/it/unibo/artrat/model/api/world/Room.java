package it.unibo.artrat.model.api.world;

import java.util.Set;

import it.unibo.artrat.model.api.characters.AbstractEntity;
import it.unibo.artrat.model.impl.AbstractGameObject;

/**
 * interface that describes the room.
 */
public interface Room {

    /**
     * getter for the walls structure of the room.
     * 
     * @return set of gameobject
     */
    Set<AbstractGameObject> getStructure();

    /**
     * getter for all enemies of the room.
     * 
     * @return set of gameobject
     */
    Set<AbstractEntity> getEnemies();

    /**
     * getter for all valuable objects of the room.
     * 
     * @return set of gameobject
     */
    Set<AbstractGameObject> getValues();
}
