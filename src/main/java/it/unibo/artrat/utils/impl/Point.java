package it.unibo.artrat.utils.impl;

/**
 * Point is used to define the space where game objects can be.
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

    public Point sum(final Vector2d v) {
        return new Point(this.x + v.getX(), this.y + v.getY());
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

    public double getDistance(final Point p) {
        final double distX = this.x - p.x;
        final double distY = this.y - p.y;
        return Math.sqrt(distX * distX + distY * distY);
    }

}
