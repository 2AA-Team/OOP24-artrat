package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.Compass;
import it.unibo.artrat.utils.impl.Point;

/**
 * abstract compass.
 * 
 * @author Matteo Tonelli
 */
public abstract class AbstractCompass implements Compass {

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
     * {@inheritDoc}
     */
    @Override
    public double calculateAngle() {
        return Math.atan2(getNorth().getY() - getCenter().getY(), getNorth().getX() - getCenter().getX());
    }
}
