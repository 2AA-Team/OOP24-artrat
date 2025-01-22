package it.unibo.artrat.utils.impl;

import it.unibo.artrat.utils.api.BoundingBox;

/**
 * Class of bounding box as a circle.
 */
public class BoundingBoxImpl implements BoundingBox {
    private Point center;
    private double radius;

    /**
     * @param p      box center
     * @param radius circle radius
     */
    public BoundingBoxImpl(final Point p, final double radius) {
        this.center = p;
        this.radius = radius;
    }

    /**
     * @return bounding box center
     */
    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * @return bounding box radius
     */
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Check circles collision.
     * 
     * @param box box to check collision with
     */
    @Override
    public boolean isColliding(BoundingBoxImpl box) {
        return Math.floor(this.center.getDistance(box.getCenter())) <= box.getRadius() + this.getRadius();
    }

}
