package it.unibo.artrat.model.api;

import it.unibo.artrat.model.impl.characters.characters.Enemy;
import it.unibo.artrat.model.impl.characters.characters.Player;

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
    GameObject getWall(int x, int y);

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
    Enemy getRandomEnemy(int x, int y);

    /**
     * create a new collectable object game object.
     * 
     * @param x x position
     * @param y y position
     * @return the game object for the collectable object
     */
    Collectable getCollectable(int x, int y);

    /**
     * create a new exit.
     * 
     * @param x x position
     * @param y y position
     * @return the game object for the exit
     */
    GameObject getExit(int x, int y);
}
