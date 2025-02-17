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

}
