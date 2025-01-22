package it.unibo.artrat.utils.impl;

/**
 * Vector that determines speed and direction.
 */
public final class Vector2d {
    /**
     * Default vector position is up.
     */
    private double x;
    private double y;

    public Vector2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d() {
        this.x = 1;
        this.y = 1;
    }

    /**
     * Multiplication of a vector2d.
     * 
     * @param coefficient
     * @return
     */
    public Vector2d mul(final double coefficient) {
        return new Vector2d(this.x * coefficient, this.y * coefficient);
    }

    /**
     * Vector module to avoid diagonal movement speed up.
     * 
     * @return
     */
    public double module() {
        return Math.sqrt(Math.pow(this.x, this.x) + Math.pow(this.y, this.y));
    }

    /**
     * Sum of two vector2d.
     * 
     * @param vector2d
     * @return
     */
    public Vector2d summVector2d(final Vector2d vector2d) {
        return new Vector2d(this.x + vector2d.x, this.y * vector2d.y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
