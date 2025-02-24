package it.unibo.artrat.controller.api.subcontroller;

import java.util.Set;
import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.utils.impl.Point;

/**
 * controller for the effective game.
 */
public interface GameSubController extends SubController {

    /**
     * getter for all the visible wall position.
     * 
     * @return a set of points
     */
    Set<Point> getVisibleWallPositions();

    /**
     * getter for all the visible enemy position.
     * 
     * @return a set of points
     */
    Set<Point> getVisibleEnemyPositions();

    /**
     * method to get the position of the player.
     * 
     * @return the position as a point
     */
    Point getPlayerPos();

    /**
     * get the zoom of the view.
     * 
     * @return the int for the zoom
     */
    int getRenderDistance();

    /**
     * method to get the position of the exit.
     * 
     * @return the position as a point
     */
    Set<Point> getExitPos();

    /**
     * getter for all the visible paintings position.
     * 
     * @return a set of points
     */
    Set<Point> getVisiblePaintings();

    /**
     * Initialize the game resources.
     */
    void init();

    /**
     * return the direction to show at the player the exit.
     * 
     * @return the direction as an angle
     */
    double getAngleCompass();

    int getStolenCollectable();

}
