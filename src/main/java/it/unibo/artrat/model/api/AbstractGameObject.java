package it.unibo.artrat.model.api;

import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Point;

/**
 * Abstract class that implements default instructions.
 */
public abstract class AbstractGameObject implements GameObject {
    private BoundingBoxImpl hitBox;

    /**
     * Abstract GameObject constructor without parameters.
     */
    public AbstractGameObject() {
        this.hitBox = new BoundingBoxImpl(new Point(0, 0), 0);
    }

    /**
     * Abstract GameObject constructor.
     * 
     * @param p      center of the game object's bounding box
     * @param radius radius of the game object's bounding box
     */
    public AbstractGameObject(final Point p, final double radius) {
        this.hitBox = new BoundingBoxImpl(p, radius);
    }

    /**
     * @return current game object position
     */
    public Point getPosition() {
        return this.hitBox.getCenter();
    }

    /**
     * Set the current game object position.
     * 
     * @param position
     */
    public void setPosition(final Point position) {
        this.hitBox.setCenter(position);
    }

    @Override
    public void update(final int delta) {

    }

}
