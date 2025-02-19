package it.unibo.artrat.utils.impl;

/**
 * 
 * Vector that determines speed and direction.
 * 
 * @param x
 * @param y
 * 
 * @author Samuele Trapani
 */
public record Vector2d(double x, double y) {

    /**
     * Vector default constructor: V(x=0;y=0).
     *
     */
    public Vector2d() {
        this(0, 0);
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
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Sum of two vector2d.
     * 
     * @param vector2d
     * @return sum result
     */
    public Vector2d summVector2d(final Vector2d vector2d) {
        return new Vector2d(this.x + vector2d.x, this.y + vector2d.y);
    }

    /**
     * Get normalized vector.
     * 
     * @return normalized vector
     */
    public Vector2d normalize() {
        if (this.module() == 0.0) {
            return this;
        }
        return new Vector2d(this.x / this.module(), this.y / this.module());
    }

    @Override
    public String toString() {
        return "X: " + this.x + "\tY: " + this.y;
    }

}
