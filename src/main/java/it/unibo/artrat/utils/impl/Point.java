package it.unibo.artrat.utils.impl;

import java.util.Objects;

/**
 * Point is used to define the space where game objects can be.
 * 
 * @author Samuele Trapani
 */
public final class Point {
    private double x;
    private double y;

    /**
     * Point constructor.
     * 
     * @param x axis
     * @param y axis
     */
    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Point constructor.
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Point constructor starting from a given point.
     * 
     * @param p point to be copied
     */
    public Point(final Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * Sum of point with a vector (Point Movement).
     * 
     * @param v direction vector.
     * @return return new position.
     */
    public Point sum(final Vector2d v) {
        return new Point(this.x + v.x(), this.y + v.y());
    }

    /**
     * Get point x axis.
     * 
     * @return x axis value
     */
    public double getX() {
        return x;
    }

    /**
     * Sets point x axis point.
     * 
     * @param x x axis to set
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Get point y axis.
     * 
     * @return y axis value
     */
    public double getY() {
        return y;
    }

    /**
     * Sets point y axis point.
     * 
     * @param y y axis to set
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * String rappresentation of a point.
     * 
     * @return ( X: x_axis; Y: y_axis )
     */
    @Override
    public String toString() {
        return "( X: " + this.x + "; Y: " + this.y + " )";
    }

    /**
     * Get distance from other point.
     * 
     * @param p point used to compute the distance
     * @return distance
     */
    public double getDistance(final Point p) {
        final double distX = this.x - p.x;
        final double distY = this.y - p.y;
        return Math.sqrt(distX * distX + distY * distY);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (Objects.isNull(obj)) {
            return false;
        }
        assert obj.getClass() == Point.class;
        final Point p = (Point) Objects.requireNonNull(obj);
        return Double.valueOf(this.x).equals(p.x) && Double.valueOf(this.y).equals(p.y);
    }

}
