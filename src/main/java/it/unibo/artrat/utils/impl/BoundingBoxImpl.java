package it.unibo.artrat.utils.impl;

import it.unibo.artrat.utils.api.BoundingBox;

/**
 * Class of bounding box as a circle.
 */
public class BoundingBoxImpl implements BoundingBox {
    private final Point center = new Point();
    private double radius;

    /**
     * Bounding box constructor.
     * 
     * @param p      box center
     * @param radius circle radius
     */
    public BoundingBoxImpl(final Point p, final double radius) {
        this.center.setX(p.getX());
        this.center.setY(p.getY());
        this.radius = radius;
    }

    /**
     * @return bounding box center
     */
    public Point getCenter() {
        return new Point(this.center.getX(), this.center.getY());
    }

    /**
     * Set box center.
     * 
     * @param center
     */
    public void setCenter(final Point center) {
        this.center.setX(center.getX());
        this.center.setY(center.getY());
    }

    /**
     * @return bounding box radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Set box radius.
     * 
     * @param radius
     */
    public void setRadius(final double radius) {
        this.radius = radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isColliding(final BoundingBoxImpl box) {
        return Math.floor(this.center.getDistance(box.getCenter())) <= box.getRadius() + this.getRadius();
    }

}
