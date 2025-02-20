package it.unibo.artrat.model.api;

import it.unibo.artrat.model.api.characters.AbstractEntity;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.impl.AbstractGameObject;

/**
 * interface for the game object factory.
 */
public interface GameObjectFactory {

    /**
     * create a new wall game object.
     * 
     * @param x x position
     * @param y y position
     * @return the game object for the wall
     */
    AbstractGameObject getWall(int x, int y);

    /**
     * create a new player game object.
     * 
     * @param x x position
     * @param y y position
     * @return the game object for the player
     */
    Player getPlayer(int x, int y);

    /**
     * create a new enemy game object.
     * 
     * @param x x position
     * @param y y position
     * @return the game object for the enemy
     */
    AbstractEntity getRandomEnemy(int x, int y);

    /**
     * create a new valuable object game object.
     * 
     * @param x x position
     * @param y y position
     * @return the game object for the valuable object
     */
    AbstractGameObject getPicture(int x, int y);
}
