package it.unibo.artrat.model.impl;

import it.unibo.artrat.utils.impl.Point;

/**
 * abstract compass.
 */
public abstract class AbstractCompass {

    /**
     * take the point to aim.
     * 
     * @return the point
     */
    abstract Point getNorth();

    /**
     * take the point from which to aim.
     * 
     * @return the point
     */
    abstract Point getCenter();

    /**
     * calculate the angle of the compass.
     * 
     * @return the angle as a double
     */
    public double calculateAngle() {
        return Math.atan2(getNorth().getY() - getCenter().getY(), getNorth().getX() - getCenter().getX());
    }
}
