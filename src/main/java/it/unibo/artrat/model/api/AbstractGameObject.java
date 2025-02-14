package it.unibo.artrat.model.api;

import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Point;

/**
 * Abstract class that implements default instructions.
 */
public abstract class AbstractGameObject implements GameObject {
    private final BoundingBoxImpl hitBox;

    /**
     * Abstract GameObject constructor without parameters.
     */
    public AbstractGameObject() {
        this.hitBox = new BoundingBoxImpl(new Point(0, 0), new Point(0, 0));
    }

    /**
     * Abstract GameObject constructor.
     * 
     * @param topLeft     top left corner of the game object's bounding box
     * @param bottomRight bottom right corner of the game object's bounding box
     */
    public AbstractGameObject(final Point topLeft, final Point bottomRight) {
        this.hitBox = new BoundingBoxImpl(topLeft, bottomRight);
    }

    /**
     * Abstract GameObject constructor.
     * 
     * @param center center of the game object's bounding box
     * @param width  width of the game object's bounding box
     * @param height height of the game object's bounding box
     */
    public AbstractGameObject(final Point center, final double width, final double height) {
        this.hitBox = new BoundingBoxImpl(center, width, height);
    }

    /**
     * Get current position.
     * 
     * @return current game object position
     */
    @Override
    public Point getPosition() {
        return this.hitBox.getCenter();
    }

    /**
     * Set the current game object position.
     * 
     * @param position current position
     */
    public void setPosition(final Point position) {
        this.hitBox.setCenter(position);
    }

    @Override
    public void update(final int delta) {

    }

}
