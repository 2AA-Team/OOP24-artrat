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
     * method to get the position of the player.
     * 
     * @return the position ad a point
     */
    Point getPlayerPos();

    /**
     * get the zoom of the view.
     * 
     * @return the int for the zoom
     */
    int getZoom();

}
