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

    /**
     * Vector constructor.
     * 
     * @param x x axis
     * @param y y axis
     */
    public Vector2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Vector default constructor: V(x=0;y=-1).
     *
     */
    public Vector2d() {
        this.x = 0;
        this.y = -1;
    }

    /**
     * Multiplication of a vector2d.
     * 
     * @param coefficient
     * @return vector multiplied for @coefficient.
     */
    public Vector2d mul(final double coefficient) {
        return new Vector2d(this.x * coefficient, this.y * coefficient);
    }

    /**
     * Vector module to avoid diagonal movement speed up.
     * 
     * @return vector module value.
     */
    public double module() {
        return Math.sqrt(Math.pow(this.x, this.x) + Math.pow(this.y, this.y));
    }

    /**
     * Sum of two vector2d.
     * 
     * @param vector2d
     * @return sum result
     */
    public Vector2d summVector2d(final Vector2d vector2d) {
        return new Vector2d(this.x + vector2d.x, this.y * vector2d.y);
    }

    /**
     * 
     * @return x axis
     */
    public double getX() {
        return x;
    }

    /**
     * Set x axis.
     * 
     * @param x
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * 
     * @return y axis.
     */
    public double getY() {
        return y;
    }

    /**
     * Set y axis.
     * 
     * @param y
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Get normalized vector.
     * 
     * @return normalized vector
     */
    public Vector2d normalize() {
        return new Vector2d(this.getX() / this.module(), this.getY() / this.module());
    }
}
