package it.unibo.artrat.utils.impl;

/**
 * Vector that determines speed and direction.
 * 
 * @author Samuele Trapani
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
     * Vector default constructor: V(x=0;y=0).
     *
     */
    public Vector2d() {
        this.x = 0;
        this.y = 0;
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
        if (this.module() == 0.0) {
            return this;
        }
        return new Vector2d(this.getX() / this.module(), this.getY() / this.module());
    }

    @Override
    public String toString() {
        return "X: " + this.x + "\tY: " + this.y;
    }

    @Override
    public int hashCode() {
        // auto generated hascode method.
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
        // auto generated equals method.
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        } else {
            final Vector2d other = (Vector2d) obj;
            if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
                return false;
            } else {
                return (Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y));
            }
        }

    }

}
